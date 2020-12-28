package epita.fr.java.quiz.models;

public class Mcqanswers {

    private int idMCQAnswers;
    private Questions idQuestions;
    private String a;
    private String b;
    private String c;
    private String d;
    private String solution;


    public int getIdMCQAnswers() {
        return idMCQAnswers;
    }

    public void setIdMCQAnswers(int idMCQAnswers) {
        this.idMCQAnswers = idMCQAnswers;
    }

    public Questions getIdQuestions() {
        return idQuestions;
    }

    public void setIdQuestions(Questions idQuestions) {
        this.idQuestions = idQuestions;
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    public String getC() {
        return c;
    }

    public void setC(String c) {
        this.c = c;
    }

    public String getD() {
        return d;
    }

    public void setD(String d) {
        this.d = d;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    public Mcqanswers(int idMCQAnswers, Questions idQuestions, String a, String b, String c, String d, String solution) {
        this.idMCQAnswers = idMCQAnswers;
        this.idQuestions = idQuestions;
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.solution = solution;
    }

    @Override
    public String toString() {
        return "Mcqanswers{" +
                "idMCQAnswers=" + idMCQAnswers +
                ", idQuestions=" + idQuestions +
                ", a='" + a + '\'' +
                ", b='" + b + '\'' +
                ", c='" + c + '\'' +
                ", d='" + d + '\'' +
                ", solution='" + solution + '\'' +
                '}';
    }
}
