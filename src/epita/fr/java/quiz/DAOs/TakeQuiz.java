package epita.fr.java.quiz.DAOs;

import java.util.Scanner;

public class TakeQuiz {

    public void takeQuiz(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the Quiz code you wish to take.");
        String code = scanner.next();
        System.out.println(code);
    }
}
