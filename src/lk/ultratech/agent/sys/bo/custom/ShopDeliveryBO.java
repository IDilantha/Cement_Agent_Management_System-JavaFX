package lk.ultratech.agent.sys.bo.custom;

import lk.ultratech.agent.sys.bo.SuperBO;
import lk.ultratech.agent.sys.dto.ShopDeliveryDTO;
import lk.ultratech.agent.sys.entity.ShopDelivery;

import java.sql.SQLException;
import java.util.List;

public interface ShopDeliveryBO extends SuperBO {
    boolean saveShopDelivery(List<ShopDeliveryDTO> dto) throws SQLException, ClassNotFoundException;
    List<ShopDeliveryDTO> getAllShopDelivery(String shopId) throws SQLException, ClassNotFoundException;
}
