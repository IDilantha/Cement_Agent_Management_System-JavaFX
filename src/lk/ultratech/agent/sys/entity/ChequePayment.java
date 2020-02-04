package lk.ultratech.agent.sys.entity;

import java.sql.Date;

public class ChequePayment {
    private String chequePaymentId;
    private String shopPaymentId;
    private String cheuqeNo;
    private Date dDate;
    private double amount;
    private String returned;

    public ChequePayment() {
    }

    public ChequePayment(String chequePaymentId, String shopPaymentId, String cheuqeNo, Date dDate, double amount, String returned) {
        this.chequePaymentId = chequePaymentId;
        this.shopPaymentId = shopPaymentId;
        this.cheuqeNo = cheuqeNo;
        this.dDate = dDate;
        this.amount = amount;
        this.returned = returned;
    }

    @Override
    public String toString() {
        return "ChequePayment{" +
                "chequePaymentId='" + getChequePaymentId() + '\'' +
                ", shopPaymentId='" + getShopPaymentId() + '\'' +
                ", cheuqeNo='" + getCheuqeNo() + '\'' +
                ", dDate=" + getdDate() +
                ", amount=" + getAmount() +
                ", returned='" + getReturned() + '\'' +
                '}';
    }

    public String getChequePaymentId() {
        return chequePaymentId;
    }

    public void setChequePaymentId(String chequePaymentId) {
        this.chequePaymentId = chequePaymentId;
    }

    public String getShopPaymentId() {
        return shopPaymentId;
    }

    public void setShopPaymentId(String shopPaymentId) {
        this.shopPaymentId = shopPaymentId;
    }

    public String getCheuqeNo() {
        return cheuqeNo;
    }

    public void setCheuqeNo(String cheuqeNo) {
        this.cheuqeNo = cheuqeNo;
    }

    public Date getdDate() {
        return dDate;
    }

    public void setdDate(Date dDate) {
        this.dDate = dDate;
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
