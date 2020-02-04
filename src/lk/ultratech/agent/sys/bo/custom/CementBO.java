package lk.ultratech.agent.sys.bo.custom;

import lk.ultratech.agent.sys.bo.SuperBO;

import java.sql.SQLException;
import java.util.List;

public interface CementBO extends SuperBO {
    List<String> getAllCementNames() throws SQLException, ClassNotFoundException;
    String getCementId(String cementName) throws SQLException, ClassNotFoundException;
    String getCementName(String cementId) throws SQLException, ClassNotFoundException;
}
