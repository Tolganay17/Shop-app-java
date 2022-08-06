package com.example.proglanglab.utils;

import com.example.proglanglab.classes.Order;
import com.example.proglanglab.classes.Product;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;


import java.lang.reflect.Type;

public class ProjectGsonSerializer implements JsonSerializer<Order> {
    @Override
    public JsonElement serialize(Order orders, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject personJson = new JsonObject();
        personJson.addProperty("OrderId",orders.getOrderId());
        personJson.addProperty("order_date",orders.getOrder_date().toString());
        return personJson;
    }
}
