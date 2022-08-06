package com.example.proglanglab.fxControllers;
import com.example.proglanglab.classes.Customer;
import com.example.proglanglab.classes.Order;
import com.example.proglanglab.classes.ShopAssistant;
import com.example.proglanglab.controltool.HiberTool;
import com.example.proglanglab.controltool.databaseutil;
import com.example.proglanglab.Run;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class SignUp implements Initializable {
    @FXML
    public TextField loginF;
    @FXML
    public PasswordField passwordF;
    @FXML
    public PasswordField repeatpasswordF;
    @FXML
    public TextField ASnameF;
    @FXML
    public TextField ASsurnameF;
    @FXML
    public TextField ASphoneF;
    @FXML
    public TextField idF;
    public RadioButton radioA;
    public RadioButton radioC;
    public TextField Cusnamef;
    public TextField SurnameF;
    public TextField Cusnumberf;
    public TextField CusaddressF;
    //private Connection connection;
    EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "Shop");
    HiberTool hiberTool=new HiberTool(emfactory);
    //private PreparedStatement preparedStatement;
    public void create(ActionEvent actionEvent) throws IOException, ClassNotFoundException, SQLException {
        List<Order> orders=new ArrayList<>();
        if (radioA.isSelected()){
            ShopAssistant assistant = new ShopAssistant(loginF.getText(), passwordF.getText(),orders,true, ASnameF.getText(),ASsurnameF.getText(), ASphoneF.getText());
        hiberTool.createUser(assistant);}
        else{
            Customer customer=new Customer(loginF.getText(), passwordF.getText() ,orders,false,Cusnamef.getText(),SurnameF.getText(),Cusnumberf.getText(),CusaddressF.getText());
        hiberTool.createUser(customer);
        }

        Login.alertMessage("You signed up successfully");
        returnToLogin();
    }

    public void returntologin(ActionEvent actionEvent) throws IOException {
        returnToLogin();
    }
    private void returnToLogin() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Run.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage=(Stage) loginF.getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }
    ToggleGroup radioGroup= new ToggleGroup();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Cusnamef.setDisable(true);
        SurnameF.setDisable(true);
        Cusnumberf.setDisable(true);
        CusaddressF.setDisable(true);
        ASnameF.setDisable(true);
        ASsurnameF.setDisable(true);
        ASphoneF.setDisable(true);
    }

    public void ChangeF(ActionEvent actionEvent) {
        if(radioA.isSelected()){
            radioA.setToggleGroup(radioGroup);
            Cusnamef.setDisable(true);
            SurnameF.setDisable(true);
            Cusnumberf.setDisable(true);
            CusaddressF.setDisable(true);
            ASnameF.setDisable(false);
            ASsurnameF.setDisable(false);
            ASphoneF.setDisable(false);

        }
        if(radioC.isSelected()){
            radioC.setToggleGroup(radioGroup);
            ASnameF.setDisable(true);
            ASsurnameF.setDisable(true);
            ASphoneF.setDisable(true);

            Cusnamef.setDisable(false);
            SurnameF.setDisable(false);
            Cusnumberf.setDisable(false);
            CusaddressF.setDisable(false);

        }
    }
}
