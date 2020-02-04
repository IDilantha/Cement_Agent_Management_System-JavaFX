package lk.ultratech.agent.sys.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.util.StringConverter;
import lk.ultratech.agent.sys.bo.BOFactory;
import lk.ultratech.agent.sys.bo.BOTypes;
import lk.ultratech.agent.sys.bo.custom.CementShopBO;
import lk.ultratech.agent.sys.bo.custom.ChequePaymentBO;
import lk.ultratech.agent.sys.bo.custom.ShopBO;
import lk.ultratech.agent.sys.bo.custom.ShopPaymentBO;
import lk.ultratech.agent.sys.dto.*;
import lk.ultratech.agent.sys.tm.PurchaseOrderTM;
import lk.ultratech.agent.sys.tm.ShopListTM;
import lk.ultratech.agent.sys.tm.ShopTM;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

public class AddShopPaymentController {
    public TableView tblShop;
    public AnchorPane anchorPane;
    public TableColumn clmShopID;
    public TableColumn clmShopName;
    public JFXTextField txtShopId;
    public JFXButton btnAddPayment;
    public JFXTextField txtPayAmount;
    public JFXTextField txtShopName;
    public JFXTextField txtOPCUnitPrice;
    public Label lblOPC;
    public JFXTextField txtPPCUnitPrice;
    public Label lblPPC;
    public Label lblSetCement1;
    public Label lblDate;
    public JFXComboBox cmbPayType;
    public Pane paneChequePane;
    public JFXTextField txtxChequeNo;
    public JFXDatePicker dateDueDate;
    public JFXTextField txtChequePID;
    public Label lblShopPayId;

    private ShopBO shopBO = BOFactory.getInstance().getBO(BOTypes.SHOP);
    private CementShopBO cementShopBO = BOFactory.getInstance().getBO(BOTypes.CEMENT_SHOP);
    private ShopPaymentBO shopPaymentBO = BOFactory.getInstance().getBO(BOTypes.SHOP_PAYMENT);
    private ChequePaymentBO chequePaymentBO = BOFactory.getInstance().getBO(BOTypes.CHEQUE_PAYMENT);

    public void initialize() throws SQLException, ClassNotFoundException {
        txtOPCUnitPrice.setDisable(true);
        txtPPCUnitPrice.setDisable(true);
        txtShopId.setDisable(true);
        txtShopName.setDisable(true);
        paneChequePane.setVisible(false);
        txtChequePID.setDisable(true);

        java.util.Date utilDate = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
        String date = DATE_FORMAT.format(new Date());
        lblDate.setText(sqlDate.toString());

        clmShopID.setCellValueFactory(new PropertyValueFactory<>("shopId"));
        clmShopName.setCellValueFactory(new PropertyValueFactory<>("shopName"));
        ObservableList<String> payTypes = FXCollections.observableArrayList("Cash","Cheque");
        cmbPayType.setItems(payTypes);

        loadAllShops();
        getNewChequeId();
        getNewId();

        tblShop.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<ShopListTM>() {
            @Override
            public void changed(ObservableValue<? extends ShopListTM> observable, ShopListTM oldValue, ShopListTM newValue) {
                ShopListTM selectedShop = (ShopListTM) tblShop.getSelectionModel().getSelectedItem();

                txtShopId.setText(selectedShop.getShopId());
                txtShopName.setText(selectedShop.getShopName());
                try {
                    CementShopDTO OPCPrice = cementShopBO.getCementPrice(new CementShopDTO2("C001", selectedShop.getShopId()));
                    CementShopDTO PPCPrice = cementShopBO.getCementPrice(new CementShopDTO2("C002",selectedShop.getShopId()));
                    txtOPCUnitPrice.setText(String.valueOf(OPCPrice.getUnitPrice()));
                    txtPPCUnitPrice.setText(String.valueOf(PPCPrice.getUnitPrice()));
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });

        cmbPayType.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                if (cmbPayType.getSelectionModel().getSelectedItem().toString()=="Cheque"){
                    paneChequePane.setVisible(true);
                }else {
                    paneChequePane.setVisible(false);
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
    }

    private void loadAllShops() throws SQLException, ClassNotFoundException {
        List<ShopDTO> allShops = shopBO.getAllShops();
        ObservableList<ShopListTM> tms = tblShop.getItems();
        for (ShopDTO dto:allShops) {
            tms.add(new ShopListTM(dto.getShopId(),dto.getShopName()));
        }
    }


    public void addPayment(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if (cmbPayType.getSelectionModel().getSelectedItem() == "Cash") {
            boolean cash = shopPaymentBO.saveShopPayment(new ShopPaymentDTO(lblShopPayId.getText(), txtShopId.getText(), "Cash", java.sql.Date.valueOf(lblDate.getText()), Double.valueOf(txtPayAmount.getText())));
            if (cash){
                new Alert(Alert.AlertType.INFORMATION,"Cash Payment Placed Successfully").show();
            }

        } else if (cmbPayType.getSelectionModel().getSelectedItem() == "Cheque") {
            boolean cheque = shopPaymentBO.saveShopPayment(new ShopPaymentDTO(lblShopPayId.getText(), txtShopId.getText(), "Cheque", java.sql.Date.valueOf(lblDate.getText()), Double.valueOf(txtPayAmount.getText())));

            boolean pending = chequePaymentBO.saveChequePayment(new ChequePaymentDTO(txtChequePID.getText(), lblShopPayId.getText(), txtxChequeNo.getText(), java.sql.Date.valueOf(dateDueDate.getValue()), Double.valueOf(txtPayAmount.getText()), "Pending"));
            if (cheque&&pending){
                new Alert(Alert.AlertType.INFORMATION,"Cheque Payment Placed Successfully").show();
            }
        }
    }
    private void getNewId() throws SQLException, ClassNotFoundException {
        int maxId =0;
        try {
            String lastShopPayId = shopPaymentBO.getLastShopPaymentId();

            if (lastShopPayId == null) {
                maxId = 0;
            } else {
                maxId = Integer.parseInt(lastShopPayId.replace("SP", ""));
            }

            maxId = maxId + 1;
            String id = "";
            if (maxId < 10) {
                id = "SP00" + maxId;
            } else if (maxId < 100) {
                id = "SP0" + maxId;
            } else {
                id = "SP" + maxId;
            }
            lblShopPayId.setText(id);
        } catch (Exception e) {
            //new Alert(Alert.AlertType.ERROR,"Something went wrong, please contact DEPPO").show();
        }
    }

    private void getNewChequeId() throws SQLException, ClassNotFoundException {
        int maxId =0;
        try {
            String lastChequePayId = chequePaymentBO.getLastChequeId();

            if (lastChequePayId == null) {
                maxId = 0;
            } else {
                maxId = Integer.parseInt(lastChequePayId.replace("CP", ""));
            }

            maxId = maxId + 1;
            String id = "";
            if (maxId < 10) {
                id = "CP00" + maxId;
            } else if (maxId < 100) {
                id = "CP0" + maxId;
            } else {
                id = "CP" + maxId;
            }
            txtChequePID.setText(id);
        } catch (Exception e) {
            //new Alert(Alert.AlertType.ERROR,"Something went wrong, please contact DEPPO").show();
        }
    }
}
