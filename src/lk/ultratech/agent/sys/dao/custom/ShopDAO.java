package lk.ultratech.agent.sys.dao.custom;

import lk.ultratech.agent.sys.dao.CrudDAO;
import lk.ultratech.agent.sys.entity.Shop;

import java.sql.SQLException;
import java.util.List;

public interface ShopDAO extends CrudDAO<Shop,String> {
    String getLastShopId() throws SQLException, ClassNotFoundException;
    Shop getByShopName(String shopName) throws SQLException, ClassNotFoundException;
}
