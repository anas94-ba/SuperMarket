package com.company;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.sql.*;
import java.util.concurrent.atomic.AtomicReference;

import static javafx.application.Application.launch;

public class Main extends Application {

    public static void main(String[] args) {
        // write your code here
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception  {

        String Db_Url = "jdbc:oracle:thin:@localhost:1521:xe";
        String username = "anas";
        String password = "anas";

        stage.setHeight(630);
        stage.setWidth(800);

        VBox v = new VBox();

        //nodes
        TextArea t1= new TextArea("");
        t1.setEditable(false);
        t1.setMinSize(700 , 150);


        HBox h1= new HBox();
        h1.setPadding(new Insets(5,50,5,50));
        HBox h2= new HBox();
        h2.setSpacing(40);
        h2.setAlignment(Pos.CENTER);

        HBox h3= new HBox();
        h3.setSpacing(10);
        h3.setPadding(new Insets(10));

        VBox v1= new VBox();
        v1.setSpacing(10);
        v1.setMinWidth(300);
        v1.setPadding(new Insets(15));
        VBox v2= new VBox();
        v2.setSpacing(10);
        v2.setMaxWidth(200);
        v2.setPadding(new Insets(15));

        //V2 buttons
        Button supButton = new Button("Suppliers");
        Button custButton = new Button("Customers");
        Button comButton = new Button("Commodities");
        Button salButton = new Button("Sales");
        Button purButton = new Button("Purchases");
        Button empButton = new Button("Employees");
        Button empaidButton = new Button("Employee Payments");
        Button NetProfit = new Button("Net Profit");

        Button createSupplier = new Button("CreateSupplier");
        Button displaySuppliers = new Button("DisplaySuppliers");
        Button deleteSupplier = new Button("DeleteSupplier");
        Button updateSupplier = new Button("UpdateSupplier");

        Button createCustomer = new Button("CreateCustomer");
        Button displayCustomers = new Button("DisplayCustomers");
        Button deleteCustomer = new Button("DeleteCustomer");
        Button updateCustomer = new Button("UpdateCustomer");

        Button createCommodity = new Button("CreateCommodity");
        Button displayCommodities = new Button("DisplayCommodities");
        Button deleteCommodity = new Button("DeleteCommodity");
        Button updateCommodity = new Button("UpdateCommodity");

        Button createSales = new Button("CreateSales");
        Button displaySales = new Button("DisplaySales");
        Button deleteSales = new Button("DeleteSales");
        Button updateSales = new Button("UpdateSales");

        Button createPurchases = new Button("CreatePurchases");
        Button displayPurchases = new Button("DisplayPurchases");
        Button deletePurchases = new Button("DeletePurchases");
        Button updatePurchases = new Button("UpdatePurchases");

        Button createEmployee = new Button("CreateEmployee");
        Button displayEmployees = new Button("DisplayEmployees");
        Button deleteEmployee = new Button("DeleteEmployee");
        Button updateEmployee = new Button("UpdateEmployee");

        Button createEmployeePaid = new Button("CreateEmployeePaid");
        Button displayEmployeePaid = new Button("DisplayEmployeePaid");
        Button deleteEmployeePaid = new Button("DeleteEmployeePaid");
        Button updateEmployeePaid = new Button("UpdateEmployeePaid");

        Button[] list1 = new Button[]{supButton, custButton, comButton, salButton, purButton , empButton , empaidButton };
        Button[] list2 = new Button[]{createSupplier  ,displaySuppliers, deleteSupplier ,updateSupplier };
        Button[] list3 = new Button[]{createCustomer  ,displayCustomers, deleteCustomer ,updateCustomer };
        Button[] list4 = new Button[]{createCommodity,displayCommodities, deleteCommodity ,updateCommodity };
        Button[] list5 = new Button[]{createSales ,displaySales, deleteSales ,updateSales};
        Button[] list6= new Button[]{createPurchases ,displayPurchases, deletePurchases ,updatePurchases };
        Button[] list61= new Button[]{createEmployee ,displayEmployees, deleteEmployee ,updateEmployee };
        Button[] list62= new Button[]{createEmployeePaid  ,displayEmployeePaid, deleteEmployeePaid ,updateEmployeePaid };
        Button[][] list7 = new Button[][]{list2,list3,list4,list5,list6,list61,list62};

        TextField t2 = new TextField();
        TextField t3 = new TextField();
        TextField t4 = new TextField();
        TextField t5 = new TextField();
        TextField t6 = new TextField();
        Button but =new Button("Click");
        for (int i =0 ; i< list1.length ; i++){
            v2.getChildren().add(list1[i]);
            int finalI = i;
            list1[i].setPrefSize(150,20);
            list1[i].setPadding(new Insets(2));
            list1[i].setOnAction(e ->{
                v1.getChildren().clear();
                t1.clear();
                h3.getChildren().clear();
                Button[][] list17 = list7;
                h3.getChildren().addAll(list17[finalI]) ;
            });
        }
        v2.getChildren().add(NetProfit);
        NetProfit.setPrefSize(150,10);
        NetProfit.setPadding(new Insets(0.5));
        NetProfit.setOnAction(e->{
            t1.clear();
            h3.getChildren().clear();
            v1.getChildren().clear();
            t1.appendText("---------------------------------------\n");
            t1.appendText("NetProfit : "+(sum_Salaries()-sum_Purchaese()-sum_Sales())+"\n");
            t1.appendText("---------------------------------------\n");

        });
        for(int i=0 ; i< list7.length ; i++){
            for(int j=0 ; j<list7[i].length ;j++){
                int finalJ = j;
                int finalI = i;
                list7[i][j].setStyle("-fx-background-color:#7154b0 ;");
                list7[i][j].setOnMouseEntered(e -> list7[finalI][finalJ].setStyle("-fx-background-color:#475011 ;"));
                list7[i][j].setOnMouseExited(e -> list7[finalI][finalJ].setStyle("-fx-background-color:#7154b0 ;"));
            }
        }
        but.setStyle("-fx-background-color:#55c422 ;");
        but.setOnMouseEntered(n -> but.setStyle("-fx-background-color:#7154b0 ;"));
        but.setOnMouseExited(n -> but.setStyle("-fx-background-color:#55c422 ;"));
        createSupplier.setOnAction( e-> {
            t1.clear();
            t2.clear();t3.clear();t4.clear();t5.clear();t6.clear();
            t1.appendText("---------------------------------------\n");
            v1.getChildren().clear();
            v1.getChildren().addAll(
                    new Label("SUPPLIER_ID ") ,
                    t2,
                    new Label("PHONE"),
                    t3,
                    new Label("SUPPLIER_NAME "),
                    t4,
                    but
            );

            but.setOnAction(s->{
                try(Connection conn = DriverManager.getConnection(Db_Url , username, password);
                    Statement stetment  = conn.createStatement(); ){
                    String query = "insert into SUPPLIER(SUPPLIER_ID,PHONE,SUPPLIER_NAME) "+"values(?,?,?)";
                    PreparedStatement  p = conn.prepareStatement(query);
                    p.setString(1,t2.getText());
                    p.setString(2,t3.getText());
                    p.setString(3,t4.getText());
                    int num = p.executeUpdate();
                    t1.appendText("Create Done!\n");
                }
                catch(SQLException h){
                    t1.appendText(h+"\n");
                }
                t1.appendText("---------------------------------------\n");
            });
        });

        displaySuppliers.setOnAction(e ->{
            t1.clear();
            t1.appendText("-------------------------------------------------------------\n");
            t1.appendText("SUPPLIER_ID  |   PHONE      |  SUPPLIER_NAME\n");
            t1.appendText("-------------------------------------------------------------\n");
            v1.getChildren().clear();
            try(Connection conn = DriverManager.getConnection(Db_Url , username, password);
                Statement stetment  = conn.createStatement(); ){
                String query = "select * from supplier";
                ResultSet rs = stetment .executeQuery(query );
                while(rs.next()){
                    t1.appendText(rs.getString(1)+"   |   "+rs.getString(2)+"   |   "+rs.getString(3)+"\n");
                }
            }
            catch(SQLException j){
                t1.appendText(j+"\n");
            }
            t1.appendText("---------------------------------------\n");
        });

        deleteSupplier.setOnAction(e ->{
            t2.clear();t3.clear();t4.clear();t5.clear();t6.clear();
            t1.clear();
            t1.appendText("---------------------------------------\n");
            v1.getChildren().clear();
            v1.getChildren().addAll(
                    new Label("SUPPLIER_ID ") ,
                    t2,
                    but
            );
            but.setOnAction(l -> {
                try(Connection conn = DriverManager.getConnection(Db_Url , username, password);
                    Statement stetment  = conn.createStatement(); ){
                    String query = "delete from supplier where supplier_id =? ";
                    PreparedStatement  p = conn.prepareStatement(query);
                    p.setString(1,t2.getText());
                    int num = p.executeUpdate();
                    t1.appendText("Delete Done!"+"\n");
                }
                catch(SQLException j){
                    t1.appendText(j+"\n");
                }
                t1.appendText("---------------------------------------\n");
            });
        });

        updateSupplier.setOnAction(e -> {
            t2.clear();t3.clear();t4.clear();t5.clear();t6.clear();
            t1.clear();
            t1.appendText("---------------------------------------\n");
            v1.getChildren().clear();
            v1.getChildren().addAll(
                    new Label("SUPPLIER_ID ") ,
                    t2,
                    new Label("PHONE"),
                    t3,
                    new Label("SUPPLIER_NAME "),
                    t4,
                    but
            );
            but.setOnAction(l -> {
                try(Connection conn = DriverManager.getConnection(Db_Url , username, password);
                    Statement stetment  = conn.createStatement(); ){
                    String query = "UPDATE  SUPPLIER SET PHONE=? , SUPPLIER_NAME=? where SUPPLIER_ID=? ";
                    PreparedStatement  p = conn.prepareStatement(query);
                    p.setString(1,t3.getText());
                    p.setString(2,t4.getText());
                    p.setString(3,t2.getText());
                    int num = p.executeUpdate();
                    t1.appendText("Update Done!\n");
                }
                catch(SQLException h){
                    t1.appendText(h+"\n");
                }
                t1.appendText("---------------------------------------\n");
            });
        });

        createCustomer.setOnAction( e-> {
            t2.clear();t3.clear();t4.clear();t5.clear();t6.clear();
            t1.clear();
            t1.appendText("---------------------------------------\n");
            v1.getChildren().clear();
            v1.getChildren().addAll(
                    new Label("CUSTOMER_ID ") ,
                    t2,
                    new Label("PHONE"),
                    t3,
                    new Label("CUSTOMER_NAME "),
                    t4,
                    but
            );
            but.setOnAction(s->{
                try(Connection conn = DriverManager.getConnection(Db_Url , username, password);
                    Statement stetment  = conn.createStatement(); ){
                    String query = "insert into CUSTOMER(CUSTOMER_ID,PHONE,CUSTOMER_NAME) "+"values(?,?,?)";
                    PreparedStatement  p = conn.prepareStatement(query);
                    p.setString(1,t2.getText());
                    p.setString(2,t3.getText());
                    p.setString(3,t4.getText());
                    int num = p.executeUpdate();
                    t1.appendText("Create Done!\n");
                }
                catch(SQLException h){
                    t1.appendText(h+"\n");
                }
                t1.appendText("---------------------------------------\n");
            });
        });

        displayCustomers.setOnAction(e ->{
            t1.clear();
            t1.appendText("----------------------------------------------------------------\n");
            t1.appendText("CUSTOMER_ID   |    PHONE    |   CUSTOMER_NAME\n");
            t1.appendText("----------------------------------------------------------------\n");
            v1.getChildren().clear();
            try(Connection conn = DriverManager.getConnection(Db_Url , username, password);
                Statement stetment  = conn.createStatement(); ){
                String query = "select * from CUSTOMER";
                ResultSet rs = stetment .executeQuery(query );
                while(rs.next()){
                    t1.appendText(rs.getString(1)+"   |   "+rs.getString(2)+"   |   "+rs.getString(3)+"\n");
                }
            }
            catch(SQLException j){
                t1.appendText(j+"\n");
            }
            t1.appendText("---------------------------------------\n");
        });

        deleteCustomer.setOnAction(e ->{
            t2.clear();t3.clear();t4.clear();t5.clear();t6.clear();
            t1.clear();
            t1.appendText("---------------------------------------\n");
            v1.getChildren().clear();
            v1.getChildren().addAll(
                    new Label("CUSTOMER_ID ") ,
                    t2,
                    but
            );
            but.setOnAction(l -> {
                try(Connection conn = DriverManager.getConnection(Db_Url , username, password);
                    Statement stetment  = conn.createStatement(); ){
                    String query = "delete from CUSTOMER where CUSTOMER_ID =? ";
                    PreparedStatement  p = conn.prepareStatement(query);
                    p.setString(1,t2.getText());
                    int num = p.executeUpdate();
                    t1.appendText("Delete Done!"+"\n");
                }
                catch(SQLException j){
                    t1.appendText(j+"\n");
                }
                t1.appendText("---------------------------------------\n");
            });
        });

        updateCustomer.setOnAction(e -> {
            t2.clear();t3.clear();t4.clear();t5.clear();t6.clear();
            t1.clear();
            t1.appendText("---------------------------------------\n");
            v1.getChildren().clear();
            v1.getChildren().addAll(
                    new Label("CUSTOMER_ID ") ,
                    t2,
                    new Label("PHONE"),
                    t3,
                    new Label("CUSTOMER_Name "),
                    t4,
                    but
            );
            but.setOnAction(l -> {
                try(Connection conn = DriverManager.getConnection(Db_Url , username, password);
                    Statement stetment  = conn.createStatement(); ){
                    String query = "UPDATE  CUSTOMER SET PHONE=? , CUSTOMER_NAME=? where CUSTOMER_ID=? ";
                    PreparedStatement  p = conn.prepareStatement(query);
                    p.setString(1,t3.getText());
                    p.setString(2,t4.getText());
                    p.setString(3,t2.getText());
                    int num = p.executeUpdate();
                    t1.appendText("Update Done!\n");
                }
                catch(SQLException h){
                    t1.appendText(h+"\n");
                }
                t1.appendText("---------------------------------------\n");
            });
        });

        createCommodity.setOnAction( e-> {
            t2.clear();t3.clear();t4.clear();t5.clear();t6.clear();
            t1.clear();
            t1.appendText("---------------------------------------\n");
            v1.getChildren().clear();
            v1.getChildren().addAll(
                    new Label("COMMODITY_ID") ,
                    t2,
                    new Label("COMMODITY_NAME"),
                    t3,
                    new Label("PRICE"),
                    t4,
                    but
            );
            but.setOnAction(s->{
                try(Connection conn = DriverManager.getConnection(Db_Url , username, password);
                    Statement stetment  = conn.createStatement(); ){
                    String query = "insert into COMMODITY(COMMODITY_ID,COMMODITY_NAME,PRICE) "+"values(?,?,?)";
                    PreparedStatement  p = conn.prepareStatement(query);
                    p.setString(1,t2.getText());
                    p.setString(2,t3.getText());
                    p.setString(3,t4.getText());
                    int num = p.executeUpdate();
                    t1.appendText("Create Done!\n");
                }
                catch(SQLException h){
                    t1.appendText(h+"\n");
                }
                t1.appendText("---------------------------------------\n");
            });
        });

        displayCommodities.setOnAction(e ->{
            t1.clear();
            t1.appendText("-----------------------------------------------------------------\n");
            t1.appendText("COMMODITY_ID   |   COMMODITY_NAME   |     PRICE\n");
            t1.appendText("-----------------------------------------------------------------\n");
            v1.getChildren().clear();
            try(Connection conn = DriverManager.getConnection(Db_Url , username, password);
                Statement stetment  = conn.createStatement(); ){
                String query = "select * from Commodity";
                ResultSet rs = stetment .executeQuery(query );
                while(rs.next()){
                    t1.appendText(rs.getString(1)+"   |   "+rs.getString(2)+"   |   "+rs.getString(3)+"\n");
                }
            }
            catch(SQLException j){
                t1.appendText(j+"\n");
            }
            t1.appendText("---------------------------------------\n");
        });

        deleteCommodity.setOnAction(e ->{
            t2.clear();t3.clear();t4.clear();t5.clear();t6.clear();
            t1.clear();
            t1.appendText("---------------------------------------\n");
            v1.getChildren().clear();
            v1.getChildren().addAll(
                    new Label("Commodity_ID ") ,
                    t2,
                    but
            );
            but.setOnAction(l -> {
                try(Connection conn = DriverManager.getConnection(Db_Url , username, password);
                    Statement stetment  = conn.createStatement(); ){
                    String query = "delete from Commodity  where Commodity_ID =? ";
                    PreparedStatement  p = conn.prepareStatement(query);
                    p.setString(1,t2.getText());
                    int num = p.executeUpdate();
                    t1.appendText("Delete Done!"+"\n");
                }
                catch(SQLException j){
                    t1.appendText(j+"\n");
                }
                t1.appendText("---------------------------------------\n");
            });
        });

        updateCommodity.setOnAction(e -> {
            t2.clear();t3.clear();t4.clear();t5.clear();t6.clear();
            t1.clear();
            t1.appendText("---------------------------------------\n");
            v1.getChildren().clear();
            v1.getChildren().addAll(
                    new Label("Commodity_ID ") ,
                    t2,
                    new Label("COMMODITY_NAME"),
                    t3,
                    new Label("PRICE"),
                    t4,
                    but
            );
            but.setOnAction(l -> {
                try(Connection conn = DriverManager.getConnection(Db_Url , username, password);
                    Statement stetment  = conn.createStatement(); ){
                    String query = "UPDATE  COMMODITY SET COMMODITY_NAME=? , PRICE=? where Commodity_ID=? ";
                    PreparedStatement  p = conn.prepareStatement(query);
                    p.setString(1,t3.getText());
                    p.setString(2,t4.getText());
                    p.setString(3,t2.getText());
                    int num = p.executeUpdate();
                    t1.appendText("Update Done!\n");
                }
                catch(SQLException h){
                    t1.appendText(h+"\n");
                }
                t1.appendText("---------------------------------------\n");
            });
        });


        createSales.setOnAction( e-> {
            t2.clear();t3.clear();t4.clear();t5.clear();t6.clear();
            t1.clear();
            t1.appendText("---------------------------------------\n");
            v1.getChildren().clear();
            v1.getChildren().addAll(
                    new Label("SALES_ID") ,
                    t2,
                    new Label("QUANTITY"),
                    t3,
                    new Label("CUSTOMER_ID"),
                    t4,
                    new Label("COMMODITY_ID"),
                    t5,
                    new Label("SALES_PAID"),
                    t6,
                    but
            );
            but.setOnAction(s->{
                try(Connection conn = DriverManager.getConnection(Db_Url , username, password);
                    Statement stetment  = conn.createStatement(); ){
                    String query = "insert into SALES(SALES_ID,QUANTITY,CUSTOMER_ID,COMMODITY_ID,SALES_PAID) "+"values(?,?,?,?,?)";
                    PreparedStatement  p = conn.prepareStatement(query);
                    p.setString(1,t2.getText());
                    p.setString(2,t3.getText());
                    p.setString(3,t4.getText());
                    p.setString(4,t5.getText());
                    p.setString(5,t6.getText());
                    int num = p.executeUpdate();
                    t1.appendText("Create Done!\n");
                }
                catch(SQLException h){
                    t1.appendText(h+"\n");
                }
                t1.appendText("---------------------------------------\n");
            });
        });

        displaySales.setOnAction(e ->{
            t1.clear();
            t1.appendText("-----------------------------------------------------------------------------------------------\n");
            t1.appendText("SALES_ID  |   QUANTITY   |   CUSTOMER_ID   |   COMMODITY_ID   |   SALES_PAID\n");
            t1.appendText("-----------------------------------------------------------------------------------------------\n");
            v1.getChildren().clear();
            try(Connection conn = DriverManager.getConnection(Db_Url , username, password);
                Statement stetment  = conn.createStatement(); ){
                String query = "select * from SALES";
                ResultSet rs = stetment .executeQuery(query );
                while(rs.next()){
                    t1.appendText(rs.getString(1)+"   |   "+rs.getString(2)+"   |   "+rs.getString(3)+"   |   "+rs.getString(4)+"   |   "+rs.getString(5)+"\n");
                }
                t1.appendText("---------------------------------------\n");
                t1.appendText("TOTAL :           "+sum_Sales()+"\n");
            }
            catch(SQLException j){
                t1.appendText(j+"\n");
            }
            t1.appendText("---------------------------------------\n");
        });

        deleteSales.setOnAction(e ->{
            t2.clear();t3.clear();t4.clear();t5.clear();t6.clear();
            t1.clear();
            t1.appendText("---------------------------------------\n");
            v1.getChildren().clear();
            v1.getChildren().addAll(
                    new Label("Commodity_ID ") ,
                    t2,
                    but
            );
            but.setOnAction(l -> {
                try(Connection conn = DriverManager.getConnection(Db_Url , username, password);
                    Statement stetment  = conn.createStatement(); ){
                    String query = "delete from SALES  where SALES_ID=? ";
                    PreparedStatement  p = conn.prepareStatement(query);
                    p.setString(1,t2.getText());
                    int num = p.executeUpdate();
                    t1.appendText("Delete Done!"+"\n");
                }
                catch(SQLException j){
                    t1.appendText(j+"\n");
                }
                t1.appendText("---------------------------------------\n");
            });
        });

        updateSales.setOnAction(e -> {
            t2.clear();t3.clear();t4.clear();t5.clear();t6.clear();
            t1.clear();
            t1.appendText("---------------------------------------\n");
            v1.getChildren().clear();
            v1.getChildren().addAll(
                    new Label("SALES_ID") ,
                    t2,
                    new Label("QUANTITY"),
                    t3,
                    new Label("CUSTOMER_ID"),
                    t4,
                    new Label("COMMODITY_ID"),
                    t5,
                    new Label("SALES_PAID"),
                    t6,
                    but
            );
            but.setOnAction(l -> {
                try(Connection conn = DriverManager.getConnection(Db_Url , username, password);
                    Statement stetment  = conn.createStatement(); ){
                    String query = "UPDATE  SALES SET QUANTITY=? , CUSTOMER_ID=? , COMMODITY_ID=? , SALES_PAID=?  where SALES_ID=? ";
                    PreparedStatement  p = conn.prepareStatement(query);
                    p.setString(1,t3.getText());
                    p.setString(2,t4.getText());
                    p.setString(3,t5.getText());
                    p.setString(4,t6.getText());
                    p.setString(5,t2.getText());
                    int num = p.executeUpdate();
                    t1.appendText("Update Done!\n");
                }
                catch(SQLException h){
                    t1.appendText(h+"\n");
                }
                t1.appendText("---------------------------------------\n");
            });
        });

        createPurchases.setOnAction( e-> {
            t2.clear();t3.clear();t4.clear();t5.clear();t6.clear();
            t1.clear();
            t1.appendText("---------------------------------------\n");
            v1.getChildren().clear();
            v1.getChildren().addAll(
                    new Label("PURCHASES_ID") ,
                    t2,
                    new Label("QUANTITY"),
                    t3,
                    new Label("SUPPLIER_ID"),
                    t4,
                    new Label("COMMODITY_ID"),
                    t5,
                    new Label("PURCHASES_PAID"),
                    t6,
                    but
            );
            but.setOnAction(s->{
                try(Connection conn = DriverManager.getConnection(Db_Url , username, password);
                    Statement stetment  = conn.createStatement(); ){
                    String query = "insert into PURCHASES(PURCHASES_ID,QUANTITY,SUPPLIER_ID,COMMODITY_ID,PURCHASES_PAID) "+"values(?,?,?,?,?)";
                    PreparedStatement  p = conn.prepareStatement(query);
                    p.setString(1,t2.getText());
                    p.setString(2,t3.getText());
                    p.setString(3,t4.getText());
                    p.setString(4,t5.getText());
                    p.setString(5,t6.getText());
                    int num = p.executeUpdate();
                    t1.appendText("Create Done!\n");
                }
                catch(SQLException h){
                    t1.appendText(h+"\n");
                }
                t1.appendText("---------------------------------------\n");
            });
        });

        displayPurchases.setOnAction(e ->{
            t1.clear();
            t1.appendText("--------------------------------------------------------------------------------------------\n");
            t1.appendText("PURCHASES_ID  |  QUANTITY  |  SUPPLIER_ID  |  COMMODITY_ID  |  PURCHASES_PAID\n");
            t1.appendText("--------------------------------------------------------------------------------------------\n");
            v1.getChildren().clear();
            try(Connection conn = DriverManager.getConnection(Db_Url , username, password);
                Statement stetment  = conn.createStatement(); ){
                String query = "select * from PURCHASES";
                ResultSet rs = stetment .executeQuery(query );
                while(rs.next()){
                    t1.appendText(rs.getString(1)+"   |   "+rs.getString(2)+"   |   "+rs.getString(3)+"   |   "+rs.getString(4)+"   |   "+rs.getString(5)+"\n");
                }
                t1.appendText("---------------------------------------\n");
                t1.appendText("TOTAL PURCHASES:          "+sum_Purchaese()+"\n");
            }
            catch(SQLException j){
                t1.appendText(j+"\n");
            }
            t1.appendText("---------------------------------------\n");
        });

        deletePurchases.setOnAction(e ->{
            t2.clear();t3.clear();t4.clear();t5.clear();t6.clear();
            t1.clear();
            t1.appendText("---------------------------------------\n");
            v1.getChildren().clear();
            v1.getChildren().addAll(
                    new Label("PURCHASES_ID ") ,
                    t2,
                    but
            );
            but.setOnAction(l -> {
                try(Connection conn = DriverManager.getConnection(Db_Url , username, password);
                    Statement stetment  = conn.createStatement(); ){
                    String query = "delete from PURCHASES  where PURCHASES_ID=? ";
                    PreparedStatement  p = conn.prepareStatement(query);
                    p.setString(1,t2.getText());
                    int num = p.executeUpdate();
                    t1.appendText("Delete Done!"+"\n");
                }
                catch(SQLException j){
                    t1.appendText(j+"\n");
                }
                t1.appendText("---------------------------------------\n");
            });
        });

        updatePurchases.setOnAction(e -> {
            t2.clear();t3.clear();t4.clear();t5.clear();t6.clear();
            t1.clear();
            t1.appendText("---------------------------------------\n");
            v1.getChildren().clear();
            v1.getChildren().addAll(
                    new Label("PURCHASES_ID") ,
                    t2,
                    new Label("QUANTITY"),
                    t3,
                    new Label("SUPPLIER_ID"),
                    t4,
                    new Label("COMMODITY_ID"),
                    t5,
                    new Label("PURCHASES_PAID"),
                    t6,
                    but
            );
            but.setOnAction(l -> {
                try(Connection conn = DriverManager.getConnection(Db_Url , username, password);
                    Statement stetment  = conn.createStatement(); ){
                    String query = "UPDATE  PURCHASES SET QUANTITY=? , SUPPLIER_ID=? , COMMODITY_ID=? , PURCHASES_PAID=?  where PURCHASES_ID=? ";
                    PreparedStatement  p = conn.prepareStatement(query);
                    p.setString(1,t3.getText());
                    p.setString(2,t4.getText());
                    p.setString(3,t5.getText());
                    p.setString(4,t6.getText());
                    p.setString(5,t2.getText());
                    int num = p.executeUpdate();
                    t1.appendText("Update Done!\n");
                }
                catch(SQLException h){
                    t1.appendText(h+"\n");
                }
                t1.appendText("---------------------------------------\n");
            });
        });

        createEmployee.setOnAction( e-> {
            t2.clear();t3.clear();t4.clear();t5.clear();t6.clear();
            t1.clear();
            t1.appendText("---------------------------------------\n");
            v1.getChildren().clear();
            v1.getChildren().addAll(
                    new Label("EMPLOYEE_ID") ,
                    t2,
                    new Label("SALARY"),
                    t3,
                    new Label("JOB"),
                    t4,
                    new Label("EMPLOYEE_NAME"),
                    t5,
                    but
            );
            but.setOnAction(s->{
                try(Connection conn = DriverManager.getConnection(Db_Url , username, password);
                    Statement stetment  = conn.createStatement(); ){
                    String query = "insert into EMPLOYEE(EMPLOYEE_ID,SALARY,JOB,EMPLOYEE_NAME) "+"values(?,?,?,?)";
                    PreparedStatement  p = conn.prepareStatement(query);
                    p.setString(1,t2.getText());
                    p.setString(2,t3.getText());
                    p.setString(3,t4.getText());
                    p.setString(4,t5.getText());
                    int num = p.executeUpdate();
                    t1.appendText("Create Done!\n");
                }
                catch(SQLException h){
                    t1.appendText(h+"\n");
                }
                t1.appendText("---------------------------------------\n");
            });
        });
        displayEmployees.setOnAction(e ->{
            t1.clear();
            t1.appendText("-------------------------------------------------------------------\n");
            t1.appendText("EMPLOYEE_ID  |  SALARY  |   JOB   |  EMPLOYEE_NAME\n");
            t1.appendText("-------------------------------------------------------------------\n");
            v1.getChildren().clear();
            try(Connection conn = DriverManager.getConnection(Db_Url , username, password);
                Statement stetment  = conn.createStatement(); ){
                String query = "select * from EMPLOYEE";
                ResultSet rs = stetment .executeQuery(query );
                while(rs.next()){
                    t1.appendText(rs.getString(1)+"   |   "+rs.getString(2)+"   |   "+rs.getString(3)+"   |   "+rs.getString(4)+"\n");
                }
            }
            catch(SQLException j){
                t1.appendText(j+"\n");
            }
            t1.appendText("---------------------------------------\n");
        });
        deleteEmployee.setOnAction(e ->{
            t2.clear();t3.clear();t4.clear();t5.clear();t6.clear();
            t1.clear();
            t1.appendText("---------------------------------------\n");
            v1.getChildren().clear();
            v1.getChildren().addAll(
                    new Label("EMPLOYEE_ID") ,
                    t2,
                    but
            );
            but.setOnAction(l -> {
                try(Connection conn = DriverManager.getConnection(Db_Url , username, password);
                    Statement stetment  = conn.createStatement(); ){
                    String query = "delete from EMPLOYEE  where EMPLOYEE_ID=? ";
                    PreparedStatement  p = conn.prepareStatement(query);
                    p.setString(1,t2.getText());
                    int num = p.executeUpdate();
                    t1.appendText("Delete Done!"+"\n");
                }
                catch(SQLException j){
                    t1.appendText(j+"\n");
                }
                t1.appendText("---------------------------------------\n");
            });
        });

        updateEmployee.setOnAction(e -> {
            t2.clear();t3.clear();t4.clear();t5.clear();t6.clear();
            t1.clear();
            t1.appendText("---------------------------------------\n");
            v1.getChildren().clear();
            v1.getChildren().addAll(
                    new Label("EMPLOYEE_ID") ,
                    t2,
                    new Label("SALARY"),
                    t3,
                    new Label("JOB"),
                    t4,
                    new Label("EMPLOYEE_NAME"),
                    t5,
                    but
            );
            but.setOnAction(l -> {
                try(Connection conn = DriverManager.getConnection(Db_Url , username, password);
                    Statement stetment  = conn.createStatement(); ){
                    String query = "UPDATE  EMPLOYEE SET SALARY=? , JOB=? , EMPLOYEE_NAME=? where EMPLOYEE_ID=? ";
                    PreparedStatement  p = conn.prepareStatement(query);
                    p.setString(1,t3.getText());
                    p.setString(2,t4.getText());
                    p.setString(3,t5.getText());
                    p.setString(4,t2.getText());
                    int num = p.executeUpdate();
                    t1.appendText("Update Done!\n");
                }
                catch(SQLException h){
                    t1.appendText(h+"\n");
                }
                t1.appendText("---------------------------------------\n");
            });
        });

        createEmployeePaid.setOnAction( e-> {
            t2.clear();t3.clear();t4.clear();t5.clear();t6.clear();
            t1.clear();
            t1.appendText("---------------------------------------\n");
            v1.getChildren().clear();
            v1.getChildren().addAll(
                    new Label("EMPLOYEE_PAYMENT_ID ") ,
                    t2,
                    new Label("PAYMENT_ID"),
                    t3,
                    but
            );
            but.setOnAction(s->{
                try(Connection conn = DriverManager.getConnection(Db_Url , username, password);
                    Statement stetment  = conn.createStatement(); ){
                    String query = "insert into EMPLOYEE_PAYMENT(EMPLOYEE_PAYMENT_ID,EMPLOYEE_ID) "+"values(?,?)";
                    PreparedStatement  p = conn.prepareStatement(query);
                    p.setString(1,t2.getText());
                    p.setString(2,t3.getText());
                    int num = p.executeUpdate();
                    t1.appendText("Create Done!\n");
                }
                catch(SQLException h){
                    t1.appendText(h+"\n");
                }
                t1.appendText("---------------------------------------\n");
            });
        });

        displayEmployeePaid.setOnAction(e ->{
            t1.clear();
            t1.appendText("------------------------------------------------------\n");
            t1.appendText("EMPLOYEE_PAYMENT_ID  |  EMPLOYEE_ID\n");
            t1.appendText("------------------------------------------------------\n");
            v1.getChildren().clear();
            try(Connection conn = DriverManager.getConnection(Db_Url , username, password);
                Statement stetment  = conn.createStatement(); ){
                String query = "select * from EMPLOYEE_PAYMENT";
                ResultSet rs = stetment .executeQuery(query );
                while(rs.next()){
                    t1.appendText(rs.getString(1)+"   |   "+rs.getString(2)+"\n");
                }
                t1.appendText("---------------------------------------\n");
                t1.appendText("TOTAL Employee Paid:      "+sum_Salaries()+"\n");
            }
            catch(SQLException j){
                t1.appendText(j+"\n");
            }
            t1.appendText("---------------------------------------\n");
        });

        deleteEmployeePaid.setOnAction(e ->{
            t2.clear();t3.clear();t4.clear();t5.clear();t6.clear();
            t1.clear();
            t1.appendText("---------------------------------------\n");
            v1.getChildren().clear();
            v1.getChildren().addAll(
                    new Label("EMPLOYEE_PAYMENT_ID ") ,
                    t2,
                    but
            );
            but.setOnAction(l -> {
                try(Connection conn = DriverManager.getConnection(Db_Url , username, password);
                    Statement stetment  = conn.createStatement(); ){
                    String query = "delete from EMPLOYEE_PAYMENT where EMPLOYEE_PAYMENT_ID =? ";
                    PreparedStatement  p = conn.prepareStatement(query);
                    p.setString(1,t2.getText());
                    int num = p.executeUpdate();
                    t1.appendText("Delete Done!"+"\n");
                }
                catch(SQLException j){
                    t1.appendText(j+"\n");
                }
                t1.appendText("---------------------------------------\n");
            });
        });

        updateEmployeePaid.setOnAction(e -> {
            t2.clear();t3.clear();t4.clear();t5.clear();t6.clear();
            t1.clear();
            t1.appendText("---------------------------------------\n");
            v1.getChildren().clear();
            v1.getChildren().addAll(
                    new Label("EMPLOYEE_PAYMENT_ID ") ,
                    t2,
                    new Label("PAYMENT_ID"),
                    t3,
                    but
            );
            but.setOnAction(l -> {
                try(Connection conn = DriverManager.getConnection(Db_Url , username, password);
                    Statement stetment  = conn.createStatement(); ){
                    String query = "UPDATE  EMPLOYEE_PAYMENT SET EMPLOYEE_ID=? where EMPLOYEE_PAYMENT_ID=? ";
                    PreparedStatement  p = conn.prepareStatement(query);
                    p.setString(1,t3.getText());
                    p.setString(2,t2.getText());
                    int num = p.executeUpdate();
                    t1.appendText("Update Done!\n");
                }
                catch(SQLException h){
                    t1.appendText(h+"\n");
                }
                t1.appendText("---------------------------------------\n");
            });
        });



        h1.getChildren().addAll(t1);
        h2.getChildren().addAll(v1,v2);

        v.getChildren().addAll(h1,h2,h3);


        Scene s = new Scene(v);
        stage.setTitle("SuperMarket");

        Label ll1=new Label("WELCOME TO SUPERMARKET\n     MANAGMENT SYSTEM   ");
        ll1.setStyle("-fx-font-size:3em; -fx-background-color:#ffff74 ; ");
        Label ll2=new Label("USER NAME: ");
        ll2.setStyle("-fx-font-size:2em;");
        Label ll3=new Label("Password:    ");
        ll3.setStyle("-fx-font-size:2em; ");
        Label ll4=new Label("");
        ll4.setStyle("-fx-font-size:2em; -fx-background-color:#115574 ; ");

        TextField lt1=new TextField();
        TextField lt2=new TextField();

        Button lb = new Button("Login");
        lb.setPrefSize(150,40);

        lb.setStyle("-fx-background-color:#7154b0 ;");
        lb.setOnMouseEntered(e -> lb.setStyle("-fx-background-color:#475011 ;"));
        lb.setOnMouseExited(e -> lb.setStyle("-fx-background-color:#7154b0 ;"));
        lb.setOnAction(e->{
            try(Connection conn = DriverManager.getConnection(Db_Url , username, password);
                Statement stetment  = conn.createStatement(); ){
                String query = "select PASSWORD from USERS WHERE USER_NAME= ?";
                PreparedStatement  p = conn.prepareStatement(query);
                p.setString(1,lt1.getText());
                ResultSet rs = p.executeQuery();
                rs.next();
                if(rs.getString(1).equals(lt2.getText()) ){
                    stage.setScene(s);
                }else{
                    ll4.setText("INVAILED USERNAME OR PASSWORD ");
                }
            }
            catch(SQLException h){
                System.out.println(h);
                ll4.setText("INVAILED USERNAME OR PASSWORD ");
            }
        });
        VBox vl=new VBox();
        vl.setStyle("-fx-background-color:#ffff74 ; ");
        vl.setSpacing(50);
        vl.setPadding(new Insets(30));

        HBox hl=new HBox();
        hl.setAlignment(Pos.CENTER);

        HBox hl1=new HBox();
        hl1.setAlignment(Pos.CENTER);

        HBox hl2=new HBox();
        hl2.setAlignment(Pos.CENTER);

        HBox hl3=new HBox();
        hl3.setAlignment(Pos.CENTER);

        HBox hl4=new HBox();
        hl4.setAlignment(Pos.CENTER);

        hl.getChildren().add(ll1);
        hl1.getChildren().addAll(ll2 , lt1);

        hl2.getChildren().addAll(ll3,lt2);

        hl3.getChildren().add(lb);

        hl4.getChildren().add(ll4);

        vl.getChildren().addAll(hl,hl1,hl2,hl3,hl4);
        Scene s2 = new Scene(vl);
        stage.setScene(s2);

        stage.show();

    }
    public int sum_Sales(){
        String Db_Url = "jdbc:oracle:thin:@localhost:1521:xe";
        String username = "anas";
        String password = "anas";
        try(Connection conn = DriverManager.getConnection(Db_Url , username, password);
            Statement stetment  = conn.createStatement(); ){
            String query = "select SALES_PAID,QUANTITY,PRICE from sales natural join commodity";
            ResultSet rs = stetment .executeQuery(query );
            int sum=0;
            while(rs.next()){
                if(rs.getString(1).equals("1")){
                    sum+=(Integer.parseInt(rs.getString(2)))*(Integer.parseInt(rs.getString(3)));
                }
            }
            return sum;
        }
        catch(SQLException e){
            return -1;
        }
    }
    public int sum_Purchaese(){
        String Db_Url = "jdbc:oracle:thin:@localhost:1521:xe";
        String username = "anas";
        String password = "anas";
        try(Connection conn = DriverManager.getConnection(Db_Url , username, password);
            Statement stetment  = conn.createStatement(); ){
            String query = "select PURCHASES_PAID,QUANTITY,PRICE from PURCHASES natural join commodity";
            ResultSet rs = stetment .executeQuery(query );
            int sum=0;
            while(rs.next()){
                if(rs.getString(1).equals("1")){
                    sum+=(Integer.parseInt(rs.getString(2)))*(Integer.parseInt(rs.getString(3)));
                }
            }

            return sum;
        }
        catch(SQLException e){

            return -1;
        }
    }
    public int sum_Salaries(){
        String Db_Url = "jdbc:oracle:thin:@localhost:1521:xe";
        String username = "anas";
        String password = "anas";
        try(Connection conn = DriverManager.getConnection(Db_Url , username, password);
            Statement stetment  = conn.createStatement(); ){
            String query = "select sum(salary) from EMPLOYEE_PAYMENT natural join EMPLOYEE";
            ResultSet rs = stetment .executeQuery(query );
            rs.next();
            int sum = (rs.getString(1)== null) ? 0 : Integer.parseInt(rs.getString(1)) ;
            return sum;
        }
        catch(SQLException e){
            return -1;
        }
    }
}
