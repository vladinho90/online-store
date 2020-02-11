package com.sda.grupa11.onlinestore.controller;

import com.sda.grupa11.onlinestore.dto.product.ProductRequest;
import com.sda.grupa11.onlinestore.dto.product.ProductResponse;
import com.sda.grupa11.onlinestore.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/protucts")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(@RequestBody ProductRequest request) {
        return new ResponseEntity<>(productService.save(request), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> findAllProducts() {
        return new ResponseEntity<>(productService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<ProductResponse>> getProductsByCategory(@RequestParam(name = "category") String category){
        List<ProductResponse> products= productService.findByCategory(category);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable(name = "id") Long id) {
        productService.deleteById(id);
        return new ResponseEntity<>("product deleted", HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> update(@PathVariable(name = "id") Long id,
                                               @RequestBody ProductRequest request) {
        ProductResponse response = productService.update(id, request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
