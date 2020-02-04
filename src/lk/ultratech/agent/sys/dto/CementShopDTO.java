package lk.ultratech.agent.sys.dto;

public class CementShopDTO {
    private String cementId;
    private String shopId;
    private double unitPrice;

    public CementShopDTO() {
    }

    public CementShopDTO(String cementId, String shopId, double unitPrice) {
        this.cementId = cementId;
        this.shopId = shopId;
        this.unitPrice = unitPrice;
    }

    public String getCementId() {
        return cementId;
    }

    public void setCementId(String cementId) {
        this.cementId = cementId;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }
}
