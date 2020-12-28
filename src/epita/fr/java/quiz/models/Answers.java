package epita.fr.java.quiz.models;

public class Answers {
    private int idAnswers;
    private Questions idQuestions;
    private String answer;


    public int getIdAnswers() {
        return idAnswers;
    }

    public void setIdAnswers(int idAnswers) {
        this.idAnswers = idAnswers;
    }

    public Questions getIdQuestions() {
        return idQuestions;
    }

    public void setIdQuestions(Questions idQuestions) {
        this.idQuestions = idQuestions;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Answers(int idAnswers, Questions idQuestions, String answer) {
        this.idAnswers = idAnswers;
        this.idQuestions = idQuestions;
        this.answer = answer;
    }

    @Override
    public String toString() {
        return "Answers{" +
                "idAnswers=" + idAnswers +
                ", idQuestions=" + idQuestions +
                ", answer='" + answer + '\'' +
                '}';
    }


}
