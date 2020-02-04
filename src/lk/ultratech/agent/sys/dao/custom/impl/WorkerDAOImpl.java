package lk.ultratech.agent.sys.dao.custom.impl;

import lk.ultratech.agent.sys.dao.CrudUtil;
import lk.ultratech.agent.sys.dao.custom.WorkerDAO;
import lk.ultratech.agent.sys.entity.Worker;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WorkerDAOImpl implements WorkerDAO {

    @Override
    public boolean save(Worker entity) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("INSERT INTO Worker VALUES(?,?,?,?)",entity.getWorkerId(),entity.getName(),entity.getTelephone(),entity.getType());
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("DELETE FROM Worker WHERE workerId=?",s);
    }

    @Override
    public boolean update(Worker entity) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("UPDATE Worker SET name=?,telephone=? WHERE workerId=?",entity.getName(),entity.getTelephone(),entity.getWorkerId());
    }

    @Override
    public Worker get(String s) throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("SELECT * FROM Worker WHERE workerId=?",s);
        if (rst.next()){
            return new Worker(rst.getString(1),rst.getString(2),rst.getInt(3),rst.getString(4));
        }
        return null;
    }

    @Override
    public List<Worker> getAll() throws SQLException, ClassNotFoundException {
        ResultSet execute = CrudUtil.execute("SELECT * FROM Worker");
        List<Worker> workerList = new ArrayList<>();
        while (execute.next()){
            workerList.add(new Worker(execute.getString(1),execute.getString(2),execute.getInt(3),execute.getString(4)));
        }
        return workerList;
    }

    @Override
    public String getLastWorkerId() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("SELECT workerId FROM Worker ORDER BY workerId DESC LIMIT 1");
        if (rst.next()) {
            return rst.getString(1);
        }
        return null;
    }

    @Override
    public Worker getWorkerByName(String s) throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("SELECT * FROM Worker WHERE name=?",s);
        if (rst.next()){
            return new Worker(rst.getString(1),rst.getString(2),rst.getInt(3),rst.getString(4));
        }
        return null;
    }

}
