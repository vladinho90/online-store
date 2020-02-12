package com.sda.grupa11.onlinestore.controller;

import com.sda.grupa11.onlinestore.dto.product.ProductResponse;
import com.sda.grupa11.onlinestore.model.Category;
import com.sda.grupa11.onlinestore.service.ProductService;
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
    private ProductService productService;

    @GetMapping("/search")
    public ResponseEntity<List<ProductResponse>> getProductsByCategory(@RequestParam(name = "category") Category category){

        List<ProductResponse> products= productService.findAllByCategory(category);

        return new ResponseEntity<>(products, HttpStatus.OK);
    }
}
