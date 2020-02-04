package lk.ultratech.agent.sys.bo.custom;

import lk.ultratech.agent.sys.bo.SuperBO;
import lk.ultratech.agent.sys.dto.CementAgentDTO;
import lk.ultratech.agent.sys.dto.CementAgentDTO2;
import java.sql.SQLException;
import java.util.List;

public interface CementAgentBO extends SuperBO {
    boolean save(CementAgentDTO entity);
    boolean update(CementAgentDTO entity);
    CementAgentDTO getCementPrice(CementAgentDTO2 pk) throws SQLException, ClassNotFoundException;
    List<CementAgentDTO> getAllCementAgent() throws SQLException, ClassNotFoundException;

}
