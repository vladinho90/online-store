package com.sda.group11.onlinestore.controller;


import com.sda.group11.onlinestore.service.IProductService;
import com.sda.group11.onlinestore.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/product")
public class DemoController {



    @Autowired
    public IProductService productService;


    @GetMapping("")
    public String listProducts(Model theModel){

        //get producs from db
        List<Product> productList= productService.findAllProducts();

        //add to the spring model
        theModel.addAttribute("products", productList);
        return "index";
    }


}