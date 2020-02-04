package lk.ultratech.agent.sys.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ultratech.agent.sys.bo.BOFactory;
import lk.ultratech.agent.sys.bo.BOTypes;
import lk.ultratech.agent.sys.bo.custom.ChequePaymentBO;
import lk.ultratech.agent.sys.bo.custom.ShopBO;
import lk.ultratech.agent.sys.bo.custom.ShopPaymentBO;
import lk.ultratech.agent.sys.dto.ChequePaymentDTO;
import lk.ultratech.agent.sys.tm.ChequePaymentTM;
import lk.ultratech.agent.sys.tm.ShopTM;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ChequePaymentController {
    public AnchorPane anchorPane;
    public TableView<ChequePaymentTM> tblChequePay;
    public TableColumn clmShopId;
    public TableColumn clmShopName;
    public TableColumn clmPayId;
    public TableColumn clmPayDate;
    public TableColumn clmChequeNo;
    public TableColumn clmAmount;
    public TableColumn clmReturned;
    public JFXComboBox cmbReturned;
    public JFXButton btnUpdate;

    ChequePaymentBO chequePaymentBO = BOFactory.getInstance().getBO(BOTypes.CHEQUE_PAYMENT);
    ShopBO shopBO = BOFactory.getInstance().getBO(BOTypes.SHOP);
    ShopPaymentBO shopPaymentBO = BOFactory.getInstance().getBO(BOTypes.SHOP_PAYMENT);

    public  void initialize() throws SQLException, ClassNotFoundException {
        tblChequePay.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("shopId"));
        tblChequePay.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("shopName"));
        tblChequePay.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("shopPayId"));
        tblChequePay.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("duedate"));
        tblChequePay.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("chequeNo"));
        tblChequePay.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("amount"));
        tblChequePay.getColumns().get(6).setCellValueFactory(new PropertyValueFactory<>("returned"));

        cmbReturned.setDisable(true);
        btnUpdate.setDisable(true);
        loadAllCheques();

        tblChequePay.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<ChequePaymentTM>() {

            @Override
            public void changed(ObservableValue<? extends ChequePaymentTM> observable, ChequePaymentTM oldValue, ChequePaymentTM newValue) {
                ChequePaymentTM selectedItem = tblChequePay.getSelectionModel().getSelectedItem();
                cmbReturned.setDisable(false);
                btnUpdate.setDisable(false);

                ObservableList<String> chequeType = FXCollections.observableArrayList("Submitted","Returned");
                cmbReturned.setItems(chequeType);
            }
        });



    }

    private void loadAllCheques() throws SQLException, ClassNotFoundException {
        List<ChequePaymentDTO> allCheques = chequePaymentBO.getAllCheques();
        ObservableList<ChequePaymentTM> items = tblChequePay.getItems();
        for (ChequePaymentDTO dto:allCheques) {
            String shopId = shopPaymentBO.getByPayId(dto.getShopPaymentId()).getShopId();
            String shopName = shopBO.getShopById(shopId).getShopName();
            items.add(new ChequePaymentTM(shopId,shopName,dto.getShopPaymentId(),dto.getdDate(),dto.getCheuqeNo(),dto.getAmount(),dto.getReturned()));
        }
    }

    public void updateCheque(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        boolean b = chequePaymentBO.updateChequeStatus(cmbReturned.getSelectionModel().getSelectedItem().toString(), tblChequePay.getSelectionModel().getSelectedItem().getShopPayId());
        if (b){
            new Alert(Alert.AlertType.INFORMATION,"Successfully Updated Cheque Status").show();
        }
    }
}
