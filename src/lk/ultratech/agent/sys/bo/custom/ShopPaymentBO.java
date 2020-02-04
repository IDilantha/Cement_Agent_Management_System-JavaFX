package lk.ultratech.agent.sys.bo.custom;

import lk.ultratech.agent.sys.bo.SuperBO;
import lk.ultratech.agent.sys.dto.ShopPaymentDTO;
import lk.ultratech.agent.sys.entity.ShopPayment;

import java.sql.SQLException;
import java.util.List;

public interface ShopPaymentBO extends SuperBO {
    String getLastShopPaymentId() throws SQLException, ClassNotFoundException;
    boolean saveShopPayment(ShopPaymentDTO paymentDTO) throws SQLException, ClassNotFoundException;
    ShopPaymentDTO getByPayId(String s) throws SQLException, ClassNotFoundException;
    List<ShopPaymentDTO> getAllShopPayments(String shopId) throws SQLException, ClassNotFoundException;
}
