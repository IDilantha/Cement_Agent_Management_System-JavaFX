package lk.ultratech.agent.sys.dao.custom;

import lk.ultratech.agent.sys.dao.CrudDAO;
import lk.ultratech.agent.sys.entity.CementAgent;
import lk.ultratech.agent.sys.entity.CementAgentPK;

import java.sql.SQLException;

public interface CementAgentDAO extends CrudDAO<CementAgent, CementAgentPK> {
    boolean setPrices(CementAgent agent) throws SQLException, ClassNotFoundException;
}
