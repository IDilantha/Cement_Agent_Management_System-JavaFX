package lk.ultratech.agent.sys.bo.custom;

import lk.ultratech.agent.sys.bo.SuperBO;
import lk.ultratech.agent.sys.dto.AgentDTO;
import java.sql.SQLException;
import java.util.List;

public interface AgentBO extends SuperBO {
    boolean saveAgent(AgentDTO agentDTO) throws SQLException, ClassNotFoundException;
    boolean deleteAgent(String s) throws SQLException, ClassNotFoundException;
    boolean updateAgent(AgentDTO entity) throws SQLException, ClassNotFoundException;
    List<AgentDTO> getAllAgents() throws SQLException, ClassNotFoundException;
    AgentDTO getFirstAgent() throws SQLException, ClassNotFoundException;
}
