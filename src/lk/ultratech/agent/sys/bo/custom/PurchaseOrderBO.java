package lk.ultratech.agent.sys.bo.custom;

import lk.ultratech.agent.sys.bo.SuperBO;
import lk.ultratech.agent.sys.dto.PurchaseOrderDTO;

import java.sql.SQLException;
import java.util.List;

public interface PurchaseOrderBO extends SuperBO {
    boolean savePurchaseOrder(PurchaseOrderDTO dto) throws SQLException, ClassNotFoundException;
    String getLastPOId() throws SQLException, ClassNotFoundException;
    List<PurchaseOrderDTO> getAllPO() throws SQLException, ClassNotFoundException;
    List<String> getAllPOId() throws SQLException, ClassNotFoundException;
    PurchaseOrderDTO getPOById(String pOId) throws SQLException, ClassNotFoundException;
}
