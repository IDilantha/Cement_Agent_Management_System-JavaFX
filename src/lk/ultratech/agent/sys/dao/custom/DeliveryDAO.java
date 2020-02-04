package lk.ultratech.agent.sys.dao.custom;

import lk.ultratech.agent.sys.dao.CrudDAO;
import lk.ultratech.agent.sys.entity.Delivery;

import java.sql.SQLException;

public interface DeliveryDAO extends CrudDAO<Delivery,String> {

    String getLastDeliveryId() throws SQLException, ClassNotFoundException;

    boolean existByPurchaseOrderId(String shopId) throws SQLException, ClassNotFoundException;
}
