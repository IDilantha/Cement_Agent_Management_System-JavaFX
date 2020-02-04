package lk.ultratech.agent.sys.dto;

import java.sql.Date;

public class ShopPaymentDTO {
    private String shopPaymentId;
    private String shopId;
    private String type;
    private Date date;
    private double amount;

    public ShopPaymentDTO() {
    }

    public ShopPaymentDTO(String shopPaymentId, String shopId, String type, Date date, double amount) {
        this.shopPaymentId = shopPaymentId;
        this.shopId = shopId;
        this.type = type;
        this.date = date;
        this.amount = amount;
    }

    public String getShopPaymentId() {
        return shopPaymentId;
    }

    public void setShopPaymentId(String shopPaymentId) {
        this.shopPaymentId = shopPaymentId;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
