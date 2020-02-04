package lk.ultratech.agent.sys.bo.custom.impl;

import lk.ultratech.agent.sys.bo.custom.AgentBO;
import lk.ultratech.agent.sys.dao.DAOFactory;
import lk.ultratech.agent.sys.dao.DAOType;
import lk.ultratech.agent.sys.dao.custom.AgentDAO;
import lk.ultratech.agent.sys.dto.AgentDTO;
import lk.ultratech.agent.sys.entity.Agent;
import sun.management.resources.agent;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AgentBOImpl implements AgentBO {
   private AgentDAO agentDAO;

   public AgentBOImpl(){
        this.agentDAO = DAOFactory.getInstance().getDAO(DAOType.AGENT);
    }

    @Override
    public boolean saveAgent(AgentDTO agentDTO) throws SQLException, ClassNotFoundException {
        return agentDAO.save(new Agent(agentDTO.getAgentId(),agentDTO.getAgentCode(),agentDTO.getAgentName(),agentDTO.getRegion(),agentDTO.getTerritory()));
    }

    @Override
    public boolean deleteAgent(String s) throws SQLException, ClassNotFoundException {
        return agentDAO.delete(s);
    }

    @Override
    public boolean updateAgent(AgentDTO entity) throws SQLException, ClassNotFoundException {
        return agentDAO.update(new Agent(entity.getAgentId(),entity.getAgentCode(),entity.getAgentName(),entity.getRegion(),entity.getTerritory()));
    }

    @Override
    public List<AgentDTO> getAllAgents() throws SQLException, ClassNotFoundException {
        List<Agent> all = agentDAO.getAll();
        List<AgentDTO> agentDTOS = new ArrayList<>();
        for (Agent agent:all) {
            agentDTOS.add(new AgentDTO(agent.getAgentId(),agent.getAgentCode(),agent.getAgentName(),agent.getRegion(),agent.getTerritory()));
        }
        return agentDTOS;
    }

    @Override
    public AgentDTO getFirstAgent() throws SQLException, ClassNotFoundException {
        Agent firstAgent = agentDAO.getFirstAgent();
        AgentDTO dto = new AgentDTO(firstAgent.getAgentId(),firstAgent.getAgentCode(),firstAgent.getAgentName(),firstAgent.getRegion(),firstAgent.getTerritory());
        return dto;
   }

}
