package lk.ultratech.agent.sys.dao.custom;

import lk.ultratech.agent.sys.dao.CrudDAO;
import lk.ultratech.agent.sys.entity.ShopOrder;

import java.sql.SQLException;
import java.util.List;

public interface ShopOrderDAO extends CrudDAO<ShopOrder,String> {
    String getLastShopOrderId() throws SQLException, ClassNotFoundException;
    List<ShopOrder> getAllByType(String type) throws SQLException, ClassNotFoundException;
    boolean existsByShopId(String shopId) throws SQLException, ClassNotFoundException;
}
