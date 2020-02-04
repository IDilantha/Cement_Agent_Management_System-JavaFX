package lk.ultratech.agent.sys.bo.custom;

import lk.ultratech.agent.sys.bo.SuperBO;
import lk.ultratech.agent.sys.dto.ShopDTO;
import lk.ultratech.agent.sys.entity.Shop;

import java.sql.SQLException;
import java.util.List;


public interface ShopBO extends SuperBO {
    boolean saveShop(ShopDTO entity) throws SQLException, ClassNotFoundException;
    boolean deleteShop(String s) throws SQLException, ClassNotFoundException;
    boolean updateShop(ShopDTO entity) throws SQLException, ClassNotFoundException;
    ShopDTO getShopById(String s) throws SQLException, ClassNotFoundException;
    List<ShopDTO> getAllShops() throws SQLException, ClassNotFoundException;
    List<String> getAllShopNames() throws SQLException, ClassNotFoundException;
    String getLastShopId() throws SQLException, ClassNotFoundException;
    ShopDTO getByShopName(String shopName) throws SQLException, ClassNotFoundException;
}
