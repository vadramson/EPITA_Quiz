package epita.fr.java.quiz.menu;

import epita.fr.java.quiz.DAOs.AdminCodesDAO;
import epita.fr.java.quiz.DAOs.QuestionsDAO;
import epita.fr.java.quiz.DAOs.RegisterDAO;
import epita.fr.java.quiz.DAOs.TopicsDAO;

import java.util.Scanner;

import static jdk.nashorn.internal.objects.NativeString.toLowerCase;

public class Menu {

    public void mainMenu(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("-|-*********************************************************-|-");
        System.out.println(" |                                                           |");
        System.out.println(" |         EPITA JAVA QUIZ By VADRAMA NDISANG NGYIBI!        |");
        System.out.println(" |                                                           |");
        System.out.println("-|-*********************************************************-|-");
        System.out.println("");
        System.out.println("");
        System.out.println("What will you like to do?");
        System.out.println("T) Manage Quiz    OR    S) Take a Quiz");
        System.out.println("Enter T or S");
        String choices = scanner.next();
        String choice = lowercase(choices);
        if (choice.equals("e")){
            exit();
        }
        if (choice.equals("t")){
            AdminCodesDAO adminCodesDAO = new AdminCodesDAO();
            adminCodesDAO.checkIfAdmin();
            if(Globals.isAdmin){

                System.out.println("What will you like to do?");
                System.out.println("Q) To Manage Questions    OR    TP) To Manage Topics OR  QZ) To Manage Quiz");
                System.out.println("Enter Q or T or QZ ");
                String adminChoices = scanner.next();
                String adminChoice = lowercase(adminChoices);

                if (adminChoice.equals("e")){
                    exit();
                }
                if (adminChoice.equals("q")){
                    QuestionsDAO questionsDAO = new QuestionsDAO();
                    questionsDAO.addQuestions();
                }
                if (adminChoice.equals("tp")){
                    TopicsDAO topicsDAO = new TopicsDAO();
                    topicsDAO.addTopics();
                }
                if (adminChoice.equals("qz")){
                    exit();
                }
                exitInvalidEntry();



            }

        }
        if (choice.equals("s")){
            RegisterDAO registerDAO = new RegisterDAO();
            registerDAO.loginOrRegister();
        }

        if (!choice.equals("t") | !choice.equals("s")){

            exitInvalidEntry();
        }
    }

    public void exit(){
        System.out.println("Thanks For using Me :) :::: Exiting...");
        System.exit(1);
    }

    public String lowercase(String choice) {
        return toLowerCase(choice);
    }

    public void exitInvalidEntry(){
        System.out.println("Invalid Entry");
        System.exit(1);
    }

    public void justDisplay(){
        System.out.println("-|-###################################################################-|-");
        System.out.println(" |   You can always Enter (M) to return to main menu or (E) to exit    |");
        System.out.println("-|-###################################################################-|-");
        System.out.println("");
        System.out.println("");
    }
}
