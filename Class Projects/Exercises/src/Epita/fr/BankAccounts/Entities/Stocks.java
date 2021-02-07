package Epita.fr.BankAccounts.Entities;

import java.math.BigDecimal;

public class Stocks {
    private Integer stocksId;
    private Integer quantity;
    private Double unitPrice;
    private Double comission;


    public Integer getStocksId() {
        return stocksId;
    }

    public void setStocksId(Integer stocksId) {
        this.stocksId = stocksId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }


    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Double getComission() {
        return comission;
    }

    public void setComission(Double comission) {
        this.comission = comission;
    }
}
