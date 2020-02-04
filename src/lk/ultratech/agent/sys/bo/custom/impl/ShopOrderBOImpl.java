package lk.ultratech.agent.sys.bo.custom.impl;

import lk.ultratech.agent.sys.bo.custom.ShopOrderBO;
import lk.ultratech.agent.sys.dao.DAOFactory;
import lk.ultratech.agent.sys.dao.DAOType;
import lk.ultratech.agent.sys.dao.custom.ShopOrderDAO;
import lk.ultratech.agent.sys.dto.ShopOrderDTO;
import lk.ultratech.agent.sys.entity.ShopOrder;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ShopOrderBOImpl implements ShopOrderBO {
    private ShopOrderDAO shopOrderDAO;

    public ShopOrderBOImpl(){
        this.shopOrderDAO= DAOFactory.getInstance().getDAO(DAOType.SHOP_ORDER);
    }
    @Override
    public String getLastShopOrderId() throws SQLException, ClassNotFoundException {
        return shopOrderDAO.getLastShopOrderId();
    }

    @Override
    public boolean saveShopOrder(ShopOrderDTO shopOrderDTO) throws SQLException, ClassNotFoundException {
        return shopOrderDAO.save(new ShopOrder(shopOrderDTO.getShopOrderId(),shopOrderDTO.getShopId(),shopOrderDTO.getType(),shopOrderDTO.getoDate(),shopOrderDTO.getdDate(),shopOrderDTO.getQty()));
    }

    @Override
    public List<ShopOrderDTO> getAllShopOrders() throws SQLException, ClassNotFoundException {
        List<ShopOrder> all = shopOrderDAO.getAll();
        List<ShopOrderDTO> shopDTOS = new ArrayList<>();
        for (ShopOrder shopOrders:all) {
            shopDTOS.add(new ShopOrderDTO(shopOrders.getShopOrderId(),shopOrders.getShopId(),shopOrders.getType(),shopOrders.getoDate(),shopOrders.getdDate(),shopOrders.getQty()));
        }
        return shopDTOS;
    }

    @Override
    public List<ShopOrderDTO> getAllShopOrdersByType(String type) throws SQLException, ClassNotFoundException {
        List<ShopOrder> all = shopOrderDAO.getAllByType(type);
        List<ShopOrderDTO> shopDTOS = new ArrayList<>();
        for (ShopOrder shopOrders:all) {
            shopDTOS.add(new ShopOrderDTO(shopOrders.getShopOrderId(),shopOrders.getShopId(),shopOrders.getType(),shopOrders.getoDate(),shopOrders.getdDate(),shopOrders.getQty()));
        }
        return shopDTOS;
    }

    @Override
    public boolean deleteShopOrder(String shopId) throws SQLException, ClassNotFoundException {
        return shopOrderDAO.delete(shopId);
    }
}
