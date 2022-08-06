package com.example.proglanglab.fxControllers;
import com.example.proglanglab.Run;
import com.example.proglanglab.classes.Order;
import com.example.proglanglab.classes.Product;
import com.example.proglanglab.classes.Ranobe;
import com.example.proglanglab.controltool.HiberTool;
import com.example.proglanglab.controltool.OrderHiberTool;
import com.example.proglanglab.controltool.ProductHiberTool;
import com.example.proglanglab.controltool.databaseutil;
import com.example.proglanglab.classes.Manga;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

public class Orderlist implements Initializable {

    public Button gotomenu;
    public TextField manganameF;
    public TextField manganameforNew;
    public TextField orderID;
    public TextField orderIDfornew;
    public RadioButton ran_check;
    public RadioButton man_check;
    public Label namelabel;
    public Label authorlabel;
    public Label pricelabel;
    public Label prodlabel;
    public Label prodidLabel;
    public TextField enterforEdit;
    public Label DateLabel;
    public Label formatLabel;
    public Button buttonShowAll;
    public Button deleteButton;
    public MenuButton buttonEdit;
    public Button addd;
    private int orderid;
    private int userID;
    private boolean isUserAdmin;
    public ListView myOrders;
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Shop");
    OrderHiberTool oht = new OrderHiberTool(entityManagerFactory);
    ProductHiberTool pht=new ProductHiberTool(entityManagerFactory);
    HiberTool hiberTool=new HiberTool(entityManagerFactory);
    public void setDataForOrder(int id,boolean isUserAdmin){
        this.userID=id;
        this.isUserAdmin=isUserAdmin;
        if(!this.isUserAdmin){
            buttonEdit.setVisible(false);
            deleteButton.setVisible(false);

            //buttonShowAll.setVisible(false);
        }
        if(this.isUserAdmin){
            addd.setVisible(false);
        }
    }
    public void setOrder(int orderid) throws ClassNotFoundException {
        this.orderid = orderid;
        fillTables();
    }
    private void fillTables() throws ClassNotFoundException {
        myOrders.getItems().clear();
         Order order=oht.getOrderById(orderid);

        for(Product pro:order.getProducts()) {
            myOrders.getItems().add(pro.getProduct_type()+" *** "+pro.getProductName()+" **** "+pro.getAuthorName()+ " *** " + pro.getPrice());
        }
    }


    public void gotomenu(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Run.class.getResource("main.fxml"));
        Parent root = fxmlLoader.load();
        MainF mainF = fxmlLoader.getController();
        mainF.setUserID(this.userID,this.isUserAdmin);
        Scene scene = new Scene(root);

        Stage stage = new Stage();
        stage.setTitle("Manga shop");
        stage.setScene(scene);
        stage.show();
    }
    private void fillTablesAllOrders() throws ClassNotFoundException, SQLException {
        myOrders.getItems().clear();
        List<Order> orders=hiberTool.getUserById(userID).getMyOrders();

        for (Order order:orders) {
            myOrders.getItems().add("id: " +order.getOrderId() + " Date: " + order.getOrder_date());
        }

    }




    public void showOrders(ActionEvent actionEvent) throws ClassNotFoundException {

        setOrder(Integer.parseInt(orderID.getText()));
        prodlabel.setVisible(true);
        authorlabel.setVisible(true);
        pricelabel.setVisible(true);
        namelabel.setVisible(true);
    }

    public void addNew(ActionEvent actionEvent) throws ClassNotFoundException {
        Order order =oht.getOrderById(Integer.parseInt(orderIDfornew.getText()));

            Product product = pht.getProductById(Integer.parseInt(manganameforNew.getText()));
        order.getProducts().add(product);
        product.setBigOrder(order);
        oht.editOrder(order);
        pht.editProduct(product);
        hiberTool.editUser(hiberTool.getUserById(userID));
            Login.alertMessage(product.getProductName()+ " was added to "+order.getOrderId());
    }

    public void createOrder(ActionEvent actionEvent) {
        Order order = new Order(LocalDate.now());
        oht.createOrder(order);
        hiberTool.getUserById(userID).getMyOrders().add(order);
        hiberTool.editUser(hiberTool.getUserById(userID));
        order.setCustomer(hiberTool.getUserById(userID));
        oht.editOrder(order);
        Login.alertMessage("New order N "+order.getOrderId()+ " was created");
    }

    public void showAllOrders(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        fillTablesAllOrders();
    }

    public void deleteOrder(ActionEvent actionEvent) throws ClassNotFoundException, SQLException {
        oht.removeOrder(Integer.parseInt(orderID.getText()));
        hiberTool.editUser(hiberTool.getUserById(userID));
        fillTablesAllOrders();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        prodlabel.setVisible(false);
        authorlabel.setVisible(false);
        pricelabel.setVisible(false);
        namelabel.setVisible(false);
        enterforEdit.setVisible(false);
        formatLabel.setVisible(false);
        prodidLabel.setVisible(false);
        DateLabel.setVisible(false);
    }

    public void deleteProductFromOrder(ActionEvent actionEvent) {
        enterforEdit.setVisible(true);
        prodidLabel.setVisible(true);
        DateLabel.setVisible(false);
        formatLabel.setVisible(false);
        if(!enterforEdit.getText().equals("")) {
            Order order = oht.getOrderById(Integer.parseInt(orderID.getText()));
            order.getProducts().removeIf(product->product.getProduct_id()==Integer.parseInt(enterforEdit.getText()));
            oht.editOrder(order);
        }
    }

    public void Editdate(ActionEvent actionEvent) {
        prodidLabel.setVisible(false);
        DateLabel.setVisible(true);
        formatLabel.setVisible(true);
        enterforEdit.setVisible(true);
        if(!enterforEdit.getText().equals("")) {

            Order order = oht.getOrderById(Integer.parseInt(orderID.getText()));
            order.setOrder_date(LocalDate.parse(enterforEdit.getText()));
            oht.editOrder(order);
        }
    }
}
