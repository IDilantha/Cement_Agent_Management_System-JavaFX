package lk.ultratech.agent.sys.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ultratech.agent.sys.bo.BOFactory;
import lk.ultratech.agent.sys.bo.BOTypes;
import lk.ultratech.agent.sys.bo.custom.*;
import lk.ultratech.agent.sys.dto.*;
import lk.ultratech.agent.sys.entity.ShopPayment;
import lk.ultratech.agent.sys.tm.ActivityDeliveryTM;
import lk.ultratech.agent.sys.tm.ActivityPayTM;
import lk.ultratech.agent.sys.tm.ShopTM;

import java.sql.SQLException;
import java.util.List;

public class ShopActivityController {
    public AnchorPane anchorPane;
    public TableView<ActivityDeliveryTM> tblActivityDeliveryTM;
    public JFXTextField txtUnitPrice;
    public Label lblTotal;
    public JFXTextField txtShopId;
    public JFXTextField txtUnitPrice1;
    public TableView<ActivityPayTM> tblActivityPayTM;
    public JFXComboBox cmbShopName;

    double balance=0;
    double deliveryTotal=0;
    double payTotal=0;

    ShopDeliveryBO shopDeliveryBO= BOFactory.getInstance().getBO(BOTypes.SHOP_DELIVERY);
    ShopPaymentBO shopPaymentBO = BOFactory.getInstance().getBO(BOTypes.SHOP_PAYMENT);
    ShopBO shopBO = BOFactory.getInstance().getBO(BOTypes.SHOP);
    CementShopBO cementShopBO = BOFactory.getInstance().getBO(BOTypes.CEMENT_SHOP);

    public void initialize() throws SQLException, ClassNotFoundException {
        txtShopId.setDisable(true);
        txtUnitPrice.setDisable(true);
        txtUnitPrice1.setDisable(true);

        tblActivityDeliveryTM.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("deliverDate"));
        tblActivityDeliveryTM.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("deliveryId"));
        tblActivityDeliveryTM.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("type"));
        tblActivityDeliveryTM.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("qty"));
        tblActivityDeliveryTM.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("amount"));

        tblActivityPayTM.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("paidDate"));
        tblActivityPayTM.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("paymentId"));
        tblActivityPayTM.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("type"));
        tblActivityPayTM.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("amount"));

        loadShopCombo();

        cmbShopName.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                Object selectedItem = cmbShopName.getSelectionModel().getSelectedItem();
                try {
                    tblActivityPayTM.getItems().clear();
                    tblActivityDeliveryTM.getItems().clear();

                    ShopDTO shop = shopBO.getByShopName(selectedItem.toString());
                    txtShopId.setText(shop.getShopId());
                    CementShopDTO OPCPrice = cementShopBO.getCementPrice(new CementShopDTO2("C001", shop.getShopId()));
                    CementShopDTO PPCPrice = cementShopBO.getCementPrice(new CementShopDTO2("C002",shop.getShopId()));
                    txtUnitPrice.setText(String.valueOf(OPCPrice.getUnitPrice()));
                    txtUnitPrice1.setText(String.valueOf(PPCPrice.getUnitPrice()));

                    List<ShopDeliveryDTO> allShopDelivery = shopDeliveryBO.getAllShopDelivery(txtShopId.getText());
                    ObservableList<ActivityDeliveryTM> tm = tblActivityDeliveryTM.getItems();

                    double unit=0;
                    for (ShopDeliveryDTO delivery:allShopDelivery) {
                        if(delivery.getType()=="C001"){
                            unit=OPCPrice.getUnitPrice();
                        }else {
                            unit=PPCPrice.getUnitPrice();
                        }
                        tm.add(new ActivityDeliveryTM(delivery.getDate(),delivery.getDeliveryId(),delivery.getType(),delivery.getQty(),Double.valueOf(Double.valueOf(delivery.getQty())*unit)));
                        deliveryTotal+=Double.valueOf(Double.valueOf(delivery.getQty())*unit);
                    }

                    List<ShopPaymentDTO> shopPaymentDTOS = shopPaymentBO.getAllShopPayments(txtShopId.getText());
                    ObservableList<ActivityPayTM> payments = tblActivityPayTM.getItems();

                    for (ShopPaymentDTO dto1:shopPaymentDTOS) {
                        payments.add(new ActivityPayTM(dto1.getDate(),dto1.getShopPaymentId(),dto1.getType(),Double.valueOf(dto1.getAmount())));
                        payTotal+=Double.valueOf(dto1.getAmount());
                    }

                    balance = deliveryTotal - payTotal;
                    lblTotal.setText("Balance : "+balance);
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });

    }


    private void loadShopCombo() throws SQLException, ClassNotFoundException {
        List<String> allShopNames = shopBO.getAllShopNames();
        cmbShopName.setItems(FXCollections.observableArrayList(allShopNames));
    }
}
