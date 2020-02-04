package lk.ultratech.agent.sys.dao.custom;

import lk.ultratech.agent.sys.dao.CrudDAO;
import lk.ultratech.agent.sys.entity.ChequePayment;
import lk.ultratech.agent.sys.entity.ShopPayment;

import java.sql.SQLException;

public interface ChequePaymentDAO extends CrudDAO<ChequePayment,String> {
    String getLastChequePaymentId() throws SQLException, ClassNotFoundException;

    boolean existsByShopPaymentId(String shopPaymentId) throws SQLException, ClassNotFoundException;

    boolean updateStatus(String status,String chequeId) throws SQLException, ClassNotFoundException;
}
