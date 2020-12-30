package epita.fr.java.quiz.DAOs;

import epita.fr.java.quiz.connection.DatabaseConnection;
import epita.fr.java.quiz.menu.Menu;

import java.sql.Connection;
import java.util.Scanner;

public class QuestionsDAO {

    public void addQuestions() {
        Scanner scanner = new Scanner(System.in);
        Menu menu = new Menu();
        Connection connection = DatabaseConnection.getConnection();
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

    }
}
