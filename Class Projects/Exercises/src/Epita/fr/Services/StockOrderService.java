package Epita.fr.Services;

import Epita.fr.BankAccounts.Entities.InvestmentAccounts;
import Epita.fr.BankAccounts.Entities.Stocks;
import Epita.fr.BankAccounts.Entities.Trade;

public class StockOrderService {

    public Trade calculateStockOrder(Stocks stocks, InvestmentAccounts investmentAccounts, Integer quantity){
        Trade trade = new Trade();

        trade.setInvestmentAccounts(investmentAccounts);
        trade.setStocks(stocks);

        Double totalAmount = stocks.getUnitPrice() * quantity;
        Double newBalance = investmentAccounts.getBalance() - totalAmount;

        investmentAccounts.setBalance(newBalance);

        System.out.println("The totalAmount is: " + totalAmount);
        System.out.println("The newBalance is: " + newBalance);

        return trade;
    }


}
