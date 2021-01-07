package epita.fr.java.quiz.DAOs;

import epita.fr.java.quiz.connection.DatabaseConnection;
import epita.fr.java.quiz.menu.Globals;
import epita.fr.java.quiz.menu.Menu;

import java.sql.*;
import java.util.Scanner;

public class RegisterDAO {

    Connection connection = DatabaseConnection.getConnection();
    TakeQuiz takeQuiz = new TakeQuiz();
    Menu menu = new Menu();
    public void loginOrRegister() {
        int registerId = 0;
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
            while (checkMatricule.next()) {
                registerId = checkMatricule.getInt(1);
            }
            if(registerId != 0) {
                System.out.println("You are registered already");
                connection.close();
                Globals.studentMatricule = registerId;
                takeQuiz.takeQuiz();
            }
            else {
                System.out.println("You are not yet registered.");
                System.out.println("");
                System.out.println("");
                System.out.println("Please wait! ");
                System.out.println("");
                System.out.println("Registering you...");

                String addStudentMatricule = "INSERT INTO register (studentmatricule) VALUES (?) ";
                PreparedStatement registerStudent = connection.prepareStatement(addStudentMatricule, Statement.RETURN_GENERATED_KEYS);
                registerStudent.setString(1, studentMatricule);
                registerStudent.executeUpdate();
                ResultSet registerStudentResultSet = registerStudent.getGeneratedKeys();

                if (registerStudentResultSet.next()) {
                    registerId = registerStudentResultSet.getInt(1);

                    System.out.println("");
                    System.out.println("You have been registered Successfully");
                    Globals.studentMatricule = registerId;
                    takeQuiz.takeQuiz();
                }
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
