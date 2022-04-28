package me.zdziszkee.openx.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.WebClient;

class ProductServiceTest {
    
    private WebClient webClient;
    private ProductService productService;
    
    @BeforeEach
    void setUp() {
        
        webClient = WebClient.create("https://fakestoreapi.com/");
        productService = new ProductService(webClient);
    }
    
    @Test
    void shouldReturnedMapSizeBeBiggerThanZero() {
        Assertions.assertTrue(productService.getProducts().size() > 0);
        
    }
    
    @Test
    void shouldReturnEmptySetIfGivenNull() {
        Assertions.assertNull(productService.getProductCategories(null));
    }
    
    @Test
    void shouldReturnZeroIfGivenNull() {
        Assertions.assertEquals(productService.getTotalCategoryValue(null,null),0.0);
    }
}