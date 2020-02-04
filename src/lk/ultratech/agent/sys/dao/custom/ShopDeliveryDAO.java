package lk.ultratech.agent.sys.dao.custom;

import lk.ultratech.agent.sys.dao.CrudDAO;
import lk.ultratech.agent.sys.entity.ShopDelivery;
import lk.ultratech.agent.sys.entity.ShopDeliveryPK;

import java.sql.SQLException;
import java.util.List;

public interface ShopDeliveryDAO extends CrudDAO<ShopDelivery, ShopDeliveryPK> {
    boolean saveAll(List<ShopDelivery> entity) throws SQLException, ClassNotFoundException;
    List<ShopDelivery> getAllShopDelivery(String shopId) throws SQLException, ClassNotFoundException;
}
