package epita.fr.java.quiz.models;

public class Register {
    private int idRegister;
    private String studentMatricule;


    public int getIdRegister() {
        return idRegister;
    }

    public void setIdRegister(int idRegister) {
        this.idRegister = idRegister;
    }

    public String getStudentMatricule() {
        return studentMatricule;
    }

    public void setStudentMatricule(String studentMatricule) {
        this.studentMatricule = studentMatricule;
    }

    public Register(int idRegister, String studentMatricule) {
        this.idRegister = idRegister;
        this.studentMatricule = studentMatricule;
    }

    @Override
    public String toString() {
        return "Register{" +
                "idRegister=" + idRegister +
                ", studentMatricule='" + studentMatricule + '\'' +
                '}';
    }
}
