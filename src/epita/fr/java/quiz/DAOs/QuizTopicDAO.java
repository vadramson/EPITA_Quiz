package epita.fr.java.quiz.DAOs;

import epita.fr.java.quiz.menu.Menu;

import java.util.Scanner;

public class QuizTopicDAO {

    QuizDAO quizDAO = new QuizDAO();
    Scanner scanner = new Scanner(System.in);
    Menu menu = new Menu();

    public void createQuizTopic() {
        System.out.println("Enter the Quiz code to get started");
        String quizCode = scanner.next();
        Integer potentialQuizId = quizDAO.getQuizID(quizCode);
        if (potentialQuizId != 0){
            quizDAO.addQuizTopics(potentialQuizId);
        }else {
            System.out.println("Invalid Quiz Code");
            menu.mainMenu();
        }

    }
}
