import Epita.fr.BankAccounts.Entities.*;
import Epita.fr.Services.CustomerService;
import Epita.fr.Services.InterestServices;
import Epita.fr.Services.StockOrderService;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Banking App with Scanner");

        Customer customer = new Customer();
        System.out.println("Enter Customer's First Name: ");
        String fname = scanner.next();
        customer.setFirstName(fname);

        System.out.println("Enter Customer's Last Name: ");
        String lname = scanner.next();
        customer.setLastName(lname);

        System.out.println("Enter Customer's Address: ");
        String cusAddress = scanner.next();
        customer.setAddress(cusAddress);


        InvestmentAccounts investmentAccounts = new InvestmentAccounts();
        SavingsAccounts savingsAccounts = new SavingsAccounts();
        BankAccounts bankAccounts = new BankAccounts();
        Stocks stocks = new Stocks();

        System.out.println("Enter The unit price for your desired Stock: ");
        Double stockPrice = scanner.nextDouble();

        stocks.setUnitPrice(stockPrice);
        System.out.println("Your investment Account Balance is: ");
        investmentAccounts.setBalance(5000.0);
        System.out.println("5000.0");
        System.out.println("Your Saving Account interest rate is: ");
        savingsAccounts.setInterestRate(0.075);
        System.out.println("0.075");
//        savingsAccounts.setBalance(2200000000.05);
        System.out.println("Your investment Account Balance is: ");
        savingsAccounts.setBalance(2000.05);
        System.out.println("2000.05");


        StockOrderService stockOrderService = new StockOrderService();
        InterestServices interestServices = new InterestServices();

        CustomerService customerService = new CustomerService();

        System.out.println("Customer Info next: ");
        customerService.customerDetail(customer);

        System.out.println("Stock Purchase next: ");
        stockOrderService.calculateStockOrder(stocks,investmentAccounts,5);

        System.out.println("Savings Account Interest rate Calculation next: ");
        interestServices.computeInterest(savingsAccounts, 5);

        System.out.println("Savings Account withdrawal next: ");
        interestServices.withdraw(savingsAccounts, 500.02);

    }
}
