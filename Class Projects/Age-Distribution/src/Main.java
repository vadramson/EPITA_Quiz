import Service.MyService;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        System.out.println("Hello World!");
        MyService myService = new MyService();
        myService.getCSV_Data();
    }
}
