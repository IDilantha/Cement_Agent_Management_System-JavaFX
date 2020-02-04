package lk.ultratech.agent.sys.dao.custom.impl;

import lk.ultratech.agent.sys.dao.CrudUtil;
import lk.ultratech.agent.sys.dao.custom.ChequePaymentDAO;
import lk.ultratech.agent.sys.entity.ChequePayment;
import lk.ultratech.agent.sys.entity.ShopPayment;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ChequePaymentDAOImpl implements ChequePaymentDAO {
    @Override
    public boolean save(ChequePayment entity) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("INSERT INTO ChequePayment VALUES (?,?,?,?,?,?)",entity.getChequePaymentId(),entity.getShopPaymentId(),entity.getCheuqeNo(),entity.getdDate(),entity.getAmount(),entity.getReturned());
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("DELETE FROM ChequePayment WHERE chequePaymentId=?",s);
    }

    @Override
    public boolean update(ChequePayment entity) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("UPDATE ChequePayment SET dDate=?,returned=? WHERE chequePaymentId=?",entity.getdDate(),entity.getReturned(),entity.getChequePaymentId());
    }

    @Override
    public ChequePayment get(String s) throws SQLException, ClassNotFoundException {
        ResultSet set = CrudUtil.execute("SELECT * FROM ChequePayment WHERE chequePaymentId=?",s);
        if (set.next()){
            return new ChequePayment(set.getString(1),set.getString(2),set.getString(3),set.getDate(4),set.getDouble(5),set.getString(6));
        }
        return null;
    }

    @Override
    public List<ChequePayment> getAll() throws SQLException, ClassNotFoundException {
        ResultSet set = CrudUtil.execute("SELECT * FROM ChequePayment");
        List<ChequePayment> chequePaymentList = new ArrayList<>();
        while (set.next()){
            chequePaymentList.add(new ChequePayment(set.getString(1),set.getString(2),set.getString(3),set.getDate(4),set.getDouble(5),set.getString(6)));
        }
        return chequePaymentList;
    }

    @Override
    public String getLastChequePaymentId() throws SQLException, ClassNotFoundException {
        ResultSet set = CrudUtil.execute("SELECT chequePaymentId FROM ChequePayment ORDER BY chequePaymentId DESC LIMIT 1");
        if (set.next()){
            return set.getString(1);
        }else {
            return null;
        }

    }

    @Override
    public boolean existsByShopPaymentId(String shopPaymentId) throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("SELECT * FROM  ChequePayment WHERE shopPaymentId=?",shopPaymentId );
        return rst.next();
    }

    @Override
    public boolean updateStatus(String status,String cheque) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("UPDATE ChequePayment SET returned=? WHERE shopPaymentId=?",status,cheque);
    }
}
