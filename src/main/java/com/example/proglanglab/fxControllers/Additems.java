package com.example.proglanglab.fxControllers;

import com.example.proglanglab.Run;
import com.example.proglanglab.classes.Manga;
import com.example.proglanglab.classes.Ranobe;
import com.example.proglanglab.classes.ShopAssistant;
import com.example.proglanglab.controltool.HiberTool;
import com.example.proglanglab.controltool.ProductHiberTool;
import com.example.proglanglab.controltool.databaseutil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class Additems implements Initializable {
    
    @FXML
    public RadioButton mangaAddt;
    @FXML
    public RadioButton ranobeAddt;
    @FXML
    public TextField Mnameadd;
    @FXML
    public TextField Mauthoradd;
    @FXML
    public TextField Mdateadd;
    @FXML
    public TextField Mpriceadd;
    @FXML
    public TextField Rnameadd;
    @FXML
    public TextField Rauthoradd;
    @FXML
    public TextField Rdateadd;
    @FXML
    public TextField Rpriceadd;
    public Button addt;
    public Button raddt;
    public TextField Rpartsaddf;
    public ChoiceBox<String>lang;
    public ChoiceBox<String>adapt;

    private Connection connection;

    private PreparedStatement preparedStatement;
    private int userID;
    private boolean isUserAdmin;
    EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "Shop");
    ProductHiberTool hiberTool=new ProductHiberTool(emfactory);
    public void setDataForOrder(int id,boolean isUserAdmin) {
        this.userID = id;
        this.isUserAdmin = isUserAdmin;
    }
    public void additem(ActionEvent actionEvent) throws IOException, ClassNotFoundException, SQLException {
        Manga manga = new Manga(Mnameadd.getText(),Mauthoradd.getText(), Mdateadd.getText(),Mpriceadd.getText(),adapt.getValue());
        hiberTool.createProduct(manga);

    }


    public void rabobeadd(ActionEvent actionEvent) throws ClassNotFoundException, SQLException, IOException {
        Ranobe ranobe = new Ranobe(Rnameadd.getText(), Rauthoradd.getText(),Rdateadd.getText(),Rpriceadd.getText(),Integer.parseInt(Rpartsaddf.getText()), lang.getValue());
        hiberTool.createProduct(ranobe);




    }


    @FXML
    private void savet(ActionEvent actionEvent) throws IOException {
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


ToggleGroup radioGroup= new ToggleGroup();
    public void Changefield(ActionEvent actionEvent) {
        if(mangaAddt.isSelected()){
            mangaAddt.setToggleGroup(radioGroup);
            Rnameadd.setDisable(true);
            Rauthoradd.setDisable(true);
            Rdateadd.setDisable(true);
            Rpriceadd.setDisable(true);
            Rpartsaddf.setDisable(true);
            lang.setDisable(true);
            adapt.setDisable(false);
            Mnameadd.setDisable(false);
            Mauthoradd.setDisable(false);
            Mdateadd.setDisable(false);
            Mpriceadd.setDisable(false);

        }
        if(ranobeAddt.isSelected()){
            ranobeAddt.setToggleGroup(radioGroup);
            Mnameadd.setDisable(true);
            Mauthoradd.setDisable(true);
            Mdateadd.setDisable(true);
            Mpriceadd.setDisable(true);
            Rpartsaddf.setDisable(false);
            lang.setDisable(false);
            adapt.setDisable(true);
            Rnameadd.setDisable(false);
            Rauthoradd.setDisable(false);
            Rdateadd.setDisable(false);
            Rpriceadd.setDisable(false);

        }
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Mnameadd.setDisable(true);
        Mauthoradd.setDisable(true);
        Mdateadd.setDisable(true);
        Mpriceadd.setDisable(true);
        Rnameadd.setDisable(true);
        Rauthoradd.setDisable(true);
        Rdateadd.setDisable(true);
        Rpriceadd.setDisable(true);
        Rpartsaddf.setDisable(true);
        lang.setDisable(true);
        adapt.setDisable(true);
        lang.getItems().addAll("Russian","Japanese","English");
        adapt.getItems().addAll("Yes","No");



    }

    public void languageget(MouseEvent mouseEvent) {
        String langa=lang.getSelectionModel().getSelectedItem();
    }


    public void adaptget(MouseEvent mouseEvent) {
        String adapta=adapt.getSelectionModel().getSelectedItem();
    }
}