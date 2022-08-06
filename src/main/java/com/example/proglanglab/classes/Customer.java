package com.example.proglanglab.classes;

import javax.persistence.Entity;
import java.util.List;

@Entity
public class Customer extends User{
    private String CustomerName;
    private String CustomerSurname;
    private String CustomerAddress;
    private String CustomerTelNumber;


    public Customer(String username, String password, List<Order> myOrders, boolean hasRights, String customerName, String customerSurname, String customerAddress, String customerTelNumber) {
        super(username, password, myOrders, hasRights);
        CustomerName = customerName;
        CustomerSurname = customerSurname;
        CustomerAddress = customerAddress;
        CustomerTelNumber = customerTelNumber;
    }

    public Customer() {
    }

    @Override
    public void remove(User user) {

    }

    public String getCustomerName() {
        return CustomerName;
    }

    public void setCustomerName(String customerName) {
        CustomerName = customerName;
    }

    public String getCustomerSurname() {
        return CustomerSurname;
    }

    public void setCustomerSurname(String customerSurname) {
        CustomerSurname = customerSurname;
    }

    public String getCustomerAddress() {
        return CustomerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        CustomerAddress = customerAddress;
    }

    public String getCustomerTelNumber() {
        return CustomerTelNumber;
    }

    public void setCustomerTelNumber(String customerTelNumber) {
        CustomerTelNumber = customerTelNumber;
    }
}

