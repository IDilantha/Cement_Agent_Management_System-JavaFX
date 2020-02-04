package lk.ultratech.agent.sys.tm;

import java.sql.Date;

public class ChequePaymentTM {
    private String shopId;
    private String shopName;
    private String shopPayId;
    private Date duedate;
    private String chequeNo;
    private double amount;
    private String returned;

    public ChequePaymentTM() {
    }

    public ChequePaymentTM(String shopId, String shopName, String shopPayId, Date duedate, String chequeNo, double amount, String returned) {
        this.shopId = shopId;
        this.shopName = shopName;
        this.shopPayId = shopPayId;
        this.duedate = duedate;
        this.chequeNo = chequeNo;
        this.amount = amount;
        this.returned = returned;
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

    public String getShopPayId() {
        return shopPayId;
    }

    public void setShopPayId(String shopPayId) {
        this.shopPayId = shopPayId;
    }

    public Date getDuedate() {
        return duedate;
    }

    public void setDuedate(Date duedate) {
        this.duedate = duedate;
    }

    public String getChequeNo() {
        return chequeNo;
    }

    public void setChequeNo(String chequeNo) {
        this.chequeNo = chequeNo;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getReturned() {
        return returned;
    }

    public void setReturned(String returned) {
        this.returned = returned;
    }
}
