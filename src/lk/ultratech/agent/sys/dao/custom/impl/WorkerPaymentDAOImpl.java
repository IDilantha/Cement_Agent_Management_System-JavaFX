package lk.ultratech.agent.sys.dao.custom.impl;

import lk.ultratech.agent.sys.dao.CrudUtil;
import lk.ultratech.agent.sys.dao.custom.WorkerPaymentDAO;
import lk.ultratech.agent.sys.entity.WorkerPayment;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WorkerPaymentDAOImpl implements WorkerPaymentDAO {
    @Override
    public boolean save(WorkerPayment entity) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("INSERT INTO WorkerPayment VALUES (?,?,?,?)",entity.getWorkerPaymentId(),entity.getWorkerId(),entity.getDate(),entity.getAmount());
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("DELETE FROM WorkerPayment WHERE workerPaymentId=?",s);
    }

    @Override
    public boolean update(WorkerPayment entity) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("UPDATE WorkerPayment SET date=?,amount=? WHERE workerPaymentId=?",entity.getDate(),entity.getAmount(),entity.getWorkerPaymentId());
    }

    @Override
    public WorkerPayment get(String s) throws SQLException, ClassNotFoundException {
        ResultSet set = CrudUtil.execute("SELECT * FROM WorkerPayment WHERE workerPaymentId=?",s);
        if (set.next()){
            return new WorkerPayment(set.getString(1),set.getString(2),set.getDate(3),set.getDouble(4));
        }
        return null;
    }

    @Override
    public List<WorkerPayment> getAll() throws SQLException, ClassNotFoundException {
        ResultSet set = CrudUtil.execute("SELECT * FROM WorkerPayment");
        List<WorkerPayment> workerPaymentList = new ArrayList<>();
        while (set.next()){
            workerPaymentList.add(new WorkerPayment(set.getString(1),set.getString(2),set.getDate(3),set.getDouble(4)));
        }
        return workerPaymentList;
    }

    @Override
    public String getLastWorkerPaymentId() throws SQLException, ClassNotFoundException {
        ResultSet set = CrudUtil.execute("SELECT workerPaymentId FROM WorkerPayment ORDER BY workerPaymentId DESC LIMIT 1");
        if (set.next()){
            return set.getString(1);
        }else {
            return null;
        }
    }

    @Override
    public boolean existsByWorkerId(String workerId) throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("SELECT * FROM WorkerPayment WHERE workerId=?",workerId);
        return rst.next();
    }

    @Override
    public List<WorkerPayment> getByWorkerId(String workerId) throws SQLException, ClassNotFoundException {
        ResultSet set = CrudUtil.execute("SELECT * FROM WorkerPayment WHERE workerId=?",workerId);
        List<WorkerPayment> workerPaymentList = new ArrayList<>();
        if (set.next()){
            workerPaymentList.add(new WorkerPayment(set.getString(1),set.getString(2),set.getDate(3),set.getDouble(4)));
        }
        return workerPaymentList;
    }
}
