package lk.ultratech.agent.sys.dao.custom.impl;

import lk.ultratech.agent.sys.dao.CrudUtil;
import lk.ultratech.agent.sys.dao.custom.PurchaseOrderDAO;
import lk.ultratech.agent.sys.entity.PurchaseOrder;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PurchaseOrderDAOImpl implements PurchaseOrderDAO {
    @Override
    public boolean save(PurchaseOrder entity) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("INSERT INTO PurchaseOrder VALUES (?,?,?,?,?,?,?,?)",entity.getPurchaseOrderId(),entity.getAgentId(),entity.getCementId(),entity.getoDate(),entity.getQty(),entity.getChequeNumber(),entity.getdDate(),entity.getAmount());
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("DELETE FROM PurchaseOrder WHERE purchaseOrderId=?",s);
    }

    @Override
    public boolean update(PurchaseOrder entity) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("UPDATE PurchaseOrder SET qty=?,chequeNumber=?,dDate=?,amount=? WHERE purchaseOrderId=?",entity.getQty(),entity.getChequeNumber(),entity.getdDate(),entity.getAmount(),entity.getPurchaseOrderId());
    }

    @Override
    public PurchaseOrder get(String s) throws SQLException, ClassNotFoundException {
        ResultSet set = CrudUtil.execute("SELECT * FROM PurchaseOrder WHERE purchaseOrderId=?",s);
        if (set.next()){
            return new PurchaseOrder(set.getString(1),set.getString(2),set.getString(3),set.getDate(4),set.getInt(5),set.getString(6),set.getDate(7),set.getDouble(8));
        }
        return null;
    }

    @Override
    public List<PurchaseOrder> getAll() throws SQLException, ClassNotFoundException {
        ResultSet set = CrudUtil.execute("SELECT * FROM PurchaseOrder");
        List<PurchaseOrder> purchaseOrderList = new ArrayList<>();
        while (set.next()){
            purchaseOrderList.add(new PurchaseOrder(set.getString(1),set.getString(2),set.getString(3),set.getDate(4),set.getInt(5),set.getString(6),set.getDate(7),set.getDouble(8)));
        }
        return purchaseOrderList;
    }

    @Override
    public String getLastPOId() throws SQLException, ClassNotFoundException {
        ResultSet set = CrudUtil.execute("SELECT purchaseOrderId FROM PurchaseOrder ORDER BY purchaseOrderId DESC LIMIT 1");
        if (set.next()){
            return set.getString(1);
        }else {
            return null;
        }

    }
}
