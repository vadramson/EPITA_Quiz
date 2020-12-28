package epita.fr.java.quiz.models;

public class Questions {
    private int idQuestions;
    private Topics idTopics;
    private Integer difficulty;
    private String question;
    private String questionType;

    public int getIdQuestions() {
        return idQuestions;
    }

    public void setIdQuestions(int idQuestions) {
        this.idQuestions = idQuestions;
    }

    public Topics getIdTopics() {
        return idTopics;
    }

    public void setIdTopics(Topics idTopics) {
        this.idTopics = idTopics;
    }

    public Integer getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Integer difficulty) {
        this.difficulty = difficulty;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getQuestionType() {
        return questionType;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }


    public Questions(int idQuestions, Topics idTopics, Integer difficulty, String question, String questionType) {
        this.idQuestions = idQuestions;
        this.idTopics = idTopics;
        this.difficulty = difficulty;
        this.question = question;
        this.questionType = questionType;
    }

    @Override
    public String toString() {
        return "Questions{" +
                "idQuestions=" + idQuestions +
                ", idTopics=" + idTopics +
                ", difficulty=" + difficulty +
                ", question='" + question + '\'' +
                ", questionType='" + questionType + '\'' +
                '}';
    }
}
