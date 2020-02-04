package lk.ultratech.agent.sys.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXProgressBar;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.scene.Cursor;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import lk.ultratech.agent.sys.bo.BOFactory;
import lk.ultratech.agent.sys.bo.BOTypes;
import lk.ultratech.agent.sys.bo.custom.CementBO;
import lk.ultratech.agent.sys.bo.custom.CementShopBO;
import lk.ultratech.agent.sys.db.DBConnection;
import lk.ultratech.agent.sys.dto.CementShopDTO;
import lk.ultratech.agent.sys.dto.CementShopDTO2;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

public class SettingsController {
    public AnchorPane anchorPane;
    public Tab tabSetCementPrices;
    public Tab tabBackupRestore;
    public FontAwesomeIcon btnBackup;
    public FontAwesomeIcon btnRestore;
    public Label lblBackup;
    public Label lblRestore;
    public JFXProgressBar pgb;
    public JFXTextField txtOPCUnitPrice;
    public Label lblOPC;
    public JFXTextField txtPPCUnitPrice;
    public Label lblPPC;
    public Label lblSetCement;
    public JFXButton btnUpdate;
    public JFXComboBox cmbPriceType;

    CementBO cementBO = BOFactory.getInstance().getBO(BOTypes.CEMENT);
    CementShopBO cementShopBO = BOFactory.getInstance().getBO(BOTypes.CEMENT_SHOP);

    public void initialize(){
        pgb.setVisible(false);
        ObservableList<String> prices = FXCollections.observableArrayList("Increase","Decrease");
        cmbPriceType.setItems(prices);
    }

    public void backupDB(MouseEvent event) {
        DBConnection.getInstance().getConnection();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save the DB Backup");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("SQL File","*.sql"));
        File file = fileChooser.showSaveDialog(this.anchorPane.getScene().getWindow());
        if(file != null){
            Process process = null;
            try {
                process = Runtime.getRuntime().exec("mysqldump -h"+ DBConnection.host +" -u"+DBConnection.user+" -p"
                        +DBConnection.password+" "+DBConnection.db+" --result-file "
                        +file.getAbsolutePath()+(file.getAbsolutePath().endsWith(".sql")? "":".sql"));
                int exitCode = process.waitFor();
                pgb.setVisible(true);

                if (exitCode !=0){
                    BufferedReader br = new BufferedReader(new InputStreamReader(process.getErrorStream()));
                    br.lines().forEach(System.out::println);
                    br.close();
                    throw new RuntimeException("Backup Failed");
                }else {
                    new Alert(Alert.AlertType.INFORMATION,"Database Backedup Successfully").show();
                    pgb.setVisible(false);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void restoreDB(MouseEvent event) {
        DBConnection.getInstance().getConnection();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Let's restore the backup");
        fileChooser.getExtensionFilters()
                .add(new FileChooser.ExtensionFilter("SQL File", "*.sql"));
        File file = fileChooser.showOpenDialog(this.anchorPane.getScene().getWindow());

        if (file != null) {
            String[] commands;
            if (DBConnection.password.length() > 0) {
                commands = new String[]{"mysql", "-h", DBConnection.host, "--port", DBConnection.port, "-u", DBConnection.user, "-p" + DBConnection.password, DBConnection.db, "-e", "source " + file.getAbsolutePath()};
            } else {
                commands = new String[]{"mysql", "-h", DBConnection.host, "--port", DBConnection.port, "-u", DBConnection.user, DBConnection.db, "-e", "source " + file.getAbsolutePath()};
            }
            this.anchorPane.getScene().setCursor(Cursor.WAIT);
            pgb.setVisible(true);

            Task<Void> task = new Task<Void>() {

                @Override
                protected Void call() throws Exception {
                    Process process = Runtime.getRuntime().exec(commands);
                    int exitCode = process.waitFor();
                    if (exitCode != 0) {
                        BufferedReader br = new BufferedReader(new InputStreamReader(process.getErrorStream()));
                        br.lines().forEach(System.out::println);
                        br.close();
                        throw new RuntimeException("Sorry Restore Failed");
                    } else {
                        return null;
                    }
                }
            };

            task.setOnSucceeded(event1 -> {
                this.anchorPane.getScene().setCursor(Cursor.DEFAULT);
                new Alert(Alert.AlertType.INFORMATION, "Database Restored Successfully").show();
                pgb.setVisible(false);
            });
            task.setOnFailed(event1 -> {
                this.anchorPane.getScene().setCursor(Cursor.DEFAULT);
                new Alert(Alert.AlertType.ERROR, "Database Restored Error").show();
                pgb.setVisible(false);
            });
            new Thread(task).start();
        }
    }

    public void btnUpdateAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException,NumberFormatException {
        double opcPrice=0;
        double ppcPrice=0;

        if (!txtOPCUnitPrice.getText().isEmpty()){
            opcPrice = Double.valueOf(txtOPCUnitPrice.getText());
        }
        if (!txtPPCUnitPrice.getText().isEmpty()) {
            ppcPrice = Double.valueOf(txtPPCUnitPrice.getText());
        }

        if (cmbPriceType.getSelectionModel().getSelectedItem()=="Increase"){
            cementShopBO.increaseAllPrices("C001",opcPrice);
            cementShopBO.increaseAllPrices("C002",ppcPrice);
            new Alert(Alert.AlertType.INFORMATION,"All Shop Cement Prices Increased").show();
        }
        if (cmbPriceType.getSelectionModel().getSelectedItem()=="Decrease"){
            cementShopBO.decreaseAllPrices("C001",opcPrice);
            cementShopBO.decreaseAllPrices("C002",ppcPrice);
            new Alert(Alert.AlertType.INFORMATION,"All Shop Cement Prices Decreased").show();
        }
    }
}
