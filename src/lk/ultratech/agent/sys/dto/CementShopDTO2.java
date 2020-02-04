package lk.ultratech.agent.sys.dto;

public class CementShopDTO2 {
    private String cementId;
    private String shopId;

    public CementShopDTO2() {
    }

    public CementShopDTO2(String cementId, String shopId) {
        this.cementId = cementId;
        this.shopId = shopId;
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
}
