package com.example.proglanglab.classes;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.time.LocalDate;
@Entity
@DiscriminatorValue("manga" )
public class Manga extends Product {
    String has_anime;

    public Manga(String text) {
        super(text);
    }

    public Manga(String productName, String authorName, String releaseDate, String price, String has_anime) {
        super(productName, authorName, releaseDate, price);
        this.has_anime = has_anime;
    }

    public Manga() {

    }

    public String getHas_anime() {
        return has_anime;
    }

    public void setHas_anime(String has_anime) {
        this.has_anime = has_anime;
    }

    @Override
    public int getProduct_id() {
        return super.getProduct_id();
    }
}


