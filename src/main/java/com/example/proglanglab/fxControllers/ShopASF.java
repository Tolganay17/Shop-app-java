package com.example.proglanglab.fxControllers;

import com.example.proglanglab.classes.Manga;
import com.example.proglanglab.classes.Product;
import com.example.proglanglab.classes.ShopAssistant;
import com.example.proglanglab.classes.User;
import com.example.proglanglab.controltool.HiberTool;
import com.example.proglanglab.controltool.ProductHiberTool;
import com.example.proglanglab.controltool.databaseutil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.Callback;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ResourceBundle;

public class ShopASF implements Initializable {
    @FXML
    public TableView SAtable;
    @FXML
    public TableColumn<ShopControl, Integer> idT;
    @FXML
    public TableColumn<ShopControl, String> SusernameT;
    @FXML
    public TableColumn<ShopControl, String> SnameT;
    @FXML
    public TableColumn<ShopControl, String> SsurnameT;
    @FXML
    public TableColumn<ShopControl, String> SphoneT;
    public TableColumn<ShopControl, Void> actionField;
    private Connection connection;
    private Statement statement;
    private ObservableList<ShopControl> data = FXCollections.observableArrayList();
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Shop");
    HiberTool hiberTool = new HiberTool(entityManagerFactory);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        SAtable.setVisible(true);
        SAtable.setEditable(true);
        idT.setCellValueFactory(new PropertyValueFactory<>("id"));
        SusernameT.setCellValueFactory(new PropertyValueFactory<>("username"));
        SnameT.setCellValueFactory(new PropertyValueFactory<>("name"));
        SsurnameT.setCellValueFactory(new PropertyValueFactory<>("surname"));
        SphoneT.setCellValueFactory(new PropertyValueFactory<>("number"));
        SusernameT.setCellFactory(TextFieldTableCell.forTableColumn());
        SusernameT.setOnEditCommit(
                t -> {
                    t.getTableView().getItems().get(
                            t.getTablePosition().getRow()).setUsername(t.getNewValue());

                    User user=hiberTool.getUserById( t.getTableView().getItems().get(
                            t.getTablePosition().getRow()).getId());
                    user.setUsername(t.getNewValue());
                    hiberTool.editUser(user);

                }
        );
        SnameT.setCellFactory(TextFieldTableCell.forTableColumn());
        SnameT.setOnEditCommit(
                t -> {
                    t.getTableView().getItems().get(
                            t.getTablePosition().getRow()).setName(t.getNewValue());

                    ShopAssistant user=hiberTool.getAssistantById( t.getTableView().getItems().get(
                            t.getTablePosition().getRow()).getId());
                    user.setAssistantName(t.getNewValue());
                    hiberTool.editUser(user);

                }
        );
        SsurnameT.setCellFactory(TextFieldTableCell.forTableColumn());
        SsurnameT.setOnEditCommit(
                t -> {
                    t.getTableView().getItems().get(
                            t.getTablePosition().getRow()).setSurname(t.getNewValue());

                    ShopAssistant user=hiberTool.getAssistantById( t.getTableView().getItems().get(
                            t.getTablePosition().getRow()).getId());
                    user.setAssistantSecondName(t.getNewValue());
                    hiberTool.editUser(user);

                }
        );
        SphoneT.setCellFactory(TextFieldTableCell.forTableColumn());
        SphoneT.setOnEditCommit(
                t -> {
                    t.getTableView().getItems().get(
                            t.getTablePosition().getRow()).setNumber(t.getNewValue());

                    ShopAssistant user=hiberTool.getAssistantById( t.getTableView().getItems().get(
                            t.getTablePosition().getRow()).getId());
                    user.setPhoneNum(t.getNewValue());
                    hiberTool.editUser(user);

                }
        );


        Callback<TableColumn<ShopControl, Void>, TableCell<ShopControl, Void>> cellFactory = new Callback<TableColumn<ShopControl, Void>, TableCell<ShopControl, Void>>() {
            @Override
            public TableCell<ShopControl, Void> call(final TableColumn<ShopControl, Void> param) {
                final TableCell<ShopControl, Void> cell = new TableCell<ShopControl, Void>() {

                    private final Button button = new Button("Delete");

                    {
                        button.setOnAction((ActionEvent event) -> {
                            ShopControl data = getTableView().getItems().get(getIndex());
                            hiberTool.removeUser(data.getId());

                            try {

                                loadUsers();
                            } catch (SQLException | ClassNotFoundException e) {
                                e.printStackTrace();
                            }
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(button);
                        }
                    }
                };
                return cell;
            }
        };

        actionField.setCellFactory(cellFactory);
        try {
            loadUsers();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void showTab(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        SAtable.setVisible(true);

    }

    private void loadUsers() throws SQLException, ClassNotFoundException {
        SAtable.setEditable(true);
        SAtable.getItems().clear();
        List<ShopAssistant> users=hiberTool.getAllShopAssistants();

            for(ShopAssistant shops:users){
                    ShopControl shopControl = new ShopControl();
                    shopControl.setId(shops.getId());
                shopControl.setUsername(shops.getUsername());
                shopControl.setName(shops.getAssistantName());
                    shopControl.setSurname(shops.getAssistantSecondName());
                    shopControl.setNumber(shops.getPhoneNum());

                    data.add(shopControl);
                }
        SAtable.setItems(data);
            }
    }

