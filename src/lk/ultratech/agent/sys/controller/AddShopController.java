package lk.ultratech.agent.sys.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import lk.ultratech.agent.sys.bo.BOFactory;
import lk.ultratech.agent.sys.bo.BOTypes;
import lk.ultratech.agent.sys.bo.custom.CementShopBO;
import lk.ultratech.agent.sys.bo.custom.ShopBO;
import lk.ultratech.agent.sys.dto.CementShopDTO;
import lk.ultratech.agent.sys.dto.ShopDTO;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AddShopController  {


    public AnchorPane anchorPane;
    public Label lblId;
    public JFXTextField txtShopId;
    public JFXTextField txtShopName;
    public JFXTextField txtShopAddress;
    public JFXTextField txtOPCUnitPrice;
    public Label lblSetCement;
    public JFXButton btnSaveShop;
    public Label lblOPC;
    public JFXTextField txtPPCUnitPrice;
    public Label lblPPC;
    public JFXTextField txtShopTele;
    public Label lblIShopId;

    private ShopBO shopBO = BOFactory.getInstance().getBO(BOTypes.SHOP);
    private CementShopBO cementShopBO = BOFactory.getInstance().getBO(BOTypes.CEMENT_SHOP);

    public void initialize() throws SQLException, ClassNotFoundException {
        txtShopId.setDisable(true);
        getNewId();
    }

    public void btnSaveAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        boolean s=shopBO.saveShop(new ShopDTO(txtShopId.getText(),txtShopName.getText(),txtShopAddress.getText(),Integer.valueOf(txtShopTele.getText())));
        boolean cs=cementShopBO.save(new CementShopDTO("C001",txtShopId.getText(),Double.valueOf(txtOPCUnitPrice.getText())));
        boolean cs1=cementShopBO.save(new CementShopDTO("C002",txtShopId.getText(),Double.valueOf(txtPPCUnitPrice.getText())));
        if (s && cs && cs1){
            new Alert(Alert.AlertType.INFORMATION,"Shop Added Successfully").show();
            getNewId();
            txtOPCUnitPrice.clear();
            txtPPCUnitPrice.clear();
            txtShopName.clear();
            txtShopTele.clear();;
            txtShopAddress.clear();
        }
    }

    private void getNewId() throws SQLException, ClassNotFoundException {
        int maxId =0;
        try {
            String lastShopId = shopBO.getLastShopId();

            if (lastShopId == null) {
                maxId = 0;
            } else {
                maxId = Integer.parseInt(lastShopId.replace("S", ""));
            }

            maxId = maxId + 1;
            String id = "";
            if (maxId < 10) {
                id = "S00" + maxId;
            } else if (maxId < 100) {
                id = "S0" + maxId;
            } else {
                id = "S" + maxId;
            }
            txtShopId.setText(id);
            lblIShopId.setText(id);
        } catch (Exception e) {
            //new Alert(Alert.AlertType.ERROR,"Something went wrong, please contact DEPPO").show();
        }
    }
}
