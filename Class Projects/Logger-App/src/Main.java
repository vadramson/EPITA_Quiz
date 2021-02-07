import com.epita.fr.methods.IamLog;
import com.epita.fr.methods.SaveDateToFile;
import com.epita.fr.methods.SimpleDates;
import com.epita.fr.methods.TimeZoneDates;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws ParseException {
        System.out.println("Hello World here !");
//       SimpleDates simpleDates = new SimpleDates();
//       simpleDates.simpledate();
//        TimeZoneDates timeZoneDates = new TimeZoneDates();
//        timeZoneDates.localDates();
//        timeZoneDates.moreLocalDates();
        IamLog iamLog = new IamLog();
        iamLog.log("Beginning of the program");

        SaveDateToFile saveDateToFile = new SaveDateToFile();
        saveDateToFile.saveFile();

    }
}
