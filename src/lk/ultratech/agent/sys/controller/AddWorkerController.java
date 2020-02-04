package lk.ultratech.agent.sys.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
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
import lk.ultratech.agent.sys.dto.WorkerDTO;
import lk.ultratech.agent.sys.entity.Worker;
import lk.ultratech.agent.sys.tm.WorkerPaymentTM;
import lk.ultratech.agent.sys.tm.WorkerTM;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AddWorkerController {
    public AnchorPane anchorPane;
    public Label lblId;
    public JFXTextField txtWorkerId;
    public JFXTextField txtxWorkerName;
    public JFXButton btnSaveWorker;
    public JFXTextField txtWorkerTele;
    public JFXComboBox cmbWorkerType;
    public TableColumn clmWorkerId;
    public TableColumn clmWorkerName;
    public TableColumn clmWorkerTele;
    public TableColumn clmWorkerType;
    public TableView tblWorker;

    private WorkerBO workerBO = BOFactory.getInstance().getBO(BOTypes.WORKER);

    public void initialize() throws SQLException, ClassNotFoundException {
        clmWorkerId.setCellValueFactory(new PropertyValueFactory<>("workerId"));
        clmWorkerName.setCellValueFactory(new PropertyValueFactory<>("name"));
        clmWorkerTele.setCellValueFactory(new PropertyValueFactory<>("telephone"));
        clmWorkerType.setCellValueFactory(new PropertyValueFactory<>("type"));
        getNewId();
        setTable();

        ObservableList<String> workerTypes = FXCollections.observableArrayList("Worker","Driver");
        cmbWorkerType.setItems(workerTypes);
        txtWorkerId.setDisable(true);

    }

    private void loadAllWorkers() throws SQLException, ClassNotFoundException {
        List<WorkerDTO> allWorkers = workerBO.getAllWorker();
        ObservableList<WorkerTM> tm = tblWorker.getItems();

        for (WorkerDTO workerDTO:allWorkers) {
            tm.add(new WorkerTM(workerDTO.getWorkerId(),workerDTO.getName(),workerDTO.getTelephone(),workerDTO.getType()));
        }
    }

    public void btnSave(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String type = (String) cmbWorkerType.getSelectionModel().getSelectedItem();
        WorkerDTO dto = new WorkerDTO(txtWorkerId.getText(),txtxWorkerName.getText(), Integer.valueOf(txtWorkerTele.getText()),type);
        workerBO.saveWorker(dto);

        ObservableList<WorkerTM> workerTMS = tblWorker.getItems();
        workerTMS.add(new WorkerTM(dto.getWorkerId(),dto.getName(),dto.getTelephone(),dto.getType()));
        btnAddNew_OnAction(actionEvent);
    }

    private void btnAddNew_OnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        txtWorkerId.clear();
        txtWorkerTele.clear();
        txtxWorkerName.clear();
        cmbWorkerType.getSelectionModel().clearSelection();
        tblWorker.getSelectionModel().clearSelection();
        cmbWorkerType.setDisable(false);
        getNewId();
    }

    private void setTable() throws SQLException, ClassNotFoundException {
        tblWorker.getSelectionModel().clearSelection();
        loadAllWorkers();
    }

    private void getNewId() throws SQLException, ClassNotFoundException {
        int maxId =0;
        try {
            String lastWorkerId = workerBO.getLastWorkerId();

            if (lastWorkerId == null) {
                maxId = 0;
            } else {
                maxId = Integer.parseInt(lastWorkerId.replace("W", ""));
            }

            maxId = maxId + 1;
            String id = "";
            if (maxId < 10) {
                id = "W00" + maxId;
            } else if (maxId < 100) {
                id = "W0" + maxId;
            } else {
                id = "W" + maxId;
            }
            txtWorkerId.setText(id);
            lblId.setText("WORKER ID : "+id+"");
        } catch (Exception e) {
            //new Alert(Alert.AlertType.ERROR,"Something went wrong, please contact DEPPO").show();
        }
    }
}
