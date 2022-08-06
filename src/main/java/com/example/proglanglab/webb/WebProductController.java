package com.example.proglanglab.webb;

import com.example.proglanglab.classes.Manga;
import com.example.proglanglab.classes.Product;
import com.example.proglanglab.classes.Ranobe;
import com.example.proglanglab.classes.ShopAssistant;
import com.example.proglanglab.controltool.HiberTool;
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
import java.util.List;
import java.util.Properties;
@Controller
public class WebProductController {
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Shop");
    ProductHiberTool hiberTool = new ProductHiberTool(entityManagerFactory);

    @RequestMapping(value = "/product/allPRoducts", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String getAllProducts() {
        List<Product> allProjects = hiberTool.getAllProducts();

        Gson gson = new Gson();
        return gson.toJson(hiberTool.getAllProducts().toString());
    }
    @RequestMapping(value = "/product/getProductByID/{id}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String getProductById(@PathVariable(name = "id") int id) {
        Gson gson = new Gson();
        String json = gson.toJson(hiberTool.getProductById(id).toString());
        return json;
    }



    @RequestMapping(value = "/product/updateProduct/{id}", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String updateProduct(@RequestBody String request, @PathVariable(name = "id") int id) {
        Gson gson = new Gson();
        Properties properties = gson.fromJson(request, Properties.class);

        Product product = hiberTool.getProductById(id);

        product.setProductName(properties.getProperty("productname"));
        product.setAuthorName(properties.getProperty("authorname"));
        product.setPrice(properties.getProperty("price"));
        //pabaigsim

        //Person person = new Person(properties.getProperty("login"), properties.getProperty("psw"), properties.getProperty("name"), properties.getProperty("surname"), properties.getProperty("email"));
        hiberTool.editProduct(product);
        return "Success";
    }

    @RequestMapping(value = "/product/addRanobe", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String addNewRanobe(@RequestBody String request) {
        Gson gson = new Gson();
        Properties properties = gson.fromJson(request, Properties.class);
        Ranobe ranobe = new Ranobe(properties.getProperty("ranobename"), properties.getProperty("author"),properties.getProperty("date"),properties.getProperty("price"),Integer.parseInt(properties.getProperty("partsnumber")),properties.getProperty("language"));
        hiberTool.createProduct(ranobe);
        return "Success";
    }
    @RequestMapping(value = "/product/addManga", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String addNewManga(@RequestBody String request) {
        Gson gson = new Gson();
        Properties properties = gson.fromJson(request, Properties.class);
        Manga manga = new Manga(properties.getProperty("manganame"), properties.getProperty("author"),properties.getProperty("date"),properties.getProperty("price"),properties.getProperty("hasAnime"));
        hiberTool.createProduct(manga);
        return "Success";
    }


    @RequestMapping(value = "/product/deleteProduct/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String updatePerson(@PathVariable(name = "id") int id) {
        hiberTool.removeProduct(id);
        //Check if really deleted
        return "Success";
    }
}
