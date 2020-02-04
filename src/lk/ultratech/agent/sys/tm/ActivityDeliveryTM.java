package lk.ultratech.agent.sys.tm;

import java.sql.Date;

public class ActivityDeliveryTM {
    private Date deliverDate;
    private String deliveryId;
    private String type;
    private int qty;
    private double amount;

    public ActivityDeliveryTM() {
    }

    public ActivityDeliveryTM(Date deliverDate, String deliveryId, String type, int qty, double amount) {
        this.deliverDate = deliverDate;
        this.deliveryId = deliveryId;
        this.type = type;
        this.qty = qty;
        this.amount = amount;
    }

    public Date getDeliverDate() {
        return deliverDate;
    }

    public void setDeliverDate(Date deliverDate) {
        this.deliverDate = deliverDate;
    }

    public String getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(String deliveryId) {
        this.deliveryId = deliveryId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
