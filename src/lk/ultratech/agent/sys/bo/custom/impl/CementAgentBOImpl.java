package lk.ultratech.agent.sys.bo.custom.impl;

import lk.ultratech.agent.sys.bo.custom.CementAgentBO;
import lk.ultratech.agent.sys.dao.CrudUtil;
import lk.ultratech.agent.sys.dao.DAOFactory;
import lk.ultratech.agent.sys.dao.DAOType;
import lk.ultratech.agent.sys.dao.custom.CementAgentDAO;
import lk.ultratech.agent.sys.dto.CementAgentDTO;
import lk.ultratech.agent.sys.dto.CementAgentDTO2;
import lk.ultratech.agent.sys.entity.CementAgent;
import lk.ultratech.agent.sys.entity.CementAgentPK;
import sun.management.resources.agent;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CementAgentBOImpl implements CementAgentBO {

    private CementAgentDAO cementAgentDAO;

    public CementAgentBOImpl(){
        this.cementAgentDAO = DAOFactory.getInstance().getDAO(DAOType.CEMENT_AGENT);
    }

    @Override
    public boolean save(CementAgentDTO entity) {
        //return cementAgentDAO.save(entity.getCementId(),entity.getAgentId(),entity.getUnitPrice());
        return false;
    }

    @Override
    public boolean update(CementAgentDTO entity) {
        return false;
    }

    @Override
    public CementAgentDTO getCementPrice(CementAgentDTO2 pk) throws SQLException, ClassNotFoundException {
        CementAgent agent = cementAgentDAO.get(new CementAgentPK(pk.getCementId(), pk.getAgentId()));
        CementAgentDTO dto = new CementAgentDTO(agent.getCementAgentPK().getCementId(),agent.getCementAgentPK().getAgentId(),agent.getUnitPrice());
        return dto;
    }

    @Override
    public List<CementAgentDTO> getAllCementAgent() throws SQLException, ClassNotFoundException {
        List<CementAgent> all = cementAgentDAO.getAll();
        List<CementAgentDTO> dtos = new ArrayList<>();
        for (CementAgent agent:all) {
            dtos.add(new CementAgentDTO(agent.getCementAgentPK().getCementId(),agent.getCementAgentPK().getAgentId(),agent.getUnitPrice()));
        }
        return dtos;
    }
}
