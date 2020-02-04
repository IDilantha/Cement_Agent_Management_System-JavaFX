package lk.ultratech.agent.sys.bo.custom.impl;

import lk.ultratech.agent.sys.bo.custom.CementShopBO;
import lk.ultratech.agent.sys.dao.DAOFactory;
import lk.ultratech.agent.sys.dao.DAOType;
import lk.ultratech.agent.sys.dao.custom.CementShopDAO;
import lk.ultratech.agent.sys.dto.CementAgentDTO2;
import lk.ultratech.agent.sys.dto.CementShopDTO;
import lk.ultratech.agent.sys.dto.CementShopDTO2;
import lk.ultratech.agent.sys.entity.CementShop;
import lk.ultratech.agent.sys.entity.CementShopPK;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CementShopBOImpl implements CementShopBO {
    private CementShopDAO cementShopDAO;

    public CementShopBOImpl(){
        this.cementShopDAO = DAOFactory.getInstance().getDAO(DAOType.CEMENT_SHOP);
    }

    @Override
    public boolean save(CementShopDTO entity) throws SQLException, ClassNotFoundException {
        return cementShopDAO.save(new CementShop(entity.getCementId(),entity.getShopId(),entity.getUnitPrice()));
    }

    @Override
    public boolean update(CementShopDTO entity) throws SQLException, ClassNotFoundException {
        return cementShopDAO.update(new CementShop(entity.getCementId(),entity.getShopId(),entity.getUnitPrice()));
    }

    @Override
    public CementShopDTO getCementPrice(CementShopDTO2 pk) throws SQLException, ClassNotFoundException {
        CementShop cementShop= cementShopDAO.get(new CementShopPK(pk.getCementId(),pk.getShopId()));
        CementShopDTO dto = new CementShopDTO(cementShop.getCementShopPK().getCementId(),cementShop.getCementShopPK().getShopId(),cementShop.getUnitPrice());
        return dto;
    }

    @Override
    public List<CementShopDTO> getAllCementAgent() throws SQLException, ClassNotFoundException {
        List<CementShop> list = cementShopDAO.getAll();
        List<CementShopDTO> dtos = new ArrayList<>();
        for (CementShop shop :list  ) {
            dtos.add(new CementShopDTO(shop.getCementShopPK().getCementId(),shop.getCementShopPK().getShopId(),shop.getUnitPrice()));
        }
        return null;
    }

    @Override
    public boolean increaseAllPrices(String type, double price) throws SQLException, ClassNotFoundException {
        return cementShopDAO.increaseAllPrices(type,price);
    }

    @Override
    public boolean decreaseAllPrices(String type, double price) throws SQLException, ClassNotFoundException {
        return cementShopDAO.decreaseAllPrices(type,price);
    }
}
