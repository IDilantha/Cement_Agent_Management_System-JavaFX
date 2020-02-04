package lk.ultratech.agent.sys.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.util.StringConverter;
import lk.ultratech.agent.sys.bo.BOFactory;
import lk.ultratech.agent.sys.bo.BOTypes;
import lk.ultratech.agent.sys.bo.custom.CementBO;
import lk.ultratech.agent.sys.bo.custom.CementShopBO;
import lk.ultratech.agent.sys.bo.custom.ShopBO;
import lk.ultratech.agent.sys.bo.custom.ShopOrderBO;
import lk.ultratech.agent.sys.dto.CementShopDTO;
import lk.ultratech.agent.sys.dto.CementShopDTO2;
import lk.ultratech.agent.sys.dto.ShopDTO;
import lk.ultratech.agent.sys.dto.ShopOrderDTO;

import java.sql.SQLException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

public class AddShopOrderController {
    public AnchorPane anchorPane;
    public Label lblId;
    public Label lblDate;
    public JFXTextField txtShopId;
    public JFXTextField txtShopAddress;
    public JFXComboBox cmbCementType;
    public JFXTextField txtUnitPrice;
    public JFXTextField txtQty;
    public Label lblTotal;
    public JFXDatePicker dateDueDate;
    public JFXComboBox cmbShopName;
    public JFXButton btnPlaceShopOrder;
    public JFXTextField txtShopTelephone;

    ShopOrderBO shopOrderBO = BOFactory.getInstance().getBO(BOTypes.SHOP_ORDER);
    ShopBO shopBO = BOFactory.getInstance().getBO(BOTypes.SHOP);
    CementBO cementBO = BOFactory.getInstance().getBO(BOTypes.CEMENT);
    CementShopBO cementShopBO = BOFactory.getInstance().getBO(BOTypes.CEMENT_SHOP);

    public void initialize() throws SQLException, ClassNotFoundException {

        java.util.Date utilDate = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
        String date = DATE_FORMAT.format(new Date());
        lblDate.setText(sqlDate.toString());

        getNewId();
        txtShopAddress.setDisable(true);
        txtShopId.setDisable(true);
        txtShopTelephone.setDisable(true);
        txtUnitPrice.setDisable(true);

        List<String> allShopNames = shopBO.getAllShopNames();
        cmbShopName.setItems(FXCollections.observableArrayList(allShopNames));
        cmbShopName.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                Object selectedItem = cmbShopName.getSelectionModel().getSelectedItem();
                try {
                    ShopDTO shop = shopBO.getByShopName(selectedItem.toString());
                    txtShopAddress.setText(shop.getAddress());
                    txtShopId.setText(shop.getShopId());
                    txtShopTelephone.setText(String.valueOf(shop.getTelephone()));
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });

        List<String> allCementNames = cementBO.getAllCementNames();
        cmbCementType.setItems(FXCollections.observableArrayList(allCementNames));
        cmbCementType.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                Object selectedItem = cmbCementType.getSelectionModel().getSelectedItem();
                try {
                    String cementId = cementBO.getCementId(selectedItem.toString());
                    CementShopDTO cementPrice = cementShopBO.getCementPrice(new CementShopDTO2(cementId, txtShopId.getText()));
                    txtUnitPrice.setText(String.valueOf(cementPrice.getUnitPrice()));
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });

        dateDueDate.setConverter(new StringConverter<LocalDate>() {
            String pattern = "yyyy-MM-dd";
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);
            {
                dateDueDate.setPromptText(pattern.toLowerCase());
            }

            @Override
            public String toString(LocalDate date) {
                if (date != null) {
                    return dateFormatter.format(date);
                } else {
                    return "";
                }
            }

            @Override
            public LocalDate fromString(String string) {
                if (string != null && !string.isEmpty()) {
                    return LocalDate.parse(string, dateFormatter);
                } else {
                    return null;
                }
            }
        });

        txtQty.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                calculateTotal();
            }
        });

    }

    private void calculateTotal() {
        double total = 0;
        total = Double.valueOf(txtUnitPrice.getText())*Double.valueOf(txtQty.getText());

        NumberFormat nf = NumberFormat.getInstance();
        nf.setMaximumFractionDigits(2);
        nf.setMinimumFractionDigits(2);
        nf.setGroupingUsed(false);

        lblTotal.setText("Total : " + nf.format(total));
    }

    public void placeShopOrder(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        java.sql.Date orderDateSQL = java.sql.Date.valueOf(lblDate.getText());
        java.sql.Date dueDateSQL= java.sql.Date.valueOf( dateDueDate.getValue());
        ShopOrderDTO shopOrderDTO = new ShopOrderDTO(lblId.getText(),txtShopId.getText(),cmbCementType.getSelectionModel().getSelectedItem().toString(),orderDateSQL,dueDateSQL,Integer.valueOf(txtQty.getText()));
        boolean b = shopOrderBO.saveShopOrder(shopOrderDTO);
        if (b){
            new Alert(Alert.AlertType.INFORMATION,"Shop Order Placed Successfully").show();
            getNewId();
            txtShopTelephone.clear();
            txtShopId.clear();
            txtShopAddress.clear();
            dateDueDate.setValue(null);
        }
    }

    private void getNewId() throws SQLException, ClassNotFoundException {
        int maxId =0;
        try {
            String lastShopOrderId = shopOrderBO.getLastShopOrderId();

            if (lastShopOrderId == null) {
                maxId = 0;
            } else {
                maxId = Integer.parseInt(lastShopOrderId.replace("SO", ""));
            }

            maxId = maxId + 1;
            String id = "";
            if (maxId < 10) {
                id = "SO00" + maxId;
            } else if (maxId < 100) {
                id = "SO0" + maxId;
            } else {
                id = "SO" + maxId;
            }
            lblId.setText(id);
        } catch (Exception e) {
            //new Alert(Alert.AlertType.ERROR,"Something went wrong, please contact DEPPO").show();
        }
    }
}
