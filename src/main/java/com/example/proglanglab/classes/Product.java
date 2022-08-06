package com.example.proglanglab.classes;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "product_type", discriminatorType = DiscriminatorType.STRING)
@AttributeOverride(name = "product_type",
        column = @Column(name="product_type", insertable = false, updatable = false))

public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Product_id;
    private String ProductName;
    private String AuthorName;
    private String ReleaseDate;
    private String Price;
    private String product_type;
    @ManyToOne
    private Order BigOrder;

    public Product() {
    }

    public Order getBigOrder() {
        return BigOrder;
    }

    public void setBigOrder(Order bigOrder) {
        BigOrder = bigOrder;
    }

    public Product(int product_id) {
        Product_id = product_id;
    }

    public String getProduct_type() {
        return product_type;
    }

    public void setProduct_type(String product_type) {
        this.product_type = product_type;
    }





    public Product(String text) {
        ProductName=text;
    }

    public int getProduct_id() {
        return Product_id;
    }

    public void setProduct_id(int product_id) {
        Product_id = product_id;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public Product(String productName, String authorName, String releaseDate, String price) {
        ProductName = productName;
        AuthorName = authorName;
        ReleaseDate = releaseDate;
        Price = price;
    }


    public String getAuthorName() {
        return AuthorName;
    }

    public void setAuthorName(String authorName) {
        AuthorName = authorName;
    }

    public String getReleaseDate() {
        return ReleaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        ReleaseDate = releaseDate;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }



    public String toString() {
   return "{" +
            "\"Product_id\": " + Product_id +
            ", \"ProductName\": " + '"' + ProductName + '"' +
          ", \"AuthorName\": " + "\"" + AuthorName + "\"" +
           ", \"ReleaseDate\": " + "\"" + ReleaseDate + "\"" +
           ", \"Price\": " + "\"" + Price + "\"" +
           ", \"product_type\": " + "\"" + product_type + "\""+"}";
}
}
