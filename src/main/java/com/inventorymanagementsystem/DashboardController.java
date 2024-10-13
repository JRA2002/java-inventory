package com.inventorymanagementsystem;

import com.inventorymanagementsystem.entity.*;
import com.inventorymanagementsystem.config.Database;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.GridPane;
import javafx.geometry.Insets;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Modality;
import javafx.stage.StageStyle;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

import static org.burningwave.core.assembler.StaticComponentContainer.Modules;

public class DashboardController implements Initializable {


    public FontAwesomeIconView product_print;
    private double x;
    private double y;

    @FXML
    private Button billing_btn;

    @FXML
    private Button product_btn;

    @FXML
    private AnchorPane billing_pane;

    @FXML
    private Button customer_btn;

    @FXML
    private Button dashboard_btn;

    @FXML
    private AnchorPane customer_pane;

    @FXML
    private AnchorPane product_pane;

    @FXML
    private AnchorPane dasboard_pane;


    @FXML
    private Button purchase_btn;

    @FXML
    private AnchorPane purchase_pane;

    @FXML
    private Button sales_btn;

    @FXML
    private AnchorPane sales_pane;

    @FXML
    private Label user;

    @FXML
    private Label inv_num;

    private Connection connection;

    private Statement statement;

    private PreparedStatement preparedStatement;

    private ResultSet resultSet;

    @FXML
    private Button bill_add;

    @FXML
    private Button bill_clear;

    @FXML
    private DatePicker bill_date;

    @FXML
    private TextField bill_item;

    @FXML
    private TextField bill_name;

    @FXML
    private TextField bill_phone;

    @FXML
    private TextField bill_price;

    @FXML
    private Button bill_print;

    @FXML
    private TextField sales_quantity;

    @FXML
    private ComboBox<?> prod_category;

    @FXML
    private Button bill_save;

    @FXML
    private TextField bill_total_amount;

    @FXML
    private TableView<SalesDetails> billing_table;

    @FXML
    private TextField billing_table_search;

    @FXML
    private Label final_amount;

    private  String invoiceList[]={"BX123456","ZX123456","AX123456"};

    private String unitList[]={"Kg","Ltr","Gr","Und"};
    @FXML
    private TableColumn<?, ?> col_bill_item_num;

    @FXML
    private TableColumn<?, ?> col_bill_item_name;

    @FXML
    private TableColumn<?, ?> col_bill_price;

    @FXML
    private TableColumn<?, ?> col_bill_quantity;

    @FXML
    private TableColumn<?, ?> col_bill_total_amt;

    @FXML
    private Button cust_btn_add;

    private boolean saleCreated = false;


    @FXML
    private Button cust_btn_delete;

    @FXML
    private Button cust_btn_edit;

    @FXML
    private TableView<User> customer_table;

    @FXML
    private TableColumn<?, ?> cust_col_id;

    @FXML
    private TableColumn<?, ?> cust_col_username;

    @FXML
    private TableColumn<?, ?> cust_col_email;

    @FXML
    private TableColumn<?, ?> cust_col_rol;

    @FXML
    private TableColumn<?, ?> cust_col_phone;

    @FXML
    private TableView<Product> product_table;

    @FXML
    private TableColumn<?, ?> prod_col_id;

    @FXML
    private TableColumn<?, ?> prod_col_name;

    @FXML
    private TableColumn<?, ?> prod_col_qty;

    @FXML
    private TableColumn<?, ?> prod_col_pre;

    @FXML
    private TableColumn<?, ?> prod_col_uni;

    @FXML
    private TableColumn<?, ?> prod_col_cat_name;

    @FXML
    private TableColumn<?, ?> prod_col_date;

    @FXML
    private TextField prod_field_search;

    @FXML
    private Button prod_btn_print;

    @FXML
    private Button prod_btn_search;

    @FXML
    private Button prod_btn_delete;

    @FXML
    private Button prod_btn_update;

    @FXML
    private Button prod_btn_add;

    @FXML
    private TextField cust_field_id;

    @FXML
    private TextField cust_field_username;

    @FXML
    private TextField prod_field_name;

    @FXML
    private TextField prod_field_price;

    @FXML
    private TextField cust_field_password;

    @FXML
    private TextField cust_field_phone;

    @FXML
    private TextField cust_field_email;

    @FXML
    private TextField cust_field_rol;

    @FXML
    private TextField customer_search;



    @FXML
    private TableColumn<?, ?> sales_col_cust_name;

    @FXML
    private TableColumn<?, ?> sales_col_date_of_sales;

    @FXML
    private TableColumn<?, ?> sales_col_id;

    @FXML
    private TableColumn<?, ?> sales_col_inv_num;

    @FXML
    private TableColumn<?, ?> sales_col_quantity;

    @FXML
    private TableColumn<?, ?> sales_col_total_amount;

    @FXML
    private TableColumn<?, ?> sales_col_price;

    @FXML
    private TableColumn<?, ?> sales_col_item_num;

    @FXML
    private TableView<Sales> sales_table;

    @FXML
    private Label sales_total_amount;

    @FXML
    private Button purchase_btn_add;

    @FXML
    private Button purchase_btn_print;

    @FXML
    private Label purchase_total_amount;

    @FXML
    private TableColumn<?, ?> purchase_col_date_of_purchase;

    @FXML
    private TableColumn<?, ?> purchase_col_id;

    @FXML
    private TableColumn<?, ?> purchase_col_invoice;

    @FXML
    private TableColumn<?, ?> purchase_col_shop_details;

    @FXML
    private TableColumn<?, ?> purchase_col_total_amount;

    @FXML
    private TableColumn<?, ?> purchase_col_total_items;

    @FXML
    private TableView<Purchase> purchase_table;

    @FXML
    private Label dash_total_items_sold_this_month;

    @FXML
    private Label dash_total_purchase;

    @FXML
    private Label dash_total_sales_items_this_month_name;

    @FXML
    private Label dash_total_sales_this_month;

    @FXML
    private Label dash_total_sales_this_month_name;

    @FXML
    private Label dash_total_sold;

    @FXML
    private Label dash_total_stocks;

    @FXML
    private Button signout_btn;

    ObservableList<Product> productsList;

    public void onExit(){
        System.exit(0);
    }

    public void activateAnchorPane(){
        dashboard_btn.setOnMouseClicked(mouseEvent -> {
            dasboard_pane.setVisible(true);
            product_pane.setVisible(false);
            billing_pane.setVisible(false);
            customer_pane.setVisible(false);
            sales_pane.setVisible(false);
            purchase_pane.setVisible(false);
            dashboard_btn.setStyle("-fx-background-color:linear-gradient(to bottom right , rgba(121,172,255,0.7),  rgba(255,106,239,0.7))");
            billing_btn.setStyle("-fx-background-color:linear-gradient(to bottom right , rgba(121,172,255,0.2),  rgba(255,106,239,0.2))");
            product_btn.setStyle("-fx-background-color:linear-gradient(to bottom right , rgba(121,172,255,0.2),  rgba(255,106,239,0.2))");
            customer_btn.setStyle("-fx-background-color:linear-gradient(to bottom right , rgba(121,172,255,0.2),  rgba(255,106,239,0.2))");
            sales_btn.setStyle("-fx-background-color:linear-gradient(to bottom right , rgba(121,172,255,0.2),  rgba(255,106,239,0.2))");
            purchase_btn.setStyle("-fx-background-color:linear-gradient(to bottom right , rgba(121,172,255,0.2),  rgba(255,106,239,0.2))");
        });
        billing_btn.setOnMouseClicked(mouseEvent -> {
            dasboard_pane.setVisible(false);
            billing_pane.setVisible(true);
            product_pane.setVisible(false);
            customer_pane.setVisible(false);
            sales_pane.setVisible(false);
            purchase_pane.setVisible(false);
            dashboard_btn.setStyle("-fx-background-color:linear-gradient(to bottom right , rgba(121,172,255,0.2),  rgba(255,106,239,0.2))");
            billing_btn.setStyle("-fx-background-color:linear-gradient(to bottom right , rgba(121,172,255,0.7),  rgba(255,106,239,0.7))");
            customer_btn.setStyle("-fx-background-color:linear-gradient(to bottom right , rgba(121,172,255,0.2),  rgba(255,106,239,0.2))");
            sales_btn.setStyle("-fx-background-color:linear-gradient(to bottom right , rgba(121,172,255,0.2),  rgba(255,106,239,0.2))");
            product_btn.setStyle("-fx-background-color:linear-gradient(to bottom right , rgba(121,172,255,0.2),  rgba(255,106,239,0.2))");
            purchase_btn.setStyle("-fx-background-color:linear-gradient(to bottom right , rgba(121,172,255,0.2),  rgba(255,106,239,0.2))");
        });
        product_btn.setOnMouseClicked(mouseEvent -> {
            dasboard_pane.setVisible(false);
            billing_pane.setVisible(false);
            product_pane.setVisible(true);
            customer_pane.setVisible(false);
            sales_pane.setVisible(false);
            purchase_pane.setVisible(false);
            product_btn.setStyle("-fx-background-color:linear-gradient(to bottom right , rgba(121,172,255,0.7),  rgba(255,106,239,0.7))");
            dashboard_btn.setStyle("-fx-background-color:linear-gradient(to bottom right , rgba(121,172,255,0.2),  rgba(255,106,239,0.2))");
            billing_btn.setStyle("-fx-background-color:linear-gradient(to bottom right , rgba(121,172,255,0.2),  rgba(255,106,239,0.2))");
            customer_btn.setStyle("-fx-background-color:linear-gradient(to bottom right , rgba(121,172,255,0.2),  rgba(255,106,239,0.2))");
            sales_btn.setStyle("-fx-background-color:linear-gradient(to bottom right , rgba(121,172,255,0.2),  rgba(255,106,239,0.2))");
            purchase_btn.setStyle("-fx-background-color:linear-gradient(to bottom right , rgba(121,172,255,0.2),  rgba(255,106,239,0.2))");
        });
        customer_btn.setOnMouseClicked(mouseEvent -> {
            dasboard_pane.setVisible(false);
            billing_pane.setVisible(false);
            product_pane.setVisible(false);
            customer_pane.setVisible(true);
            sales_pane.setVisible(false);
            purchase_pane.setVisible(false);
            dashboard_btn.setStyle("-fx-background-color:linear-gradient(to bottom right , rgba(121,172,255,0.2),  rgba(255,106,239,0.2))");
            billing_btn.setStyle("-fx-background-color:linear-gradient(to bottom right , rgba(121,172,255,0.2),  rgba(255,106,239,0.2))");
            customer_btn.setStyle("-fx-background-color:linear-gradient(to bottom right , rgba(121,172,255,0.7),  rgba(255,106,239,0.7))");
            sales_btn.setStyle("-fx-background-color:linear-gradient(to bottom right , rgba(121,172,255,0.2),  rgba(255,106,239,0.2))");
            product_btn.setStyle("-fx-background-color:linear-gradient(to bottom right , rgba(121,172,255,0.2),  rgba(255,106,239,0.2))");
            purchase_btn.setStyle("-fx-background-color:linear-gradient(to bottom right , rgba(121,172,255,0.2),  rgba(255,106,239,0.2))");
        });
        sales_btn.setOnMouseClicked(mouseEvent -> {
            dasboard_pane.setVisible(false);
            billing_pane.setVisible(false);
            product_pane.setVisible(false);
            customer_pane.setVisible(false);
            sales_pane.setVisible(true);
            purchase_pane.setVisible(false);
            dashboard_btn.setStyle("-fx-background-color:linear-gradient(to bottom right , rgba(121,172,255,0.2),  rgba(255,106,239,0.2))");
            billing_btn.setStyle("-fx-background-color:linear-gradient(to bottom right , rgba(121,172,255,0.2),  rgba(255,106,239,0.2))");
            customer_btn.setStyle("-fx-background-color:linear-gradient(to bottom right , rgba(121,172,255,0.2),  rgba(255,106,239,0.2))");
            sales_btn.setStyle("-fx-background-color:linear-gradient(to bottom right , rgba(121,172,255,0.7),  rgba(255,106,239,0.7))");
            product_btn.setStyle("-fx-background-color:linear-gradient(to bottom right , rgba(121,172,255,0.2),  rgba(255,106,239,0.2))");
            purchase_btn.setStyle("-fx-background-color:linear-gradient(to bottom right , rgba(121,172,255,0.2),  rgba(255,106,239,0.2))");
        });
        purchase_btn.setOnMouseClicked(mouseEvent -> {
            dasboard_pane.setVisible(false);
            billing_pane.setVisible(false);
            product_pane.setVisible(false);
            customer_pane.setVisible(false);
            sales_pane.setVisible(false);
            purchase_pane.setVisible(true);
            dashboard_btn.setStyle("-fx-background-color:linear-gradient(to bottom right , rgba(121,172,255,0.2),  rgba(255,106,239,0.2))");
            billing_btn.setStyle("-fx-background-color:linear-gradient(to bottom right , rgba(121,172,255,0.2),  rgba(255,106,239,0.2))");
            customer_btn.setStyle("-fx-background-color:linear-gradient(to bottom right , rgba(121,172,255,0.2),  rgba(255,106,239,0.2))");
            sales_btn.setStyle("-fx-background-color:linear-gradient(to bottom right , rgba(121,172,255,0.2),  rgba(255,106,239,0.2))");
            product_btn.setStyle("-fx-background-color:linear-gradient(to bottom right , rgba(121,172,255,0.2),  rgba(255,106,239,0.2))");
            purchase_btn.setStyle("-fx-background-color:linear-gradient(to bottom right , rgba(121,172,255,0.7),  rgba(255,106,239,0.7))");
            });

    }

    public void setUsername() {
        User loggedInUser = Session.getCurrentUser();
        user.setText(loggedInUser.getUsername().toUpperCase());
    }

    private void checkUserRole() {
        User loggedInUser = Session.getCurrentUser();
        if ("admin".equals(loggedInUser.getRol())) {
            cust_btn_add.setVisible(true);
            cust_btn_delete.setVisible(true);
            cust_btn_edit.setVisible(true);
        } else {
            cust_btn_add.setVisible(false);
            cust_btn_delete.setVisible(false);
            cust_btn_edit.setVisible(false);
        }
    }

    public void activateDashboard(){
        dasboard_pane.setVisible(true);
        billing_pane.setVisible(false);
        product_pane.setVisible(false);
        customer_pane.setVisible(false);
        sales_pane.setVisible(false);
        purchase_pane.setVisible(false);
    }

    public ObservableList<Product> getItemsList(){

        productsList=FXCollections.observableArrayList();
        connection= Database.getInstance().connectDB();
        String sql="SELECT pd.id,pd.name,pd.unit, pd.quantity,pd.price,ct.cat_name,pd.exp_date,ln.loc_name FROM products AS pd \n" +
                "JOIN category AS ct \n" +
                "JOIN location AS ln \n" +
                "WHERE pd.cat_id=ct.id and pd.loc_id=ln.loc_id;";
        try{
            statement=connection.createStatement();
            resultSet=statement.executeQuery(sql);
            Product product;
            while (resultSet.next()){
                product = new Product(
                        Integer.parseInt(resultSet.getString("id")),       // id
                        resultSet.getString("name"),                       // name
                        resultSet.getString("unit"),                       // unit
                        Integer.parseInt(resultSet.getString("quantity")), // quantity
                        Double.parseDouble(resultSet.getString("price")),
                        resultSet.getString("cat_name"),// price
                        resultSet.getDate("exp_date").toLocalDate(),
                        resultSet.getString("loc_name")
                );
                productsList.add(product);
            }
        }catch (Exception err){
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setHeight(500);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText(err.getMessage());
            alert.showAndWait();
        }
        prod_field_search.textProperty().addListener((observable, oldValue, newValue) -> filterProducts(newValue));
        return productsList;
    }
    public void filterProducts(String searchText) {
        ObservableList<Product> filteredList = FXCollections.observableArrayList();

        if (searchText == null || searchText.isEmpty()) {
            product_table.setItems(productsList);
        } else {
            for (Product product : productsList) {
                if (product.getName().toLowerCase().contains(searchText.toLowerCase()) ||
                        product.getCat_name().toLowerCase().contains(searchText.toLowerCase())) {
                    filteredList.add(product);
                }
            }
            product_table.setItems(filteredList);
        }
    }
    public void showProductsData(){
        ObservableList<Product> productsList=getItemsList();
        prod_col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        prod_col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        prod_col_pre.setCellValueFactory(new PropertyValueFactory<>("price"));
        prod_col_qty.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        prod_col_uni.setCellValueFactory(new PropertyValueFactory<>("unit"));
        prod_col_cat_name.setCellValueFactory(new PropertyValueFactory<>("cat_name"));
        prod_col_date.setCellValueFactory(new PropertyValueFactory<>("exp_date"));
        product_table.setItems(productsList);

    }

    public void printProductsDetails(){
        connection=Database.getInstance().connectDB();
        String sql="SELECT * FROM products";
        try{
            JasperDesign jasperDesign= JRXmlLoader.load(this.getClass().getClassLoader().getResourceAsStream("jasper-reports/products.jrxml"));
            JRDesignQuery updateQuery=new JRDesignQuery();
            updateQuery.setText(sql);
            jasperDesign.setQuery(updateQuery);
            JasperReport jasperReport= JasperCompileManager.compileReport(jasperDesign);
            JasperPrint jasperPrint= JasperFillManager.fillReport(jasperReport,null,connection);
            JasperViewer.viewReport(jasperPrint ,false);
        }catch (Exception err){
            err.printStackTrace();
        }
    }

    public void searchProductsLocation(){
        if(product_table.getSelectionModel().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Message");
            alert.setHeaderText(null);
            alert.setContentText("Por favor seleccione un producto.");
            alert.showAndWait();
            return;
        }
        String productLocation = product_table.getSelectionModel().getSelectedItem().getLoc_name();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Producto encontrado");
        alert.setHeaderText(null);
        alert.setContentText("El producto esta en: " + productLocation.toUpperCase());
        alert.showAndWait();
    }
    public void editProduct(){

    }
    public void deleteProduct(){
        if(product_table.getSelectionModel().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Message");
            alert.setHeaderText(null);
            alert.setContentText("Por favor selecciona aun producto.");
            alert.showAndWait();
            return;
        }
        String productName = product_table.getSelectionModel().getSelectedItem().getName();

        Alert confirmationAlert = new Alert(Alert.AlertType.WARNING);
        confirmationAlert.setTitle("Delete Confirmation");
        confirmationAlert.setHeaderText("Esta  seguro de eliminar a este producto?");
        confirmationAlert.setContentText("Producto: " + productName.toUpperCase());

        Optional<ButtonType> result1 = confirmationAlert.showAndWait();

        if (result1.isPresent() && result1.get() == ButtonType.OK) {
            connection = Database.getInstance().connectDB();

            String sql="DELETE FROM products WHERE id=?";
            try {
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1,product_table.getSelectionModel().getSelectedItem().getId());
                int result = preparedStatement.executeUpdate();

                if (result > 0) {
                    showCustomerData();
                    customerClearData();
                } else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Message");
                    alert.setHeaderText(null);
                    alert.setContentText("No hay productos en la Tabla.");
                    alert.showAndWait();
                }
            } catch (Exception err) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeight(500);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText(err.getMessage());
                alert.showAndWait();
            }

        }
    }

    public void updateProduct(){
        showProductsData();
    }
    public ComboBox<String> comboBoxUnit(){
        ComboBox<String> comboUnit = new ComboBox<>();
        for(String unit:unitList){
            comboUnit.getItems().add(unit);
        }
        return comboUnit;
    }

    public ComboBox<Category> comboCategoryData(){
        ComboBox<Category> categoryCombo=new ComboBox<>();
        connection=Database.getInstance().connectDB();
        String sql="SELECT * FROM category";
        try{
            statement=connection.createStatement();
            resultSet=statement.executeQuery(sql);


            Category categoryData;
            while (resultSet.next()){
                categoryData=new Category(
                        Integer.parseInt(resultSet.getString("id")),
                        resultSet.getString("cat_name"));
                System.out.println(categoryData);
                categoryCombo.getItems().add(categoryData);
            }

        }catch (Exception err){
            err.printStackTrace();
        }
        return categoryCombo;
    }

    public ComboBox<Supplier> comboSupplierData(){
        ComboBox<Supplier> supplierCombo=new ComboBox<>();
        connection=Database.getInstance().connectDB();
        String sql="SELECT * FROM supplier";
        try{
            statement=connection.createStatement();
            resultSet=statement.executeQuery(sql);
            Supplier supplierData;
            while (resultSet.next()){
                supplierData=new Supplier(
                        Integer.parseInt(resultSet.getString("id")),
                        resultSet.getString("name"),
                        resultSet.getString("phone"));
                System.out.println(supplierData);
                supplierCombo.getItems().add(supplierData);
            }

        }catch (Exception err){
            err.printStackTrace();
        }

        return supplierCombo;
    }

    public ComboBox<Location> comboLocationData(){
        ComboBox<Location> locationCombo=new ComboBox<>();
        connection=Database.getInstance().connectDB();
        String sql="SELECT * FROM location";
        try{
            statement=connection.createStatement();
            resultSet=statement.executeQuery(sql);

            Location locationData;
            while (resultSet.next()){
                locationData=new Location(
                        Integer.parseInt(resultSet.getString("loc_id")),
                        resultSet.getString("loc_name"));

                System.out.println(locationData);
                locationCombo.getItems().add(locationData);
            }

        }catch (Exception err){
            err.printStackTrace();
        }

        return locationCombo;
    }

    public void addProduct(){

        Stage popup_window = new Stage();
        popup_window.initModality(Modality.APPLICATION_MODAL);
        popup_window.setTitle("Agregar nuevo producto");

        Label lblName = new Label("Producto:");
        TextField prod_field_name = new TextField();
        Label lblUnit = new Label("Unidad:");
        ComboBox<String> comboBoxUnit = comboBoxUnit();
        Label lblPrice = new Label("Precio:");
        TextField prod_field_price = new TextField();
        Label lblCat = new Label("Categoria:");
        ComboBox<Category> categoryList=comboCategoryData();
        Label lblQty = new Label("Cantidad:");
        TextField prod_field_qty = new TextField();
        Label lblDate = new Label("Vencimiento:");
        DatePicker expDate = new DatePicker();
        Label lblSupp = new Label("Proveedor:");
        ComboBox<Supplier> supplierCombo=comboSupplierData();
        Label lblLoc = new Label("Location:");
        ComboBox<Location> locationCombo=comboLocationData();

        Button btnSave = new Button("Guardar");
        btnSave.setOnAction(e -> {

            Category categorySelected = categoryList.getValue();
            int cat_id = categorySelected.getId();

            Supplier supplierSelected = supplierCombo.getValue();
            int supp_id = supplierSelected.getSupp_id();

            Location locationSelected = locationCombo.getValue();
            int loc_id = locationSelected.getLoc_id();

            LocalDate dateSelected = expDate.getValue();
            String date_exp = dateSelected.toString();

            String unitSelected = comboBoxUnit.getValue();

            connection=Database.getInstance().connectDB();
            String sql="INSERT INTO products(name, cat_id,quantity,price,exp_date,unit,supp_id,loc_id)VALUES(?,?,?,?,?,?,?,?)";
            try{

                preparedStatement=connection.prepareStatement(sql);
                preparedStatement.setString(1,prod_field_name.getText());
                preparedStatement.setInt(2,cat_id);
                preparedStatement.setInt(3,Integer.parseInt(prod_field_qty.getText()));
                preparedStatement.setDouble(4,Double.parseDouble(prod_field_price.getText()));
                preparedStatement.setString(5,date_exp);
                preparedStatement.setString(6,unitSelected);
                preparedStatement.setInt(7,supp_id);
                preparedStatement.setInt(8,loc_id);

                int result=preparedStatement.executeUpdate();
                if(result>0){
                    showCustomerData();
                    customerClearData();
                }else{
                    Alert alert=new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Please fill the mandatory data such as name and price.");
                    alert.showAndWait();
                }
            }catch (Exception err){
                err.printStackTrace();
            }

            popup_window.close();
        });
        GridPane layout = new GridPane();
        layout.setPadding(new Insets(20));
        layout.setVgap(10);
        layout.setHgap(10);
        layout.add(lblName, 0, 0);
        layout.add(prod_field_name, 1, 0);
        layout.add(lblPrice, 0, 1);
        layout.add(prod_field_price, 1, 1);
        layout.add(lblCat, 0, 2);
        layout.add(categoryList, 1, 2);
        layout.add(lblQty, 0, 3);
        layout.add(prod_field_qty, 1, 3);
        layout.add(lblDate, 0, 4);
        layout.add(expDate, 1, 4);
        layout.add(lblUnit, 0, 5);
        layout.add(comboBoxUnit, 1, 5);
        layout.add(lblSupp, 0, 6);
        layout.add(supplierCombo, 1, 6);
        layout.add(lblLoc, 0, 7);
        layout.add(locationCombo, 1, 7);
        layout.add(btnSave, 1, 9);

        Scene scene = new Scene(layout, 300, 400);
        popup_window.setScene(scene);

        popup_window.showAndWait();
    }

    private boolean isInteger(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public void createNewSale() {
        if(bill_item.getText().isBlank()||sales_quantity.getText().isEmpty()){
            Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Message");
            alert.setHeaderText(null);
            alert.setContentText("Por Favor ingrese codigo de producto o cantidad");
            alert.showAndWait();
            return;
        }else if(!isInteger(bill_item.getText()) || !isInteger(sales_quantity.getText()) ){
            Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Message");
            alert.setHeaderText(null);
            alert.setContentText("Por Favor ingrese un ID o cantidad valida");
            alert.showAndWait();
            return;
        } else if (!existsProductId()) {
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Message");
            alert.setHeaderText(null);
            alert.setContentText("PRODUCT ID NO EXISTE !!");
            alert.showAndWait();
            return;
        }
        connection=Database.getInstance().connectDB();
        String sql="INSERT INTO sales(date,user_id)VALUES(?,?)";

        try{
            User loggedInUser = Session.getCurrentUser();
            int userId = loggedInUser.getId();
            LocalDate date = LocalDate.now();
            Date dateSale = Date.valueOf(date);

            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setDate(1,dateSale);
            preparedStatement.setInt(2,userId);

            int result=preparedStatement.executeUpdate();
            if(result>0){
                saleCreated = true;
                System.out.println("heeeree");
            }else{
                Alert alert=new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill the mandatory data such as name and price.");
                alert.showAndWait();
            }
        }catch (Exception err) {
            err.printStackTrace();
        }
    }
    public void insertNewProductDetailsSales(int salesId, int productId, int quantity){
        connection=Database.getInstance().connectDB();
        String sql="INSERT INTO details_sales(sales_id,quantity,product_id)VALUES(?,?,?)";

        try{
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setInt(1,salesId);
            preparedStatement.setInt(2,quantity);
            preparedStatement.setInt(3,productId);

            int result=preparedStatement.executeUpdate();
            if(result>0){
                System.out.println("heeeree in details");
            }else{
                Alert alert=new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill the mandatory data such as name and price.");
                alert.showAndWait();
            }
        }catch (Exception err) {
            err.printStackTrace();
        }
    }

    public int getSalesId(){
        int salesId=0;
        if(saleCreated){
            connection=Database.getInstance().connectDB();
            String sql="SELECT MAX(sales_id) AS sales_id FROM sales";
            try{
                statement=connection.createStatement();
                resultSet=statement.executeQuery(sql);

                while (resultSet.next()){
                    salesId = resultSet.getInt("sales_id");
                }

            }catch (Exception err){
                err.printStackTrace();
            }
        }

        return salesId;
    }
    private boolean existsProductId(){

            String input = bill_item.getText();
            int productId = Integer.parseInt(input);
            return productsList.stream().anyMatch(product -> product.getId() == productId);

    }

    public void addProductBilling() {
        try {
            if (!saleCreated && existsProductId()) {
                createNewSale();
            } else if (!existsProductId()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Message");
                alert.setHeaderText(null);
                alert.setContentText("PRODUCT ID INVALIDO");
                alert.showAndWait();
                billClearData();
                return;
            }
            int salId = getSalesId();
            int productId = Integer.parseInt(bill_item.getText());
            int quantity = Integer.parseInt(sales_quantity.getText());

            insertNewProductDetailsSales(salId, productId, quantity);

        } catch (Exception err) {
            err.printStackTrace();
        }
        billClearData();
        showBillingData();
    }

    public ObservableList<SalesDetails> listBillingData(int salesId){
        ObservableList<SalesDetails> billingList=FXCollections.observableArrayList();
        connection=Database.getInstance().connectDB();
        String sql="SELECT p.id,ds.quantity,p.name, p.price,(p.price*ds.quantity) AS subtotal " +
                "FROM details_sales AS ds " +
                "JOIN products AS p ON ds.product_id=p.id\n" +
                "WHERE ds.sales_id=?;";
        try{
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setInt(1,salesId);
            resultSet=preparedStatement.executeQuery();


              SalesDetails billingData;
              while (resultSet.next()){
              billingData=new SalesDetails(
                      Integer.parseInt(resultSet.getString("id")),
                      resultSet.getString("name"),
                      Integer.parseInt(resultSet.getString("quantity")),
                      Double.parseDouble(resultSet.getString("price")),
                  Double.parseDouble(resultSet.getString("subtotal")));

              billingList.addAll(billingData);
             }


        }catch (Exception err){
            err.printStackTrace();
        }

        return billingList;
    }

    public void calculateFinalAmount(){
        int salId = getSalesId();
        connection=Database.getInstance().connectDB();
        String sql="SELECT ds.sales_id,SUM(ds.quantity*p.price) AS final_amount " +
                "FROM details_sales AS ds JOIN products AS p\n" +
                "WHERE ds.product_id=p.id and ds.sales_id=?;";
        try{
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setInt(1,salId);
            resultSet=preparedStatement.executeQuery();
            if(resultSet.next()){
                final_amount.setText(resultSet.getString("final_amount"));
            }

        }catch (Exception err){
            err.printStackTrace();
        }

    }

    public void showBillingData(){
        int salId = getSalesId();
        ObservableList<SalesDetails> billingList=listBillingData(salId);
        col_bill_item_num.setCellValueFactory(new PropertyValueFactory<>("productId"));
        col_bill_item_name.setCellValueFactory(new PropertyValueFactory<>("productName"));
        col_bill_quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        col_bill_price.setCellValueFactory(new PropertyValueFactory<>("prodPrice"));
        col_bill_total_amt.setCellValueFactory(new PropertyValueFactory<>("subTotal"));
        billing_table.setItems(billingList);

        if(!billingList.isEmpty()){
            calculateFinalAmount();
        }else{
            final_amount.setText("0.00");
        }
    }

    public void billClearData(){
        bill_item.clear();
        sales_quantity.clear();
    }

    public void billCancelSale(){
        int salId = getSalesId();
        if (salId != 0){
            connection = Database.getInstance().connectDB();
            String sql="DELETE FROM sales WHERE sales_id=?";
            try {
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1,salId);
                int result = preparedStatement.executeUpdate();

                if (result > 0) {
                    billClearData();
                    billing_table.getItems().clear();
                } else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Message");
                    alert.setHeaderText(null);
                    alert.setContentText("No hay datos en la Tabla.");
                    alert.showAndWait();
                }
            } catch (Exception err) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeight(500);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText(err.getMessage());
                alert.showAndWait();
            }
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Message");
            alert.setHeaderText(null);
            alert.setContentText("Aun no ha hecho una venta !!");
            alert.showAndWait();
        }
    }

    public void deleteBillingData(){
        if(billing_table.getSelectionModel().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Message");
            alert.setHeaderText(null);
            alert.setContentText("Por favor seleccione un producto.");
            alert.showAndWait();
            return;
        }
        int salId = getSalesId();
        connection = Database.getInstance().connectDB();
        String sql="DELETE FROM details_sales WHERE product_id=? and sales_id=?";
            try {
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1,billing_table.getSelectionModel().getSelectedItem().getProductId());
                preparedStatement.setInt(2,salId);
                int result = preparedStatement.executeUpdate();

                if (result > 0) {
                    showCustomerData();
                    customerClearData();
                } else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Message");
                    alert.setHeaderText(null);
                    alert.setContentText("No hay datos en la Tabla.");
                    alert.showAndWait();
                }
            } catch (Exception err) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeight(500);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText(err.getMessage());
                alert.showAndWait();
            }
        showBillingData();
    }

    public void billSave(){

        if(!billing_table.getItems().isEmpty()) {
            saleCreated=false;
            billing_table.getItems().clear();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Venta Realizada");
            alert.setHeaderText(null);
            alert.setContentText("Venta realizada con exito");
            alert.showAndWait();
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Agregue productos para realizar la venta");
            alert.showAndWait();
        }

    }

    public void printBill(){
     connection=Database.getInstance().connectDB();
     String sql="SELECT * FROM `sales` s INNER JOIN customers c ON s.cust_id=c.id and s.inv_num=(SELECT MAX(inv_num) as inv_num FROM `sales`)";
     try{
         JasperDesign jasperDesign= JRXmlLoader.load(this.getClass().getClassLoader().getResourceAsStream("jasper-reports/Invoice.jrxml"));
         JRDesignQuery updateQuery=new JRDesignQuery();
         updateQuery.setText(sql);
         jasperDesign.setQuery(updateQuery);
         JasperReport jasperReport= JasperCompileManager.compileReport(jasperDesign);
         JasperPrint jasperPrint= JasperFillManager.fillReport(jasperReport,null,connection);
         JasperViewer.viewReport(jasperPrint ,false);
     }catch (Exception err){
      err.printStackTrace();
     }
    }
    public void searchForBills(){
        try{
            Parent root = FXMLLoader.load(getClass().getResource("bills.fxml"));
            Scene scene = new Scene(root);
            Stage stage=new Stage();
            root.setOnMousePressed((event)->{
                x=event.getSceneX();
                y=event.getSceneY();
            });
            root.setOnMouseDragged((event)->{
                stage.setX(event.getScreenX()-x);
                stage.setY(event.getScreenY()-y);
            });
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setScene(scene);
            stage.show();
        }catch (Exception err){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeight(500);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText(err.getMessage());
            alert.showAndWait();
        }
    }
    public void customerClearData(){
        cust_field_username.setText("");
        cust_field_phone.setText("");
        cust_field_email.setText("");
        cust_field_rol.setText("");
        cust_field_password.setText("");
    }
    public ObservableList<User> listCustomerData(){
        ObservableList<User> customersList=FXCollections.observableArrayList();
        connection=Database.getInstance().connectDB();
        String sql="SELECT * FROM users";
        try{
            statement=connection.createStatement();
            resultSet=statement.executeQuery(sql);

            User customer;
            while (resultSet.next()){
                customer=new User(
                        Integer.parseInt(resultSet.getString("id")),
                        resultSet.getString("username"),
                        resultSet.getString("password"),
                        resultSet.getString("phone"),
                        resultSet.getString("email"),
                        resultSet.getString("rol"));
                customersList.add(customer);
            }


        }catch (Exception err){
            err.printStackTrace();
        }
        return customersList;
    }
    public void showCustomerData(){
        ObservableList<User> customerList=listCustomerData();
        cust_col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        cust_col_username.setCellValueFactory(new PropertyValueFactory<>("username"));
        cust_col_phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        cust_col_email.setCellValueFactory(new PropertyValueFactory<>("email"));
        cust_col_rol.setCellValueFactory(new PropertyValueFactory<>("rol"));
        customer_table.setItems(customerList);
    }
    public boolean checkForCustomerAvailability(){
        connection=Database.getInstance().connectDB();
        String sql="SELECT * FROM users WHERE phone=?";
        try{
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,cust_field_phone.getText());
            resultSet=preparedStatement.executeQuery();
            if(resultSet.next()){
                Alert alert=new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Message");
                alert.setHeaderText(null);
                alert.setContentText("Customer already present in the customer table.");
                alert.showAndWait();
                return false;
            }else {
              return true;
            }
        }catch (Exception err){
            err.printStackTrace();
        }

        return false;
    }
    public void addCustomerData(){
        if(!checkForCustomerAvailability()){
            return;
        }
        connection=Database.getInstance().connectDB();
        String sql="INSERT INTO users(username,password,phone,email,rol)VALUES(?,?,?,?,?)";
        try{
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,cust_field_username.getText());
            preparedStatement.setString(2,cust_field_password.getText());
            preparedStatement.setString(3,cust_field_phone.getText());
            preparedStatement.setString(4,cust_field_email.getText());
            preparedStatement.setString(5,cust_field_rol.getText());
            int result=preparedStatement.executeUpdate();
            if(result>0){
                showCustomerData();
                customerClearData();
            }else{
                Alert alert=new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill the mandatory data such as name and phone number.");
                alert.showAndWait();
            }
        }catch (Exception err){
            err.printStackTrace();
        }
    }
    public void selectCustomerTableData(){
        int num=customer_table.getSelectionModel().getSelectedIndex();
        User customerData=customer_table.getSelectionModel().getSelectedItem();
        if(num-1 < -1){
            return;
        }

        cust_field_username.setText(customerData.getUsername());
        cust_field_phone.setText(customerData.getPhone());
        cust_field_email.setText(customerData.getEmail());
        cust_field_rol.setText(customerData.getRol());
    }

    public void updateCustomerData(){

        connection = Database.getInstance().connectDB();
        String sql = "UPDATE users SET phone=?, email=?, password=?,rol=? WHERE username=?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, cust_field_phone.getText());
            preparedStatement.setString(2, cust_field_email.getText());
            preparedStatement.setString(3, cust_field_password.getText());
            preparedStatement.setString(4, cust_field_rol.getText());
            preparedStatement.setString(5, cust_field_username.getText());

            int result = preparedStatement.executeUpdate();
            if (result > 0) {
                showCustomerData();
                customerClearData();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill the mandatory data such as username, phone number .");
                alert.showAndWait();
            }
        } catch (Exception err) {
            err.printStackTrace();
        }
    }

    public void deleteCustomerData(){
        if(customer_table.getSelectionModel().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Message");
            alert.setHeaderText(null);
            alert.setContentText("Por favor seleciona aun usuario.");
            alert.showAndWait();
            return;
        }
        String customerName = customer_table.getSelectionModel().getSelectedItem().getUsername();

        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationAlert.setTitle("Delete Confirmation");
        confirmationAlert.setHeaderText("Esta  seguro de eliminar a este usuario?");
        confirmationAlert.setContentText("usuario: " + customerName.toUpperCase());

        Optional<ButtonType> result1 = confirmationAlert.showAndWait();

        if (result1.isPresent() && result1.get() == ButtonType.OK) {
            connection = Database.getInstance().connectDB();

            String sql="DELETE FROM users WHERE username=?";
            try {
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1,customer_table.getSelectionModel().getSelectedItem().getUsername());
                int result = preparedStatement.executeUpdate();

                if (result > 0) {
                    showCustomerData();
                    customerClearData();
                } else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Message");
                    alert.setHeaderText(null);
                    alert.setContentText("No hay datos en la Tabla.");
                    alert.showAndWait();
                }
            } catch (Exception err) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeight(500);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText(err.getMessage());
                alert.showAndWait();
            }

        }
    }

    public void printCustomersDetails(){
        connection=Database.getInstance().connectDB();
        String sql="SELECT * FROM users";
        try{
            JasperDesign jasperDesign= JRXmlLoader.load(this.getClass().getClassLoader().getResourceAsStream("jasper-reports/customers.jrxml"));
            JRDesignQuery updateQuery=new JRDesignQuery();
            updateQuery.setText(sql);
            jasperDesign.setQuery(updateQuery);
            JasperReport jasperReport= JasperCompileManager.compileReport(jasperDesign);
            JasperPrint jasperPrint= JasperFillManager.fillReport(jasperReport,null,connection);
            JasperViewer.viewReport(jasperPrint ,false);
        }catch (Exception err){
            err.printStackTrace();
        }
    }

    public void getTotalSalesAmount(){
        connection=Database.getInstance().connectDB();
        String sql="SELECT SUM(total_amount) as total_sale_amount FROM sales";
        try{
            statement=connection.createStatement();
            resultSet=statement.executeQuery(sql);
            while (resultSet.next()){
                String result=resultSet.getString("total_sale_amount");
                if (result == null) {
                    sales_total_amount.setText("0.00");
                }else{
                    sales_total_amount.setText(result);
                }
            }
        }catch (Exception err){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeight(500);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText(err.getMessage());
            alert.showAndWait();
        }

    }
    public ObservableList<Sales> listSalesData(){
        ObservableList<Sales> salesList=FXCollections.observableArrayList();
        connection=Database.getInstance().connectDB();
        String sql="SELECT * FROM SALES s INNER JOIN CUSTOMERS c ON s.cust_id=c.id";
        try{
            statement=connection.createStatement();
            resultSet=statement.executeQuery(sql);
            Sales sale;
            while (resultSet.next()){
                sale=new Sales(Integer.parseInt(resultSet.getString("id")),resultSet.getString("inv_num"),Integer.parseInt(resultSet.getString("cust_id")),resultSet.getString("name"),Double.parseDouble(resultSet.getString("price")),Integer.parseInt(resultSet.getString("quantity")),Double.parseDouble(resultSet.getString("total_amount")),resultSet.getString("date"),resultSet.getString("item_number"));
                salesList.addAll(sale);
            }
        }catch (Exception err){
            err.printStackTrace();
        }
        return salesList;
    }
    
    public void showSalesData(){
        ObservableList<Sales> salesList=listSalesData();
        sales_col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        sales_col_inv_num.setCellValueFactory(new PropertyValueFactory<>("inv_num"));
        sales_col_cust_name.setCellValueFactory(new PropertyValueFactory<>("custName"));
        sales_col_price.setCellValueFactory(new PropertyValueFactory<>("price"));
        sales_col_quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        sales_col_total_amount.setCellValueFactory(new PropertyValueFactory<>("total_amount"));
        sales_col_date_of_sales.setCellValueFactory(new PropertyValueFactory<>("date"));
        sales_col_item_num.setCellValueFactory(new PropertyValueFactory<>("item_num"));
        sales_table.setItems(salesList);

        getTotalSalesAmount();
    }
    public void printSalesDetails(){
        connection=Database.getInstance().connectDB();
        String sql="SELECT * FROM sales s INNER JOIN customers c ON s.cust_id=c.id";
        try{
            JasperDesign jasperDesign= JRXmlLoader.load(this.getClass().getClassLoader().getResourceAsStream("jasper-reports/sales_report.jrxml"));
            JRDesignQuery updateQuery=new JRDesignQuery();
            updateQuery.setText(sql);
            jasperDesign.setQuery(updateQuery);
            JasperReport jasperReport= JasperCompileManager.compileReport(jasperDesign);
            JasperPrint jasperPrint= JasperFillManager.fillReport(jasperReport,null,connection);
            JasperViewer.viewReport(jasperPrint ,false);
        }catch (Exception err){
            err.printStackTrace();
        }
    }
    public void getTotalPurchaseAmount(){
        connection=Database.getInstance().connectDB();
        String sql="SELECT SUM(total_amount) as total_purchase_amount FROM purchase";
        try{
            statement=connection.createStatement();
            resultSet=statement.executeQuery(sql);
            while (resultSet.next()){
                String result=resultSet.getString("total_purchase_amount");
                if (result == null) {
                    purchase_total_amount.setText("0.00");
                }else{
                    purchase_total_amount.setText(result);
                }
            }
        }catch (Exception err){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeight(500);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText(err.getMessage());
            alert.showAndWait();
        }

    }
    public void printPurchaseDetails(){
        connection=Database.getInstance().connectDB();
        String sql="SELECT * FROM purchase";
        try{
            JasperDesign jasperDesign= JRXmlLoader.load(this.getClass().getClassLoader().getResourceAsStream("jasper-reports/purchase_report.jrxml"));
            JRDesignQuery updateQuery=new JRDesignQuery();
            updateQuery.setText(sql);
            jasperDesign.setQuery(updateQuery);
            JasperReport jasperReport= JasperCompileManager.compileReport(jasperDesign);
            JasperPrint jasperPrint= JasperFillManager.fillReport(jasperReport,null,connection);
            JasperViewer.viewReport(jasperPrint ,false);
        }catch (Exception err){
            err.printStackTrace();
        }
    }
    public ObservableList<Purchase> listPurchaseData(){
        ObservableList<Purchase> purchaseList=FXCollections.observableArrayList();
        connection=Database.getInstance().connectDB();
        String sql="SELECT * FROM Purchase";
        try{
            statement=connection.createStatement();
            resultSet=statement.executeQuery(sql);
            Purchase purchase;
            while (resultSet.next()){
                purchase=new Purchase(
                        Integer.parseInt(resultSet.getString("id")),
                        resultSet.getString("invoice"),
                        resultSet.getString("shop and address"),
                        Integer.parseInt(resultSet.getString("total_items")),
                        Double.parseDouble(resultSet.getString("total_amount")),
                        resultSet.getString("date_of_purchase"));
                purchaseList.addAll(purchase);
            }
        }catch (Exception err){
            err.printStackTrace();
        }
        return purchaseList;
    }
    public void showPurchaseData(){
        ObservableList<Purchase> purchaseList=listPurchaseData();
        purchase_col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        purchase_col_invoice.setCellValueFactory(new PropertyValueFactory<>("invoice"));
        purchase_col_shop_details.setCellValueFactory(new PropertyValueFactory<>("shopDetails"));
        purchase_col_total_items.setCellValueFactory(new PropertyValueFactory<>("totalItems"));
        purchase_col_total_amount.setCellValueFactory(new PropertyValueFactory<>("totalAmount"));
        purchase_col_date_of_purchase.setCellValueFactory(new PropertyValueFactory<>("dateOfPurchase"));
        purchase_table.setItems(purchaseList);
        getTotalPurchaseAmount();
    }

    public void getTotalPurchase(){
        connection=Database.getInstance().connectDB();
        String sql="SELECT SUM(total_items) as total_purchase FROM PURCHASE";
        try{
            statement=connection.createStatement();
            resultSet=statement.executeQuery(sql);
            while (resultSet.next()){
                String result=resultSet.getString("total_purchase");
                if (result == null) {
                    dash_total_purchase.setText("0");
                }else{
                    dash_total_purchase.setText(result);
                }
            }
        }catch (Exception err){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeight(500);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText(err.getMessage());
            alert.showAndWait();
        }

    }

    public void getTotalSales(){
        connection=Database.getInstance().connectDB();
        String sql="SELECT SUM(quantity) as total_sale FROM sales";
        try{
            statement=connection.createStatement();
            resultSet=statement.executeQuery(sql);
            while (resultSet.next()){
                String result=resultSet.getString("total_sale");
                if (result == null) {
                    dash_total_sold.setText("0");
                }else{
                    dash_total_sold.setText(result);
                }
            }
        }catch (Exception err){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeight(500);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText(err.getMessage());
            alert.showAndWait();
        }

    }

    public void getTotalStocks(){
        int totalPurchase=Integer.parseInt(dash_total_purchase.getText());
        int total_sold= Integer.parseInt(dash_total_sold.getText());
        int totalStockLeft=totalPurchase-total_sold;
        dash_total_stocks.setText(String.valueOf(totalStockLeft));
    }

    public void getSalesDetailsOfThisMonth(){
        LocalDate date=LocalDate.now();
        String monthName=date.getMonth().toString();
        connection=Database.getInstance().connectDB();
        String sql="SELECT SUM(total_amount) as total_sales_this_month FROM SALES WHERE MONTHNAME(DATE)=?";
        try{
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,monthName);
            resultSet=preparedStatement.executeQuery();
            while (resultSet.next()){
                String result=resultSet.getString("total_sales_this_month");
                if (result == null) {
                    dash_total_sales_this_month.setText("0.00");
                }else{
                    dash_total_sales_this_month.setText(result);
                }
                dash_total_sales_this_month_name.setText(monthName);
            }
        }catch (Exception err){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeight(500);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText(err.getMessage());
            alert.showAndWait();
        }
    }
    public void getItemSoldThisMonth(){
        LocalDate date=LocalDate.now();
        String monthName=date.getMonth().toString();
        connection=Database.getInstance().connectDB();
        String sql="SELECT SUM(quantity) as total_items_sold_this_month FROM SALES WHERE MONTHNAME(DATE)=?";
        try{
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,monthName);
            resultSet=preparedStatement.executeQuery();
            while (resultSet.next()){
                String result=resultSet.getString("total_items_sold_this_month");
                if (result == null) {
                    dash_total_items_sold_this_month.setText("0");
                }else{
                    dash_total_items_sold_this_month.setText(result);
                }
                dash_total_sales_items_this_month_name.setText(monthName);
            }
        }catch (Exception err){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeight(500);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText(err.getMessage());
            alert.showAndWait();
        }
    }
    public void showDashboardData(){
     getTotalPurchase();
     getTotalSales();
     getTotalStocks();
     getSalesDetailsOfThisMonth();
     getItemSoldThisMonth();
    }
    public void signOut(){
        signout_btn.getScene().getWindow().hide();
        try{
        Parent root = FXMLLoader.load(getClass().getResource("login-view.fxml"));
        Scene scene = new Scene(root);
        Stage stage=new Stage();
            root.setOnMousePressed((event)->{
                x=event.getSceneX();
                y=event.getSceneY();
            });
            root.setOnMouseDragged((event)->{
                stage.setX(event.getScreenX()-x);
                stage.setY(event.getScreenY()-y);
            });

            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setScene(scene);
            stage.show();
        }catch (Exception err){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeight(500);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText(err.getMessage());
            alert.showAndWait();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Exports all modules to other modules
        Modules.exportAllToAll();

        setUsername();
        activateDashboard();

//      DASHBOARD PANE
        showDashboardData();

//      BILLING PANE


//        showBillingData();

//      CUSTOMER PANE
        checkUserRole();
        showCustomerData();


 //     PRODUCTS PANE
        showProductsData();



//      SALES PANE
        showSalesData();

//      Purchase Pane
        showPurchaseData();
    }
}
