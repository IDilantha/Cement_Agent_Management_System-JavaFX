package lk.ultratech.agent.sys.tm;

import java.sql.Date;

public class ShopOrderTM {
    private String shopOrderId;
    private String shopId;
    private String shopName;
    private String type;
    private Date orderDate;
    private Date dueDate;
    private int qty;

    public ShopOrderTM() {
    }

    public ShopOrderTM(String shopOrderId, String shopId, String shopName, String type, Date orderDate, Date dueDate, int qty) {
        this.shopOrderId = shopOrderId;
        this.shopId = shopId;
        this.shopName = shopName;
        this.type = type;
        this.orderDate = orderDate;
        this.dueDate = dueDate;
        this.qty = qty;
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

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
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
}
