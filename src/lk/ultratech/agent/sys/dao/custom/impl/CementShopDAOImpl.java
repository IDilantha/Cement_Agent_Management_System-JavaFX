package lk.ultratech.agent.sys.dao.custom.impl;

import lk.ultratech.agent.sys.dao.CrudUtil;
import lk.ultratech.agent.sys.dao.custom.CementShopDAO;
import lk.ultratech.agent.sys.entity.CementShop;
import lk.ultratech.agent.sys.entity.CementShopPK;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CementShopDAOImpl implements CementShopDAO {
    @Override
    public boolean save(CementShop entity) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("INSERT INTO CementShop VALUES (?,?,?)",entity.getCementShopPK().getCementId(),entity.getCementShopPK().getShopId(),entity.getUnitPrice());
    }

    @Override
    public boolean delete(CementShopPK cementShopPK) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("DELETE FROM CementShop WHERE cementId=? AND shopId=? ",cementShopPK.getCementId(),cementShopPK.getCementId());
    }

    @Override
    public boolean update(CementShop entity) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("UPDATE CementShop SET unitPrice=? WHERE cementId=? AND shopId=?",entity.getUnitPrice(),entity.getCementShopPK().getCementId(),entity.getCementShopPK().getShopId());
    }

    @Override
    public CementShop get(CementShopPK cementShopPK) throws SQLException, ClassNotFoundException {
        ResultSet set = CrudUtil.execute("SELECT * FROM CementShop WHERE cementId=? AND shopId=?", cementShopPK.getCementId(),cementShopPK.getShopId());
        if (set.next()){
            return new CementShop(set.getString(1),set.getString(2),set.getDouble(3));
        }
        return null;
    }

    @Override
    public List<CementShop> getAll() throws SQLException, ClassNotFoundException {
        ResultSet set = CrudUtil.execute("SELECT * FROM CementShop");
        List<CementShop> cementShopList = new ArrayList<>();
        if (set.next()){
            cementShopList.add(new CementShop(set.getString(1),set.getString(2),set.getDouble(3)));
        }
        return cementShopList;
    }

    @Override
    public CementShop getByShopID(String s) throws SQLException, ClassNotFoundException {
        ResultSet set = CrudUtil.execute("SELECT * FROM CementShop WHERE shopId=?",s);
        if (set.next()){
            return new CementShop(set.getString(1),set.getString(2),set.getDouble(3));
        }
        return null;
    }

    @Override
    public boolean increaseAllPrices(String type, double price) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("UPDATE CementShop SET unitPrice=unitPrice+? WHERE cementId=?",price,type);
    }

    @Override
    public boolean decreaseAllPrices(String type, double price) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("UPDATE CementShop SET unitPrice=unitPrice-? WHERE cementId=?",price,type);
    }
}
