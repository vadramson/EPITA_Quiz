package Epita.fr.BankAccounts.Entities;

public class SavingsAccounts extends  BankAccounts{
    private  Integer savingsAccountsID;
    private Double interestRate;
    private Double balance;


    public Integer getSavingsAccountsID() {
        return savingsAccountsID;
    }

    public void setSavingsAccountsID(Integer savingsAccountsID) {
        this.savingsAccountsID = savingsAccountsID;
    }

    public Double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(Double interestRate) {
        this.interestRate = interestRate;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }
}
