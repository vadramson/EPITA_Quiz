package epita.fr.java.quiz.DAOs;

import epita.fr.java.quiz.connection.DatabaseConnection;
import epita.fr.java.quiz.menu.Globals;
import epita.fr.java.quiz.menu.Menu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class TakeQuiz {
    QuizDAO quizDAO = new QuizDAO();
    Scanner scanner = new Scanner(System.in);
    Menu menu = new Menu();
    Connection connection = DatabaseConnection.getConnection();

    public void takeQuiz(){
        System.out.println("Please enter the Quiz code you wish to take.");
        String code = scanner.next();
        Integer potentialQuizId = quizDAO.getQuizID(code);
        if (potentialQuizId != 0){
            generateAndTakeQuiz(potentialQuizId);
        }else {
            System.out.println("Invalid Quiz Code");
            menu.mainMenu();
        }
    }

    private void generateAndTakeQuiz(Integer quizIDGenerated){
        System.out.println("");
        System.out.println("Quiz Started.\n\nFor MCQ Question, enter only the letter corresponding to the right answer when prompt to do so.\nFor Ordinary questions, enter your answer only when prompt to do so.\n\n Tic Toc\n\n BEGIN\n\n");
        int questionID = 0, topicId=0, difficulty=0, questionsCount = 1, marksCount = 0;
        String question = "", questionType = "", answer = "", userAnswer = "", a = "", b = "", c = "", d = "";
        String getQuizTopicsQuery = "SELECT * FROM topicquiz WHERE idquiz=?";
        String getQuizTopicsQuestionsQuery = "SELECT * FROM questions WHERE idtopics =? AND difficulty =?";
        String getQuestionsMCQ_AnswersQuery = "SELECT * FROM mcqanswers WHERE idquestions=?";
        String getQuestionsO_AnswersQuery = "SELECT * FROM answers WHERE idquestions=?";
        try {
//            Get All the topics and difficulties need to generate the chosen quiz
            PreparedStatement getQuizTopicsStatement = connection.prepareStatement(getQuizTopicsQuery);
            getQuizTopicsStatement.setInt(1, quizIDGenerated);
            ResultSet quiz = getQuizTopicsStatement.executeQuery();
            while (quiz.next()) {
                topicId = quiz.getInt(3);
                difficulty = quiz.getInt(4);
                try {
                    PreparedStatement getQuizTopicsQuestionsStatement = connection.prepareStatement(getQuizTopicsQuestionsQuery);
                    getQuizTopicsQuestionsStatement.setInt(1, topicId);
                    getQuizTopicsQuestionsStatement.setInt(2, difficulty);
                    ResultSet questions = getQuizTopicsQuestionsStatement.executeQuery();
                    while (questions.next()) {
                        questionID = questions.getInt(1);
                        question = questions.getString(4);
                        questionType = questions.getString(5);
                        System.out.println("");
                        System.out.println(questionsCount +"). " + question);
                        System.out.println("");
                        if(questionType.equals("MCQ")){
                            try {
                                PreparedStatement getMCQ_QuestionsAnswersStatement = connection.prepareStatement(getQuestionsMCQ_AnswersQuery);
                                getMCQ_QuestionsAnswersStatement.setInt(1, questionID);
                                ResultSet mcqQuestionsAnswers = getMCQ_QuestionsAnswersStatement.executeQuery();
                                while (mcqQuestionsAnswers.next()) {
                                    a = mcqQuestionsAnswers.getString(3);
                                    b = mcqQuestionsAnswers.getString(4);
                                    c = mcqQuestionsAnswers.getString(5);
                                    d = mcqQuestionsAnswers.getString(6);
                                    answer = mcqQuestionsAnswers.getString(7);

                                    System.out.println("A). " + a);
                                    System.out.println("B). " + b);
                                    System.out.println("C). " + c);
                                    System.out.println("D). " + d);
                                    System.out.println("");
                                    System.out.println("Enter the later corresponding to the right answer");
                                    userAnswer = scanner.next();
                                    if (userAnswer.equals(answer)){
                                        marksCount+=1;
                                    }else{
                                        System.out.println("Wrong Later enterd, you score 0 for this question");
                                        System.out.println("");
                                    }
                                }
                                } catch (SQLException e) {
                                    e.printStackTrace();
                                }
                        }
                        if(questionType.equals("o")){
                            try{
                            PreparedStatement getO_QuestionsAnswersStatement = connection.prepareStatement(getQuestionsO_AnswersQuery);
                            getO_QuestionsAnswersStatement.setInt(1, questionID);
                            ResultSet oQuestionsAnswers = getO_QuestionsAnswersStatement.executeQuery();
                            while (oQuestionsAnswers.next()) {
                                answer = oQuestionsAnswers.getString(3);
                                System.out.println("Enter your answer ");
                                userAnswer = scanner.next();
                                if (userAnswer.equals(answer)){
                                    marksCount+=1;
                                }
                                }
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        }
                        questionsCount+=1;
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
//            Finish Qnawering quiz, save the results and display user's score for the quiz
            System.out.println("\n\nYour score: " + marksCount);
            saveQuizResults(Globals.studentMatricule, quizIDGenerated, marksCount);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void saveQuizResults(Integer register, Integer quiz,Integer score) {
        String saveScoreQuery = "INSERT INTO results (idregister, idquiz, quizscore) VALUES (?, ?, ?)";
        try {
            PreparedStatement saveScoreStatement = connection.prepareStatement(saveScoreQuery);
            saveScoreStatement.setInt(1, register);
            saveScoreStatement.setInt(2, quiz);
            saveScoreStatement.setInt(3, score);
            int saveScore = saveScoreStatement.executeUpdate();
            if(saveScore > 0) {
                System.out.println("");
                System.out.println("Your Score for this quiz has been saved Successfully!\n\nQuiz End ");
                System.out.println("\nReturning to main Menu");
                menu.mainMenu();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
