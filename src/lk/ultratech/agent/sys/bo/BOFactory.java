package lk.ultratech.agent.sys.bo;

import lk.ultratech.agent.sys.bo.custom.impl.*;
import lk.ultratech.agent.sys.dao.DAOType;

public class BOFactory {
    private static  BOFactory boFactory;

    private BOFactory() {
    }

    public static BOFactory getInstance(){
        return (boFactory == null)? (boFactory = new BOFactory()): boFactory;
    }

    public <T extends SuperBO> T getBO(BOTypes boType){
        switch (boType){
            case WORKER:
                return (T) new WorkerBOImpl();
            case WORKER_PAYMENT:
                return (T) new WorkerPaymentBOImpl();
            case SHOP:
                return (T) new ShopBOImpl();
            case AGENT:
                return (T) new AgentBOImpl();
            case CEMENT:
                return (T) new CementBOImpl();
            case CEMENT_AGENT:
                return (T) new CementAgentBOImpl();
            case CEMENT_SHOP:
                return (T) new CementShopBOImpl();
            case PURCHASE_ORDER:
                return (T) new PurchaseOrderBOImpl();
            case SHOP_ORDER:
                return (T) new ShopOrderBOImpl();
            case DELIVERY:
                return (T) new DeliveryBOImpl();
            case SHOP_DELIVERY:
                return (T) new ShopDeliveryBOImpl();
            case SHOP_PAYMENT:
                return (T) new ShopPaymentBOImpl();
            case CHEQUE_PAYMENT:
                return (T) new ChequePaymentBOImpl();
            default:
                return null;
        }
    }
}
