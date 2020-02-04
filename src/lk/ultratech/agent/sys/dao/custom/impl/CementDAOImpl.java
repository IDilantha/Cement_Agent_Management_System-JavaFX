package lk.ultratech.agent.sys.dao.custom.impl;

import lk.ultratech.agent.sys.dao.CrudUtil;
import lk.ultratech.agent.sys.dao.custom.CementDAO;
import lk.ultratech.agent.sys.entity.Cement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CementDAOImpl implements CementDAO {
    @Override
    public boolean save(Cement entity) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("INSERT INTO Cement VALUES (?,?)",entity.getCementId(),entity.getName());
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("DELETE FROM Cement WHERE cementId=?",s);
    }

    @Override
    public boolean update(Cement entity) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("UPDATE Cement SET name=? WHERE cementId=?",entity.getName(),entity.getCementId());
    }

    @Override
    public Cement get(String s) throws SQLException, ClassNotFoundException {
        ResultSet set= CrudUtil.execute("SELECT * FROM Cement WHERE cementId=?",s);
        if (set.next()){
            return new Cement(set.getString(1),set.getString(2));
        }
        return null;
    }

    @Override
    public List<Cement> getAll() throws SQLException, ClassNotFoundException {
        ResultSet set = CrudUtil.execute("SELECT * FROM Cement");
        List<Cement> cementList = new ArrayList<>();
        while (set.next()){
            cementList.add(new Cement(set.getString(1),set.getString(2)));
        }
        return cementList;
    }

    @Override
    public String getIdByName(String name) throws SQLException, ClassNotFoundException {
        ResultSet set= CrudUtil.execute("SELECT * FROM Cement WHERE name=?",name);
        if (set.next()){
            return set.getString(1);
        }
        return null;
    }

    @Override
    public String getNameById(String id) throws SQLException, ClassNotFoundException {
        ResultSet set= CrudUtil.execute("SELECT * FROM Cement WHERE cementId=?",id);
        if (set.next()){
            return set.getString(2);
        }
        return null;
    }
}
