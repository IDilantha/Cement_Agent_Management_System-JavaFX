package lk.ultratech.agent.sys.dao.custom.impl;

import lk.ultratech.agent.sys.dao.CrudUtil;
import lk.ultratech.agent.sys.dao.custom.ShopPaymentDAO;
import lk.ultratech.agent.sys.entity.ShopPayment;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ShopPaymentDAOImpl implements ShopPaymentDAO {
    @Override
    public boolean save(ShopPayment entity) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("INSERT INTO ShopPayment VALUES (?,?,?,?,?)",entity.getShopPaymentId(),entity.getShopId(),entity.getType(),entity.getDate(),entity.getAmount());
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("DELETE FROM ShopPayment WHERE shopPaymentId=?",s);
    }

    @Override
    public boolean update(ShopPayment entity) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("UPDATE ShopPayment SET type=?,date=?,amount=? WHERE shopPaymentId=?",entity.getType(),entity.getDate(),entity.getAmount(),entity.getShopPaymentId());
    }

    @Override
    public ShopPayment get(String s) throws SQLException, ClassNotFoundException {
        ResultSet set = CrudUtil.execute("SELECT * FROM ShopPayment WHERE shopPaymentId=?",s);
        if (set.next()){
            return new ShopPayment(set.getString(1),set.getString(2),set.getString(3),set.getDate(4),set.getDouble(5));
        }
        return null;
    }

    @Override
    public List<ShopPayment> getAll() throws SQLException, ClassNotFoundException {
        ResultSet set = CrudUtil.execute("SELECT * FROM ShopPayment");
        List<ShopPayment> shopPaymentList = new ArrayList<>();
        if (set.next()){
            shopPaymentList.add(new ShopPayment(set.getString(1),set.getString(2),set.getString(3),set.getDate(4),set.getDouble(5)));
        }
        return shopPaymentList;
    }

    @Override
    public String getLastShopPaymentId() throws SQLException, ClassNotFoundException {
        ResultSet set = CrudUtil.execute("SELECT shopPaymentId FROM ShopPayment ORDER BY shopPaymentId DESC LIMIT 1");
        if (set.next()){
            return set.getString(1);
        }else {
            return null;
        }

    }

    @Override
    public boolean existsByShopId(String shopId) throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("SELECT * FROM ShopPayment WHERE shopId=?", shopId);
        return rst.next();
    }

    @Override
    public List<ShopPayment> getAllShopPayments(String shopId) throws SQLException, ClassNotFoundException {
        ResultSet set = CrudUtil.execute("SELECT * FROM ShopPayment WHERE shopId=?",shopId);
        List<ShopPayment> shopPaymentList = new ArrayList<>();
        if (set.next()){
            shopPaymentList.add(new ShopPayment(set.getString(1),set.getString(2),set.getString(3),set.getDate(4),set.getDouble(5)));
        }
        return shopPaymentList;
    }
}
