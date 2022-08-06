package com.example.proglanglab.classes;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.time.LocalDate;
@Entity
@DiscriminatorValue("ranobe")
public class Ranobe extends Product {

    private int NumberOfParts;
    private String Language;

    public Ranobe(String text) {
        super(text);
    }

    public Ranobe(String productName, String authorName, String releaseDate, String price, int numberOfParts, String language) {
        super(productName, authorName, releaseDate, price);
        NumberOfParts = numberOfParts;
        Language = language;
    }

    public Ranobe() {

    }

    @Override
    public int getProduct_id() {
        return super.getProduct_id();
    }

    public int getNumberOfParts() {
        return NumberOfParts;
    }

    public void setNumberOfParts(int numberOfParts) {
        NumberOfParts = numberOfParts;
    }

    public String getLanguage() {
        return Language;
    }

    public void setLanguage(String language) {
        Language = language;
    }
}