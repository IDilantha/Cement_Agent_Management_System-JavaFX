package lk.ultratech.agent.sys.bo.custom;

import lk.ultratech.agent.sys.bo.SuperBO;
import lk.ultratech.agent.sys.dto.DeliveryDTO;

import java.sql.SQLException;

public interface DeliveryBO extends SuperBO {
    String getLastDeliveryId() throws SQLException, ClassNotFoundException;
    boolean saveDelivery(DeliveryDTO dto) throws SQLException, ClassNotFoundException;
}
