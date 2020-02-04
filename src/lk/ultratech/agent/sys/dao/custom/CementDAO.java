package lk.ultratech.agent.sys.dao.custom;

import lk.ultratech.agent.sys.dao.CrudDAO;
import lk.ultratech.agent.sys.entity.Cement;

import java.sql.SQLException;
import java.util.List;

public interface CementDAO extends CrudDAO<Cement,String> {
    String getIdByName(String name) throws SQLException, ClassNotFoundException;
    String getNameById(String id) throws SQLException, ClassNotFoundException;
}
