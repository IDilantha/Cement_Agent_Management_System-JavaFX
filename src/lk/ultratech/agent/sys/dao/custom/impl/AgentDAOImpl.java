package lk.ultratech.agent.sys.dao.custom.impl;

import lk.ultratech.agent.sys.dao.CrudUtil;
import lk.ultratech.agent.sys.dao.custom.AgentDAO;
import lk.ultratech.agent.sys.entity.Agent;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AgentDAOImpl implements AgentDAO {
    @Override
    public boolean save(Agent entity) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("INSERT INTO Agent VALUES (?,?,?,?,?)",entity.getAgentId(),entity.getAgentCode(),entity.getAgentName(),entity.getRegion(),entity.getTerritory());
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("DELETE FROM Agent WHERE agentId=?",s);
    }

    @Override
    public boolean update(Agent entity) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("UPDATE Agent SET agentCode=? ,region=?,territory=? WHERE agentId=?",entity.getAgentCode(),entity.getRegion(),entity.getTerritory(),entity.getAgentId());
    }

    @Override
    public Agent get(String s) throws SQLException, ClassNotFoundException {
        ResultSet set = CrudUtil.execute("SELECT * FROM Agent WHERE agentId=?",s);
        if (set.next()){
         return new Agent(set.getString(1),set.getString(2),set.getString(3),set.getString(4),set.getString(5));
        }
        return null;
    }

    @Override
    public List<Agent> getAll() throws SQLException, ClassNotFoundException {
        ResultSet set = CrudUtil.execute("SELECT * FROM Agent");
        List<Agent> agentList = new ArrayList<>();
        if (set.next()){
            agentList.add(new Agent(set.getString(1),set.getString(2),set.getString(3),set.getString(4),set.getString(5)));
        }
        return agentList;
    }

    @Override
    public Agent getFirstAgent() throws SQLException, ClassNotFoundException {
        ResultSet set = CrudUtil.execute("SELECT * FROM Agent WHERE agentId=?","A001");
        if (set.next()){
            return new Agent(set.getString(1),set.getString(2),set.getString(3),set.getString(4),set.getString(5));
        }
        return null;
    }
}
