import epita.fr.logger.IamLog;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        IamLog iamLog = new IamLog();

        iamLog.log("Hello, Is it me you are looking for?");
        iamLog.info("Cause I wonder where you are.");
        iamLog.warn("And  I wonder what you do.");
        iamLog.error("I am somewhere feeling lonely :(");
        iamLog.debug(" שְׁמַע יִשְׂרָאֵל");
    }
}
