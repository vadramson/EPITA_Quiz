package Services;

import Model.Persons;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Service {

    private static final int VALUE_FOR_FEMALE = 1;
    private static final int VALUE_FOR_MALE = 0;

    public void serviceMethods() throws IOException {
        //        List<String> listOfMyLines = Files.readAllLines(new File("test.log").toPath());
//        System.out.println(listOfMyLines);

        File file = new File("data.csv");
        List<String> listOfLines = Files.readAllLines(file.toPath());
        List<Persons>  person = new ArrayList<>();
        listOfLines.remove(0);
        System.out.println(listOfLines.get(0));
        System.out.println(listOfLines);


        for (String line: listOfLines){
            String[] fields = line.split(",");
            String name = fields[0].trim().replaceAll("\"", "");
            String sex = fields[1].trim().replaceAll("\"", "");
            Integer age = Integer.valueOf(fields[2].trim());
            Integer height = Integer.valueOf(fields[3].trim().replaceAll("\"", ""));
            Integer weight = Integer.valueOf(fields[4].trim().replaceAll("\"", ""));

//            lines.trim();
//            lines.replaceAll("\"", "");
//            System.out.println(lines);

            Persons persons = new Persons(name, sex, age, height, weight);
            person.add(persons);

        }
        System.out.println(person.size());
        System.out.println(person);
//        List<Persons> persons2 = from2dMatrix(to2dMatrix(person));
        List<Persons> persons2 = from2dMatrix(to2dMatrix(person));
    }

    //        TODO   Find a way to encode double so that it is reversible

    private static double[][] t02dMatrixT(List<Persons> person) {
        double[][] results = new double[18][4];
        return results;
    }

    private static double[][] to2dMatrix(List<Persons> person) {
        int size = person.size();
        double[][] results = new double[size][4];
        for (int i = 0; i < size; i++) {
            double[] ds = new double[4];
            double encodedSex = -1;
            Persons currentPerson = person.get(i);
            String currentPersonSex = currentPerson.getSex();
            if ("M".equals(currentPersonSex)) {
                encodedSex = VALUE_FOR_MALE;
            }else if ("F".equals(currentPersonSex)) {
                encodedSex = VALUE_FOR_FEMALE;
            }
            ds[0]= encodedSex;
            ds[1]= currentPerson.getAge();
            ds[2]= currentPerson.getHeight();
            ds[3]= currentPerson.getAge();
            results[i] = ds;
        }
        System.out.println(Arrays.deepToString(results));
        return results;
    }
    private static List<Persons> from2dMatrix(double[][] matrix) {
        List<Persons> persons = new ArrayList<>();

        for (double[] ds : matrix) {


            String decodedSex = "";
            if (VALUE_FOR_MALE == ds[0]) {
                decodedSex = "M";
            }else if (VALUE_FOR_FEMALE == ds[0]) {
                decodedSex = "F";
            }


            Persons currentPerson = new Persons("",decodedSex, Double.valueOf(ds[1]).intValue(),
                    Double.valueOf(ds[2]).intValue(),
                    Double.valueOf(ds[3]).intValue()
                     );
            persons.add(currentPerson);
        }
        System.out.println(persons);
        return persons;
    }




}
