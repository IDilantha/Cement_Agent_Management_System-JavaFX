package lk.ultratech.agent.sys.controller;

import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ultratech.agent.sys.bo.BOFactory;
import lk.ultratech.agent.sys.bo.BOTypes;
import lk.ultratech.agent.sys.bo.custom.CementBO;
import lk.ultratech.agent.sys.bo.custom.PurchaseOrderBO;
import lk.ultratech.agent.sys.db.DBConnection;
import lk.ultratech.agent.sys.dto.PurchaseOrderDTO;
import lk.ultratech.agent.sys.tm.PurchaseOrderTM;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.sql.Date;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ViewPurchaseOrderController {
    public AnchorPane anchorPane;
    public TableColumn clmPOID;
    public TableColumn clmODate;
    public TableColumn clmType;
    public TableColumn clmQty;
    public TableColumn clmAmount;
    public TableColumn clmChequeNo;
    public TableColumn clmDDate;
    public TableView<PurchaseOrderTM> tblPO;

    private PurchaseOrderBO purchaseOrderBO = BOFactory.getInstance().getBO(BOTypes.PURCHASE_ORDER);
    private CementBO cementBO = BOFactory.getInstance().getBO(BOTypes.CEMENT);

    public void initialize() throws SQLException, ClassNotFoundException, JRException {
        clmODate.setCellValueFactory(new PropertyValueFactory<>("orderDate"));
        clmDDate.setCellValueFactory(new PropertyValueFactory<>("dueDate"));
        //tblPO.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("odDate"));
        clmPOID.setCellValueFactory(new PropertyValueFactory<>("POID"));
        clmAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        clmChequeNo.setCellValueFactory(new PropertyValueFactory<>("chequeNo"));
        clmType.setCellValueFactory(new PropertyValueFactory<>("type"));
        clmQty.setCellValueFactory(new PropertyValueFactory<>("qty"));

        loadAllPurchaseOrders();
    }

    private void loadAllPurchaseOrders() throws SQLException, ClassNotFoundException, JRException {
        List<PurchaseOrderDTO> allPO = purchaseOrderBO.getAllPO();
        ObservableList<PurchaseOrderTM> tms = tblPO.getItems();
        for (PurchaseOrderDTO dto:allPO) {
            String type = cementBO.getCementName(dto.getCementId());
            tms.add(new PurchaseOrderTM(dto.getPurchaseOrderId(),dto.getoDate(),type,dto.getQty(),dto.getAmount(),dto.getChequeNumber(),dto.getdDate()));
        }
        /*JasperReport jasperReport = (JasperReport) JRLoader.loadObject(this.getClass().getResourceAsStream("/lk/ultratech/agent/sys/report/CementAgent.jasper"));
        Map<String, Object> params = new HashMap<>();
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, DBConnection.getInstance().getConnection());
        JasperViewer.viewReport(jasperPrint, false);*/
       loadJasper();
    }

    public void loadJasper() {
        JasperDesign load = null;
        try {
            load = JRXmlLoader.load(ViewPurchaseOrderController.class.getResourceAsStream("/lk/ultratech/agent/sys/report/CementAgent.jrxml"));
            JasperReport jasperReport = JasperCompileManager.compileReport(load);

            Map<String,Object> params = new HashMap<>();
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, DBConnection.getInstance().getConnection());
            JasperViewer.viewReport(jasperPrint,false);
        } catch (JRException e) {
            new Alert(Alert.AlertType.CONFIRMATION,"Something went wrong", ButtonType.OK).show();
        }
    }

}
