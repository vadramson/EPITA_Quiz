package Epita.fr.BankAccounts.Entities;

public class Trade {

    private InvestmentAccounts tradeID;
    private InvestmentAccounts investmentAccounts;
    private Stocks stocks;


    public InvestmentAccounts getTradeID() {
        return tradeID;
    }

    public void setTradeID(InvestmentAccounts tradeID) {
        this.tradeID = tradeID;
    }

    public InvestmentAccounts getInvestmentAccounts() {
        return investmentAccounts;
    }

    public void setInvestmentAccounts(InvestmentAccounts investmentAccounts) {
        this.investmentAccounts = investmentAccounts;
    }

    public Stocks getStocks() {
        return stocks;
    }

    public void setStocks(Stocks stocks) {
        this.stocks = stocks;
    }
}
