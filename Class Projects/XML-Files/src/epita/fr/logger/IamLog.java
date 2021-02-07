package epita.fr.logger;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class IamLog {

    // enum to set the desired Log Levels
    public enum Levels {
        INFO, WARN, DEBUG, ERROR, NORMAL
    }

    // Construction of a DataTime component to be used later in methods
    public String getDateTime() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY/mm/dd - HH:mm:ss.SSSS");
        Date date = new Date();
        return simpleDateFormat.format(date);
    }

    // Check if a log file exists, if not create one
    // Prepare file for writing and do the actual writing to the file using the given params

    public void fileWriting(String msg, Levels levels) {
//      Create a new File object to later create log files directory
        File fileDirectory = new File("./Logs");

//      Test if file exists if not create one
        boolean fileDirectotyExists = fileDirectory.exists();
        if (!fileDirectotyExists) {
            fileDirectory.mkdir();
        }

        String vad = "VadsonBobby";

        File file = new File("./Logs/"+vad+".txt");

//      Test if file exists if not create one
        boolean fileExists = file.exists();
        if (!fileExists) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

//        Begin writing log into file
        try {

//            Log content message
            String content = getDateTime() + " :  [" + levels + "] " + msg;

//            Actual writing into Log file
            FileWriter fileWriter = new FileWriter(file, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            PrintWriter printWriter = new PrintWriter(bufferedWriter);
            printWriter.println(content);
            printWriter.println(content);
            printWriter.println(content);
            printWriter.println(content);
            printWriter.println(content);
            printWriter.flush();

//            printWriter.close();
//            bufferedWriter.close();
//            fileWriter.close();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

        public void log (String message){
            fileWriting(message, Levels.NORMAL);
        }

        public void info (String message){
            fileWriting(message, Levels.INFO);
        }

        public void warn (String message){
            fileWriting(message, Levels.WARN);
        }

        public void error (String message){
            fileWriting(message, Levels.ERROR);
        }

        public void debug (String message){
            fileWriting(message, Levels.DEBUG);
        }


    }


