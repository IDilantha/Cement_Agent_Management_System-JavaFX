package lk.ultratech.agent.sys.entity;

public class ShopDeliveryPK {
    private String deliveryId;
    private String shopId;

    public ShopDeliveryPK() {
    }

    public ShopDeliveryPK(String deliveryId, String shopId) {
        this.deliveryId = deliveryId;
        this.shopId = shopId;
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

    @Override
    public String toString() {
        return "ShopDeliveryPK{" +
                "deliveryId='" + deliveryId + '\'' +
                ", shopId='" + shopId + '\'' +
                '}';
    }
}
