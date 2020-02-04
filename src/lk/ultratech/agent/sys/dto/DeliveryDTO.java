package lk.ultratech.agent.sys.dto;

import java.sql.Date;

public class DeliveryDTO {
    private String deliveryId;
    private String purchaseOrderId;
    private String type;
    private Date date;

    public DeliveryDTO(String deliveryId, String purchaseOrderId, String type, Date date) {
        this.deliveryId = deliveryId;
        this.purchaseOrderId = purchaseOrderId;
        this.type = type;
        this.date = date;
    }

    public DeliveryDTO() {
    }

    public String getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(String deliveryId) {
        this.deliveryId = deliveryId;
    }

    public String getPurchaseOrderId() {
        return purchaseOrderId;
    }

    public void setPurchaseOrderId(String purchaseOrderId) {
        this.purchaseOrderId = purchaseOrderId;
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
}
