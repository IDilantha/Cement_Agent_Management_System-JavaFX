package lk.ultratech.agent.sys.tm;

import java.sql.Date;

public class PurchaseOrderTM {
    private String POID;
    private String orderDate;
    private String type;
    private int qty;
    private double amount;
    private String chequeNo;
    private String dueDate;

    public PurchaseOrderTM() {
    }

    public PurchaseOrderTM(String POID, Date odDate, String type, int qty, double amount, String chequeNo, Date ddDate) {
        this.POID = POID;
        this.orderDate = String.valueOf(odDate);
        this.type = type;
        this.qty = qty;
        this.amount = amount;
        this.chequeNo = chequeNo;
        this.dueDate = String.valueOf(ddDate);
    }



    public String getPOID() { return POID; }

    public void setPOID(String POID) {
        this.POID = POID;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = String.valueOf(orderDate);
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

    public String getChequeNo() {
        return chequeNo;
    }

    public void setChequeNo(String chequeNo) {
        this.chequeNo = chequeNo;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = String.valueOf(dueDate);
    }
}
