package com.sda.grupa11.onlinestore.controller;

import com.sda.grupa11.onlinestore.model.Product;
import com.sda.grupa11.onlinestore.model.enums.Category;
import com.sda.grupa11.onlinestore.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {



    @Autowired
    private IProductService productService;

    @GetMapping("/search")
    public ResponseEntity<List<Product>> getProductsByCategory(@RequestParam(name = "category") Category category){

        List<Product> products= productService.findAllProductByCategory(category);

        return new ResponseEntity<>(products, HttpStatus.OK);
    }
}
