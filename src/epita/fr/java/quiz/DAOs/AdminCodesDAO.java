package epita.fr.java.quiz.DAOs;

import epita.fr.java.quiz.connection.DatabaseConnection;
import epita.fr.java.quiz.menu.Globals;
import epita.fr.java.quiz.menu.Menu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class AdminCodesDAO {

    public void checkIfAdmin() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your Admin Code to confirm your Status");

        int counts = 0;
        while (true) {
            try {
                Integer adminCode = scanner.nextInt();
                Connection connection = DatabaseConnection.getConnection();
                String getAdminCode = "SELECT * FROM admincodes WHERE code=?";
                try {
                    PreparedStatement preparedSelectStatement = connection.prepareStatement(getAdminCode);
                    preparedSelectStatement.setInt(1, adminCode);
                    ResultSet checkCode = preparedSelectStatement.executeQuery();
                    if (checkCode.next()) {
                        System.out.println("Authenticated");
                        connection.close();
                        Globals.isAdmin = true;
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                break;
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
