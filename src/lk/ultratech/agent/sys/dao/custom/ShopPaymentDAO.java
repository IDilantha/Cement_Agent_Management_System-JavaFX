package lk.ultratech.agent.sys.dao.custom;

import lk.ultratech.agent.sys.dao.CrudDAO;
import lk.ultratech.agent.sys.entity.ShopPayment;

import java.sql.SQLException;
import java.util.List;

public interface ShopPaymentDAO extends CrudDAO<ShopPayment,String> {
    String getLastShopPaymentId() throws SQLException, ClassNotFoundException;

    boolean existsByShopId(String shopId) throws SQLException, ClassNotFoundException;
    List<ShopPayment> getAllShopPayments(String shopId) throws SQLException, ClassNotFoundException;
}

