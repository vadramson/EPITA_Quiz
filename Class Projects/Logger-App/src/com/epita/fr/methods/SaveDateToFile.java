package com.epita.fr.methods;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SaveDateToFile {

    public void saveFile(){
        IamLog iamLog = new IamLog();
        File file = new File("./test.log");
        boolean fileExists = file.exists();
        System.out.println("file exists? " + fileExists);

        File parentFile = file.getParentFile();
        System.out.println("is the parent file a directory? : " + parentFile.isDirectory());

        parentFile.mkdirs();

        System.out.println("is the parent file a directory? : " + parentFile.isDirectory());

//        file.createNewFile();

        System.out.println("file exists : " + file.exists());

        if(!fileExists){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            FileWriter fileWriter  =  new FileWriter(file);
//            fileWriter.append("Hello there! ");
            fileWriter.append(iamLog.log("Called from Save Data To File"));
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
