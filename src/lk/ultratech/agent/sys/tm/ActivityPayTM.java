package lk.ultratech.agent.sys.tm;

import java.sql.Date;

public class ActivityPayTM {
    private Date paidDate;
    private String paymentId;
    private String type;
    private double amount;

    public ActivityPayTM(Date paidDate, String paymentId, String type, double amount) {
        this.paidDate = paidDate;
        this.paymentId = paymentId;
        this.type = type;
        this.amount = amount;
    }

    public ActivityPayTM() {
    }

    public Date getPaidDate() {
        return paidDate;
    }

    public void setPaidDate(Date paidDate) {
        this.paidDate = paidDate;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
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
