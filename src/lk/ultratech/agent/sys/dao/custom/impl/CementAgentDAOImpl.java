package lk.ultratech.agent.sys.dao.custom.impl;

import lk.ultratech.agent.sys.dao.CrudUtil;
import lk.ultratech.agent.sys.dao.custom.CementAgentDAO;
import lk.ultratech.agent.sys.entity.CementAgent;
import lk.ultratech.agent.sys.entity.CementAgentPK;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CementAgentDAOImpl implements CementAgentDAO {
    @Override
    public boolean save(CementAgent entity) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("INSERT INTO CementAgent VALUES (?,?,?)",entity.getCementAgentPK().getCementId(),entity.getCementAgentPK().getAgentId(),entity.getUnitPrice());
    }
private String cementId;
    private String agentId;
    @Override
    public boolean delete(CementAgentPK cementAgentPK) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("DELETE FROM CementAgent WHERE cementId=? AND agentId=?",cementAgentPK.getCementId(),cementAgentPK.getAgentId());
    }

    @Override
    public boolean update(CementAgent entity) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("UPDATE CementAgent SET unitPrice=? WHERE cementId=? AND agentId=?",entity.getUnitPrice(),entity.getCementAgentPK().getCementId(),entity.getCementAgentPK().getAgentId());
    }

    @Override
    public CementAgent get(CementAgentPK cementAgentPK) throws SQLException, ClassNotFoundException {
        ResultSet set = CrudUtil.execute("SELECT * FROM CementAgent WHERE cementId=? AND agentId=?",cementAgentPK.getCementId(),cementAgentPK.getAgentId());
        if (set.next()){
            return new CementAgent(set.getString(1),set.getString(2),set.getDouble(3));
        }
        return null;
    }

    @Override
    public List<CementAgent> getAll() throws SQLException, ClassNotFoundException {
        ResultSet set = CrudUtil.execute("SELECT * FROM CementAgent");
        List<CementAgent> cementAgentList = new ArrayList<>();
        if (set.next()){
            cementAgentList.add(new CementAgent(set.getString(1),set.getString(2),set.getDouble(3)));
        }
        return cementAgentList;
    }

    @Override
    public boolean setPrices(CementAgent agent) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("UPDATE CementAgent SET unitPrice=? WHERE cementId=? AND agentId=?",agent.getUnitPrice(),agent.getCementAgentPK().getCementId(),agent.getCementAgentPK().getAgentId());
    }
}
