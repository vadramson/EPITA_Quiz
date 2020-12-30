package epita.fr.java.quiz.DAOs;

import epita.fr.java.quiz.connection.DatabaseConnection;
import epita.fr.java.quiz.menu.Globals;
import epita.fr.java.quiz.menu.Menu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class RegisterDAO {

    Connection connection = DatabaseConnection.getConnection();
    TakeQuiz takeQuiz = new TakeQuiz();
    Menu menu = new Menu();
    public void loginOrRegister() {
        menu.justDisplay();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your student Number to begin.");
        String matricule = scanner.next();
        String studentMatricule = menu.lowercase(matricule);

        if(studentMatricule.equals("m") | studentMatricule.equals("e")){
            if(studentMatricule.equals("m")){
                menu.mainMenu();
            }
            else{
                menu.exit();
            }
        }

        String getStudentMatricule = "SELECT * FROM register WHERE studentmatricule=?";
        try {
            PreparedStatement preparedSelectStatement = connection.prepareStatement(getStudentMatricule);
            preparedSelectStatement.setString(1, studentMatricule);
            ResultSet checkMatricule = preparedSelectStatement.executeQuery();
            if (checkMatricule.next()) {
                System.out.println("You are registered already");
                connection.close();
                Globals.studentMatricule = studentMatricule;
                takeQuiz.takeQuiz();
            }
            if (!checkMatricule.next()) {
                System.out.println("You are not yet registered.");
                System.out.println("");
                System.out.println("");
                System.out.println("Please wait! ");
                System.out.println("");
                System.out.println("Registering you...");
                Globals.studentMatricule = studentMatricule;

                String addStudentMatricule = "INSERT INTO register (studentmatricule) VALUES (?) ";
                PreparedStatement registerStudent = connection.prepareStatement(addStudentMatricule);
                registerStudent.setString(1, studentMatricule);
                int saveStudent = registerStudent.executeUpdate();
                connection.close();
                if (saveStudent > 0) {
                    System.out.println("");
                    System.out.println("You have been registered Successfully");
                    takeQuiz.takeQuiz();
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
