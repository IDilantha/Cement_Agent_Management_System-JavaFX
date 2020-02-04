package lk.ultratech.agent.sys.dto;

public class ShopDTO {
    private String shopId;
    private String shopName;
    private String address;
    private int telephone;

    public ShopDTO() {
    }

    public ShopDTO(String shopId, String shopName, String address, int telephone) {
        this.shopId = shopId;
        this.shopName = shopName;
        this.address = address;
        this.telephone = telephone;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getTelephone() {
        return telephone;
    }

    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }
}
