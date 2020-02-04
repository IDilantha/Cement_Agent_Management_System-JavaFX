package lk.ultratech.agent.sys.tm;

import javafx.scene.control.Button;

import java.sql.Date;

public class ShopDeliveryTM {
    private String deliveryId;
    private String shopId;
    private String shopName;
    private String type;
    private int qty;
    private double unitPrice;
    private double amount;
    private Button btnDelete;

    public ShopDeliveryTM() {
    }

    public ShopDeliveryTM(String deliveryId, String shopId, String shopName, String type, int qty, double unitPrice, double amount, Button btnDelete) {
        this.deliveryId = deliveryId;
        this.shopId = shopId;
        this.shopName = shopName;
        this.type = type;
        this.qty = qty;
        this.unitPrice = unitPrice;
        this.amount = amount;
        this.btnDelete = btnDelete;
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

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Button getBtnDelete() {
        return btnDelete;
    }

    public void setBtnDelete(Button btnDelete) {
        this.btnDelete = btnDelete;
    }
}
