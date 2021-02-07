package factoria.com.epita.fr;

import java.util.Scanner;

public class Factorial {

    public int forFac(){
        Scanner scanner = new Scanner(System.in);
        System.out.println(" *** Factorial Calculation with ForLoop*** ");
        System.out.println("  ");
        System.out.println("Enter the number you want to calculate it's Factorial: ");
        int fac = 1;
        int factorial = scanner.nextInt();
        for(int i = factorial; i > 0; i--){
            fac = fac * i;
        }
        System.out.println(" factorial is: " + fac);
//        scanner.close();
        return fac;
    }

    public  void whileFac(){
        Scanner scanner = new Scanner(System.in);
        System.out.println(" END ");
        System.out.println("  ");
        System.out.println(" *** Factorial Calculation with While Loop*** ");
        System.out.println("  ");
        System.out.println("Enter the number you want to calculate it's Factorial: ");
        int fac = 1;
        int i = scanner.nextInt();
        while (i  > 0){
            fac = fac * i;
            i -= 1;
        }
        System.out.println(" factorial is: " + fac);
//        scanner.close();
    }

    static int recurFac(int factorial){
            if (factorial == 0)
                return 1;
            else
                return(factorial * recurFac(factorial-1));
        }


    public  int recursionFac() {
        Scanner scanner = new Scanner(System.in);
        System.out.println(" END ");
        System.out.println("  ");
        System.out.println(" *** Factorial Calculation with Recursion *** ");
        System.out.println("  ");
        int fact = 1;
        System.out.println("Enter the number you want to calculate it's Factorial: ");
        int factorial = scanner.nextInt();
        fact = recurFac(factorial);
        scanner.close();
        System.out.println(" factorial is: " + fact);
        return fact;
    }

          }
