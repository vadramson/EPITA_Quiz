import epita.fr.java.quiz.DAOs.QuestionsDAO;
import epita.fr.java.quiz.menu.Menu;
import epita.fr.java.quiz.models.Topics;

import java.util.List;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) {
        Menu menu = new Menu();
        menu.mainMenu();

//        QuestionsDAO questionsDAO = new QuestionsDAO();
//        questionsDAO.displayTopics();
//        List<Topics> checkTopicID =  questionsDAO.displayTopics();
//        System.out.println("Enter toic ID");
//        Scanner scanner = new Scanner(System.in);
//        int idTopic = scanner.nextInt();
////        String idTopic = scanner.next();
//        System.out.println("The element idTopic is available in ArrayList? " + questionsDAO.checkTopic(idTopic));

    }
}
