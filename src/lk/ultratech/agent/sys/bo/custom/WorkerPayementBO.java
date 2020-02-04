package lk.ultratech.agent.sys.bo.custom;

import lk.ultratech.agent.sys.bo.SuperBO;
import lk.ultratech.agent.sys.dto.WorkerPaymentDTO;
import lk.ultratech.agent.sys.entity.WorkerPayment;

import java.sql.SQLException;
import java.util.List;

public interface WorkerPayementBO extends SuperBO {
    boolean addWorkerPayment(WorkerPaymentDTO workerPaymentDTO) throws SQLException, ClassNotFoundException;
    String getLastWorkerPaymentId() throws SQLException, ClassNotFoundException;
    List<WorkerPaymentDTO> getAllWorkerPayment() throws SQLException, ClassNotFoundException;
    WorkerPaymentDTO getByPayId(String s);
    List<WorkerPaymentDTO> getByWorkerId(String s) throws SQLException, ClassNotFoundException;
}
