package epita.fr.java.quiz.DAOs;

import epita.fr.java.quiz.connection.DatabaseConnection;
import epita.fr.java.quiz.menu.Menu;

import java.sql.*;
import java.util.InputMismatchException;
import java.util.Scanner;

import static jdk.nashorn.internal.objects.NativeString.toLowerCase;

public class QuizDAO {

    Connection connection = DatabaseConnection.getConnection();
    Scanner scanner = new Scanner(System.in);
    int  generatedQuizKey = 0;
    QuestionsDAO questionsDAO = new QuestionsDAO();
    Menu menu = new Menu();


    public void createQuiz(){
        System.out.println("You are about to create a new Quiz");
        System.out.println("");
        System.out.println("");
        System.out.println("Enter a code for your Quiz");
        String quizCode = scanner.next();
        String getQuizCodeQuery = "SELECT * FROM quiz WHERE quizcode=?";
        String addQuizCodeQuery = "INSERT INTO quiz (quizcode) VALUES (?)";

        try {
            PreparedStatement preparedSelectQuizCodeStatement = connection.prepareStatement(getQuizCodeQuery);
            preparedSelectQuizCodeStatement.setString(1, quizCode);
            ResultSet checkQuizCode = preparedSelectQuizCodeStatement.executeQuery();
            if (checkQuizCode.next()) {
                System.out.println("Quiz Code exists Already.\nEnter a new code or return to main menu by entering m");
                connection.close();
                createQuiz();
            }
            else if(!checkQuizCode.next()){
                try {
                    PreparedStatement preparedSaveQuizStatement = connection.prepareStatement(addQuizCodeQuery, Statement.RETURN_GENERATED_KEYS);
                    preparedSaveQuizStatement.setString(1, quizCode);
                    preparedSaveQuizStatement.execute();
                    ResultSet quizResultSet = preparedSaveQuizStatement.getGeneratedKeys();
                    if (quizResultSet.next()) {
                      generatedQuizKey = quizResultSet.getInt(1);
                    }
                    if (generatedQuizKey > 0) {
                        System.out.println("The Quiz was Saved Successfully");
                        String displayPromptAddQuizNow = "Do you wish to add Topics to the quiz now OR you wish to do that later? \n Enter Y or N";
                        System.out.println("");
                        System.out.println("");
                        postIsTopicQuiz(generatedQuizKey, displayPromptAddQuizNow);
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

    public Integer getQuizID(String quizCode) {
        int quizID = 0, status= -1;
        String quizCodeDb = "";
        String getQuizCodeQuery = "SELECT * FROM quiz WHERE quizcode=?";
        try {
            PreparedStatement checkQuizStatement = connection.prepareStatement(getQuizCodeQuery);
            checkQuizStatement.setString(1, quizCode);
            ResultSet quiz = checkQuizStatement.executeQuery();
            while (quiz.next()) {
                quizID = quiz.getInt(1);
                quizCodeDb = quiz.getString(2);
                status = quiz.getInt(3);
            }
            if(quizID != 0) {
                String stat = "";
                if (status == 1) {
                    stat = "Active";
                } else {
                    stat = "Deactivated";
                }
                System.out.println("Quiz Code : " + quizCodeDb + "\nStatus : " + stat);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return quizID;
    }

    public boolean isQuizTopic(Integer quizID, Integer topicID) {
        boolean found = false;
        int existingId = 0;
        String getQuizCodeQuery = "SELECT * FROM topicquiz WHERE idquiz=? AND idtopics=?";
        try {
            PreparedStatement checkQuizStatement = connection.prepareStatement(getQuizCodeQuery);
            checkQuizStatement.setInt(1, quizID);
            checkQuizStatement.setInt(2, topicID);
            ResultSet quizTopic = checkQuizStatement.executeQuery();
            while (quizTopic.next()) {
                existingId = quizTopic.getInt(1);
            }
            if (existingId > 0) {
                found = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return found;
    }

    public void addQuizTopics(Integer quizID) {
        System.out.println("The System generates quiz questions based on the topics and difficulties for the desired questions you choose.\n\nYou will have to choose a topic, enter a difficulty for the questions you would like to be generated.");
        System.out.println("");
        System.out.println("Choose a topic from the list\n Enter: ");
        questionsDAO.displayTopics();
            int counts = 0;
            while (true) {
                try {
                    int idTopic = scanner.nextInt();
                    if(questionsDAO.checkTopic(idTopic)){
                        System.out.println("Enter a Question difficulty level between 1 to 3");
                        int count = 0;
                        while (true) {
                            try {
                                int generatedQuestionDifficulty = scanner.nextInt();
                                if(generatedQuestionDifficulty < 1){
                                    generatedQuestionDifficulty = 1;
                                    System.out.println("Difficulty 1 chosen by default due to faulty answer");
                                }
                                if(generatedQuestionDifficulty > 3){
                                    generatedQuestionDifficulty = 3;
                                    System.out.println("Difficulty 3 chosen by default due to faulty answer");
                                }
                                String myDisplayPrompt = "Do you wish to add another Topic to your Quiz? \n Enter Y or N";
                                if(!isQuizTopic(quizID, idTopic)) {
                                    try {
                                        String saveQuizTopic_Question = "INSERT INTO  topicquiz (idquiz, idtopics, questiondifficulty) values (?,?,?)";
                                        PreparedStatement preparedQuizTopicStatement = connection.prepareStatement(saveQuizTopic_Question, Statement.RETURN_GENERATED_KEYS);
                                        preparedQuizTopicStatement.setInt(1, quizID);
                                        preparedQuizTopicStatement.setInt(2, idTopic);
                                        preparedQuizTopicStatement.setInt(3, generatedQuestionDifficulty);
                                        preparedQuizTopicStatement.execute();
                                        ResultSet quizTopicResultSet = preparedQuizTopicStatement.getGeneratedKeys();
                                        int generatedKeyMCQ_Question = 0;
                                        if (quizTopicResultSet.next()) {
                                            generatedKeyMCQ_Question = quizTopicResultSet.getInt(1);
                                        }
                                        if (generatedKeyMCQ_Question > 0) {
                                            System.out.println("The Question was added Successfully");
                                            postIsTopicQuiz(quizID, myDisplayPrompt);
                                        }
                                        connection.close();
                                        break;
                                    } catch (SQLException e) {
                                        e.printStackTrace();
                                    }
                                }else{
                                    System.out.println("Your chosen Topic for this Quiz exists already");
                                    postIsTopicQuiz(quizID, myDisplayPrompt);
                                }
                            } catch (InputMismatchException e) {
                                System.out.println("Please enter only numbers");
                                scanner.next();
                                count +=1;
                                if (count == 4) {
                                    System.out.println("You love Playing ): ! \nReturning you to Main Menu...");
                                    menu.mainMenu();
                                }
                            }
                        }
                        break;
                    }else{
                        System.out.println("Topic does not Exists!");
                        menu.mainMenu();
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Please enter only numbers");
                    scanner.next();
                    counts +=1;
                    if (counts == 4) {
                        System.out.println("Returning to Main Menu...");
                        menu.mainMenu();
                    }
                }
            }
    }

    public void postIsTopicQuiz(Integer quizID, String displayPrompt) {
        System.out.println(displayPrompt);
        String ansChoices = scanner.next();
        String ansChoice = toLowerCase(ansChoices);

        if( ansChoice.equals("y") ){
            addQuizTopics(quizID);
        }else if (ansChoice.equals("n") || !ansChoice.equals("y")){
            menu.mainMenu();
        }
    }

    public void viewQuizzes(Integer displayFor) {
        int quizID = 0, status= -1;
        String quizCodeDb = "";
        String getQuizCodeQuery = "SELECT * FROM quiz";
        try {
            PreparedStatement checkQuizStatement = connection.prepareStatement(getQuizCodeQuery);
            ResultSet quiz = checkQuizStatement.executeQuery();
            Integer count = 1;
            while (quiz.next()) {
                quizID = quiz.getInt(1);
                quizCodeDb = quiz.getString(2);
                status = quiz.getInt(3);

                String stat = "";
                if (quizID != 0) {
                    if (status == 1) {
                        stat = "Active";
                    } else {
                        stat = "Deactivated";
                    }
                    if (displayFor == 0) {
                        System.out.println(count + "). Quiz Code : " + quizCodeDb + ", -|- Status : " + stat);
                        count+=1;
                    } else {
                        System.out.println("( " + quizID + " ) for: " + quizCodeDb + " Status : " + stat);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
