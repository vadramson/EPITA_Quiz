package epita.fr.java.quiz.models;

public class Admincodes {
    private int idAdminCodes;
    private int code;


    public int getIdAdminCodes() {
        return idAdminCodes;
    }

    public void setIdAdminCodes(int idAdminCodes) {
        this.idAdminCodes = idAdminCodes;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Admincodes(int idAdminCodes, int code) {
        this.idAdminCodes = idAdminCodes;
        this.code = code;
    }

    @Override
    public String toString() {
        return "Admincodes{" +
                "idAdminCodes=" + idAdminCodes +
                ", code=" + code +
                '}';
    }
}
