package lk.ultratech.agent.sys.bo.custom.impl;

import lk.ultratech.agent.sys.bo.custom.DeliveryBO;
import lk.ultratech.agent.sys.dao.DAOFactory;
import lk.ultratech.agent.sys.dao.DAOType;
import lk.ultratech.agent.sys.dao.custom.DeliveryDAO;
import lk.ultratech.agent.sys.dto.DeliveryDTO;
import lk.ultratech.agent.sys.entity.Delivery;

import java.sql.SQLException;

public class DeliveryBOImpl implements DeliveryBO {
    private DeliveryDAO deliveryDAO;

    public DeliveryBOImpl(){
        this.deliveryDAO = DAOFactory.getInstance().getDAO(DAOType.DELIVERY);
    }

    @Override
    public String getLastDeliveryId() throws SQLException, ClassNotFoundException {
        return deliveryDAO.getLastDeliveryId();
    }

    @Override
    public boolean saveDelivery(DeliveryDTO dto) throws SQLException, ClassNotFoundException {
        return deliveryDAO.save(new Delivery(dto.getDeliveryId(),dto.getPurchaseOrderId(),dto.getType(),dto.getDate()));
    }
}
