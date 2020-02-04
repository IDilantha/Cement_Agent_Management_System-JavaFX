package lk.ultratech.agent.sys.dao.custom.impl;

import lk.ultratech.agent.sys.dao.CrudUtil;
import lk.ultratech.agent.sys.dao.custom.ShopDAO;
import lk.ultratech.agent.sys.entity.Shop;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ShopDAOImpl implements ShopDAO {
    @Override
    public boolean save(Shop entity) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("INSERT INTO Shop VALUES(?,?,?,?)",entity.getShopId(),entity.getShopName(),entity.getAddress(),entity.getTelephone());
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("DELETE FROM Shop WHERE shopId=?",s);
    }

    @Override
    public boolean update(Shop entity) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("UPDATE Shop SET name=?,address=?,telephone=? WHERE shopId=?",entity.getShopName(),entity.getAddress(),entity.getTelephone(),entity.getShopId());
    }

    @Override
    public Shop get(String s) throws SQLException, ClassNotFoundException {
        ResultSet set = CrudUtil.execute("SELECT * FROM Shop WHERE shopId=?",s);
        if (set.next()){
            return new Shop(set.getString(1),set.getString(2),set.getString(3),set.getInt(4));
        }
        return null;
    }

    @Override
    public List<Shop> getAll() throws SQLException, ClassNotFoundException {
        ResultSet set = CrudUtil.execute("SELECT * FROM Shop");
        List<Shop> shopList = new ArrayList<>();
        while (set.next()){
            shopList.add(new Shop(set.getString(1),set.getString(2),set.getString(3),set.getInt(4)));
        }
        return shopList;
    }

    @Override
    public String getLastShopId() throws SQLException, ClassNotFoundException {
        ResultSet set = CrudUtil.execute("SELECT shopId FROM Shop ORDER BY shopId DESC LIMIT 1");
        if (set.next()){
            return set.getString(1);
        }else {
            return null;
        }
    }

    @Override
    public Shop getByShopName(String shopName) throws SQLException, ClassNotFoundException {
        ResultSet set = CrudUtil.execute("SELECT * FROM Shop WHERE name=?",shopName);
        if (set.next()){
            return new Shop(set.getString(1),set.getString(2),set.getString(3),set.getInt(4));
        }
        return null;
    }
}
