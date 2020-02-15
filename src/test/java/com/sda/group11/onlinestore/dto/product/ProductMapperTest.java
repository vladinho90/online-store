package com.sda.group11.onlinestore.dto.product;

import com.sda.group11.onlinestore.model.enums.Category;
import com.sda.group11.onlinestore.UnitTest;
import com.sda.group11.onlinestore.model.Product;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class ProductMapperTest extends UnitTest {

    private ProductMapper mapper;

    @BeforeEach
    public void before(){
        mapper = new ProductMapper();
    }

    @Test
    void givenProduct_whenToDtoIsCalled_thenProductDtoIsCreatedCorrectly() {

       //given
        Product actual = new Product();
        actual.setId(1L);
        actual.setPictureURL("client-server.jpeg");
        actual.setPrice(BigDecimal.valueOf(530));
        actual.setStock(false);
        actual.setUnitsInStock(0);
        actual.setTitle("Nike AF1 Shadow");
        actual.setCategory(Category.SHOES);

       //when
        ProductResponse expected = mapper.toDto(actual);

       //then
        assertThat(expected.getId()).isEqualTo(actual.getId());
        assertThat(expected.getPictureURL()).isEqualTo(actual.getPictureURL());
        assertThat(expected.getPrice()).isEqualTo(actual.getPrice());
        assertThat(expected.getUnitsInStock()).isEqualTo(actual.getUnitsInStock());
        assertThat(expected.isStock()).isEqualTo(actual.isStock());
        Assertions.assertThat(expected.getCategory()).isEqualTo(actual.getCategory());
        assertThat(expected.getTitle()).isEqualTo(actual.getTitle());
    }

    @Test
    void givenProductDto_whenToEntityIsCalled_thenProductIsCreatedCorrectly() {
        //given
        ProductRequest expected = new ProductRequest();
        expected.setCategory(Category.SHOES);
        expected.setPictureURL("client-server.jpeg");
        expected.setPrice(BigDecimal.valueOf(350));
        expected.setStock(true);
        expected.setTitle("Air Max");
        expected.setUnitsInStock(5);

        //when
        Product actual = mapper.toEntity(expected);

        //then
        assertThat(actual.getCategory()).isEqualTo(expected.getCategory());
        assertThat(actual.getPictureURL()).isEqualTo(expected.getPictureURL());
        assertThat(actual.getPrice()).isEqualTo(expected.getPrice());
        assertThat(actual.isStock()).isEqualTo(expected.isStock());
        assertThat(actual.getTitle()).isEqualTo(expected.getTitle());
        assertThat(actual.getUnitsInStock()).isEqualTo(expected.getUnitsInStock());
    }
}