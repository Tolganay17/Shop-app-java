package com.example.proglanglab.webb;

import com.example.proglanglab.classes.Customer;
import com.example.proglanglab.classes.ShopAssistant;
import com.example.proglanglab.controltool.HiberTool;
import com.example.proglanglab.utils.AssistantGsonSerializer;
import com.example.proglanglab.utils.CustomerGsonSerializer;
import com.example.proglanglab.utils.LocalDateGsonSerializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.util.Properties;

@Controller
public class WebUserController {
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Shop");
    HiberTool hiberTool = new HiberTool(entityManagerFactory);
    @RequestMapping(value = "/users/userLogin", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String userlogin(@RequestBody String request) {

        Gson parser = new Gson();
        Properties data = parser.fromJson(request, Properties.class);
        GsonBuilder gson = new GsonBuilder();
        Customer customer = null;
        ShopAssistant shopAssistant = null;
        /*
        { "type":"C"
        "login":
        "psw": }
         */
       if (data.getProperty("DTYPE").equals("Customer")) {
           customer = (Customer) hiberTool.getUserByLoginData(data.getProperty("username"), data.getProperty("password"));
           gson.registerTypeAdapter(Customer.class, new CustomerGsonSerializer());
        } else if (data.getProperty("DTYPE").equals("ShopAssistant")) {
           shopAssistant = (ShopAssistant) hiberTool.getUserByLoginData(data.getProperty("username"), data.getProperty("password"));
            gson.registerTypeAdapter(ShopAssistant.class, new AssistantGsonSerializer());
        }

        if (customer == null && shopAssistant == null) {
            return "Wrong credentials or no such user";
        }


        Gson builder = gson.create();
        return customer != null ? builder.toJson(customer) : builder.toJson(shopAssistant);


    }
    /*public String userLogin(@RequestBody String request) {
        Gson gson = new Gson();
        Properties properties = gson.fromJson(request, Properties.class);
        if(properties.getProperty("DTYPE").equals("Customer")){
         gson.toJson(hiberTool.getUserByLoginData(properties.getProperty("username"), properties.getProperty("password" +
                "")));}
        if(properties.getProperty("DTYPE").equals("Customer")){
             gson.toJson(hiberTool.getUserByLoginData(properties.getProperty("username"), properties.getProperty("password" +
                    "")));
        }
    }*/

    @RequestMapping(value = "/users/allUsers", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String getAllUsers() {
        Gson gson = new Gson();
        return gson.toJson(hiberTool.getAllUsers().toString());
    }


    @RequestMapping(value = "/users/updatePerson/{id}", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String updatePerson(@RequestBody String request, @PathVariable(name = "id") int id) {
        Gson gson = new Gson();
        Properties properties = gson.fromJson(request, Properties.class);

        ShopAssistant assistant = hiberTool.getAssistantById(
                id);

        assistant.setAssistantName(properties.getProperty("AssistantName"));
        assistant.setUsername(properties.getProperty("username"));
        assistant.setAssistantSecondName(properties.getProperty("AssistantSecondName"));
        //pabaigsim

        //ShopAssistant assistant = new ShopAssistant(properties.getProperty("login"), properties.getProperty("psw"), properties.getProperty("name"), properties.getProperty("surname"), properties.getProperty("email"));
        hiberTool.editUser(assistant);
        return "Success";
    }

    @RequestMapping(value = "/users/addPerson", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String addNewPerson(@RequestBody String request) {
        Gson gson = new Gson();
        Properties properties = gson.fromJson(request, Properties.class);
        ShopAssistant assistant = new ShopAssistant(properties.getProperty("username"), properties.getProperty("psw"), properties.getProperty("name"), properties.getProperty("surname"), properties.getProperty("email"));
        hiberTool.createUser(assistant);
        return "Success";
    }

    @RequestMapping(value = "/users/deleteUser/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String updatePerson(@PathVariable(name = "id") int id) {
        hiberTool.removeUser(id);
        //Check if really deleted
        return "Success";
    }

}
