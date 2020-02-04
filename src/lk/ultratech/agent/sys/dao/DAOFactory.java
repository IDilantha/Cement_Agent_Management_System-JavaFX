package lk.ultratech.agent.sys.dao;

import lk.ultratech.agent.sys.dao.custom.impl.*;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory() {

    }

    public static  DAOFactory getInstance(){
        return (daoFactory == null) ? (daoFactory = new DAOFactory()) : daoFactory;
    }


    public <T> T getDAO(DAOType daoType){
        switch (daoType){
            case AGENT:
                return (T) new AgentDAOImpl();
            case CEMENT:
                return (T) new CementDAOImpl();
            case CEMENT_AGENT:
                return (T) new CementAgentDAOImpl();
            case CEMENT_SHOP:
                return (T) new CementShopDAOImpl();
            case CHEQUE_PAYMENT:
                return (T) new ChequePaymentDAOImpl();
            case DELIVERY:
                return (T) new DeliveryDAOImpl();
            case PURCHASE_ORDER:
                return (T) new PurchaseOrderDAOImpl();
            case SHOP:
                return (T) new ShopDAOImpl();
            case SHOP_DELIVERY:
                return (T) new ShopDeliveryDAOImpl();
            case SHOP_ORDER:
                return (T) new ShopOrderDAOImpl();
            case SHOP_PAYMENT:
                return (T) new ShopPaymentDAOImpl();
            case WORKER_PAYMENT:
                return (T) new WorkerPaymentDAOImpl();
            case QUERY:
                return (T) new QueryDAOImpl();
            case WORKER:
                return (T) new WorkerDAOImpl();
            default:
                return null;
        }
    }
}
