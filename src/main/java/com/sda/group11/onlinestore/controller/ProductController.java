package com.sda.group11.onlinestore.controller;

import com.sda.group11.onlinestore.model.Product;
import com.sda.group11.onlinestore.repository.ProductRepository;
import com.sda.group11.onlinestore.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping(ProductController.API_PRODUCT)
public class ProductController {

    public static final String API_PRODUCT = "/api/products";

    @Autowired
    public IProductService productService;

    @Autowired
    public ProductRepository productRepository;

   @PostMapping
    public ResponseEntity<Product> create (@RequestBody Product product) {
       productService.save(product);
      return new ResponseEntity<>(HttpStatus.CREATED);
  }

    @GetMapping
    public ResponseEntity<List<Product>> findAll() {
        return new ResponseEntity<>(productService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> findById(@PathVariable(name = "id") Long id){
        Product product= productService.findById(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete (@PathVariable(name = "id") Long id, Principal principal) {
        principal.getName();
        productService.delete(id);
        return new ResponseEntity<>("product deleted", HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> update(@PathVariable(name = "id") Long id,
                                          @RequestBody Product request, Principal principal) {
        principal.getName();
        Product response = productService.update(id, request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    //    @GetMapping("/search")
//    public ResponseEntity<List<Product>> getProductsByCategory(@RequestParam(name = "category") Category category){
//
//        List<Product> products= productService.findAllProductByCategory(category);
//
//        return new ResponseEntity<>(products, HttpStatus.OK);
//    }

    @GetMapping("/search")
    public ResponseEntity<List<Product>> searchForProducts(@RequestParam(name = "product") String productSearch){
        List<Product> productsSearch= productRepository.searchForProducts(productSearch);
        return new ResponseEntity<>(productsSearch, HttpStatus.OK);
    }
}
