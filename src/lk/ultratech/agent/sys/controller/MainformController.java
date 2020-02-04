package lk.ultratech.agent.sys.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.ScaleTransition;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import lk.ultratech.agent.sys.AppInitializer;
import lk.ultratech.agent.sys.bo.BOFactory;
import lk.ultratech.agent.sys.bo.BOTypes;
import lk.ultratech.agent.sys.bo.custom.CementBO;
import lk.ultratech.agent.sys.bo.custom.ShopBO;
import lk.ultratech.agent.sys.bo.custom.ShopOrderBO;
import lk.ultratech.agent.sys.db.DBConnection;
import lk.ultratech.agent.sys.dto.ShopDTO;
import lk.ultratech.agent.sys.dto.ShopOrderDTO;
import lk.ultratech.agent.sys.entity.Shop;
import lk.ultratech.agent.sys.tm.ShopOrdersMainTM;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.Clock;
import java.util.*;

public class MainformController implements Initializable {

    public JFXButton btnManageShopOrder;
    public JFXButton btnManageWorker;
    public JFXButton btnManageDelivery;
    public JFXButton btnManageShop;
    public JFXButton btnManagePayment;
    public JFXButton btnShopActivity;
    public Label lblMainTitle;
    public TableView<ShopOrdersMainTM> tblShopOrdersMain;
    public TableColumn clmShopName;
    public TableColumn clmType;
    public TableColumn clmQty;
    public TableColumn clmDDate;
    public Label lblMainPanelTitle;
    public Label lblDate;
    public Label lblToday;
    public Label lblQuickNav;
    public Label lblSelected;
    public ImageView imgShopOrder;
    public ImageView imgShopPay;
    public Label lblTime;
    public JFXButton btnDeliveryReports;
    public JFXButton btnPurchaseOrderReports;
    @FXML
    private AnchorPane root;

    @FXML
    private Pane paneTop;

    @FXML
    private ImageView imgLogo;

    @FXML
    private ImageView imgLogout;

    @FXML
    private FontAwesomeIcon iconSettings;

    @FXML
    private FontAwesomeIcon iconHome;

    @FXML
    private Label lblTitle;

    @FXML
    private AnchorPane mainAnchorPane;

    @FXML
    private ImageView imgShop;

    @FXML
    private FontAwesomeIcon iconPurchase;

    @FXML
    private FontAwesomeIcon iconSearch;

    @FXML
    private JFXTextField textSearch;

    @FXML
    private VBox vBoxMain;

    @FXML
    private VBox vBoxPO;

    @FXML
    private JFXButton btnPO;

    @FXML
    private VBox cBoxPOSub;

    @FXML
    private JFXButton btnAddPO;

    @FXML
    private JFXButton btnViewPO;

    @FXML
    private VBox vBoxShopOrder;

    @FXML
    private JFXButton btnShopOrder;

    @FXML
    private VBox vBoxShopOrderSub;

    @FXML
    private JFXButton btnAddShopOrder;

    @FXML
    private VBox vBoxShop;

    @FXML
    private JFXButton btnShop;

    @FXML
    private VBox vBoxShopSub;

    @FXML
    private JFXButton btnAddShop;

    @FXML
    private JFXButton btnViewShop;

    @FXML
    private JFXButton btnUpdateShop;

    @FXML
    private JFXButton btnDeleteShop;

    @FXML
    private VBox vBoxPayment;

    @FXML
    private JFXButton btnPayment;

    @FXML
    private VBox vBoxPaymentSub;

    @FXML
    private JFXButton btnAddPayment;

    @FXML
    private JFXButton btnViewPayment;

    @FXML
    private JFXButton btnUpdatePayment;

    @FXML
    private JFXButton btnChequePayment;

    @FXML
    private VBox vBoxDelivery;

    @FXML
    private JFXButton btnDelivery;

    @FXML
    private VBox vBoxDeliverySub;

    @FXML
    private JFXButton btnAddDelivery;

    @FXML
    private JFXButton btnViewDelivery;

    @FXML
    private JFXButton btnUpdateDelivery;

    @FXML
    private VBox vBoxWorker;

    @FXML
    private JFXButton btnWorker;

    @FXML
    private VBox vBoxWorkerSub;

    @FXML
    private JFXButton btnAddWorker;

    @FXML
    private JFXButton btnAddWorkerPayment;

    @FXML
    private JFXButton btnViewWorker;

    @FXML
    private JFXButton btnDeleteWorker;

    @FXML
    private VBox vBoxReports;

    @FXML
    private JFXButton btnReports;

    @FXML
    private VBox vBoxReportSub;
    Map<VBox,VBox> map = new HashMap<VBox,VBox>();

    ShopOrderBO shopOrderBO = BOFactory.getInstance().getBO(BOTypes.SHOP_ORDER);
    ShopBO shopBO = BOFactory.getInstance().getBO(BOTypes.SHOP);
    int timeRun = 0;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //getTime();
        FadeTransition fadeIn = new FadeTransition(Duration.millis(200), root);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);
        fadeIn.play();

        addMenusToMap();
        setComponentsSize();
        SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
        String date = DATE_FORMAT.format(new Date());
        lblDate.setText(date);

        tblShopOrdersMain.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("shopName"));
        tblShopOrdersMain.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("type"));
        tblShopOrdersMain.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("orderDueDate"));
        tblShopOrdersMain.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("qty"));
        try {
            loadShopOrders();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        btnPO.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                toolsSlider(vBoxPO, cBoxPOSub);
                removeOtherMenus(vBoxPO);
            }
        });

        btnShopOrder.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                toolsSlider(vBoxShopOrder, vBoxShopOrderSub);
                removeOtherMenus(vBoxShopOrder);
            }
        });

        btnShop.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                toolsSlider(vBoxShop, vBoxShopSub);
                removeOtherMenus(vBoxShop);
            }
        });

        btnPayment.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                toolsSlider(vBoxPayment, vBoxPaymentSub);
                removeOtherMenus(vBoxPayment);
            }
        });

        btnDelivery.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                toolsSlider(vBoxDelivery, vBoxDeliverySub);
                removeOtherMenus(vBoxDelivery);
            }
        });

        btnWorker.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                toolsSlider(vBoxWorker, vBoxWorkerSub);
                removeOtherMenus(vBoxWorker);
            }
        });

        btnReports.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                toolsSlider(vBoxReports, vBoxReportSub);
                removeOtherMenus(vBoxReports);
            }
        });
    }

    private void loadShopOrders() throws SQLException, ClassNotFoundException {
        List<ShopOrderDTO> allShopOrders = shopOrderBO.getAllShopOrders();
        ObservableList<ShopOrdersMainTM> items = tblShopOrdersMain.getItems();
        for (ShopOrderDTO dto:allShopOrders) {
            String name = shopBO.getShopById(dto.getShopId()).getShopName();
            items.add(new ShopOrdersMainTM(name,dto.getType(),dto.getdDate(),dto.getQty()));
        }
    }

    private void setComponentsSize() {
        vBoxMain.setPrefHeight(390  );

        //vBoxMain.prefHeightProperty().bind(vBoxMain.heightProperty());
    /*    vBoxMain.setPrefWidth(200);*/
    }


    // Add Menus to map
    public void addMenusToMap() {
        addMenusToMapImpl();
    }

    private void addMenusToMapImpl() {

        map.put(vBoxPO,cBoxPOSub);
        map.put(vBoxShopOrder,vBoxShopOrderSub);
        map.put(vBoxShop,vBoxShopSub);
        map.put(vBoxPayment,vBoxPaymentSub);
        map.put(vBoxDelivery,vBoxDeliverySub);
        map.put(vBoxWorker,vBoxWorkerSub);
        map.put(vBoxReports,vBoxReportSub);

        //Remove the components from VBox on load of stage
        for (Map.Entry<VBox,VBox> entry : map.entrySet()) {
            entry.getKey().getChildren().remove(entry.getValue());
        }
    }


    //Menu slider
    public void toolsSlider(VBox menu,VBox subMenu){
        toolsSliderImpl(menu,subMenu);
    }

    private void toolsSliderImpl(VBox menu,VBox subMenu) {
        if(menu.getChildren().contains(subMenu)){
            final FadeTransition transition = new FadeTransition(Duration.millis(500), menu);
            transition.setFromValue(0.5);
            transition.setToValue(1);
            transition.setInterpolator(Interpolator.EASE_IN);
            menu.getChildren().remove(subMenu);
            transition.play();
        }else{
            final FadeTransition transition = new FadeTransition(Duration.millis(500), menu);
            transition.setFromValue(0.5);
            transition.setToValue(1);
            transition.setInterpolator(Interpolator.EASE_IN);
            menu.getChildren().add(subMenu);
            transition.play();
        }
    }

    //Remove other menus
    public void removeOtherMenus(VBox menu){
        removeOtherMenusImpl(menu);
    }
    private void removeOtherMenusImpl(VBox menu) {
        for (Map.Entry<VBox,VBox> entry : map.entrySet()) {
            if(!entry.getKey().equals(menu))
                entry.getKey().getChildren().remove(entry.getValue());
        }
    }



    @FXML
    public void navigate(MouseEvent mouseEvent) throws IOException {
       if (mouseEvent.getSource() instanceof FontAwesomeIcon) {
            FontAwesomeIcon icon = (FontAwesomeIcon) mouseEvent.getSource();

            Parent root = null;

            FXMLLoader fxmlLoader = null;
            AnchorPane newLoadedPane;

            switch (icon.getId()) {
                case "iconHome":
                    newLoadedPane = FXMLLoader.load(getClass().getResource("/lk/ultratech/agent/sys/view/Mainform.fxml"));
                    this.root.getChildren().setAll(newLoadedPane);
                    lblTitle.setText("Mainform");
                    break;
                case "iconPurchase":
                    newLoadedPane = FXMLLoader.load(getClass().getResource("/lk/ultratech/agent/sys/view/AddPurchaseOrder.fxml"));
                    this.mainAnchorPane.getChildren().setAll(newLoadedPane);
                    lblTitle.setText("Add Purchase Order");
                    break;
                case "iconSettings":
                    newLoadedPane = FXMLLoader.load(getClass().getResource("/lk/ultratech/agent/sys/view/Settings.fxml"));
                    this.mainAnchorPane.getChildren().setAll(newLoadedPane);
                    lblTitle.setText("Settings");
                    break;
            }
        }
    }

    @FXML
    public void playMouseEnterAnimation(MouseEvent event) {
        if (event.getSource() instanceof ImageView){
            ImageView icon = (ImageView) event.getSource();

            switch(icon.getId()){
                case "imgShopPay":
                    lblSelected.setText("Add Shop Payment");
                 //   lblDescription.setText("Click to add, edit, delete, search or lk.ijse.dep.pos.lk.ultratech.agent.sys.view customers");
                    break;
                case "imgShopOrder":
                    lblSelected.setText("Add Shop Order");
               //     lblDescription.setText("Click to add, edit, delete, search or lk.ijse.dep.pos.lk.ultratech.agent.sys.view items");
                    break;
            }

            ScaleTransition scaleT =new ScaleTransition(Duration.millis(200), icon);
            scaleT.setToX(1.2);
            scaleT.setToY(1.2);
            scaleT.play();

            DropShadow glow = new DropShadow();
            glow.setColor(Color.CORNFLOWERBLUE);
            glow.setWidth(20);
            glow.setHeight(20);
            glow.setRadius(20);
            icon.setEffect(glow);
        }
    }

    public void playMouseExitAnimation(MouseEvent event) {
        if (event.getSource() instanceof ImageView){
            ImageView icon = (ImageView) event.getSource();
            ScaleTransition scaleT =new ScaleTransition(Duration.millis(200), icon);
            scaleT.setToX(1);
            scaleT.setToY(1);
            scaleT.play();

            icon.setEffect(null);
            lblSelected.setText("");
            /*lblMenu.setText("Welcome");
            lblDescription.setText("Please select one of above main operations to proceed");*/
        }
    }

    public void playMouseEnterAnim(MouseEvent event) throws IOException {
        if (event.getSource() instanceof FontAwesomeIcon){
            FontAwesomeIcon icon = (FontAwesomeIcon) event.getSource();
            Parent root = null;

            FXMLLoader fxmlLoader = null;
            AnchorPane newLoadedPane;

            switch(icon.getId()){
                case "iconHome":
                    //  lblMenu.setText("Manage Customers");
                    //   lblDescription.setText("Click to add, edit, delete, search or lk.ijse.dep.pos.lk.ultratech.agent.sys.view customers");
                       break;
                case "iconSettings":


            }

            ScaleTransition scaleT =new ScaleTransition(Duration.millis(200), icon);
            scaleT.setToX(1.2);
            scaleT.setToY(1.2);
            scaleT.play();

            DropShadow glow = new DropShadow();
            glow.setColor(Color.CORNFLOWERBLUE);
            glow.setWidth(20);
            glow.setHeight(20);
            glow.setRadius(20);
            icon.setEffect(glow);
        }
    }

    public void playMouseExitAnim(MouseEvent event) {
        if (event.getSource() instanceof FontAwesomeIcon){
            FontAwesomeIcon icon = (FontAwesomeIcon) event.getSource();
            ScaleTransition scaleT =new ScaleTransition(Duration.millis(200), icon);
            scaleT.setToX(1);
            scaleT.setToY(1);
            scaleT.play();

            icon.setEffect(null);
            /*lblMenu.setText("Welcome");
            lblDescription.setText("Please select one of above main operations to proceed");*/
        }
    }

    public void navigates(MouseEvent mouseEvent) throws IOException, JRException {

        if (mouseEvent.getSource() instanceof JFXButton) {
            JFXButton btn = (JFXButton) mouseEvent.getSource();

            Parent root = null;

            FXMLLoader fxmlLoader = null;
            AnchorPane newLoadedPane;

            switch (btn.getId()) {
                case "btnAddShop":
                    newLoadedPane = FXMLLoader.load(getClass().getResource("/lk/ultratech/agent/sys/view/AddShop.fxml"));
                    this.mainAnchorPane.getChildren().setAll(newLoadedPane);
                    lblTitle.setText("Add Shop");
                    break;
                case "btnAddPO":
                    newLoadedPane = FXMLLoader.load(getClass().getResource("/lk/ultratech/agent/sys/view/AddPurchaseOrder.fxml"));
                    this.mainAnchorPane.getChildren().setAll(newLoadedPane);
                    lblTitle.setText("Add Purchase Order");
                    break;
                case "btnAddWorker":
                    newLoadedPane = FXMLLoader.load(getClass().getResource("/lk/ultratech/agent/sys/view/AddWorker.fxml"));
                    this.mainAnchorPane.getChildren().setAll(newLoadedPane);
                    lblTitle.setText("Add Worker");
                    break;
                case "btnAddWorkerPayment":
                    newLoadedPane = FXMLLoader.load(getClass().getResource("/lk/ultratech/agent/sys/view/AddWorkerPayment.fxml"));
                    this.mainAnchorPane.getChildren().setAll(newLoadedPane);
                    lblTitle.setText("Add Worker Payment");
                    break;
                case "btnAddShopOrder":
                    newLoadedPane = FXMLLoader.load(getClass().getResource("/lk/ultratech/agent/sys/view/AddShopOrder.fxml"));
                    this.mainAnchorPane.getChildren().setAll(newLoadedPane);
                    lblTitle.setText("Add Shop Order");
                    break;
                case "btnViewPO":
                    newLoadedPane = FXMLLoader.load(getClass().getResource("/lk/ultratech/agent/sys/view/ViewPurchaseOrder.fxml"));
                    this.mainAnchorPane.getChildren().setAll(newLoadedPane);
                    lblTitle.setText("View Purchase Order");
                    break;
                case "btnManageShopOrder":
                    newLoadedPane = FXMLLoader.load(getClass().getResource("/lk/ultratech/agent/sys/view/ManageShopOrder.fxml"));
                    this.mainAnchorPane.getChildren().setAll(newLoadedPane);
                    lblTitle.setText("Manage Shop Order");
                    break;
                case "btnManageWorker":
                    newLoadedPane = FXMLLoader.load(getClass().getResource("/lk/ultratech/agent/sys/view/ManageWorker.fxml"));
                    this.mainAnchorPane.getChildren().setAll(newLoadedPane);
                    lblTitle.setText("Manage Worker");
                    break;
                case "btnAddDelivery":
                    newLoadedPane = FXMLLoader.load(getClass().getResource("/lk/ultratech/agent/sys/view/Add Delivery.fxml"));
                    this.mainAnchorPane.getChildren().setAll(newLoadedPane);
                    lblTitle.setText("Add Shop Delivery");
                    break;
                case "btnManageDelivery":
                    newLoadedPane = FXMLLoader.load(getClass().getResource("/lk/ultratech/agent/sys/view/ManageDelivery.fxml"));
                    this.mainAnchorPane.getChildren().setAll(newLoadedPane);
                    lblTitle.setText("Manage Delivery");
                    break;
                case "btnManageShop":
                    newLoadedPane = FXMLLoader.load(getClass().getResource("/lk/ultratech/agent/sys/view/ManageShop.fxml"));
                    this.mainAnchorPane.getChildren().setAll(newLoadedPane);
                    lblTitle.setText("Manage Shop");
                    break;
                case "btnAddPayment":
                    newLoadedPane = FXMLLoader.load(getClass().getResource("/lk/ultratech/agent/sys/view/AddShopPayment.fxml"));
                    this.mainAnchorPane.getChildren().setAll(newLoadedPane);
                    lblTitle.setText("Add Shop Payment");
                    break;
                case "btnManagePayment":
                    newLoadedPane = FXMLLoader.load(getClass().getResource("/lk/ultratech/agent/sys/view/ManageShopPayment.fxml"));
                    this.mainAnchorPane.getChildren().setAll(newLoadedPane);
                    lblTitle.setText("Manage Shop Payment");
                    break;
                case "btnChequePayment":
                    newLoadedPane = FXMLLoader.load(getClass().getResource("/lk/ultratech/agent/sys/view/ChequePayment.fxml"));
                    this.mainAnchorPane.getChildren().setAll(newLoadedPane);
                    lblTitle.setText("Shop Cheque Payments");
                    break;
                case "btnShopActivity":
                    newLoadedPane = FXMLLoader.load(getClass().getResource("/lk/ultratech/agent/sys/view/ShopActivity.fxml"));
                    this.mainAnchorPane.getChildren().setAll(newLoadedPane);
                    lblTitle.setText("Shop Activities");
                    break;
                case "btnDeliveryReports":
                    JasperReport jasperReport = (JasperReport) JRLoader.loadObject(this.getClass().getResourceAsStream("/lk/ultratech/agent/sys/report/Delivery.jasper"));
                    Map<String, Object> params = new HashMap<>();
                    JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, DBConnection.getInstance().getConnection());
                    JasperViewer.viewReport(jasperPrint, false);
                    break;
                case "btnPurchaseOrderReports":
                    JasperReport pojasperReport = (JasperReport) JRLoader.loadObject(this.getClass().getResourceAsStream("/lk/ultratech/agent/sys/report/CementAgent.jasper"));
                    Map<String, Object> oparams = new HashMap<>();
                    JasperPrint pojasperPrint = JasperFillManager.fillReport(pojasperReport, oparams, DBConnection.getInstance().getConnection());
                    JasperViewer.viewReport(pojasperPrint, false);
                    break;
            }
        }
    }

    public void logout(MouseEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Are you sure to Logout?",ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> buttonType = alert.showAndWait();
        if (buttonType.get() == ButtonType.YES) {
            System.exit(0);
        }else{

        }
    }

    public void navigatesByImg(MouseEvent event) throws IOException {
        if (event.getSource() instanceof ImageView) {
            ImageView icon = (ImageView) event.getSource();
            Parent root = null;

            FXMLLoader fxmlLoader = null;
            AnchorPane newLoadedPane;

            switch (icon.getId()) {
                case "imgShopPay":
                    newLoadedPane = FXMLLoader.load(getClass().getResource("/lk/ultratech/agent/sys/view/AddShopPayment.fxml"));
                    this.mainAnchorPane.getChildren().setAll(newLoadedPane);
                    lblTitle.setText("Add Shop Payment");
                    break;
                case "imgShopOrder":
                    newLoadedPane = FXMLLoader.load(getClass().getResource("/lk/ultratech/agent/sys/view/AddShopOrder.fxml"));
                    this.mainAnchorPane.getChildren().setAll(newLoadedPane);
                    lblTitle.setText("Add Shop Order");
                    break;
            }
        }
    }

   /* void getTime(){
        new Thread() {
            public void run() {
                while (timeRun == 0) {
                    Calendar cal = new GregorianCalendar();

                    int hour = cal.get(Calendar.HOUR);
                    if (hour == 0) {
                        hour = 12;
                    }
                    int min = cal.get(Calendar.MINUTE);
                    int sec = cal.get(Calendar.SECOND);
                    int AM_PM = cal.get(Calendar.AM_PM);

                    String day_night;
                    if (AM_PM == 1) {
                        day_night = "PM";
                    } else {
                        day_night = "AM";
                    }

                    String time = hour + ":" + min + ":" + sec + " " + day_night;
                    lblTime.setText(time);
                }
            }
        }.start();


*/
    }


