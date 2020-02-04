package lk.ultratech.agent.sys.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.StringConverter;
import lk.ultratech.agent.sys.bo.BOFactory;
import lk.ultratech.agent.sys.bo.BOTypes;
import lk.ultratech.agent.sys.bo.custom.AgentBO;
import lk.ultratech.agent.sys.bo.custom.CementAgentBO;
import lk.ultratech.agent.sys.bo.custom.CementBO;
import lk.ultratech.agent.sys.bo.custom.PurchaseOrderBO;
import lk.ultratech.agent.sys.dto.AgentDTO;
import lk.ultratech.agent.sys.dto.CementAgentDTO;
import lk.ultratech.agent.sys.dto.CementAgentDTO2;
import lk.ultratech.agent.sys.dto.PurchaseOrderDTO;
import lk.ultratech.agent.sys.entity.PurchaseOrder;

import java.net.URL;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class AddPurchaseOrderController {

    public AnchorPane anchorPane;
    public Label lblId;
    public Label lblDate;
    public JFXTextField txtUnitPrice;
    public JFXTextField txtQty;
    public Label lblTotal;
    public Label lblId1;
    public JFXButton btnPlaceOrder;
    public JFXTextField txtAgentCode;
    public JFXTextField txtAgentRegion;
    public JFXTextField txtAgentTerritory;
    public JFXTextField txtAgentName;
    public JFXComboBox cmbCementType;
    public JFXTextField txtxChequeNumber;
    public JFXTextField txtxAmount;
    public JFXDatePicker dateDueDate;
    public Label lblPOID;
    public JFXTextField txtAgentId;
    String cementId;

    private AgentBO agentBO = BOFactory.getInstance().getBO(BOTypes.AGENT);
    private CementBO cementBO = BOFactory.getInstance().getBO(BOTypes.CEMENT);
    private CementAgentBO cementAgentBO = BOFactory.getInstance().getBO(BOTypes.CEMENT_AGENT);
    private PurchaseOrderBO purchaseOrderBO = BOFactory.getInstance().getBO(BOTypes.PURCHASE_ORDER);

    public  void initialize() throws SQLException, ClassNotFoundException {
        AgentDTO firstAgent = agentBO.getFirstAgent();
        txtAgentCode.setText(firstAgent.getAgentCode());
        txtAgentName.setText(firstAgent.getAgentName());
        txtAgentRegion.setText(firstAgent.getRegion());
        txtAgentTerritory.setText(firstAgent.getTerritory());
        txtAgentId.setText(firstAgent.getAgentId());

        txtAgentId.setDisable(true);
        txtAgentName.setDisable(true);
        txtAgentCode.setDisable(true);
        txtAgentTerritory.setDisable(true);
        txtAgentRegion.setDisable(true);
        txtUnitPrice.setDisable(true);
        txtxAmount.setDisable(true);
        getNewId();


        SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
        String date = DATE_FORMAT.format(new Date());
        lblDate.setText(date);

        List<String> cementNameList = cementBO.getAllCementNames();
        cmbCementType.setItems(FXCollections.observableArrayList(cementNameList));
        cmbCementType.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                Object selectedItem = cmbCementType.getSelectionModel().getSelectedItem();
                try {
                    cementId = cementBO.getCementId(selectedItem.toString());
                    CementAgentDTO2 dto2 = new CementAgentDTO2(cementId, txtAgentId.getText());
                    CementAgentDTO cementPrice = cementAgentBO.getCementPrice(dto2);

                    txtUnitPrice.setText(String.valueOf(cementPrice.getUnitPrice()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        txtQty.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                calculateTotal();
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

    private void calculateTotal() {
        double total = 0;
        total = Double.valueOf(txtUnitPrice.getText())*Double.valueOf(txtQty.getText());

        NumberFormat nf = NumberFormat.getInstance();
        nf.setMaximumFractionDigits(2);
        nf.setMinimumFractionDigits(2);
        nf.setGroupingUsed(false);

        lblTotal.setText("Total : " + nf.format(total));
        txtxAmount.setText(nf.format(total));
    }

    public void placeOrder(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        java.sql.Date orderDateSQL = java.sql.Date.valueOf(lblDate.getText());
        java.sql.Date dueDateSQL= java.sql.Date.valueOf( dateDueDate.getValue());
        boolean b = purchaseOrderBO.savePurchaseOrder(new PurchaseOrderDTO(lblPOID.getText(), txtAgentId.getText(), cementId, orderDateSQL, Integer.valueOf(txtQty.getText()), txtxChequeNumber.getText(), dueDateSQL, Double.valueOf(txtxAmount.getText())));
        if (b==true){
            new Alert(Alert.AlertType.INFORMATION,"Purchase Order Placed Successfully").show();
            txtxAmount.clear();
            txtxChequeNumber.clear();
            dateDueDate.setValue(null);
            getNewId();
        }
    }

    private void getNewId() throws SQLException, ClassNotFoundException {
        int maxId =0;
        try {
            String lastWorkerId = purchaseOrderBO.getLastPOId();

            if (lastWorkerId == null) {
                maxId = 0;
            } else {
                maxId = Integer.parseInt(lastWorkerId.replace("PO", ""));
            }

            maxId = maxId + 1;
            String id = "";
            if (maxId < 10) {
                id = "PO00" + maxId;
            } else if (maxId < 100) {
                id = "PO0" + maxId;
            } else {
                id = "PO" + maxId;
            }
            lblPOID.setText(id);
        } catch (Exception e) {
            //new Alert(Alert.AlertType.ERROR,"Something went wrong, please contact DEPPO").show();
        }
    }
}
