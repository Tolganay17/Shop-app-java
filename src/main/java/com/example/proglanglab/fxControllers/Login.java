package com.example.proglanglab.fxControllers;

import com.example.proglanglab.Run;
import com.example.proglanglab.classes.ShopAssistant;
import com.example.proglanglab.classes.User;
import com.example.proglanglab.controltool.HiberTool;
import com.example.proglanglab.controltool.databaseutil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login {
    @FXML
    public TextField logF;
    @FXML
    public PasswordField pswF;

    //private Connection connection;

    //Create entity manager factory, tell what db, how to connect, what tables should be created based on entity classes
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Shop");
    //Then comes Your hibernate controller
    HiberTool hiberTool = new HiberTool(entityManagerFactory);

    private PreparedStatement preparedStatement;

    public void validateandlogin(ActionEvent actionEvent) throws ClassNotFoundException, SQLException, IOException {

        User user = hiberTool.getUserByLoginData(logF.getText(), pswF.getText());

        if (user != null) {
            FXMLLoader fxmlLoader = new FXMLLoader(Run.class.getResource("main.fxml"));
            Parent root = fxmlLoader.load();
            MainF mainF = fxmlLoader.getController();
            mainF.setUserID(user.getId(),user.isHasRights());
            Scene scene = new Scene(root);

            Stage stage = new Stage();
            stage.setTitle("Manga shop");
            stage.setScene(scene);

            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        } else {


            alertMessage("Wrong username or password!");
        }

        /*connection = databaseutil.connectToDb();

        String sql = "SELECT count(*) FROM users AS u WHERE u.username = ? AND u.password = ?";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, logF.getText());
        preparedStatement.setString(2, pswF.getText());
        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()) {
            if (rs.getInt(1) > 0) {
                FXMLLoader fxmlLoader = new FXMLLoader(Run.class.getResource("main.fxml"));
                Parent root = fxmlLoader.load();
                MainF mainProjectsWindow = fxmlLoader.getController();
                Scene scene = new Scene(root);

                Stage stage = new Stage();
                stage.setTitle("Manga shop");
                stage.setScene(scene);

                stage.initModality(Modality.APPLICATION_MODAL);
                stage.showAndWait();
            }else {
                alertMessage("Wrong username or password!");
            }

        }
        databaseutil.disconnectfromdatabase(connection, preparedStatement);*/
    }

    public void opensignupfrom(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Run.class.getResource("sign-up.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) logF.getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }

    public static void alertMessage(String mess) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText("Message:");
        alert.setContentText(mess);
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.showAndWait();
    }
}
