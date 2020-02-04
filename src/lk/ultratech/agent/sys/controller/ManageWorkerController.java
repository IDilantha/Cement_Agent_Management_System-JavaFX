package lk.ultratech.agent.sys.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
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
import java.util.List;
import java.util.Optional;

public class ManageWorkerController {

    public AnchorPane anchorPane;
    public TableView tblWorker;
    public TableColumn clmWorkerId;
    public JFXTextField txtWorkerId;
    public JFXTextField txtWorkerTele;
    public JFXTextField txtWorkerName;
    public JFXButton btnUpdate;
    public JFXButton btnDelete;
    public TableColumn clmWorkerName;
    public TableColumn clmWorkerType;
    public TableColumn clmWorkerTele;
    public JFXTextField txtWorkerType;

    private WorkerBO workerBO = BOFactory.getInstance().getBO(BOTypes.WORKER);

    public void initialize() throws SQLException, ClassNotFoundException {
        clmWorkerId.setCellValueFactory(new PropertyValueFactory<>("workerId"));
        clmWorkerName.setCellValueFactory(new PropertyValueFactory<>("name"));
        clmWorkerType.setCellValueFactory(new PropertyValueFactory<>("type"));
        clmWorkerTele.setCellValueFactory(new PropertyValueFactory<>("telephone"));
        loadAllWorkers();

        txtWorkerId.setDisable(true);
        txtWorkerName.setDisable(true);
        txtWorkerTele.setDisable(true);
        txtWorkerType.setDisable(true);
        btnDelete.setDisable(true);
        btnUpdate.setDisable(true);

        tblWorker.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<WorkerTM>() {
            @Override
            public void changed(ObservableValue<? extends WorkerTM> observable, WorkerTM oldValue, WorkerTM newValue) {
                WorkerTM selectedWorker = (WorkerTM) tblWorker.getSelectionModel().getSelectedItem();

                if (selectedWorker == null) {
                    btnDelete.setDisable(true);
                }

                btnUpdate.setDisable(false);
                btnDelete.setDisable(false);
                txtWorkerName.setDisable(false);
                txtWorkerTele.setDisable(false);
                txtWorkerId.setText(selectedWorker.getWorkerId());
                txtWorkerName.setText(selectedWorker.getName());
                txtWorkerTele.setText(String.valueOf(selectedWorker.getTelephone()));
                txtWorkerType.setText(selectedWorker.getType());
            }
        });
    }

    private void loadAllWorkers() throws SQLException, ClassNotFoundException {
        List<WorkerDTO> allWorkers = workerBO.getAllWorker();
        ObservableList<WorkerTM> tm = tblWorker.getItems();

        for (WorkerDTO workerDTO:allWorkers) {
            tm.add(new WorkerTM(workerDTO.getWorkerId(),workerDTO.getName(),workerDTO.getTelephone(),workerDTO.getType()));
        }
    }

    public void clickUpdate(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        WorkerDTO dto = new WorkerDTO(txtWorkerId.getText(),txtWorkerName.getText(), Integer.valueOf(txtWorkerTele.getText()),txtWorkerType.getText());
        WorkerTM selectedWorker = (WorkerTM) tblWorker.getSelectionModel().getSelectedItem();
        try {
            workerBO.updateWorker(dto);
            selectedWorker.setName(txtWorkerName.getText());
            selectedWorker.setTelephone(Integer.valueOf(txtWorkerTele.getText()));
            tblWorker.refresh();
            btnAddNew_OnAction(actionEvent);
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,"Something went wrong").show();
        }
    }

    public void clickDelete(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Are you sure to delete this Worker?",ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> buttonType = alert.showAndWait();
        if (buttonType.get() == ButtonType.YES) {
            WorkerTM selectedItem = (WorkerTM) tblWorker.getSelectionModel().getSelectedItem();
            try {
                workerBO.deleteWorker(selectedItem.getWorkerId());
                tblWorker.getItems().remove(selectedItem);
            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR,"Something went wrong").show();
            }
        }
    }

    private void btnAddNew_OnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        txtWorkerId.clear();
        txtWorkerTele.clear();
        txtWorkerType.clear();
        txtWorkerName.clear();
        tblWorker.getSelectionModel().clearSelection();
    }
}
