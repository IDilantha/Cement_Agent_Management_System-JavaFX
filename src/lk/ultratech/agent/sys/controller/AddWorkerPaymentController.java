package lk.ultratech.agent.sys.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
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
import lk.ultratech.agent.sys.bo.BOFactory;
import lk.ultratech.agent.sys.bo.BOTypes;
import lk.ultratech.agent.sys.bo.custom.WorkerBO;
import lk.ultratech.agent.sys.bo.custom.WorkerPayementBO;
import lk.ultratech.agent.sys.dto.WorkerDTO;
import lk.ultratech.agent.sys.dto.WorkerPaymentDTO;
import lk.ultratech.agent.sys.tm.WorkerPaymentTM;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AddWorkerPaymentController {
    public AnchorPane anchorPane;
    public Label lblId;
    public JFXTextField txtxWorkerType;
    public JFXComboBox cmbWorkerName;
    public TableColumn clmWorkerId;
    public TableColumn clmWorkerName;
    public TableColumn clmWorkerType;
    public TableColumn clmWorkerAmount;
    public JFXTextField txtxWorkerAmount;
    public JFXButton btnSaveWorkerPayment;
    public JFXTextField txtxWorkerId;
    public JFXTextField txtxWorkerPayId;
    public Label lblWorkerPayId;
    public Label lblDatetxt;
    public Label lblDate;
    public TableView tblWorkerPayment;
    public TableColumn clmWorkerPayDate;
    public TableColumn clmWorkerPayId;

    private WorkerPayementBO workerPayementBO = BOFactory.getInstance().getBO(BOTypes.WORKER_PAYMENT);
    private WorkerBO workerBO = BOFactory.getInstance().getBO(BOTypes.WORKER);

    public void initialize() throws Exception {
        clmWorkerPayDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        clmWorkerPayId.setCellValueFactory(new PropertyValueFactory<>("workerPayId"));
        clmWorkerId.setCellValueFactory(new PropertyValueFactory<>("workerId"));
        clmWorkerName.setCellValueFactory(new PropertyValueFactory<>("workerName"));
        clmWorkerType.setCellValueFactory(new PropertyValueFactory<>("type"));
        clmWorkerAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));

        getNewId();
        setTable();

        List<String> workerList = workerBO.getAllWorkerNames();
        cmbWorkerName.setItems(FXCollections.observableArrayList(workerList));
        txtxWorkerId.setDisable(true);
        txtxWorkerType.setDisable(true);
        txtxWorkerPayId.setDisable(true);

        SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
        String date = DATE_FORMAT.format(new Date());
        lblDate.setText(date);

        cmbWorkerName.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                String selectedCustomerName = (String) cmbWorkerName.getSelectionModel().getSelectedItem();

                if (selectedCustomerName == null) {
                    return;
                }
                try {
                    WorkerDTO worker = workerBO.findWorker(selectedCustomerName);
                    txtxWorkerId.setText(worker.getWorkerId());
                    txtxWorkerType.setText(worker.getType());
                    txtxWorkerPayId.setText(lblWorkerPayId.getText());
                } catch (Exception e) {
                    new Alert(Alert.AlertType.ERROR,"Something went wrong, please contact DEPPO").show();
                    Logger.getLogger("lk.ijse.dep.pos.controller").log(Level.SEVERE, null,e);
                }
            }
        });
    }

    public void btnSave(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        java.util.Date utilDate = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

        ObservableList<WorkerPaymentTM> workerPaymentTms = tblWorkerPayment.getItems();
        WorkerPaymentDTO wPayDTO = new WorkerPaymentDTO(txtxWorkerPayId.getText(),txtxWorkerId.getText(),sqlDate,Double.valueOf(txtxWorkerAmount.getText()));
        System.out.println(txtxWorkerPayId.getText()+" "+txtxWorkerId.getText()+" "+sqlDate+" "+Double.valueOf(txtxWorkerAmount.getText()));
        try {
           workerPayementBO.addWorkerPayment(wPayDTO);
           workerPaymentTms.add(new WorkerPaymentTM(wPayDTO.getDate(),wPayDTO.getWorkerPaymentId(),wPayDTO.getWorkerId(),wPayDTO.getWorkerId(),wPayDTO.getWorkerId(),wPayDTO.getAmount()));
           btnAddNew_OnAction(actionEvent);
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,"Something went wrong").show();
        }
    }

    private void getNewId(){
        int maxId =0;
        try {
            String lastWorkerPaymentId = workerPayementBO.getLastWorkerPaymentId();

            if (lastWorkerPaymentId == null) {
                maxId = 0;
            } else {
                maxId = Integer.parseInt(lastWorkerPaymentId.replace("WP", ""));
            }

            maxId = maxId + 1;
            String id = "";
            if (maxId < 10) {
                id = "WP00" + maxId;
            } else if (maxId < 100) {
                id = "WP0" + maxId;
            } else {
                id = "WP" + maxId;
            }
            txtxWorkerPayId.setText(id);
            lblWorkerPayId.setText(id);
        } catch (Exception e) {
            //new Alert(Alert.AlertType.ERROR,"Something went wrong, please contact DEPPO").show();
        }
    }

    private void setTable() throws Exception {
        List<WorkerPaymentDTO> workerList = workerPayementBO.getAllWorkerPayment();
        ObservableList<WorkerPaymentTM> workers = tblWorkerPayment.getItems();
        for (WorkerPaymentDTO c : workerList) {
            WorkerDTO workerById = workerBO.findWorkerById(c.getWorkerId());
            workers.add(new WorkerPaymentTM(c.getDate(), c.getWorkerPaymentId(), c.getWorkerId(), workerById.getName(), workerById.getType(),c.getAmount()));
        }
    }


    private void btnAddNew_OnAction(ActionEvent actionEvent) {
        txtxWorkerAmount.clear();
        txtxWorkerPayId.clear();
        txtxWorkerType.clear();
        txtxWorkerId.clear();
        tblWorkerPayment.getSelectionModel().clearSelection();
        cmbWorkerName.setDisable(false);
        getNewId();
    }


}
