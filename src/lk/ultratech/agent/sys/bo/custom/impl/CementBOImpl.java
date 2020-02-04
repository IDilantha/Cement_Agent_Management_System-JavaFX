package lk.ultratech.agent.sys.bo.custom.impl;

import lk.ultratech.agent.sys.bo.BOFactory;
import lk.ultratech.agent.sys.bo.BOTypes;
import lk.ultratech.agent.sys.bo.custom.CementBO;
import lk.ultratech.agent.sys.dao.DAOFactory;
import lk.ultratech.agent.sys.dao.DAOType;
import lk.ultratech.agent.sys.dao.custom.CementDAO;
import lk.ultratech.agent.sys.entity.Cement;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CementBOImpl implements CementBO {

    private CementDAO cementDAO;

    public CementBOImpl(){
        this.cementDAO = DAOFactory.getInstance().getDAO(DAOType.CEMENT);
    }

    @Override
    public List<String> getAllCementNames() throws SQLException, ClassNotFoundException {
        List<Cement> cementList = cementDAO.getAll();
        List<String> names = new ArrayList<>();
        for (Cement cement:cementList ) {
            names.add(cement.getName());
        }
        return names;
    }

    @Override
    public String getCementId(String cementName) throws SQLException, ClassNotFoundException {
        return cementDAO.getIdByName(cementName);
    }

    @Override
    public String getCementName(String cementId) throws SQLException, ClassNotFoundException {
        return cementDAO.getNameById(cementId);
    }
}
