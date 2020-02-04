package lk.ultratech.agent.sys.tm;

import java.sql.Date;

public class ShopOrderOnDeliveryTM {
    private String shopOrderId;
    private String shopId;
    private String shopName;
    private String type;
    private Date dueDate;
    private int qty;
    private double unitPrice;

    public ShopOrderOnDeliveryTM() {
    }

    public ShopOrderOnDeliveryTM(String shopOrderId, String shopId, String shopName, String type, Date dueDate, int qty, double unitPrice) {
        this.shopOrderId = shopOrderId;
        this.shopId = shopId;
        this.shopName = shopName;
        this.type = type;
        this.dueDate = dueDate;
        this.qty = qty;
        this.unitPrice = unitPrice;
    }

    public String getShopOrderId() {
        return shopOrderId;
    }

    public void setShopOrderId(String shopOrderId) {
        this.shopOrderId = shopOrderId;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }
}
