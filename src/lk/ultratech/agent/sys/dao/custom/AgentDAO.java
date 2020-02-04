package lk.ultratech.agent.sys.dao.custom;

import lk.ultratech.agent.sys.dao.CrudDAO;
import lk.ultratech.agent.sys.entity.Agent;

import java.sql.SQLException;

public interface AgentDAO extends CrudDAO<Agent,String> {
    Agent getFirstAgent() throws SQLException, ClassNotFoundException;
}
