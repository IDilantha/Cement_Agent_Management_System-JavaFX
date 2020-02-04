package lk.ultratech.agent.sys.bo.custom.impl;

import lk.ultratech.agent.sys.bo.custom.ShopBO;
import lk.ultratech.agent.sys.dao.DAOFactory;
import lk.ultratech.agent.sys.dao.DAOType;
import lk.ultratech.agent.sys.dao.custom.ShopDAO;
import lk.ultratech.agent.sys.dto.ShopDTO;
import lk.ultratech.agent.sys.dto.WorkerDTO;
import lk.ultratech.agent.sys.entity.Shop;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ShopBOImpl implements ShopBO {

    private ShopDAO shopDAO;

    public ShopBOImpl(){
        this.shopDAO = DAOFactory.getInstance().getDAO(DAOType.SHOP);
    }

    @Override
    public boolean saveShop(ShopDTO entity) throws SQLException, ClassNotFoundException {
        return shopDAO.save(new Shop(entity.getShopId(),entity.getShopName(),entity.getAddress(),entity.getTelephone()));
    }

    @Override
    public boolean deleteShop(String s) throws SQLException, ClassNotFoundException {
        return shopDAO.delete(s);
    }

    @Override
    public boolean updateShop(ShopDTO entity) throws SQLException, ClassNotFoundException {
        return shopDAO.update(new Shop(entity.getShopId(),entity.getShopName(),entity.getAddress(),entity.getTelephone()));
    }

    @Override
    public ShopDTO getShopById(String s) throws SQLException, ClassNotFoundException {
        Shop shop = shopDAO.get(s);
        return new ShopDTO(shop.getShopId(),shop.getShopName(),shop.getAddress(),shop.getTelephone());
    }

    @Override
    public List<ShopDTO> getAllShops() throws SQLException, ClassNotFoundException {
       List<Shop> all = shopDAO.getAll();
       List<ShopDTO> shopDTOS = new ArrayList<>();
        for (Shop shop:all) {
            shopDTOS.add(new ShopDTO(shop.getShopId(),shop.getShopName(),shop.getAddress(),shop.getTelephone()));
        }
        return shopDTOS;
    }

    @Override
    public List<String> getAllShopNames() throws SQLException, ClassNotFoundException {
        List<Shop> shopList = shopDAO.getAll();
        List<String> names = new ArrayList<>();
        for (Shop shop:shopList ) {
            names.add(shop.getShopName());
        }
        return names;
    }

    @Override
    public String getLastShopId() throws SQLException, ClassNotFoundException {
        return shopDAO.getLastShopId();
    }

    @Override
    public ShopDTO getByShopName(String shopName) throws SQLException, ClassNotFoundException {
        Shop shop= shopDAO.getByShopName(shopName);
        return new ShopDTO(shop.getShopId(),shop.getShopName(),shop.getAddress(),shop.getTelephone());
    }
}
