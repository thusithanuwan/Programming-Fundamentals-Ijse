package lk.ijse.dep10.app.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Blob;

public class Item implements Serializable {
    private int code;
    private String description;
    private BigDecimal sellingPrice;
    private BigDecimal buyingPrice;
    private int stock;
    private Blob picture;

    public Item() {
    }

    public Item(int code, String description, BigDecimal sellingPrice, BigDecimal buyingPrice, int stock, Blob picture) {
        this.code = code;
        this.description = description;
        this.sellingPrice = sellingPrice;
        this.buyingPrice = buyingPrice;
        this.stock = stock;
        this.picture = picture;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(BigDecimal sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public BigDecimal getBuyingPrice() {
        return buyingPrice;
    }

    public void setBuyingPrice(BigDecimal buyingPrice) {
        this.buyingPrice = buyingPrice;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Blob getPicture() {
        return picture;
    }

    public void setPicture(Blob picture) {
        this.picture = picture;
    }
    public BigDecimal getProfit(){
        return sellingPrice.subtract(buyingPrice).multiply(new BigDecimal(stock));
    }
}
