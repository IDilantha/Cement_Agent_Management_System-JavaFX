package lk.ultratech.agent.sys.bo.custom.impl;

import lk.ultratech.agent.sys.bo.custom.ShopPaymentBO;
import lk.ultratech.agent.sys.dao.DAOFactory;
import lk.ultratech.agent.sys.dao.DAOType;
import lk.ultratech.agent.sys.dao.custom.ShopOrderDAO;
import lk.ultratech.agent.sys.dao.custom.ShopPaymentDAO;
import lk.ultratech.agent.sys.dto.ShopPaymentDTO;
import lk.ultratech.agent.sys.entity.ShopPayment;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ShopPaymentBOImpl implements ShopPaymentBO {

    private ShopPaymentDAO shopPaymentDAO;

    public ShopPaymentBOImpl(){
        this.shopPaymentDAO= DAOFactory.getInstance().getDAO(DAOType.SHOP_PAYMENT);
    }

    @Override
    public String getLastShopPaymentId() throws SQLException, ClassNotFoundException {
        return shopPaymentDAO.getLastShopPaymentId();
    }

    @Override
    public boolean saveShopPayment(ShopPaymentDTO paymentDTO) throws SQLException, ClassNotFoundException {
        return shopPaymentDAO.save(new ShopPayment(paymentDTO.getShopPaymentId(),paymentDTO.getShopId(),paymentDTO.getType(),paymentDTO.getDate(),paymentDTO.getAmount()));
    }

    @Override
    public ShopPaymentDTO getByPayId(String s) throws SQLException, ClassNotFoundException {
        ShopPayment shopPay = shopPaymentDAO.get(s);
        return new ShopPaymentDTO(shopPay.getShopPaymentId(),shopPay.getShopId(),shopPay.getType(),shopPay.getDate(),shopPay.getAmount());
    }

    @Override
    public List<ShopPaymentDTO> getAllShopPayments(String shopId) throws SQLException, ClassNotFoundException {
        List<ShopPayment> allShopPayments = shopPaymentDAO.getAllShopPayments(shopId);
        List<ShopPaymentDTO> dtos = new ArrayList<>();
        for (ShopPayment sp:allShopPayments) {
            dtos.add(new ShopPaymentDTO(sp.getShopPaymentId(),sp.getShopId(),sp.getType(),sp.getDate(),sp.getAmount()));
        }
        return dtos;
    }
}
