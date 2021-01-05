package epita.fr.java.quiz.DAOs;

import epita.fr.java.quiz.connection.DatabaseConnection;
import epita.fr.java.quiz.menu.Globals;
import epita.fr.java.quiz.menu.Menu;
import epita.fr.java.quiz.models.Topics;

import java.sql.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import static jdk.nashorn.internal.objects.NativeString.toLowerCase;

public class QuestionsDAO {
    Scanner scanner = new Scanner(System.in);
    Menu menu = new Menu();
    Connection connection = DatabaseConnection.getConnection();

    public void addQuestions() {

        menu.justDisplay();
        System.out.println("You can add new questions by first choosing the type of Question");
        System.out.println();
        System.out.println("Enter (MCQ) for MCQ questions or (O) for other Question types");

        String questionTypes = scanner.next();
        String questionType = menu.lowercase(questionTypes);

        if(questionType.equals("m")){
            menu.mainMenu();
        }
        if(questionType.equals("e")){
                menu.exit();
        }
        if(questionType.equals("mcq") || questionType.equals("o")){
            addMCQQuestions(questionType);
        }

    }

    public List<Topics> displayTopics() {
        List<Topics> topics = new ArrayList<Topics>();

        try {
//            String getAllTopicsQuery = "SELECT * FROM topics WHERE status = 1";
            String getAllTopicsQuery = "SELECT * FROM topics ORDER BY idtopics ASC ";
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery(getAllTopicsQuery);
            while (results.next()) {
                int topicId = results.getInt(1);
                int status = results.getInt(3);
                String questionTopic = results.getString(2);
                Topics topic = new Topics();
                topic.setIdTopics(topicId);
                topic.setTopic(questionTopic);
                topics.add(topic);
                String stat = "";
                if(status == 1){
                    stat = "Active";
                }
                else{
                    stat = "Deactivated";
                }
                System.out.println("( " + topicId +" ) for - " + questionTopic + "  *NB* Status -> "+ stat);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return topics;
    }

    public boolean checkTopic(Integer topicId){
        boolean topicID = false;
        try {
            String getTopicId = "SELECT * FROM topics WHERE idtopics = ?";
            PreparedStatement checkTopicStatement = connection.prepareStatement(getTopicId);
            checkTopicStatement.setInt(1, topicId);
            ResultSet checkId = checkTopicStatement.executeQuery();
            if (checkId.next()) {
                topicID = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return topicID;
    }

    private boolean checkQuestion(Integer questionId){
        boolean questionID = false;
        try {
            String getQuestionId = "SELECT * FROM questions WHERE idquestions = ?";
            PreparedStatement checkQuestionStatement = connection.prepareStatement(getQuestionId);
            checkQuestionStatement.setInt(1, questionId);
            ResultSet checkId = checkQuestionStatement.executeQuery();
            if (checkId.next()) {
                questionID = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return questionID;
    }

    void getAndUpdateTopic(Integer topicId){
        int status = -1;
        String topicValue = "", topicVal= "";
        try {
            String getTopicId = "SELECT * FROM topics WHERE idtopics = ?";
            PreparedStatement checkTopicStatement = connection.prepareStatement(getTopicId);
            checkTopicStatement.setInt(1, topicId);
            ResultSet topic = checkTopicStatement.executeQuery();
            while (topic.next()) {
                status = topic.getInt(3);
                topicValue = topic.getString(2);
            }
            String stat = "";
            if(status == 1){
                stat = "Active";
            }
            else{
                stat = "Deactivated";
            }
            System.out.println("Topic : " + topicValue + "\nStatus : " + stat);

            System.out.println("Enter : \n-1 To modify only the Topic");
            if(status == 1){
                System.out.println("-2 To Deactivate only the Topic");
                status = 0;
            }else{
                System.out.println("-2 To Activate only the Topic");
                status = 1;
            }
            System.out.println("");
            System.out.println("Please Enter either 1 or 2 ONLY! ");
            String updateTopicQuery = "", newTopic = "";

            try {
                int updateChoice = scanner.nextInt();
                if(updateChoice==1){
                    updateTopicQuery = "UPDATE topics SET topic =? WHERE idtopics = ? ";
                    System.out.println("Enter the new Topic ");
                    scanner.nextLine();
                    newTopic = scanner.nextLine();
                }else if(updateChoice==2){
                    updateTopicQuery = "UPDATE topics SET status =? WHERE idtopics = ? ";
                }

//                Query to update starts

                try {
                    PreparedStatement updateTopicStatement = connection.prepareStatement(updateTopicQuery, Statement.RETURN_GENERATED_KEYS);
                    updateTopicStatement.setInt(2, topicId);
                    if(updateChoice==1){
                        updateTopicStatement.setString(1, newTopic);
                    }else if(updateChoice==2){
                        updateTopicStatement.setInt(1, status);
                    }
                    int updateTopic = updateTopicStatement.executeUpdate();
                    if (updateTopic > 0) {
                        System.out.println("Topic updated successfully!");
                        System.out.println("");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }

//                Query to update ends

            } catch (InputMismatchException e) {
                    menu.exitInvalidEntry();
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void addMCQQuestions(String questionType) {
        System.out.println("Choose Question topic by entering: ");
        displayTopics();
        int counts = 0;
        while (true) {
            try {
                int idTopic = scanner.nextInt();
                if(checkTopic(idTopic)){
                    System.out.println("Enter Question difficulty level between 1 to 3");
                    int count = 0;
                    while (true) {
                        try {
                            int difficulty = scanner.nextInt();
                            if(difficulty < 1){
                                difficulty = 1;
                                System.out.println("Difficulty 1 chosen by default due to faulty answer");
                            }
                            if(difficulty > 3){
                                difficulty = 3;
                                System.out.println("Difficulty 3 chosen by default due to faulty answer");
                            }
                            System.out.println("Enter a Question: ");
                            scanner.nextLine();
                            String question = scanner.nextLine();
                            try {
                                boolean allSystemGo = false;
                                String firstAnswer = "", secondAnswer = "", thirdAnswer = "", fourthAnswer = "", answer = "";
                                String solution = "";

                                if(questionType.equals("mcq")){

                                    System.out.println();
                                    System.out.println();

                                    System.out.println("Enter the first possible answer");
                                    firstAnswer = scanner.nextLine();

                                    System.out.println("Enter the second possible answer");
                                    secondAnswer = scanner.nextLine();

                                    System.out.println("Enter the third possible answer");
                                    thirdAnswer = scanner.nextLine();

                                    System.out.println("Enter the fourth possible answer");
                                    fourthAnswer = scanner.nextLine();

                                    solution = chooseRightAnswer(firstAnswer, secondAnswer, thirdAnswer, fourthAnswer);
                                    boolean save = rightAnswer(solution);
                                    if (save) {
                                        allSystemGo = true;
                                    } else {
                                        System.out.println("Wrong Choice. You will have one more chance to enter the right answer ");
                                        solution = chooseRightAnswer(firstAnswer, secondAnswer, thirdAnswer, fourthAnswer);
                                        save = rightAnswer(solution);
                                        if (save) {
                                            allSystemGo = true;
                                        } else {
                                            System.out.println("Two Wrong Choices,\n program will exit now!");
                                            System.out.println();
                                            menu.exit();
                                        }
                                    }
                                }else if(questionType.equals("o") ){
                                    System.out.println("Enter an Answer for Question: ");
                                    String questionAnswer = scanner.next();
                                    answer = menu.lowercase(questionAnswer);
                                    allSystemGo = true;
                                }

                                    if(allSystemGo){
                                        String saveMCQ_Question = "INSERT INTO  questions (idtopics, difficulty, question, questiontype) values (?,?,?,?)";
                                        PreparedStatement preparedStatement = connection.prepareStatement(saveMCQ_Question, Statement.RETURN_GENERATED_KEYS);
                                        preparedStatement.setInt(1, idTopic);
                                        preparedStatement.setInt(2, difficulty);
                                        preparedStatement.setString(3, question);
                                        preparedStatement.setString(4, questionType);
                                        preparedStatement.execute();
                                        ResultSet resultSet = preparedStatement.getGeneratedKeys();
                                        int generatedKeyMCQ_Question = 0;
                                        if (resultSet.next()) {
                                            generatedKeyMCQ_Question = resultSet.getInt(1);
                                        }
                                        if (generatedKeyMCQ_Question > 0 && questionType.equals("mcq")) {
                                            String saveMCQ_Answers = "INSERT INTO  mcqanswers (idquestions, a, b, c, d, solution) values (?,?,?,?,?,?)";
                                            PreparedStatement preparedStatementMCQ_Answers = connection.prepareStatement(saveMCQ_Answers, Statement.RETURN_GENERATED_KEYS);
                                            preparedStatementMCQ_Answers.setInt(1, generatedKeyMCQ_Question);
                                            preparedStatementMCQ_Answers.setString(2, firstAnswer);
                                            preparedStatementMCQ_Answers.setString(3, secondAnswer);
                                            preparedStatementMCQ_Answers.setString(4, thirdAnswer);
                                            preparedStatementMCQ_Answers.setString(5, fourthAnswer);
                                            preparedStatementMCQ_Answers.setString(6, solution);
                                            preparedStatementMCQ_Answers.execute();
                                            ResultSet resultSetMCQ_Answers = preparedStatementMCQ_Answers.getGeneratedKeys();
                                            int generatedID_MCQ_Answers = 0;
                                            if (resultSetMCQ_Answers.next()) {
                                                generatedID_MCQ_Answers = resultSetMCQ_Answers.getInt(1);
                                            }
                                            if (generatedID_MCQ_Answers > 0) {

                                                System.out.println("The Question was added Successfully");
                                                System.out.println("Do you wish to add another Question? \n Enter Y or N");
                                                String choices = scanner.next();
                                                String choice = toLowerCase(choices);

                                                if (choice.equals("y")){
                                                    addMCQQuestions(questionType);
                                                }else if (choice.equals("n") | !choice.equals("y")){
                                                    Menu menu = new Menu();
                                                    menu.mainMenu();
                                                }
                                            }
                                        }
                                        if (generatedKeyMCQ_Question > 0 && questionType.equals("o")) {
                                            String saveMCQ_Answers = "INSERT INTO  answers (idquestions, answer) values (?,?)";
                                            PreparedStatement preparedStatementOrdinary_Answers = connection.prepareStatement(saveMCQ_Answers, Statement.RETURN_GENERATED_KEYS);
                                            preparedStatementOrdinary_Answers.setInt(1, generatedKeyMCQ_Question);
                                            preparedStatementOrdinary_Answers.setString(2, answer);
                                            preparedStatementOrdinary_Answers.execute();
                                            ResultSet resultSetOrdinary_Answers = preparedStatementOrdinary_Answers.getGeneratedKeys();
                                            int generatedID_Ordinary_Answers = 0;
                                            if (resultSetOrdinary_Answers.next()) {
                                                generatedID_Ordinary_Answers = resultSetOrdinary_Answers.getInt(1);
                                            }
                                            if (generatedID_Ordinary_Answers > 0) {

                                                System.out.println("Question was added Successfully");
                                                System.out.println("Do you wish to add another Question? \n Enter Y or N");
                                                String ansChoices = scanner.next();
                                                String ansChoice = toLowerCase(ansChoices);

                                                if( ansChoice.equals("y") ){
                                                    addMCQQuestions(questionType);
                                                }else if (ansChoice.equals("n") || !ansChoice.equals("y")){
                                                    Menu menu = new Menu();
                                                    menu.mainMenu();
                                                }
                                            }
                                        }
                                    }

                                connection.close();
                                break;
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }

                        } catch (InputMismatchException e) {
                            System.out.println("Please enter only numbers");
                            scanner.next();
                            count +=1;
                            if (count == 4) {
                                Menu menu = new Menu();
                                System.out.println("You love Playing ): ! \nReturning you to Main Menu...");
                                menu.mainMenu();
                            }
                        }
                    }
                    break;
                }else{
                    System.out.println("Topic does not Exists!");
                    Menu menu = new Menu();
                    menu.mainMenu();
                }
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

    private String chooseRightAnswer(String firstAnswer, String secondAnswer, String thirdAnswer, String fourthAnswer){
        System.out.println();
        System.out.println("Which is the correct answer? ");
        System.out.println();
        System.out.println("Enter ");
        System.out.println("A for - " + firstAnswer);
        System.out.println("OR");
        System.out.println("B for - " + secondAnswer);
        System.out.println("OR");
        System.out.println("C for - " + thirdAnswer);
        System.out.println("OR");
        System.out.println("D for - " + fourthAnswer);
        String solutions = scanner.next();
        return menu.lowercase(solutions);
    }

    private boolean rightAnswer(String solution){
        return solution.equals("a") || solution.equals("b") || solution.equals("c") || solution.equals("d");
    }


    public void viewAllQuestions(Integer displayID){
        try {
            String getAllQuestionsQuery = "SELECT  questions.*, topics.idtopics, topics.topic FROM questions, topics WHERE questions.idtopics = topics.idtopics AND topics.status = 1";
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery(getAllQuestionsQuery);
            int number = 1;
            if(results.next() && displayID == 1) {
                System.out.println("Enter: ");
            }
            do{
                String question = results.getString(4);
                int questionID = results.getInt(1);
                String qType = results.getString(5);
                int questionDifficulty = results.getInt(3);
                String topic = results.getString(7);
                if(displayID == 1) {
                    System.out.println("(" + questionID + "). Topic: " + topic + " | Q: " + question + " | Qtype: " + qType + "| Difficulty: " + questionDifficulty);
                }else {
                    System.out.println(number + "). Topic: " + topic + " | Q: " + question + " | Qtype: " + qType + "| Difficulty: " + questionDifficulty);
                }
                number += 1;
            }while (results.next());

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void getAndUpdateQuestion(Integer questionId){
        int difficulty=0, newDifficulty=0;
        String questionValue = "", questionVal= "", newQuestion="";
        try {
            String getTopicId = "SELECT * FROM questions WHERE idquestions = ?";
            PreparedStatement checkQuestionStatement = connection.prepareStatement(getTopicId);
            checkQuestionStatement.setInt(1, questionId);
            ResultSet topic = checkQuestionStatement.executeQuery();
            while (topic.next()) {
                difficulty = topic.getInt(3);
                questionValue = topic.getString(4);
            }

            System.out.println("Question : " + questionValue + "\nDifficulty : " + difficulty);

            System.out.println("Enter : \n-1 To Edit only the Question");
            System.out.println("-2 To Edit only the Difficulty");

            System.out.println("");
            System.out.println("Please Enter either 1 or 2 ONLY! ");
            String updateQuestionQuery = "", newTopic = "";

            try {
                int updateQuestionChoice = scanner.nextInt();
                if(updateQuestionChoice==1){
                    updateQuestionQuery = "UPDATE questions SET question =? WHERE idquestions = ? ";
                    System.out.println("Enter a new Question ");
                    scanner.nextLine();
                    newQuestion = scanner.nextLine();
                }else if(updateQuestionChoice==2){
                    updateQuestionQuery = "UPDATE questions SET difficulty =? WHERE idquestions = ? ";
                    System.out.println("Enter a new Difficulty Between 1 and 3 ");
                    try {
                        newDifficulty = scanner.nextInt();
                    }catch (InputMismatchException e) {
                        System.out.println("Returning to Main Menu...");
                        menu.mainMenu();
                    }
                }

//                Query to update starts

                try {
                    PreparedStatement updateQuestionStatement = connection.prepareStatement(updateQuestionQuery, Statement.RETURN_GENERATED_KEYS);
                    updateQuestionStatement.setInt(2, questionId);
                    if(updateQuestionChoice==1){
                        updateQuestionStatement.setString(1, newQuestion);
                    }else if(updateQuestionChoice==2){
                        updateQuestionStatement.setInt(1, newDifficulty);
                    }
                    int updateQuestion = updateQuestionStatement.executeUpdate();
                    if (updateQuestion > 0) {
                        System.out.println("Question updated successfully!");
                        System.out.println("");
                        System.out.println("");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }

//                Query to update ends

            } catch (InputMismatchException e) {
                menu.exitInvalidEntry();
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void searchQuestions(){
        System.out.println("Enter a Topic to search for it's associated Questions: ");
        String topicQuestion = scanner.nextLine();
        try {
            String getAllSearchedQuestionsQuery = "SELECT  questions.*, topics.idtopics, topics.topic FROM questions, topics WHERE topics.topic LIKE ? AND (questions.idtopics = topics.idtopics AND topics.status = 1)";
            PreparedStatement preparedStatementSearchQuestion = connection.prepareStatement(getAllSearchedQuestionsQuery);
            preparedStatementSearchQuestion.setString(1, topicQuestion);
            ResultSet resultsQuestions = preparedStatementSearchQuestion.executeQuery();
            if (resultsQuestions.next()) {
                int number = 1;
                System.out.println("Please wait \nSearching... ");
                System.out.println("");
                System.out.println("");
                do {
                    String qType = resultsQuestions.getString(5);
                    String question = resultsQuestions.getString(4);
                    String topic = resultsQuestions.getString(7);
                    int questionDifficulty = resultsQuestions.getInt(3);
                    System.out.println(number + "). Topic: " + topic + " | Q: " + question + " | Qtype: " + qType + "| Difficulty: " + questionDifficulty);
                    number += 1;
                }
                while (resultsQuestions.next());
                System.out.println("");
                System.out.println("");
                number-=1;
                System.out.println("Your Searched return "+ number +" result(s)");
            }else{
                System.out.println("Your Searched return 0 result");
                System.out.println("");
                System.out.println("");
                System.out.println("Searched Topic has Questions associated with it");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    public void updateQuestion(){
        System.out.println("Select the Question you want to edit");
        System.out.println("");
        System.out.println("");
        viewAllQuestions(1);

        int counts = 0;
        while (true) {
            try {
                int idQuestion = scanner.nextInt();
                if(checkQuestion(idQuestion)){
                    getAndUpdateQuestion(idQuestion);
                    break;
                }else{
                    System.out.println("Question does not Exists!");
                    Menu menu = new Menu();
                    menu.mainMenu();
                }
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
