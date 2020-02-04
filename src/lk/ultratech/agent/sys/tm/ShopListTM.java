package lk.ultratech.agent.sys.tm;

public class ShopListTM {
    private String shopId;
    private String shopName;

    public ShopListTM() {
    }

    public ShopListTM(String shopId, String shopName) {
        this.shopId = shopId;
        this.shopName = shopName;
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
}
