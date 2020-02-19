package com.sda.group11.onlinestore.repository;


import com.sda.group11.onlinestore.model.Product;
import com.sda.group11.onlinestore.model.enums.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAllByCategory(Category category);
    @Query(nativeQuery = true, value = "select category from products group by category")
    List<Category> findAllCategory();
    //http://localhost:9000/api/products/search?product=ful
    //@Query(nativeQuery = true, value = "select * from Products p where p.title=':product'")
    @Query(nativeQuery = true, value = "select * from Products p where p.title like %:product%")
    List<Product> searchForProducts(@Param("product") String product);
}
