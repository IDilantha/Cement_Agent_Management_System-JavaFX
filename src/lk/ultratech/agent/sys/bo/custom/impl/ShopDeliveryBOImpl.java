package lk.ultratech.agent.sys.bo.custom.impl;

import lk.ultratech.agent.sys.bo.custom.ShopDeliveryBO;
import lk.ultratech.agent.sys.dao.DAOFactory;
import lk.ultratech.agent.sys.dao.DAOType;
import lk.ultratech.agent.sys.dao.custom.ShopDeliveryDAO;
import lk.ultratech.agent.sys.dto.ShopDeliveryDTO;
import lk.ultratech.agent.sys.dto.ShopPaymentDTO;
import lk.ultratech.agent.sys.entity.ShopDelivery;
import lk.ultratech.agent.sys.entity.ShopPayment;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ShopDeliveryBOImpl implements ShopDeliveryBO {
    private ShopDeliveryDAO shopDeliveryDAO;

    public ShopDeliveryBOImpl(){
        this.shopDeliveryDAO = DAOFactory.getInstance().getDAO(DAOType.SHOP_DELIVERY);
    }


    @Override
    public boolean saveShopDelivery(List<ShopDeliveryDTO> dto) throws SQLException, ClassNotFoundException {
    List<ShopDelivery> deliveryList = new ArrayList<>();
        for (ShopDeliveryDTO dto1:dto) {
            deliveryList.add(new ShopDelivery(dto1.getDeliveryId(),dto1.getShopId(),dto1.getType(),dto1.getDate(),dto1.getQty()));
        }
        return shopDeliveryDAO.saveAll(deliveryList);
    }

    @Override
    public List<ShopDeliveryDTO> getAllShopDelivery(String shopId) throws SQLException, ClassNotFoundException {

        List<ShopDelivery> allShopDelivery = shopDeliveryDAO.getAllShopDelivery(shopId);
        List<ShopDeliveryDTO> dtos = new ArrayList<>();
        for (ShopDelivery sp:allShopDelivery) {
            dtos.add(new ShopDeliveryDTO(sp.getShopDeliveryPK().getDeliveryId(),sp.getShopDeliveryPK().getShopId(),sp.getDate(),sp.getType(),sp.getQty()));
        }
        return dtos;
    }
}
