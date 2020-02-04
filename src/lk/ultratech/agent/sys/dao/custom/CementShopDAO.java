package lk.ultratech.agent.sys.dao.custom;

import lk.ultratech.agent.sys.dao.CrudDAO;
import lk.ultratech.agent.sys.entity.CementShop;
import lk.ultratech.agent.sys.entity.CementShopPK;

import java.sql.SQLException;

public interface CementShopDAO extends CrudDAO<CementShop, CementShopPK> {
    CementShop getByShopID(String s) throws SQLException, ClassNotFoundException;
    boolean increaseAllPrices(String type,double price) throws SQLException, ClassNotFoundException;
    boolean decreaseAllPrices(String type,double price) throws SQLException, ClassNotFoundException;
}
