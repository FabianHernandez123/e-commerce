package com.tts.ecommerce.controller;

import com.tts.ecommerce.model.Product;
import com.tts.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@ControllerAdvice
public class MainController {
    @Autowired
    ProductService productService;

    //can use the addNew below to add products to the database if the products aren't there, and
    //then comment it out
    //or manually add them to the database.

//    public void addNew() {
//        List<Product> newProducts = new ArrayList<Product>();
//
//        newProducts.add(new Product(1L, 4, (float) 1500.00, "Apple MacBook Pro", "MacBook Pro", "Apple", "computer", "images/apple_mlh32ll_a_15_4_macbook_pro_with_1293726.jpg"));
//
//        newProducts.add(new Product(2L, 3, (float) 1000.00, "C7 ST Desktop Front Edit", "Desktop", "Dell", "computer", "images/C7_ST_Desktop_Front.jpg"));
//
//        newProducts.add(new Product(3L, 12, (float) 800.00, "New iPhone 8, Silver", "IPhone 8", "Apple", "phone", "images/iphone8-silver-select-2017.jpg"));
//
//        newProducts.add(new Product(4L, 7, (float) 700.00, "New iPhone", "IPhone", "IPhone", "phone", "images/iphonexfrontback-800x573.jpg"));
//
//        List<Product> old = productService.findAll();
//        for (Product product : old) {
//            productService.deleteById(product.getId());
//        }
//
//        for(Product product : newProducts) {
//            productService.save(product);
//        }
//
//    }

    @GetMapping(value = "/")
    public String main() {
        //addNew();
        return "main";
    }

    // allowing ModelAttribute available to all controllers?
    @ModelAttribute(value = "products")
    public List<Product> products() {
        return productService.findAll();
    }

    @ModelAttribute(value = "categories")
    public List<String> categories(){
        return productService.findDistinctCategories();
    }

    @ModelAttribute(value = "brands")
    public List<String> brands(){
        return productService.findDistinctBrands();
    }

    @GetMapping(value = "/filter")
    public String filter(@RequestParam(required = false) String category,
                         @RequestParam(required = false) String brand, Model model) {
        List<Product> filtered = productService.findByBrandAndCategory(brand, category);
        model.addAttribute("products", filtered);

        return "main";
    }

    @GetMapping(value = "/about")
    public String about(){
        return "about";
    }



}
