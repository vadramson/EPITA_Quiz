package epita.fr.java.quiz.models;

public class Quiz {
    private int idQuiz;
    private String quizCode;


    public int getIdQuiz() {
        return idQuiz;
    }

    public void setIdQuiz(int idQuiz) {
        this.idQuiz = idQuiz;
    }

    public String getQuizCode() {
        return quizCode;
    }

    public void setQuizCode(String quizCode) {
        this.quizCode = quizCode;
    }

    public Quiz(int idQuiz, String quizCode) {
        this.idQuiz = idQuiz;
        this.quizCode = quizCode;
    }

    @Override
    public String toString() {
        return "Quiz{" +
                "idQuiz=" + idQuiz +
                ", quizCode='" + quizCode + '\'' +
                '}';
    }
}
