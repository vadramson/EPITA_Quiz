package epita.fr.java.quiz.DAOs;

import epita.fr.java.quiz.connection.DatabaseConnection;
import epita.fr.java.quiz.menu.Globals;
import epita.fr.java.quiz.menu.Menu;
import epita.fr.java.quiz.models.Topics;

import java.sql.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import static jdk.nashorn.internal.objects.NativeString.toLowerCase;

public class QuestionsDAO {
    Scanner scanner = new Scanner(System.in);
    Menu menu = new Menu();
    Connection connection = DatabaseConnection.getConnection();

    public void addQuestions() {

        menu.justDisplay();
        System.out.println("You can add new questions by first choosing the type of Question");
        System.out.println("");
        System.out.println("Enter (MCQ) for MCQ questions or (O) for other Question types");

        String questionTypes = scanner.next();
        String questionType = menu.lowercase(questionTypes);

        if(questionType.equals("m") | questionType.equals("e")){
            if(questionType.equals("m")){
                menu.mainMenu();
            }
            else{
                menu.exit();
            }
        }
        if(questionType.equals("mcq")){
            addMCQQuestions();
        }

        if(questionType.equals("o")){

        }
    }

    public List<Topics> displayTopics() {
        List<Topics> topics = new ArrayList<Topics>();

        try {
            String getAllTopicsQuery = "SELECT * FROM topics WHERE status = 1";
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery(getAllTopicsQuery);
            while (results.next()) {
                int topicId = results.getInt(1);
                String questionTopic = results.getString(2);
                Topics topic = new Topics();
                topic.setIdTopics(topicId);
                topic.setTopic(questionTopic);
                topics.add(topic);
                System.out.println("( " + topicId +" ) for - " + questionTopic);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return topics;
    }

    public boolean checkTopic(Integer topicId){
        boolean topicID = false;
        try {
            String getTopicId = "SELECT * FROM topics WHERE idtopics = ?";
            PreparedStatement checkTopicStatement = connection.prepareStatement(getTopicId);
            checkTopicStatement.setInt(1, topicId);
            ResultSet checkId = checkTopicStatement.executeQuery();
            if (checkId.next()) {
                topicID = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return topicID;
    }

    public void addMCQQuestions() {
        System.out.println("Choose Question topic by entering: ");
        displayTopics();
        int counts = 0;
        while (true) {
            try {
                int idTopic = scanner.nextInt();
                if(checkTopic(idTopic)){
                    System.out.println("Enter Question difficulty level between 1 to 3");
                    int count = 0;
                    while (true) {
                        try {
                            int difficulty = scanner.nextInt();
                            if(difficulty < 1){
                                difficulty = 1;
                                System.out.println("Difficulty 1 chosen by default due to faulty answer");
                            }
                            if(difficulty > 3){
                                difficulty = 3;
                                System.out.println("Difficulty 3 chosen by default due to faulty answer");
                            }
                            System.out.println("Enter the Question: ");
                            scanner.nextLine();
                            String question = scanner.nextLine();
                            try {


                                    System.out.println("");
                                    System.out.println("");

                                    System.out.println("Enter the first possible answer");
                                    String firstAnswer = scanner.nextLine();

                                    System.out.println("Enter the second possible answer");
                                    String secondAnswer = scanner.nextLine();

                                    System.out.println("Enter the third possible answer");
                                    String thirdAnswer = scanner.nextLine();

                                    System.out.println("Enter the fourth possible answer");
                                    String fourthAnswer = scanner.nextLine();

                                    boolean allSystemGo = false;
                                    String solution = "";

                                    solution = chooseRightAnswer(firstAnswer, secondAnswer, thirdAnswer, fourthAnswer);
                                    boolean save = rightAnswer(solution);
                                    if (save) {
                                        allSystemGo = true;
                                    } else {
                                        System.out.println("Wrong Choice. You will have one more chance to enter the right answer ");
                                        solution = chooseRightAnswer(firstAnswer, secondAnswer, thirdAnswer, fourthAnswer);
                                        save = rightAnswer(solution);
                                        if (save) {
                                            allSystemGo = true;
                                        } else {
                                            System.out.println("Two Wrong Choices,\n program will exit now!");
                                            System.out.println("");
                                            menu.exit();
                                        }
                                    }

                                    if(allSystemGo){
                                        String saveMCQ_Question = "INSERT INTO  questions (idtopics, difficulty, question, questiontype) values (?,?,?,?)";
                                        PreparedStatement preparedStatement = connection.prepareStatement(saveMCQ_Question, Statement.RETURN_GENERATED_KEYS);
                                        preparedStatement.setInt(1, idTopic);
                                        preparedStatement.setInt(2, difficulty);
                                        preparedStatement.setString(3, question);
                                        preparedStatement.setString(4, "MCQ");
                                        preparedStatement.execute();
                                        ResultSet resultSet = preparedStatement.getGeneratedKeys();
                                        int generatedKeyMCQ_Question = 0;
                                        if (resultSet.next()) {
                                            generatedKeyMCQ_Question = resultSet.getInt(1);
                                        }
                                        if (generatedKeyMCQ_Question > 0) {
                                            String saveMCQ_Answers = "INSERT INTO  mcqanswers (idquestions, a, b, c, d, solution) values (?,?,?,?,?,?)";
                                            PreparedStatement preparedStatementMCQ_Answers = connection.prepareStatement(saveMCQ_Answers, Statement.RETURN_GENERATED_KEYS);
                                            preparedStatementMCQ_Answers.setInt(1, generatedKeyMCQ_Question);
                                            preparedStatementMCQ_Answers.setString(2, firstAnswer);
                                            preparedStatementMCQ_Answers.setString(3, secondAnswer);
                                            preparedStatementMCQ_Answers.setString(4, thirdAnswer);
                                            preparedStatementMCQ_Answers.setString(5, fourthAnswer);
                                            preparedStatementMCQ_Answers.setString(6, solution);
                                            preparedStatementMCQ_Answers.execute();
                                            ResultSet resultSetMCQ_Answers = preparedStatementMCQ_Answers.getGeneratedKeys();
                                            int generatedID_MCQ_Answers = 0;
                                            if (resultSetMCQ_Answers.next()) {
                                                generatedID_MCQ_Answers = resultSetMCQ_Answers.getInt(1);
                                            }
                                            if (generatedID_MCQ_Answers > 0) {

                                                System.out.println("The Question was added Successfully");
                                                System.out.println("Do you wish to add another Question? \n Enter Y or N");
                                                String choices = scanner.next();
                                                String choice = toLowerCase(choices);

                                                if (choice.equals("y")){
                                                    addMCQQuestions();
                                                }else if (choice.equals("n") | !choice.equals("y")){
                                                    Menu menu = new Menu();
                                                    menu.mainMenu();
                                                }
                                            }
                                        }
                                    }




                                connection.close();
                                break;
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }

                        } catch (InputMismatchException e) {
                            System.out.println("Please enter only numbers");
                            scanner.next();
                            count +=1;
                            if (count == 4) {
                                Menu menu = new Menu();
                                System.out.println("You love Playing ): ! \nReturning you to Main Menu...");
                                menu.mainMenu();
                            }
                        }
                    }
                    break;
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

    private String chooseRightAnswer(String firstAnswer, String secondAnswer, String thirdAnswer, String fourthAnswer){
        System.out.println("");
        System.out.println("Which is the correct answer? ");
        System.out.println("");
        System.out.println("Enter ");
        System.out.println("A for - " + firstAnswer);
        System.out.println("OR");
        System.out.println("B for - " + secondAnswer);
        System.out.println("OR");
        System.out.println("C for - " + thirdAnswer);
        System.out.println("OR");
        System.out.println("D for - " + fourthAnswer);
        String solutions = scanner.next();
        return menu.lowercase(solutions);
    }

    private boolean rightAnswer(String solution){
        int countAnswer = 0;
        boolean save = false;

        if(!solution.equals("a") && !solution.equals("b") && !solution.equals("c") && !solution.equals("d")){
//            System.out.println("Wrong Choice. You will have one more chance to enter the right answer ");
            countAnswer +=1;
            return false;
        }else{
            return true;
        }

    }


}
