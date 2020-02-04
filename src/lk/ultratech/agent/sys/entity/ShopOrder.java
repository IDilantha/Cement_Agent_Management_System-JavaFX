package lk.ultratech.agent.sys.entity;

import java.sql.Date;

public class ShopOrder {
    private String shopOrderId;
    private String shopId;
    private String type;
    private Date oDate;
    private Date dDate;
    private int qty;

    public ShopOrder() {
    }

    public ShopOrder(String shopOrderId, String shopId, String type, Date oDate, Date dDate, int qty) {
        this.shopOrderId = shopOrderId;
        this.shopId = shopId;
        this.type = type;
        this.oDate = oDate;
        this.dDate = dDate;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getoDate() {
        return oDate;
    }

    public void setoDate(Date oDate) {
        this.oDate = oDate;
    }

    public Date getdDate() {
        return dDate;
    }

    public void setdDate(Date dDate) {
        this.dDate = dDate;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    @Override
    public String toString() {
        return "ShopOrder{" +
                "shopOrderId='" + shopOrderId + '\'' +
                ", shopId='" + shopId + '\'' +
                ", type='" + type + '\'' +
                ", oDate=" + oDate +
                ", dDate=" + dDate +
                ", qty=" + qty +
                '}';
    }
}
