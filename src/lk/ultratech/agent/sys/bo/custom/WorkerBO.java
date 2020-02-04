package lk.ultratech.agent.sys.bo.custom;

import lk.ultratech.agent.sys.bo.SuperBO;
import lk.ultratech.agent.sys.dto.WorkerDTO;

import java.sql.SQLException;
import java.util.List;

public interface WorkerBO extends SuperBO {
    boolean saveWorker(WorkerDTO dto) throws SQLException, ClassNotFoundException;
    boolean updateWorker(WorkerDTO dto) throws SQLException, ClassNotFoundException;
    boolean deleteWorker(String id) throws SQLException, ClassNotFoundException;
    List<WorkerDTO> getAllWorker() throws SQLException, ClassNotFoundException;
    String getLastWorkerId() throws SQLException, ClassNotFoundException;
    List<String> getAllWorkerNames() throws SQLException, ClassNotFoundException;
    WorkerDTO findWorker(String workerName) throws Exception;
    WorkerDTO findWorkerById(String workerId) throws Exception;
}
