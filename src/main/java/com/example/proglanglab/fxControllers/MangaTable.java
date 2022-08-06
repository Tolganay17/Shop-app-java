package com.example.proglanglab.fxControllers;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class MangaTable implements Initializable {
    private SimpleIntegerProperty id_manga= new SimpleIntegerProperty();
    private SimpleStringProperty manga = new SimpleStringProperty();
    private SimpleStringProperty author = new SimpleStringProperty();
    private SimpleStringProperty release = new SimpleStringProperty();
    private SimpleStringProperty price = new SimpleStringProperty();
    private SimpleStringProperty adaptation=new SimpleStringProperty();

    public MangaTable() {
    }

    public MangaTable(SimpleStringProperty manga, SimpleStringProperty author, SimpleStringProperty release, SimpleStringProperty price, SimpleStringProperty adaptation) {
        this.manga = manga;
        this.author = author;
        this.release = release;
        this.price = price;
        this.adaptation = adaptation;
    }

    public String getManga() {
        return manga.get();
    }

    public SimpleStringProperty mangaProperty() {
        return manga;
    }

    public void setManga(String manga) {
        this.manga.set(manga);
    }

    public String getAuthor() {
        return author.get();
    }

    public SimpleStringProperty authorProperty() {
        return author;
    }

    public void setAuthor(String author) {
        this.author.set(author);
    }

    public String getRelease() {
        return release.get();
    }

    public SimpleStringProperty releaseProperty() {
        return release;
    }

    public void setRelease(String release) {
        this.release.set(release);
    }

    public String getPrice() {
        return price.get();
    }

    public SimpleStringProperty priceProperty() {
        return price;
    }

    public void setPrice(String price) {
        this.price.set(price);
    }

    public String getAdaptation() {
        return adaptation.get();
    }

    public SimpleStringProperty adaptationProperty() {
        return adaptation;
    }

    public void setAdaptation(String adaptation) {
        this.adaptation.set(adaptation);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public int getId_manga() {
        return id_manga.get();
    }

    public SimpleIntegerProperty id_mangaProperty() {
        return id_manga;
    }

    public void setId_manga(int id_manga) {
        this.id_manga.set(id_manga);
    }
}

