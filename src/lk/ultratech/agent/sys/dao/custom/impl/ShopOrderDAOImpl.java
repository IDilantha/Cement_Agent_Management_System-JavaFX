package lk.ultratech.agent.sys.dao.custom.impl;

import lk.ultratech.agent.sys.dao.CrudUtil;
import lk.ultratech.agent.sys.dao.custom.ShopOrderDAO;
import lk.ultratech.agent.sys.entity.ShopOrder;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ShopOrderDAOImpl implements ShopOrderDAO {
    @Override
    public boolean save(ShopOrder entity) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("INSERT INTO ShopOrder VALUES (?,?,?,?,?,?)",entity.getShopOrderId(),entity.getShopId(),entity.getType(),entity.getoDate(),entity.getdDate(),entity.getQty());
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("DELETE FROM ShopOrder WHERE shopOrderId=?",s);
    }

    @Override
    public boolean update(ShopOrder entity) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("UPDATE ShopOrder SET type=?,dDate=?,qty=? WHERE shopOrderId=?",entity.getType(),entity.getdDate(),entity.getQty(),entity.getShopOrderId());
    }

    @Override
    public ShopOrder get(String s) throws SQLException, ClassNotFoundException {
        ResultSet set = CrudUtil.execute("SELECT * FROM ShopOrder WHERE shopOrderId=?",s);
        if (set.next()){
            return new ShopOrder(set.getString(1),set.getString(2),set.getString(3),set.getDate(4),set.getDate(5),set.getInt(6));
        }
        return null;
    }

    @Override
    public List<ShopOrder> getAll() throws SQLException, ClassNotFoundException {
        ResultSet set = CrudUtil.execute("SELECT * FROM ShopOrder");
        List<ShopOrder> shopOrderList = new ArrayList<>();
        while (set.next()){
            shopOrderList.add(new ShopOrder(set.getString(1),set.getString(2),set.getString(3),set.getDate(4),set.getDate(5),set.getInt(6)));
        }
        return shopOrderList;
    }

    @Override
    public String getLastShopOrderId() throws SQLException, ClassNotFoundException {
        ResultSet set = CrudUtil.execute("SELECT shopOrderId FROM ShopOrder ORDER BY shopOrderId DESC LIMIT 1");
        if (set.next()){
            return set.getString(1);
        }else {
            return null;
        }

    }

    @Override
    public List<ShopOrder> getAllByType(String type) throws SQLException, ClassNotFoundException {
        ResultSet set = CrudUtil.execute("SELECT * FROM ShopOrder WHERE type=?",type);
        List<ShopOrder> shopOrderList = new ArrayList<>();
        while (set.next()){
            shopOrderList.add(new ShopOrder(set.getString(1),set.getString(2),set.getString(3),set.getDate(4),set.getDate(5),set.getInt(6)));
        }
        return shopOrderList;
    }

    @Override
    public boolean existsByShopId(String shopId) throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("SELECT * FROM ShopOrder WHERE shopId=?", shopId);
        return rst.next();
    }
}
