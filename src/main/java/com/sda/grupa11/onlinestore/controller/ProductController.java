package com.sda.grupa11.onlinestore.controller;

import com.sda.grupa11.onlinestore.model.Product;
import com.sda.grupa11.onlinestore.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

//@RestController
//@RequestMapping("/protuct")
public class ProductController {


    @Autowired

    private IProductService productService;
//
//    @PostMapping
//    public ResponseEntity<Product> createProduct(@RequestBody Product request) {
//
//        ResponseEntity<Product> productResponseEntity = new ResponseEntity<Product>(productService.saveProduct(request), HttpStatus.CREATED);
//        return productResponseEntity;
//    }

    @GetMapping
    public ResponseEntity<List<Product>> findAllProducts() {
        return new ResponseEntity<>(productService.findAllProducts(), HttpStatus.OK);
    }
    @GetMapping("/{productId}")
    public ResponseEntity<Product> getOrderById(@RequestParam(name = "productId") Long id){
        Product product= productService.findProductById(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable(name = "id") Long id, Principal principal) {
        principal.getName();
        productService.deleteProductById(id);
        return new ResponseEntity<>("product deleted", HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> update(@PathVariable(name = "id") Long id,
                                          @RequestBody Product request, Principal principal) {
        principal.getName();
        Product response = productService.updateProduct(id, request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
