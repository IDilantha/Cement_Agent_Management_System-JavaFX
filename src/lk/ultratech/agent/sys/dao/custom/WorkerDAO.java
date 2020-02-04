package lk.ultratech.agent.sys.dao.custom;

import lk.ultratech.agent.sys.dao.CrudDAO;
import lk.ultratech.agent.sys.entity.Worker;

import java.sql.SQLException;
import java.util.List;

public interface WorkerDAO extends CrudDAO<Worker,String> {

    String getLastWorkerId() throws SQLException, ClassNotFoundException;

    Worker getWorkerByName(String s) throws SQLException, ClassNotFoundException;
}
