package com.example.proglanglab.utils;

import com.example.proglanglab.classes.Customer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;


import java.lang.reflect.Type;

public class CustomerGsonSerializer implements JsonSerializer<Customer> {
    @Override
    public JsonElement serialize(Customer customer, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject customerJson = new JsonObject();
        customerJson.addProperty("id", customer.getId());
        customerJson.addProperty("username", customer.getUsername());
        customerJson.addProperty("password", customer.getPassword());
        customerJson.addProperty("CustomerName", customer.getCustomerName());
        customerJson.addProperty("CustomerSurname", customer.getCustomerSurname());
        customerJson.addProperty("CustomerAddress", customer.getCustomerAddress());
        customerJson.addProperty("CustomerTelNumber", customer.getCustomerSurname());
        return customerJson;
    }
}