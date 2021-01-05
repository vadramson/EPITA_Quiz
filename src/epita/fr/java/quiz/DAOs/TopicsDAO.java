package epita.fr.java.quiz.DAOs;

import epita.fr.java.quiz.connection.DatabaseConnection;
import epita.fr.java.quiz.menu.Menu;
import epita.fr.java.quiz.models.Topics;

import java.sql.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import static jdk.nashorn.internal.objects.NativeString.toLowerCase;

public class TopicsDAO {

    Connection connection = DatabaseConnection.getConnection();
    Scanner scanner = new Scanner(System.in);
    QuestionsDAO questionsDAO = new QuestionsDAO();

    public void addTopics(){
        System.out.println("Enter a new Topic ");
        String topic = scanner.nextLine();
        String getTopicQuery = "SELECT * FROM Topics WHERE topic=?";
        String addTopicQuery = "INSERT INTO Topics (topic) VALUES (?)";

        try {
            PreparedStatement preparedSelectStatement = connection.prepareStatement(getTopicQuery);
            preparedSelectStatement.setString(1, topic);
            ResultSet checkTopic = preparedSelectStatement.executeQuery();
            if (checkTopic.next()) {
                System.out.println("Topic Already exists");
                connection.close();
            }
            else if(!checkTopic.next()){
                try {
                    PreparedStatement preparedStatement = connection.prepareStatement(addTopicQuery, Statement.RETURN_GENERATED_KEYS);
                    preparedStatement.setString(1, topic);
                    preparedStatement.execute();
                    ResultSet resultSet = preparedStatement.getGeneratedKeys();
                    int generatedKey = 0;
                    if (resultSet.next()) {
                        generatedKey = resultSet.getInt(1);
                    }
                    if (generatedKey > 0) {
                        System.out.println("The topic was added Successfully");
                        System.out.println("");
                        System.out.println("Do you wish to add another Topic? \n Enter Y or N");
                        String choices = scanner.next();
                        String choice = toLowerCase(choices);

                        if (choice.equals("y")){
                            addTopics();
                        }else if (choice.equals("n") | !choice.equals("y")){
                            System.out.println("Exiting...");
                        }
                    }
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public List<Topics> displayTopics() {
        List<Topics> topics = new ArrayList<Topics>();

        try {
//            String getAllTopicsQuery = "SELECT * FROM topics WHERE status = 1";
            String getAllTopicsQuery = "SELECT * FROM topics ORDER BY idtopics ASC ";
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery(getAllTopicsQuery);
            while (results.next()) {
                Topics topic = new Topics();
                int topicId = results.getInt(1);
                int status = results.getInt(1);
                String allTopics = results.getString(2);
                String stat = "";
                if(status == 1){
                    stat = "Active";
                }
                else{
                    stat = "Deactivated";
                }
                topic.setIdTopics(topicId);
                topic.setTopic(allTopics);
                topics.add(topic);
                System.out.println("Topic ID: " + topicId +"  - " + allTopics+ "  *NB* Status -> "+ stat);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return topics;
    }

    public void searchTopics(){
        System.out.println("Enter a Topic to search ");
        String topicSearch = scanner.nextLine();
        try {
            String getAllTopicSearchQuery = "SELECT * FROM topics WHERE topic LIKE ? AND status = 1";
            PreparedStatement preparedStatementSearchTopic = connection.prepareStatement(getAllTopicSearchQuery);
            preparedStatementSearchTopic.setString(1, topicSearch);
            ResultSet resultsTopics = preparedStatementSearchTopic.executeQuery();
            if (resultsTopics.next()) {
                System.out.println("Please wait \nSearching... ");
                int number = 1;
                System.out.println("");
                System.out.println("");
                do {
                    String topic = resultsTopics.getString(2);
                    System.out.println(number + "). Topic: " + topic);
                    number += 1;
                }
                while (resultsTopics.next());
                System.out.println("");
                System.out.println("");
                number-=1;
                System.out.println("Your Searched return "+ number +" result(s)");
            }else{
                System.out.println("Your Searched return 0 result");
                System.out.println("");
                System.out.println("");
                System.out.println("Searched Input has Topics associated with it");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void updateTopic(){
        System.out.println("Select the topic you want to edit");
        System.out.println("");
        System.out.println("Enter ");
        System.out.println("");
        questionsDAO.displayTopics();

        int counts = 0;
        while (true) {
            try {
                int idTopic = scanner.nextInt();
                if(questionsDAO.checkTopic(idTopic)){
                    questionsDAO.getAndUpdateTopic(idTopic);
                    break;
                }else{
                    System.out.println("Topic does not Exists!");
                    Menu menu = new Menu();
                    menu.mainMenu();
                }
            } catch (InputMismatchException e) {
                System.out.println("Please enter only numbers");
                scanner.next();
                counts +=1;
                if (counts == 4) {
                    Menu menu = new Menu();
                    System.out.println("Returning to Main Menu...");
                    menu.mainMenu();
                }
            }
        }

    }
}
