package lk.ijse.dep10.se.controller.util;

import java.io.Serializable;
import java.math.BigDecimal;

public class Item implements Serializable {
    private String code;
    private String description;
    private BigDecimal buyingPrice;
    private BigDecimal sellingPrice;
    private int quantity;


    public Item() {
    }

    public Item(String code, String description, BigDecimal buyingPrice, BigDecimal sellingPrice, int quantity) {
        this.code = code;
        this.description = description;
        this.buyingPrice = buyingPrice;
        this.sellingPrice = sellingPrice;
        this.quantity =quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getBuyingPrice() {
        return buyingPrice.setScale(2);
    }

    public void setBuyingPrice(BigDecimal buyingPrice) {
        this.buyingPrice = buyingPrice;
    }

    public BigDecimal getSellingPrice() {
        return sellingPrice.setScale(2);
    }

    public void setSellingPrice(BigDecimal sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public String getCode() {
        return code;

    }
    public BigDecimal getProfit(){
        return sellingPrice.subtract(buyingPrice).setScale(2);

    }
    public BigDecimal getTotal(){   // total buying price
        return buyingPrice.multiply(new BigDecimal(quantity)).setScale(2);

    }
    public BigDecimal getTotalProfit(){
        return getProfit().multiply(new BigDecimal(quantity).setScale(2));  // we can use scale to add floating points to BigDecimal
    }
}
