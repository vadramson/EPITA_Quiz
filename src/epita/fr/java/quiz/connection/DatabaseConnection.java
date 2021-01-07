package epita.fr.java.quiz.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    public static String connectionURL = "jdbc:postgresql://localhost:5432/Epita_Java_Quiz";
    public static String databaseUser = "postgres";
    public static String databasePassword = "Vadson";
    public static Connection connection;
    public static String connectionDriver = "org.postgresql.Driver";

    public static Connection getConnection() {
        Connection  con = null;
        try {
            Class.forName(connectionDriver);
            try {
                 con = DriverManager.getConnection(connectionURL, databaseUser, databasePassword);
//                 if(con != null) System.out.println("Connected!");
            } catch (SQLException ex) {
                System.out.println("Failed to create the database connection.");
            }
        } catch (ClassNotFoundException ex) {
            System.out.println("Driver not found.");
        }
        return con;
    }

}
