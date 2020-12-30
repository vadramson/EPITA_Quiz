package epita.fr.java.quiz.DAOs;

import epita.fr.java.quiz.connection.DatabaseConnection;
import epita.fr.java.quiz.models.Topics;

import java.sql.*;
import java.util.Scanner;

import static jdk.nashorn.internal.objects.NativeString.toLowerCase;

public class TopicsDAO {

    public void addTopics(){
        Connection connection = DatabaseConnection.getConnection();
        Scanner scanner = new Scanner(System.in);

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
                    PreparedStatement preparedStatement = connection.prepareStatement(addTopicQuery);
                    preparedStatement.setString(1, topic);
                    int addTopic = preparedStatement.executeUpdate();
                    if (addTopic > 0) {
                        System.out.println("The topic was add Successfully");
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
}
