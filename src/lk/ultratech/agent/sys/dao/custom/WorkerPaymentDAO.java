package lk.ultratech.agent.sys.dao.custom;

import lk.ultratech.agent.sys.dao.CrudDAO;
import lk.ultratech.agent.sys.entity.WorkerPayment;

import java.sql.SQLException;
import java.util.List;

public interface WorkerPaymentDAO extends CrudDAO<WorkerPayment,String> {

    String getLastWorkerPaymentId() throws SQLException, ClassNotFoundException;

    boolean existsByWorkerId(String workerId) throws SQLException, ClassNotFoundException;

    List<WorkerPayment> getByWorkerId(String workerId) throws SQLException, ClassNotFoundException;
}
