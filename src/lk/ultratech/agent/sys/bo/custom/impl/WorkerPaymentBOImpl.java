package lk.ultratech.agent.sys.bo.custom.impl;

import lk.ultratech.agent.sys.bo.BOFactory;
import lk.ultratech.agent.sys.bo.BOTypes;
import lk.ultratech.agent.sys.bo.custom.WorkerBO;
import lk.ultratech.agent.sys.bo.custom.WorkerPayementBO;
import lk.ultratech.agent.sys.dao.DAOFactory;
import lk.ultratech.agent.sys.dao.DAOType;
import lk.ultratech.agent.sys.dao.custom.WorkerPaymentDAO;
import lk.ultratech.agent.sys.dto.WorkerDTO;
import lk.ultratech.agent.sys.dto.WorkerPaymentDTO;
import lk.ultratech.agent.sys.entity.WorkerPayment;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class WorkerPaymentBOImpl implements WorkerPayementBO {

    private WorkerPaymentDAO workerPaymentDAO = DAOFactory.getInstance().getDAO(DAOType.WORKER_PAYMENT);


    @Override
    public boolean addWorkerPayment(WorkerPaymentDTO workerPaymentDTO) throws SQLException, ClassNotFoundException {
        return workerPaymentDAO.save(new WorkerPayment(workerPaymentDTO.getWorkerPaymentId(),workerPaymentDTO.getWorkerId(),workerPaymentDTO.getDate(),workerPaymentDTO.getAmount()));
    }

    @Override
    public String getLastWorkerPaymentId() throws SQLException, ClassNotFoundException {
        return workerPaymentDAO.getLastWorkerPaymentId();
    }

    @Override
    public List<WorkerPaymentDTO> getAllWorkerPayment() throws SQLException, ClassNotFoundException {
        List<WorkerPayment> workerPaymentList = workerPaymentDAO.getAll();
        List<WorkerPaymentDTO> workerPaymentDTOS = new ArrayList<>();
        for (WorkerPayment workerPayment : workerPaymentList) {
            workerPaymentDTOS.add(new WorkerPaymentDTO(workerPayment.getWorkerPaymentId(), workerPayment.getWorkerId(),workerPayment.getDate(),workerPayment.getAmount()));
        }
        return workerPaymentDTOS;
    }

    @Override
    public WorkerPaymentDTO getByPayId(String s) {
        return null;
    }

    @Override
    public List<WorkerPaymentDTO> getByWorkerId(String s) throws SQLException, ClassNotFoundException {
        List<WorkerPayment> workerPaymentList = workerPaymentDAO.getByWorkerId(s);
        List<WorkerPaymentDTO> workerPaymentDTOS = new ArrayList<>();
        for (WorkerPayment workerPayment : workerPaymentList) {
            workerPaymentDTOS.add(new WorkerPaymentDTO(workerPayment.getWorkerPaymentId(), workerPayment.getWorkerId(),workerPayment.getDate(),workerPayment.getAmount()));
        }
        return workerPaymentDTOS;
    }
}
