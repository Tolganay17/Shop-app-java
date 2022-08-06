package com.example.proglanglab.utils;

import com.example.proglanglab.classes.Order;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.List;

public class ProjectListGsonSerializer implements JsonSerializer<List<Order>> {
    @Override
    public JsonElement serialize(List<Order> orders, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonArray jsonArray = new JsonArray();
        Gson parser = new GsonBuilder().create();

        for (Order p : orders) {
            jsonArray.add(parser.toJson(p));
        }
        System.out.println(jsonArray);
        return jsonArray;
    }
}
