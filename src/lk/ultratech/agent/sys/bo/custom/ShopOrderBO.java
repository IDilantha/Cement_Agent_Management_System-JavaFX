package lk.ultratech.agent.sys.bo.custom;

import lk.ultratech.agent.sys.bo.SuperBO;
import lk.ultratech.agent.sys.dto.ShopOrderDTO;

import java.sql.SQLException;
import java.util.List;

public interface ShopOrderBO extends SuperBO {
    String getLastShopOrderId() throws SQLException, ClassNotFoundException;
    boolean saveShopOrder(ShopOrderDTO shopOrderDTO) throws SQLException, ClassNotFoundException;
    List<ShopOrderDTO> getAllShopOrders() throws SQLException, ClassNotFoundException;
    List<ShopOrderDTO> getAllShopOrdersByType(String type) throws SQLException, ClassNotFoundException;
    boolean deleteShopOrder(String shopId) throws SQLException, ClassNotFoundException;
}
