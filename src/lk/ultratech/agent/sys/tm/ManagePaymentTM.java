package lk.ultratech.agent.sys.tm;

import java.sql.Date;

public class ManagePaymentTM {
    private String shopId;
    private String shopName;
    private String payId;
    private Date date;
    private String type;
    private double amount;

    public ManagePaymentTM() {
    }

    public ManagePaymentTM(String shopId, String shopName, String payId, Date date, String type, double amount) {
        this.shopId = shopId;
        this.shopName = shopName;
        this.payId = payId;
        this.date = date;
        this.type = type;
        this.amount = amount;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getPayId() {
        return payId;
    }

    public void setPayId(String payId) {
        this.payId = payId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
