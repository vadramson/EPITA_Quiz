import Model.Persons;
import Services.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws IOException {

        System.out.println("Hello World!");
        Service service = new Service();

        service.serviceMethods();
    }
}
