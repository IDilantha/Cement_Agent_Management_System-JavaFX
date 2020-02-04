package lk.ultratech.agent.sys.entity;

public class CementShop implements SuperEntity {
    private CementShopPK cementShopPK;
    private double unitPrice;

    public CementShop() {
    }

    public CementShop(CementShopPK cementShopPK, double unitPrice) {
        this.cementShopPK = cementShopPK;
        this.unitPrice = unitPrice;
    }
    public CementShop(String cementId, String shopId, double unitPrice){
        this.cementShopPK = new CementShopPK(cementId,shopId);
        this.unitPrice = unitPrice;
    }

    @Override
    public String toString() {
        return "CementShop{" +
                "cementShopPK=" + getCementShopPK() +
                ", unitPrice=" + getUnitPrice() +
                '}';
    }

    public CementShopPK getCementShopPK() {
        return cementShopPK;
    }

    public void setCementShopPK(CementShopPK cementShopPK) {
        this.cementShopPK = cementShopPK;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }
}
