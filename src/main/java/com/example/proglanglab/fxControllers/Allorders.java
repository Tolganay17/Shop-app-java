package com.example.proglanglab.fxControllers;

import com.example.proglanglab.Run;
import com.example.proglanglab.classes.Order;
import com.example.proglanglab.controltool.HiberTool;
import com.example.proglanglab.controltool.OrderHiberTool;
import com.example.proglanglab.controltool.databaseutil;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ResourceBundle;

public class Allorders implements Initializable {
    public ListView ALLORDERS;
    public Button Menupage;
    private int userID;
    private boolean isUserAdmin;
    EntityManagerFactory entityManagerFactory= Persistence.createEntityManagerFactory("Shop");
    OrderHiberTool allordershibernate=new OrderHiberTool(entityManagerFactory);
    public void setDataForOrder(int id,boolean isUserAdmin) {
        this.userID = id;
        this.isUserAdmin = isUserAdmin;
    }
    private void fillTablesAllOrders() throws ClassNotFoundException, SQLException {
        ALLORDERS.getItems().clear();
       List<Order> orders=allordershibernate.getAllOrders();

        for (Order order:orders) {
            ALLORDERS.getItems().add("id: " +order.getOrderId() + " Date: " + order.getOrder_date());
        }

    }



        @Override
        public void initialize (URL location, ResourceBundle resources){
            try {
                fillTablesAllOrders();
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }

    public void Menupagef(ActionEvent actionEvent) throws IOException {
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
}
