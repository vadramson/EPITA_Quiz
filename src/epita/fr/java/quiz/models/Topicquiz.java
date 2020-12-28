package epita.fr.java.quiz.models;

public class Topicquiz {
    private int idQuetionQuiz;
    private Quiz idQuiz;
    private Topics idTopics;
    private Integer questionDifficulty;


    public Topicquiz(int idQuetionQuiz, Quiz idQuiz, Topics idTopics, Integer questionDifficulty) {
        this.idQuetionQuiz = idQuetionQuiz;
        this.idQuiz = idQuiz;
        this.idTopics = idTopics;
        this.questionDifficulty = questionDifficulty;
    }

    public int getIdQuetionQuiz() {
        return idQuetionQuiz;
    }

    public void setIdQuetionQuiz(int idQuetionQuiz) {
        this.idQuetionQuiz = idQuetionQuiz;
    }

    public Quiz getIdQuiz() {
        return idQuiz;
    }

    public void setIdQuiz(Quiz idQuiz) {
        this.idQuiz = idQuiz;
    }

    public Topics getIdTopics() {
        return idTopics;
    }

    public void setIdTopics(Topics idTopics) {
        this.idTopics = idTopics;
    }

    public Integer getQuestionDifficulty() {
        return questionDifficulty;
    }

    public void setQuestionDifficulty(Integer questionDifficulty) {
        this.questionDifficulty = questionDifficulty;
    }

    @Override
    public String toString() {
        return "Topicquiz{" +
                "idQuetionQuiz=" + idQuetionQuiz +
                ", idQuiz=" + idQuiz +
                ", idTopics=" + idTopics +
                ", questionDifficulty=" + questionDifficulty +
                '}';
    }
}
