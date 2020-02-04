package lk.ultratech.agent.sys.dao.custom.impl;

import lk.ultratech.agent.sys.dao.CrudUtil;
import lk.ultratech.agent.sys.dao.custom.ShopDeliveryDAO;
import lk.ultratech.agent.sys.entity.ShopDelivery;
import lk.ultratech.agent.sys.entity.ShopDeliveryPK;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ShopDeliveryDAOImpl implements ShopDeliveryDAO {
    @Override
    public boolean save(ShopDelivery entity) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("INSERT INTO ShopDelivery VALUES (?,?,?,?,?)",entity.getShopDeliveryPK().getDeliveryId(),entity.getShopDeliveryPK().getShopId(),entity.getType(),entity.getDate(),entity.getQty());
    }

    @Override
    public boolean delete(ShopDeliveryPK shopDeliveryPK) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("DELETE FROM ShopDelivery WHERE deliveryId=? AND shopId=?",shopDeliveryPK.getDeliveryId(),shopDeliveryPK.getShopId());
    }

    @Override
    public boolean update(ShopDelivery entity) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("UPDATE ShopDelivery SET date=?,qty=? WHERE deliveryId=? AND shopId=?",entity.getDate(),entity.getQty(),entity.getShopDeliveryPK().getDeliveryId(),entity.getShopDeliveryPK().getShopId());
    }

    @Override
    public ShopDelivery get(ShopDeliveryPK shopDeliveryPK) throws SQLException, ClassNotFoundException {
        ResultSet set = CrudUtil.execute("SELECT * FROM ShopDelivery WHERE deliveryId=? AND shopId=?",shopDeliveryPK.getDeliveryId(),shopDeliveryPK.getShopId());
        if (set.next()){
            return new ShopDelivery(set.getString(1),set.getString(2),set.getString(3),set.getDate(4),set.getInt(5));
        }
        return null;
    }

    @Override
    public List<ShopDelivery> getAll() throws SQLException, ClassNotFoundException {
        ResultSet set = CrudUtil.execute("SELECT * FROM ShopDelivery");
        List<ShopDelivery> shopDeliveryList = new ArrayList<>();
        if (set.next()){
            shopDeliveryList.add(new ShopDelivery(set.getString(1),set.getString(2),set.getString(3),set.getDate(4),set.getInt(5)));
        }
        return shopDeliveryList;
    }

    @Override
    public boolean saveAll(List<ShopDelivery> entity) throws SQLException, ClassNotFoundException {
        boolean execute = false;
        for (ShopDelivery delivery:entity) {
             execute = CrudUtil.execute("INSERT INTO ShopDelivery VALUES (?,?,?,?,?)", delivery.getShopDeliveryPK().getDeliveryId(), delivery.getShopDeliveryPK().getShopId(), delivery.getType(), delivery.getDate(), delivery.getQty());
        }
        return execute;
    }

    @Override
    public List<ShopDelivery> getAllShopDelivery(String shopId) throws SQLException, ClassNotFoundException {
        ResultSet set = CrudUtil.execute("SELECT * FROM ShopDelivery WHERE shopId=?",shopId);
        List<ShopDelivery> shopDeliveryList = new ArrayList<>();
        if (set.next()){
            shopDeliveryList.add(new ShopDelivery(set.getString(1),set.getString(2),set.getString(3),set.getDate(4),set.getInt(5)));
        }
        return shopDeliveryList;
    }
}
