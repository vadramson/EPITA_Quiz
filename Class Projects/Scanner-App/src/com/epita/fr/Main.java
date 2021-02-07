package com.epita.fr;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try { //this is a try-catch block, we will discuss it further
            System.out.println("Enter an Integer");
            int num1 = scanner.nextInt();
            System.out.println("Input 1 accepted");
            int num2 = scanner.nextInt();
            System.out.println("Input 2 accepted");
        } catch (InputMismatchException e) {
            System.out.println("Invalid Entry");
        }
        scanner.close();
    }
}
