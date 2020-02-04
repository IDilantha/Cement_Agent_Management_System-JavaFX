package lk.ultratech.agent.sys.dto;

import java.sql.Date;

public class ShopDeliveryDTO {
    private String deliveryId;
    private String shopId;
    private Date date;
    private String type;
    private int qty;

    public ShopDeliveryDTO() {
    }

    public ShopDeliveryDTO(String deliveryId, String shopId, Date date, String type, int qty) {
        this.deliveryId = deliveryId;
        this.shopId = shopId;
        this.date = date;
        this.type = type;
        this.qty = qty;
    }

    public String getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(String deliveryId) {
        this.deliveryId = deliveryId;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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
}
