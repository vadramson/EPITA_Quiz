package Epita.fr.Services;

import Epita.fr.BankAccounts.Entities.SavingsAccounts;
import Epita.fr.BankAccounts.Entities.Trade;

public class InterestServices {

    public Trade computeInterest(SavingsAccounts savingsAccounts, Integer duration){

        Trade trade = new Trade();
        Double currentInterest = (savingsAccounts.getInterestRate() / 100) * savingsAccounts.getBalance() * duration;
        System.out.println("The Current interest is: " + currentInterest);

        return trade;
    }

    public Trade withdraw(SavingsAccounts savingsAccounts, Double amount){

        Trade trade = new Trade();
        Double newSavingAccountBalance = savingsAccounts.getBalance() - amount;
        savingsAccounts.setBalance(newSavingAccountBalance);
        System.out.println("The Current Savings Account Balance is: " + newSavingAccountBalance);

        return trade;
    }


}
