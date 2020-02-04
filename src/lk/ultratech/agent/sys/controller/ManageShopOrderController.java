package lk.ultratech.agent.sys.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
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
import lk.ultratech.agent.sys.bo.custom.CementBO;
import lk.ultratech.agent.sys.bo.custom.CementShopBO;
import lk.ultratech.agent.sys.bo.custom.ShopBO;
import lk.ultratech.agent.sys.bo.custom.ShopOrderBO;
import lk.ultratech.agent.sys.dto.CementShopDTO;
import lk.ultratech.agent.sys.dto.CementShopDTO2;
import lk.ultratech.agent.sys.dto.ShopOrderDTO;
import lk.ultratech.agent.sys.dto.WorkerDTO;
import lk.ultratech.agent.sys.entity.ShopOrder;
import lk.ultratech.agent.sys.tm.ShopOrderTM;
import lk.ultratech.agent.sys.tm.ShopTM;
import lk.ultratech.agent.sys.tm.WorkerTM;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ManageShopOrderController {
    public AnchorPane anchorPane;
    public TableView<ShopOrderTM> tblShopOrder;
    public TableColumn clmShopOID;
    public TableColumn clmShopId;
    public TableColumn clmShopName;
    public TableColumn clmODate;
    public TableColumn clmType;
    public TableColumn clmQty;
    public TableColumn clmDDate;
    public JFXTextField txtShopOId;
    public JFXTextField txtxShopName;
    public JFXTextField txtUnitPrice;
    public JFXTextField txtQty;
    public Label lblTotal;
    public JFXDatePicker dateDueDate;
    public JFXTextField txtShopId;
    public JFXComboBox cmbCementType;
    public JFXButton btnUpdate;
    public JFXButton btnDelete;

    ShopOrderBO shopOrderBO = BOFactory.getInstance().getBO(BOTypes.SHOP_ORDER);
    ShopBO shopBO = BOFactory.getInstance().getBO(BOTypes.SHOP);
    CementBO cementBO = BOFactory.getInstance().getBO(BOTypes.CEMENT);
    CementShopBO cementShopBO = BOFactory.getInstance().getBO(BOTypes.CEMENT_SHOP);

    public void initialize() throws SQLException, ClassNotFoundException {
        clmDDate.setCellValueFactory(new PropertyValueFactory<>("dueDate"));
        clmODate.setCellValueFactory(new PropertyValueFactory<>("orderDate"));
        clmQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        clmShopId.setCellValueFactory(new PropertyValueFactory<>("shopId"));
        clmShopName.setCellValueFactory(new PropertyValueFactory<>("shopName"));
        clmType.setCellValueFactory(new PropertyValueFactory<>("type"));
        clmShopOID.setCellValueFactory(new PropertyValueFactory<>("shopOrderId"));

        loadAllShopOrders();

        /*tblShopOrder.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<ShopOrderTM>() {

            @Override
            public void changed(ObservableValue<? extends ShopOrderTM> observable, ShopOrderTM oldValue, ShopOrderTM newValue) {
                ShopOrderTM item = tblShopOrder.getSelectionModel().getSelectedItem();
                txtShopOId.setText(item.getShopOrderId());
                txtShopId.setText(item.getShopId());
                txtxShopName.setText(item.getShopName());
                txtQty.setText(String.valueOf(item.getQty()));

                try {
                    CementShopDTO cementPrice = cementShopBO.getCementPrice(new CementShopDTO2(item.getShopId(), txtShopId.getText()));
                    txtUnitPrice.setText(String.valueOf(cementPrice.getUnitPrice()));
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

            }
        });*/
    }

    private void loadAllShopOrders() throws SQLException, ClassNotFoundException {
        List<ShopOrderDTO> allShopOrders = shopOrderBO.getAllShopOrders();
        ObservableList<ShopOrderTM> tm = tblShopOrder.getItems();
        for (ShopOrderDTO DTO:allShopOrders) {
            String shopName = shopBO.getShopById(DTO.getShopId()).getShopName();
            tm.add(new ShopOrderTM(DTO.getShopOrderId(),DTO.getShopId(),shopName,DTO.getType(),DTO.getoDate(),DTO.getdDate(),DTO.getQty()));
        }
    }

    public void updateShopOrder(ActionEvent actionEvent) {
        ShopOrderDTO shopOrderDTO = new ShopOrderDTO();
    }

    public void deleteShopOrder(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Are you sure to delete this Shop Order?",ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> buttonType = alert.showAndWait();
        if (buttonType.get() == ButtonType.YES) {
            ShopOrderTM selectedItem = (ShopOrderTM) tblShopOrder.getSelectionModel().getSelectedItem();
            try {
                shopOrderBO.deleteShopOrder(selectedItem.getShopOrderId());
                tblShopOrder.getItems().remove(selectedItem);
            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR,"Cannot Delete Shop,Shop Details are Used").show();
            }
        }
    }

}
