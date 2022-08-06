package com.example.proglanglab.utils;

import com.example.proglanglab.classes.ShopAssistant;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;


import java.lang.reflect.Type;

public class AssistantGsonSerializer implements JsonSerializer<ShopAssistant> {
    @Override
    public JsonElement serialize(ShopAssistant shopAssistant, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject assistantJson = new JsonObject();
        assistantJson.addProperty("id", shopAssistant.getId());
        assistantJson.addProperty("username", shopAssistant.getUsername());
        assistantJson.addProperty("password", shopAssistant.getPassword());
        assistantJson.addProperty("AssistantName",shopAssistant.getAssistantName());
        assistantJson.addProperty("AssistantSecondName",shopAssistant.getAssistantSecondName());
        assistantJson.addProperty("phoneNum", shopAssistant.getPhoneNum());
        //dar json array, kur yra Task serializer, kad nebutu amzino duomenu buildinimo
        return assistantJson;
    }
}

