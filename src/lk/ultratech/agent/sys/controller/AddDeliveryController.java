package lk.ultratech.agent.sys.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ultratech.agent.sys.bo.BOFactory;
import lk.ultratech.agent.sys.bo.BOTypes;
import lk.ultratech.agent.sys.bo.custom.*;
import lk.ultratech.agent.sys.dto.*;
import lk.ultratech.agent.sys.entity.ShopOrder;
import lk.ultratech.agent.sys.tm.ShopDeliveryTM;
import lk.ultratech.agent.sys.tm.ShopOrderOnDeliveryTM;
import lk.ultratech.agent.sys.tm.ShopOrderTM;

import java.sql.SQLException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AddDeliveryController {
    public AnchorPane anchorPane;
    public JFXTextField txtUnitPrice;
    public JFXTextField txtQtyOnHand;
    public Label lblTotal;
    public JFXTextField txtCementType;
    public JFXComboBox cmbShopName;
    public JFXComboBox cmbPO;
    public Label lblId;
    public Label lblDate;
    public JFXTextField txtQty;
    public TableView<ShopDeliveryTM> tblDeliveryDetails;
    public TableColumn clm2DId;
    public TableColumn clm2ShopId;
    public TableColumn clm2ShopName;
    public TableColumn clm2Type;
    public TableColumn clm2Qty;
    public TableColumn clm2UPrice;
    public TableColumn clm2Amount;
    public TableColumn clm2Delete;
    public JFXButton btnAdd;
    public TableView tblShopOrders;
    public TableColumn clmShopOId;
    public TableColumn clmShopId;
    public TableColumn clmShopName;
    public TableColumn clmType;
    public TableColumn clmDDate;
    public TableColumn clmQty;
    public TableColumn clmUnitPrice;
    public JFXButton btnPlaceDelivery;
    public Label lblDeliveryId;
    private int qtyOnHand;
    private String cementID;

    ShopOrderBO shopOrderBO = BOFactory.getInstance().getBO(BOTypes.SHOP_ORDER);
    DeliveryBO deliveryBO = BOFactory.getInstance().getBO(BOTypes.DELIVERY);
    ShopDeliveryBO shopDeliveryBO = BOFactory.getInstance().getBO(BOTypes.SHOP_DELIVERY);
    PurchaseOrderBO purchaseOrderBO = BOFactory.getInstance().getBO(BOTypes.PURCHASE_ORDER);
    CementBO cementBO = BOFactory.getInstance().getBO(BOTypes.CEMENT);
    ShopBO shopBO = BOFactory.getInstance().getBO(BOTypes.SHOP);
    CementShopBO cementShopBO = BOFactory.getInstance().getBO(BOTypes.CEMENT_SHOP);

    public void initialize() throws SQLException, ClassNotFoundException {
        getNewId();
        SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
        String date = DATE_FORMAT.format(new Date());
        lblDate.setText(date);

        clm2Amount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        clm2DId.setCellValueFactory(new PropertyValueFactory<>("deliveryId"));
        clm2Qty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        clm2ShopId.setCellValueFactory(new PropertyValueFactory<>("shopId"));
        clm2ShopName.setCellValueFactory(new PropertyValueFactory<>("shopName"));
        clm2Type.setCellValueFactory(new PropertyValueFactory<>("type"));
        clm2UPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        clm2Delete.setCellValueFactory(new PropertyValueFactory<>("btnDelete"));

        clmDDate.setCellValueFactory(new PropertyValueFactory<>("dueDate"));
        clmQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        clmShopId.setCellValueFactory(new PropertyValueFactory<>("shopId"));
        clmShopOId.setCellValueFactory(new PropertyValueFactory<>("shopOrderId"));
        clmType.setCellValueFactory(new PropertyValueFactory<>("type"));
        clmUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        clmShopName.setCellValueFactory(new PropertyValueFactory<>("shopName"));


        txtCementType.setDisable(true);
        txtUnitPrice.setDisable(true);
        txtQtyOnHand.setDisable(true);
        txtQty.setDisable(true);

        List<String> allPOId = purchaseOrderBO.getAllPOId();
        cmbPO.setItems(FXCollections.observableArrayList(allPOId));


        cmbPO.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                try {
                    PurchaseOrderDTO poById = purchaseOrderBO.getPOById(cmbPO.getSelectionModel().getSelectedItem().toString());
                    cementID = poById.getCementId();
                    String cementName = cementBO.getCementName(cementID);
                    tblShopOrders.getItems().clear();
                    loadShopOrdersTable(cementName);

                    txtCementType.setText(cementName);
                    qtyOnHand=poById.getQty();
                    txtQtyOnHand.setText(String.valueOf(qtyOnHand));
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });

        tblShopOrders.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<ShopOrderOnDeliveryTM>(){

            @Override
            public void changed(ObservableValue<? extends ShopOrderOnDeliveryTM> observable, ShopOrderOnDeliveryTM oldValue, ShopOrderOnDeliveryTM newValue) {
                ShopOrderOnDeliveryTM select = (ShopOrderOnDeliveryTM) tblShopOrders.getSelectionModel().getSelectedItem();
                try {
                    txtQty.setText(String.valueOf(select.getQty()));
                    txtUnitPrice.setText(String.valueOf(select.getUnitPrice()));
                    calculateTotal();
                }catch ( NullPointerException e){
                    System.out.println("Table is empty");
                }
            }
        });

    }

    private void loadShopOrdersTable(String name) throws SQLException, ClassNotFoundException {
        List<ShopOrderDTO> allShopOrders = shopOrderBO.getAllShopOrdersByType(name);
        ObservableList<ShopOrderOnDeliveryTM> onDeliveryTMS= tblShopOrders.getItems();
        for (ShopOrderDTO dto : allShopOrders){
            String shopName = shopBO.getShopById(dto.getShopId()).getShopName();
            String cementId = cementBO.getCementId(dto.getType());
            double unitPrice = cementShopBO.getCementPrice(new CementShopDTO2(cementId, dto.getShopId())).getUnitPrice();
            onDeliveryTMS.add(new ShopOrderOnDeliveryTM(dto.getShopOrderId(),dto.getShopId(),shopName,dto.getType(),dto.getdDate(),dto.getQty(),unitPrice));
        }
    }

    private void getNewId() {
        int maxId =0;
        try {
            String lastDelivery = deliveryBO.getLastDeliveryId();

            if (lastDelivery == null) {
                maxId = 0;
            } else {
                maxId = Integer.parseInt(lastDelivery.replace("D", ""));
            }

            maxId = maxId + 1;
            String id = "";
            if (maxId < 10) {
                id = "D00" + maxId;
            } else if (maxId < 100) {
                id = "D0" + maxId;
            } else {
                id = "D" + maxId;
            }
            lblDeliveryId.setText(id);
        } catch (Exception e) {
            //new Alert(Alert.AlertType.ERROR,"Something went wrong, please contact DEPPO").show();
        }
    }

    public void addToDelivery(ActionEvent actionEvent) {
        ShopOrderOnDeliveryTM selectedItem = (ShopOrderOnDeliveryTM) tblShopOrders.getSelectionModel().getSelectedItem();
        ObservableList<ShopDeliveryTM> items = tblDeliveryDetails.getItems();


        JFXButton btnDelete = new JFXButton("Delete");
        items.add(new ShopDeliveryTM(lblDeliveryId.getText(),selectedItem.getShopId(),selectedItem.getShopName(),selectedItem.getType(),Integer.valueOf(txtQty.getText()),Double.valueOf(txtUnitPrice.getText()),Double.valueOf(lblTotal.getText()),btnDelete));
        qtyOnHand-=Integer.valueOf(txtQty.getText());
        txtQtyOnHand.setText(String.valueOf(qtyOnHand));
        tblShopOrders.getItems().remove(selectedItem);
    }

    private void calculateTotal() {
        double total=Double.valueOf(txtUnitPrice.getText())*Double.valueOf(txtQty.getText());
        lblTotal.setText(String.valueOf(total));
    }

    public void placeDelivery(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        java.sql.Date value = java.sql.Date.valueOf(lblDate.getText());
        boolean del = deliveryBO.saveDelivery(new DeliveryDTO(lblDeliveryId.getText(),cmbPO.getSelectionModel().getSelectedItem().toString(),cementID,value));
        ObservableList<ShopDeliveryTM> items = tblDeliveryDetails.getItems();
        List<ShopDeliveryDTO> dtos = new ArrayList<>();

        for (ShopDeliveryTM tm:items) {
            dtos.add(new ShopDeliveryDTO(lblDeliveryId.getText(),tm.getShopId(),value,cementID,tm.getQty()));
            System.out.println(tm);
        }
        boolean b = shopDeliveryBO.saveShopDelivery(dtos);
        if (b && del){
            new Alert(Alert.AlertType.INFORMATION,"Delivery Placed Succesfully",ButtonType.OK).show();
        }
    }
}
