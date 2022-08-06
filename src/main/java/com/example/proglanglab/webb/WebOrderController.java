package com.example.proglanglab.webb;

import com.example.proglanglab.classes.Order;
import com.example.proglanglab.classes.Product;
import com.example.proglanglab.classes.ShopAssistant;
import com.example.proglanglab.controltool.HiberTool;
import com.example.proglanglab.controltool.OrderHiberTool;
import com.example.proglanglab.controltool.ProductHiberTool;
import com.example.proglanglab.utils.ProjectGsonSerializer;
import com.example.proglanglab.utils.ProjectListGsonSerializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;
@Controller
public class WebOrderController {
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Shop");
    OrderHiberTool hiberTool = new OrderHiberTool(entityManagerFactory);
    ProductHiberTool productHiberTool = new ProductHiberTool(entityManagerFactory);
    @RequestMapping(value = "/order/allOrders", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String getAllOrders() {
        List<Order> allProjects = hiberTool.getAllOrders();

       GsonBuilder gson = new GsonBuilder();
        Type projectList = new TypeToken<List<Order>>() {
        }.getType();
        gson.registerTypeAdapter(Order.class, new ProjectGsonSerializer()).registerTypeAdapter(projectList, new ProjectListGsonSerializer());
        Gson parser = gson.create();
        return parser.toJson(allProjects);
       // Gson gson = new Gson();
        //String json = gson.toJson(hiberTool.getAllOrders().toString());
       //return json;
    }


    @RequestMapping(value = "/order/updateOrder/{id}", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String updateOrder(@RequestBody String request, @PathVariable(name = "id") int id) {
        Gson gson = new Gson();
        Properties properties = gson.fromJson(request, Properties.class);

        Order order = hiberTool.getOrderById(id);
        List<Product> products=order.getProducts();
        Product product = productHiberTool.getProductById(Integer.parseInt(properties.getProperty("Product_id")));
        products.add(product);
        order.setOrder_date(LocalDate.parse(properties.getProperty("date")));
        hiberTool.editOrder(order);
        return product.getProductName()+"was Successfully added";
    }

    @RequestMapping(value = "/order/addOrder", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String addNewOrder(@RequestBody String request) {
        Gson gson = new Gson();
        Properties properties = gson.fromJson(request, Properties.class);
        List<Product> products = null;
        Order order = new Order(products,LocalDate.now());
        hiberTool.createOrder(order);
        return "Success";
    }

    @RequestMapping(value = "/order/deleteorder/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String updateOrder(@PathVariable(name = "id") int id) {
        hiberTool.removeOrder(id);
        if(hiberTool.getOrderById(id)==null)
        return "Success";
        else return "Failed";
    }

}
