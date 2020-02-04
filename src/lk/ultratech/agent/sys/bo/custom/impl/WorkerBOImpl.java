package lk.ultratech.agent.sys.bo.custom.impl;

import lk.ultratech.agent.sys.bo.custom.WorkerBO;
import lk.ultratech.agent.sys.dao.DAOFactory;
import lk.ultratech.agent.sys.dao.DAOType;
import lk.ultratech.agent.sys.dao.custom.WorkerDAO;
import lk.ultratech.agent.sys.dto.WorkerDTO;
import lk.ultratech.agent.sys.entity.Worker;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WorkerBOImpl implements WorkerBO {
    private WorkerDAO workerDAO;

    public WorkerBOImpl(){
        this.workerDAO = DAOFactory.getInstance().getDAO(DAOType.WORKER);
    }

    @Override
    public boolean saveWorker(WorkerDTO dto) throws SQLException, ClassNotFoundException {
        return workerDAO.save(new Worker(dto.getWorkerId(),dto.getName(),dto.getTelephone(),dto.getType()));
    }

    @Override
    public boolean updateWorker(WorkerDTO dto) throws SQLException, ClassNotFoundException {
        return workerDAO.update(new Worker(dto.getWorkerId(),dto.getName(),dto.getTelephone(),dto.getType()));
    }

    @Override
    public boolean deleteWorker(String id) throws SQLException, ClassNotFoundException {
        return workerDAO.delete(id);
    }

    @Override
    public List<WorkerDTO> getAllWorker() throws SQLException, ClassNotFoundException {
        List<Worker> all = workerDAO.getAll();
        List<WorkerDTO> temp = new ArrayList<>();

        for (Worker worker:all) {
            temp.add(new WorkerDTO(worker.getWorkerId(),worker.getName(),worker.getTelephone(),worker.getType()));
        }
        return temp;
    }

    @Override
    public String getLastWorkerId() throws SQLException, ClassNotFoundException {
        return workerDAO.getLastWorkerId();
    }

    @Override
    public List<String> getAllWorkerNames() throws SQLException, ClassNotFoundException {
        List<Worker> workerDTOS = workerDAO.getAll();
        List<String> names = new ArrayList<>();
        for (Worker worker:workerDTOS ) {
            names.add(worker.getName());
        }
        return names;
    }

    @Override
    public WorkerDTO findWorker(String workerName) throws Exception {
        Worker worker= workerDAO.getWorkerByName(workerName);
        return new WorkerDTO(worker.getWorkerId(),worker.getName(),worker.getTelephone(),worker.getType());
    }

    @Override
    public WorkerDTO findWorkerById(String workerId) throws Exception {
        Worker worker= workerDAO.get(workerId);
        return new WorkerDTO(worker.getWorkerId(),worker.getName(),worker.getTelephone(),worker.getType());

    }
}
