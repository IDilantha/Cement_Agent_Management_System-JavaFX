package lk.ultratech.agent.sys.entity;

public class CementShopPK {
    private String cementId;
    private String shopId;

    public CementShopPK() {
    }

    public CementShopPK(String cementId, String shopId) {
        this.cementId = cementId;
        this.shopId = shopId;
    }

    @Override
    public String toString() {
        return "CementShopPK{" +
                "cementId='" + getCementId() + '\'' +
                ", shopId='" + getShopId() + '\'' +
                '}';
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
