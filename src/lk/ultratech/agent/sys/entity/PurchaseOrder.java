package lk.ultratech.agent.sys.entity;

import java.sql.Date;

public class PurchaseOrder {
    private String purchaseOrderId;
    private String agentId;
    private String cementId;
    private Date oDate;
    private int qty;
    private String chequeNumber;
    private Date dDate;
    private double amount;

    public PurchaseOrder() {
    }

    public PurchaseOrder(String purchaseOrderId, String agentId, String cementId, Date oDate, int qty, String chequeNumber, Date dDate, double amount) {
        this.purchaseOrderId = purchaseOrderId;
        this.agentId = agentId;
        this.cementId = cementId;
        this.oDate = oDate;
        this.qty = qty;
        this.chequeNumber = chequeNumber;
        this.dDate = dDate;
        this.amount = amount;
    }

    public String getPurchaseOrderId() {
        return purchaseOrderId;
    }

    public void setPurchaseOrderId(String purchaseOrderId) {
        this.purchaseOrderId = purchaseOrderId;
    }

    public String getAgentId() {
        return agentId;
    }

    public void setAgentId(String agentId) {
        this.agentId = agentId;
    }

    public String getCementId() {
        return cementId;
    }

    public void setCementId(String cementId) {
        this.cementId = cementId;
    }

    public Date getoDate() {
        return oDate;
    }

    public void setoDate(Date oDate) {
        this.oDate = oDate;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public String getChequeNumber() {
        return chequeNumber;
    }

    public void setChequeNumber(String chequeNumber) {
        this.chequeNumber = chequeNumber;
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

    @Override
    public String toString() {
        return "PurchaseOrder{" +
                "purchaseOrderId='" + purchaseOrderId + '\'' +
                ", agentId='" + agentId + '\'' +
                ", cementId='" + cementId + '\'' +
                ", oDate=" + oDate +
                ", qty=" + qty +
                ", chequeNumber='" + chequeNumber + '\'' +
                ", dDate=" + dDate +
                ", amount=" + amount +
                '}';
    }
}
