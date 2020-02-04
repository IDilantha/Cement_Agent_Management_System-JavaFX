package lk.ultratech.agent.sys.bo.custom;

import lk.ultratech.agent.sys.bo.SuperBO;
import lk.ultratech.agent.sys.dto.CementAgentDTO2;
import lk.ultratech.agent.sys.dto.CementShopDTO;
import lk.ultratech.agent.sys.dto.CementShopDTO2;

import java.sql.SQLException;
import java.util.List;

public interface CementShopBO extends SuperBO {
    boolean save(CementShopDTO entity) throws SQLException, ClassNotFoundException;
    boolean update(CementShopDTO entity) throws SQLException, ClassNotFoundException;
    CementShopDTO getCementPrice(CementShopDTO2 pk) throws SQLException, ClassNotFoundException;
    List<CementShopDTO> getAllCementAgent() throws SQLException, ClassNotFoundException;
    boolean increaseAllPrices(String type,double price) throws SQLException, ClassNotFoundException;
    boolean decreaseAllPrices(String type,double price) throws SQLException, ClassNotFoundException;
}
