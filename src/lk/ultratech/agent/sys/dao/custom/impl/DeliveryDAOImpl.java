package lk.ultratech.agent.sys.dao.custom.impl;

import lk.ultratech.agent.sys.dao.CrudUtil;
import lk.ultratech.agent.sys.dao.custom.DeliveryDAO;
import lk.ultratech.agent.sys.entity.Delivery;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DeliveryDAOImpl implements DeliveryDAO {
    @Override
    public boolean save(Delivery entity) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("INSERT INTO Delivery VALUES (?,?,?,?)",entity.getDeliveryId(),entity.getPurchaseOrderId(),entity.getType(),entity.getDate());
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("DELETE FROM Delivery WHERE deliveryId=?",s);
    }

    @Override
    public boolean update(Delivery entity) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("UPDATE Delivery SET date=? WHERE deliveryId=?",entity.getDate(),entity.getDeliveryId());
    }

    @Override
    public Delivery get(String s) throws SQLException, ClassNotFoundException {
        ResultSet set = CrudUtil.execute("SELECT * FROM Delivery WHERE deliveryId=?",s);
        if (set.next()){
            return new Delivery(set.getString(1),set.getString(2),set.getString(3),set.getDate(4));
        }
        return null;
    }

    @Override
    public List<Delivery> getAll() throws SQLException, ClassNotFoundException {
        ResultSet set = CrudUtil.execute("SELECT * FROM Delivery");
        List<Delivery> deliveryList = new ArrayList<>();
        if (set.next()){
            deliveryList.add(new Delivery(set.getString(1),set.getString(2),set.getString(3),set.getDate(4)));
        }
        return deliveryList;
    }

    @Override
    public String getLastDeliveryId() throws SQLException, ClassNotFoundException {
        ResultSet set = CrudUtil.execute("SELECT deliveryId FROM Delivery ORDER BY deliveryId DESC LIMIT 1");
        if (set.next()){
            return set.getString(1);
        }else {
            return null;
        }
    }

    @Override
    public boolean existByPurchaseOrderId(String shopId) throws SQLException, ClassNotFoundException {
        ResultSet set = CrudUtil.execute("SELECT * FROM Delivery WHERE purchaseOrderId=?",shopId);
        return set.next();
    }
}
