package lk.ultratech.agent.sys.bo.custom.impl;

import lk.ultratech.agent.sys.bo.custom.PurchaseOrderBO;
import lk.ultratech.agent.sys.dao.DAOFactory;
import lk.ultratech.agent.sys.dao.DAOType;
import lk.ultratech.agent.sys.dao.custom.PurchaseOrderDAO;
import lk.ultratech.agent.sys.dto.PurchaseOrderDTO;
import lk.ultratech.agent.sys.dto.WorkerPaymentDTO;
import lk.ultratech.agent.sys.entity.PurchaseOrder;
import lk.ultratech.agent.sys.entity.Shop;
import lk.ultratech.agent.sys.entity.WorkerPayment;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PurchaseOrderBOImpl implements PurchaseOrderBO {
    private PurchaseOrderDAO purchaseOrderDAO;
    public PurchaseOrderBOImpl(){
        this.purchaseOrderDAO = DAOFactory.getInstance().getDAO(DAOType.PURCHASE_ORDER);
    }

    @Override
    public boolean savePurchaseOrder(PurchaseOrderDTO dto) throws SQLException, ClassNotFoundException {
        return purchaseOrderDAO.save(new PurchaseOrder(dto.getPurchaseOrderId(),dto.getAgentId(),dto.getCementId(),dto.getoDate(),dto.getQty(),dto.getChequeNumber(),dto.getdDate(),dto.getAmount()));
    }

    @Override
    public String getLastPOId() throws SQLException, ClassNotFoundException {
        return purchaseOrderDAO.getLastPOId();
    }

    @Override
    public List<PurchaseOrderDTO> getAllPO() throws SQLException, ClassNotFoundException {
        List<PurchaseOrder> purchaseOrderList = purchaseOrderDAO.getAll();
        List<PurchaseOrderDTO> purchaseOrderDTOS = new ArrayList<>();
        for (PurchaseOrder purchaseOrder : purchaseOrderList) {
           purchaseOrderDTOS.add(new PurchaseOrderDTO(purchaseOrder.getPurchaseOrderId(),purchaseOrder.getAgentId(),purchaseOrder.getCementId(),purchaseOrder.getoDate(),purchaseOrder.getQty(),purchaseOrder.getChequeNumber(),purchaseOrder.getdDate(),purchaseOrder.getAmount()));
        }
        return purchaseOrderDTOS;
    }

    @Override
    public List<String> getAllPOId() throws SQLException, ClassNotFoundException {
        List<PurchaseOrder> purchaseOrderListList = purchaseOrderDAO.getAll();
        List<String> id = new ArrayList<>();
        for (PurchaseOrder order:purchaseOrderListList ) {
            id.add(order.getPurchaseOrderId());
        }
        return id;

    }

    @Override
    public PurchaseOrderDTO getPOById(String pOId) throws SQLException, ClassNotFoundException {
        PurchaseOrder pOrder = purchaseOrderDAO.get(pOId);
        return new PurchaseOrderDTO(pOrder.getPurchaseOrderId(),pOrder.getAgentId(),pOrder.getCementId(),pOrder.getoDate(),pOrder.getQty(),pOrder.getChequeNumber(),pOrder.getdDate(),pOrder.getAmount());
    }
}
