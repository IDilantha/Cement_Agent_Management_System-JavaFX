package lk.ultratech.agent.sys.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ultratech.agent.sys.bo.BOFactory;
import lk.ultratech.agent.sys.bo.BOTypes;
import lk.ultratech.agent.sys.bo.custom.CementShopBO;
import lk.ultratech.agent.sys.bo.custom.ShopBO;
import lk.ultratech.agent.sys.dto.CementShopDTO;
import lk.ultratech.agent.sys.dto.CementShopDTO2;
import lk.ultratech.agent.sys.dto.ShopDTO;
import lk.ultratech.agent.sys.dto.WorkerDTO;
import lk.ultratech.agent.sys.entity.CementShop;
import lk.ultratech.agent.sys.tm.ShopTM;
import lk.ultratech.agent.sys.tm.WorkerTM;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ManageShopController {

    public AnchorPane anchorPane;
    public TableView tblShop;
    public TableColumn clmShopId;
    public TableColumn clmShopName;
    public TableColumn clmShopAdresss;
    public TableColumn clmShopTele;
    public JFXTextField txtShopId;
    public JFXButton btnUpdate;
    public JFXButton btnDelete;
    public JFXTextField txtShopAddress;
    public JFXTextField txtShopTele;
    public JFXTextField txtShopName;
    public JFXTextField txtOPCUnitPrice;
    public Label lblSetCement;
    public Label lblOPC;
    public JFXTextField txtPPCUnitPrice;
    public Label lblPPC;

    private ShopBO shopBO = BOFactory.getInstance().getBO(BOTypes.SHOP);
    private CementShopBO cementShop = BOFactory.getInstance().getBO(BOTypes.CEMENT_SHOP);

    public void initialize() throws SQLException, ClassNotFoundException {
        clmShopId.setCellValueFactory(new PropertyValueFactory<>("shopId"));
        clmShopName.setCellValueFactory(new PropertyValueFactory<>("shopName"));
        clmShopAdresss.setCellValueFactory(new PropertyValueFactory<>("address"));
        clmShopTele.setCellValueFactory(new PropertyValueFactory<>("telephone"));
        txtShopId.setDisable(true);
        loadAllShops();

            tblShop.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<ShopTM>() {
            @Override
            public void changed(ObservableValue<? extends ShopTM> observable, ShopTM oldValue, ShopTM newValue) {
                ShopTM selectedShop = (ShopTM) tblShop.getSelectionModel().getSelectedItem();
                if (selectedShop==null){
                    btnDelete.setDisable(true);
                }
                txtShopId.setText(selectedShop.getShopId());
                txtShopAddress.setText(selectedShop.getAddress());
                txtShopTele.setText(String.valueOf(selectedShop.getTelephone()));
                txtShopName.setText(selectedShop.getShopName());
                try {
                    CementShopDTO OPCPrice = cementShop.getCementPrice(new CementShopDTO2("C001", selectedShop.getShopId()));
                    CementShopDTO PPCPrice = cementShop.getCementPrice(new CementShopDTO2("C002",selectedShop.getShopId()));
                    txtOPCUnitPrice.setText(String.valueOf(OPCPrice.getUnitPrice()));
                    txtPPCUnitPrice.setText(String.valueOf(PPCPrice.getUnitPrice()));
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    private void loadAllShops() throws SQLException, ClassNotFoundException {
        List<ShopDTO> allShops = shopBO.getAllShops();
        ObservableList<ShopTM> tm = tblShop.getItems();

        for (ShopDTO shopDTO:allShops) {
            tm.add(new ShopTM(shopDTO.getShopId(),shopDTO.getShopName(),shopDTO.getAddress(),shopDTO.getTelephone()));
        }
    }

    public void updateAction(ActionEvent actionEvent) {
        ShopDTO dto = new ShopDTO(txtShopId.getText(),txtShopName.getText(),txtShopAddress.getText(),Integer.valueOf(txtShopTele.getText()));
        ShopTM selectedShop = (ShopTM) tblShop.getSelectionModel().getSelectedItem();
        try {
            boolean ss =shopBO.updateShop(dto);
            selectedShop.setShopName(txtShopName.getText());
            selectedShop.setTelephone(Integer.valueOf(txtShopTele.getText()));
            selectedShop.setAddress(txtShopAddress.getText());
            tblShop.refresh();

            boolean cs=cementShop.update(new CementShopDTO("C001",txtShopId.getText(),Double.valueOf(txtOPCUnitPrice.getText())));
            boolean cs1=cementShop.update(new CementShopDTO("C002",txtShopId.getText(),Double.valueOf(txtPPCUnitPrice.getText())));
            if (ss && cs && cs1){
                new Alert(Alert.AlertType.INFORMATION,"Shop Details Updated Successfully").show();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,"Something went wrong").show();
        }
    }

    public void deleteAction(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Are you sure to delete this Shop?",ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> buttonType = alert.showAndWait();
        if (buttonType.get() == ButtonType.YES) {
            ShopTM selectedItem = (ShopTM) tblShop.getSelectionModel().getSelectedItem();
            try {
                shopBO.deleteShop(selectedItem.getShopId());
                tblShop.getItems().remove(selectedItem);
            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR,"Cannot Delete Shop,Shop Details are Used").show();
            }
        }
    }
}
