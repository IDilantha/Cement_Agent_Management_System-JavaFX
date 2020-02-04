package lk.ultratech.agent.sys.entity;

import java.sql.Date;

public class ShopDelivery {
    private ShopDeliveryPK shopDeliveryPK;
    private String type;
    private Date date;
    private int qty;

    public ShopDelivery() {
    }

    public ShopDelivery(ShopDeliveryPK shopDeliveryPK, String type, Date date, int qty) {
        this.shopDeliveryPK = shopDeliveryPK;
        this.type = type;
        this.date = date;
        this.qty = qty;
    }

    public ShopDelivery(String deliveryId,String shopId, String type, Date date, int qty) {
        this.shopDeliveryPK = new ShopDeliveryPK(deliveryId,shopId);
        this.type = type;
        this.date = date;
        this.qty = qty;
    }

    public ShopDeliveryPK getShopDeliveryPK() {
        return shopDeliveryPK;
    }

    public void setShopDeliveryPK(ShopDeliveryPK shopDeliveryPK) {
        this.shopDeliveryPK = shopDeliveryPK;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    @Override
    public String toString() {
        return "ShopDelivery{" +
                "shopDeliveryPK=" + shopDeliveryPK +
                ", type='" + type + '\'' +
                ", date=" + date +
                ", qty=" + qty +
                '}';
    }
}
