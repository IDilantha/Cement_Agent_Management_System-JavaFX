package lk.ultratech.agent.sys.dao.custom;

import lk.ultratech.agent.sys.dao.CrudDAO;
import lk.ultratech.agent.sys.entity.PurchaseOrder;

import java.sql.SQLException;

public interface PurchaseOrderDAO extends CrudDAO<PurchaseOrder,String> {
    String getLastPOId() throws SQLException, ClassNotFoundException;
}
