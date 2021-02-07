package com.epita.fr.services;

import com.epita.fr.models.Users;

import java.util.Scanner;

public class UserService {

    private static String username = "Vad";
    private static String password = "myPass";

    public boolean authenticateUser(Users users){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Hello Welcome");
        System.out.println("");
        System.out.println("To begin Enter your Username and Password..");
        System.out.println("Enter Your Username: ");
        users.setUsername(scanner.next());
        System.out.println("Enter Your Password: ");
        users.setPassword(scanner.next());
        scanner.close();

        if (username.equals(users.getUsername()) && password.equals(users.getPassword())){
            switching();
            return true;

        }else {
            System.out.println("Ooops... You are not log in");
            return false;
        }
    }

    public void switching(){
        Scanner scanner =  new Scanner(System.in);
        System.out.println("Hey !!! You are logged in");
        System.out.println("");
        System.out.println("");

        System.out.println("What do you want to do?");
        System.out.println("Choose either |C|R|U|D?");

        String choices = scanner.next().toUpperCase();
        switch (choices){
            case "C":
                System.out.println("You Choose Creation ");
                break;
            case "R":
                System.out.println("You Choose Reading ");
                break;
            case "U":
                System.out.println("You Choose Update ");
                break;
            case "D":
                System.out.println("You Choose Deletion ");
                break;
        }
        scanner.close();
    }
}
