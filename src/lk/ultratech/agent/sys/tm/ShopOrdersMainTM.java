package lk.ultratech.agent.sys.tm;

import java.sql.Date;

public class ShopOrdersMainTM {
    private String shopName;
    private String type;
    private Date orderDueDate;
    private int qty;

    public ShopOrdersMainTM() {
    }

    public ShopOrdersMainTM(String shopName, String type, Date orderDueDate, int qty) {
        this.shopName = shopName;
        this.type = type;
        this.orderDueDate = orderDueDate;
        this.qty = qty;
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

    public Date getOrderDueDate() {
        return orderDueDate;
    }

    public void setOrderDueDate(Date orderDueDate) {
        this.orderDueDate = orderDueDate;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }
}
