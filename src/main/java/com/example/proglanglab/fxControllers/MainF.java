package com.example.proglanglab.fxControllers;
import com.example.proglanglab.Run;
import com.example.proglanglab.classes.*;
import com.example.proglanglab.controltool.HiberTool;
import com.example.proglanglab.controltool.OrderHiberTool;
import com.example.proglanglab.controltool.ProductHiberTool;
import com.example.proglanglab.fxControllers.MangaTable;
import com.example.proglanglab.controltool.databaseutil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ResourceBundle;

public class MainF {
    @FXML
    public TableView mangaTab;
    @FXML
    public TableColumn<MangaTable,String> NameAtt;
    @FXML
    public TableColumn<MangaTable,String> authorAtt;
    @FXML
    public TableColumn<MangaTable, String> dateAtt;
    @FXML
    public TableColumn<MangaTable,String> priceAtt;
    @FXML
    public MenuItem mangaItem;
    @FXML
    public Button SAbut;
    @FXML
    public TableView ranobeTab;
    @FXML
    public TableColumn<RanobeTable,String> ranobet;
    @FXML
    public TableColumn<RanobeTable,String> relt;
    @FXML
    public TableColumn<RanobeTable,String> pricet;
    @FXML
    public TableColumn<RanobeTable,String>authort;
    @FXML
    public TableColumn<RanobeTable,String> partst;
    public TableColumn<RanobeTable,String> languagest;
    public TableColumn<RanobeTable,String> idt;
    public TableColumn<MangaTable,String> idAtt;
    public MenuItem ranobeitem;
    public Button addt;
    public TableColumn<MangaTable,Void> deleteFM;
    public TableColumn<RanobeTable,Void>deleteFR;
    public Button goorderf;
    public Button showordersf;
    public Button showallorderdf;
    public TableColumn <MangaTable,String>adaptationAtt;
    public Button addProduct;
    private int UserID;
    boolean isUserAdmin;
    public void setUserID(int userID,boolean isAdmin) {
        this.UserID = userID;
        isUserAdmin=isAdmin;
        showMainf();
    }


    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Shop");
    //Then comes Your hibernate controller
    ProductHiberTool hiberTool = new ProductHiberTool(entityManagerFactory);
    HiberTool userHiberTool=new HiberTool(entityManagerFactory);
    OrderHiberTool orderHiberTool=new OrderHiberTool(entityManagerFactory);

    private ObservableList<MangaTable> data = FXCollections.observableArrayList();
    private ObservableList<RanobeTable> data1 = FXCollections.observableArrayList();



    public void showMainf() {
        System.out.println(UserID);
        System.out.println(isUserAdmin);
        mangaTab.setEditable(true);
        if (!isUserAdmin) {
            SAbut.setVisible(false);
            addProduct.setVisible(false);
            mangaTab.setEditable(false);
            showallorderdf.setVisible(false);
        }
        else{
            SAbut.setVisible(true);
            addProduct.setVisible(true);
            mangaTab.setEditable(true);
        }
        mangaTab.setVisible(false);

        idAtt.setCellValueFactory(new PropertyValueFactory<>("id_manga"));
        NameAtt.setCellValueFactory(new PropertyValueFactory<>("manga"));
        dateAtt.setCellValueFactory(new PropertyValueFactory<>("release"));
        priceAtt.setCellValueFactory(new PropertyValueFactory<>("price"));
        authorAtt.setCellValueFactory(new PropertyValueFactory<>("author"));
        adaptationAtt.setCellValueFactory(new PropertyValueFactory<>("adaptation"));
        if(isUserAdmin) {
            NameAtt.setCellFactory(TextFieldTableCell.forTableColumn());
            NameAtt.setOnEditCommit(
                    t -> {
                        t.getTableView().getItems().get(
                                t.getTablePosition().getRow()).setManga(t.getNewValue());

                        Product product = hiberTool.getProductById(t.getTableView().getItems().get(
                                t.getTablePosition().getRow()).getId_manga());
                        product.setProductName(t.getNewValue());
                        hiberTool.editProduct(product);

                    }
            );
            adaptationAtt.setCellFactory(TextFieldTableCell.forTableColumn());
            adaptationAtt.setOnEditCommit(
                    t -> {
                        t.getTableView().getItems().get(
                                t.getTablePosition().getRow()).setAdaptation(t.getNewValue());

                        Manga manga = (Manga) hiberTool.getProductById(t.getTableView().getItems().get(
                                t.getTablePosition().getRow()).getId_manga());
                        manga.setHas_anime(t.getNewValue());
                        hiberTool.editProduct(manga);

                    }
            );
            authorAtt.setCellFactory(TextFieldTableCell.forTableColumn());
            authorAtt.setOnEditCommit(
                    t -> {
                        t.getTableView().getItems().get(
                                t.getTablePosition().getRow()).setAuthor(t.getNewValue());

                        Product product = hiberTool.getProductById(t.getTableView().getItems().get(
                                t.getTablePosition().getRow()).getId_manga());
                        product.setAuthorName(t.getNewValue());
                        hiberTool.editProduct(product);

                    }
            );

            Callback<TableColumn<MangaTable, Void>, TableCell<MangaTable, Void>> cellFactory = new Callback<TableColumn<MangaTable, Void>, TableCell<MangaTable, Void>>() {
                @Override
                public TableCell<MangaTable, Void> call(final TableColumn<MangaTable, Void> param) {
                    final TableCell<MangaTable, Void> cell = new TableCell<MangaTable, Void>() {

                        private final Button button = new Button("Delete");

                        {
                            button.setOnAction((ActionEvent event) -> {
                                MangaTable data = getTableView().getItems().get(getIndex());
                                hiberTool.removeProduct(data.getId_manga());



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

            deleteFM.setCellFactory(cellFactory);

            dateAtt.setCellFactory(TextFieldTableCell.forTableColumn());
            dateAtt.setOnEditCommit(
                    t -> {
                        t.getTableView().getItems().get(
                                t.getTablePosition().getRow()).setRelease(t.getNewValue());

                        Product product = hiberTool.getProductById(t.getTableView().getItems().get(
                                t.getTablePosition().getRow()).getId_manga());
                        product.setReleaseDate(t.getNewValue());
                        hiberTool.editProduct(product);

                    }
            );

            priceAtt.setCellFactory(TextFieldTableCell.forTableColumn());
            priceAtt.setOnEditCommit(
                    t -> {
                        t.getTableView().getItems().get(
                                t.getTablePosition().getRow()).setPrice(t.getNewValue());

                        Product product = hiberTool.getProductById(t.getTableView().getItems().get(
                                t.getTablePosition().getRow()).getId_manga());
                        product.setPrice(t.getNewValue());
                        hiberTool.editProduct(product);

                    }
            );
        }

        ranobeTab.setVisible(false);

        if (isUserAdmin) {
            ranobeTab.setEditable(true);
        }
        idt.setCellValueFactory(new PropertyValueFactory<>("ranobe_id"));
        ranobet.setCellValueFactory(new PropertyValueFactory<>("ranobe"));
        relt.setCellValueFactory(new PropertyValueFactory<>("ran_release"));
        pricet.setCellValueFactory(new PropertyValueFactory<>("ran_price"));
        authort.setCellValueFactory(new PropertyValueFactory<>("ran_uthor"));
        partst.setCellValueFactory(new PropertyValueFactory<>("ran_parts"));
        languagest.setCellValueFactory(new PropertyValueFactory<>("ran_langu"));
        if(isUserAdmin){
        authort.setCellFactory(TextFieldTableCell.forTableColumn());
        authort.setOnEditCommit(
                t -> {
                    t.getTableView().getItems().get(
                            t.getTablePosition().getRow()).setRan_uthor(t.getNewValue());

                    Product product = hiberTool.getProductById(t.getTableView().getItems().get(
                            t.getTablePosition().getRow()).getRanobe_id());
                    product.setAuthorName(t.getNewValue());
                    hiberTool.editProduct(product);

                }
        );
        relt.setCellFactory(TextFieldTableCell.forTableColumn());
        relt.setOnEditCommit(
                t -> {
                    t.getTableView().getItems().get(
                            t.getTablePosition().getRow()).setRan_release(t.getNewValue());

                    Product product = hiberTool.getProductById(t.getTableView().getItems().get(
                            t.getTablePosition().getRow()).getRanobe_id());
                    product.setReleaseDate(t.getNewValue());
                    hiberTool.editProduct(product);

                }
        );
        pricet.setCellFactory(TextFieldTableCell.forTableColumn());
        pricet.setOnEditCommit(
                t -> {
                    t.getTableView().getItems().get(
                            t.getTablePosition().getRow()).setRan_price(t.getNewValue());

                    Product product = hiberTool.getProductById(t.getTableView().getItems().get(
                            t.getTablePosition().getRow()).getRanobe_id());
                    product.setPrice(t.getNewValue());
                    hiberTool.editProduct(product);

                }
        );
        if (isUserAdmin) {
            Callback<TableColumn<RanobeTable, Void>, TableCell<RanobeTable, Void>> cell1Factory = new Callback<TableColumn<RanobeTable, Void>, TableCell<RanobeTable, Void>>() {
                @Override
                public TableCell<RanobeTable, Void> call(final TableColumn<RanobeTable, Void> param) {
                    final TableCell<RanobeTable, Void> cell = new TableCell<RanobeTable, Void>() {

                        private final Button button = new Button("Delete");

                        {
                            button.setOnAction((ActionEvent event) -> {
                                RanobeTable data = getTableView().getItems().get(getIndex());

                                hiberTool.removeProduct(data.getRanobe_id());

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

            deleteFR.setCellFactory(cell1Factory);
        }
        }
            try {
                loadUsers();
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }


    public void showTab(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {


                 mangaTab.setVisible(true);
        ranobeTab.setVisible(false);
    }

    public void showtab1(ActionEvent actionEvent) throws SQLException,ClassNotFoundException {
         ranobeTab.setVisible(true);
        mangaTab.setVisible(false);


    }

    private void loadUsers() throws SQLException, ClassNotFoundException {
        mangaTab.setEditable(true);
        mangaTab.getItems().clear();
        User user = userHiberTool.getUserById(UserID);

        List<Product>products=hiberTool.getAllProducts();

        for(Product prod:products){
            if(prod.getProduct_type().equals("manga")) {

                MangaTable mangatable = new MangaTable();
                mangatable.setId_manga(prod.getProduct_id());
                mangatable.setManga(prod.getProductName());
                mangatable.setAuthor(prod.getAuthorName());
                mangatable.setRelease(prod.getReleaseDate());
                mangatable.setPrice(prod.getPrice());
                Manga manga = hiberTool.getMangaById(prod.getProduct_id());
                mangatable.setAdaptation(manga.getHas_anime());
                data.add(mangatable);
            }
        }

        mangaTab.setItems(data);



        ranobeTab.setEditable(true);
        ranobeTab.getItems().clear();
        for(Product prod:products){
            if(prod.getProduct_type().equals("ranobe")) {
                RanobeTable ranobeTable = new RanobeTable();
                ranobeTable.setRanobe_id(prod.getProduct_id());
                ranobeTable.setRanobe(prod.getProductName());
                ranobeTable.setRan_uthor(prod.getAuthorName());
                ranobeTable.setRan_release(prod.getReleaseDate());
                ranobeTable.setRan_price(prod.getPrice());
                Ranobe ranobe = hiberTool.getRanobeById( prod.getProduct_id());
                ranobeTable.setRan_parts(ranobe.getNumberOfParts());
                ranobeTable.setRan_langu(ranobe.getLanguage());
                data1.add(ranobeTable);
            }
        }



        ranobeTab.setItems(data1);

    }

    public void openSApage(ActionEvent actionEvent) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(Run.class.getResource("ShopAS.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage=(Stage) SAbut.getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }


    public void addnewit(ActionEvent actionEvent) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(Run.class.getResource("Additems.fxml"));
        Parent root = fxmlLoader.load();
        Additems additems = fxmlLoader.getController();
        additems.setDataForOrder(this.UserID,this.isUserAdmin);
        Scene scene = new Scene(root);
        Stage stage=(Stage) showallorderdf.getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }

    public void goorderaction(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Run.class.getResource("orderit.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage=(Stage) showallorderdf.getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }

    public void showordersf(ActionEvent actionEvent) throws IOException, ClassNotFoundException {
        FXMLLoader fxmlLoader = new FXMLLoader(Run.class.getResource("orderlist.fxml"));
        Parent root = fxmlLoader.
                load();

        Orderlist orderlist = fxmlLoader.getController();
        orderlist.setDataForOrder(this.UserID,this.isUserAdmin);
        Scene scene = new Scene(root);
        Stage stage=(Stage) showallorderdf.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public void showallordersf(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Run.class.getResource("Allorders.fxml"));
        Parent root = fxmlLoader.load();
        Allorders allorders = fxmlLoader.getController();
        allorders.setDataForOrder(this.UserID,this.isUserAdmin);
        Scene scene = new Scene(root);
        Stage stage=(Stage) showallorderdf.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}
