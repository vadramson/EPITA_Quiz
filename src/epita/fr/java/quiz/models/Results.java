package epita.fr.java.quiz.models;

public class Results {
    private int idResults;
    private Register idRegister;
    private Quiz idQuiz;
    private Integer quizScore;


    public Results(int idResults, Register idRegister, Quiz idQuiz, Integer quizScore) {
        this.idResults = idResults;
        this.idRegister = idRegister;
        this.idQuiz = idQuiz;
        this.quizScore = quizScore;
    }


    public int getIdResults() {
        return idResults;
    }

    public void setIdResults(int idResults) {
        this.idResults = idResults;
    }

    public Register getIdRegister() {
        return idRegister;
    }

    public void setIdRegister(Register idRegister) {
        this.idRegister = idRegister;
    }

    public Quiz getIdQuiz() {
        return idQuiz;
    }

    public void setIdQuiz(Quiz idQuiz) {
        this.idQuiz = idQuiz;
    }

    public Integer getQuizScore() {
        return quizScore;
    }

    public void setQuizScore(Integer quizScore) {
        this.quizScore = quizScore;
    }

    @Override
    public String toString() {
        return "Results{" +
                "idResults=" + idResults +
                ", idRegister=" + idRegister +
                ", idQuiz=" + idQuiz +
                ", quizScore=" + quizScore +
                '}';
    }
}
