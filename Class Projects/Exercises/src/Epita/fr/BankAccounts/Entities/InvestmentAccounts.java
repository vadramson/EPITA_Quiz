package Epita.fr.BankAccounts.Entities;

import java.math.BigDecimal;

public class InvestmentAccounts extends BankAccounts{
    private  Integer investmentAccountsID;
    private Double balance;

    public Integer getInvestmentAccountsID() {
        return investmentAccountsID;
    }

    public void setInvestmentAccountsID(Integer investmentAccountsID) {
        this.investmentAccountsID = investmentAccountsID;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }
}
