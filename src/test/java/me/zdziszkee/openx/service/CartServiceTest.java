package me.zdziszkee.openx.service;

import me.zdziszkee.openx.data.user.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

public class CartServiceTest {
    private WebClient webClient;
    private CartService cartService;
    
    @BeforeEach
    void setUp() {
        
        webClient = WebClient.create("https://fakestoreapi.com/");
        cartService = new CartService(webClient,new ProductService(webClient),new UserService(webClient));
    }
    
    @Test
    void shouldReturnedMapSizeBeBiggerThanZero() {
        Assertions.assertTrue(cartService.getCarts().size() > 0);
    
    }
    
    @Test
    void shouldReturnNullIfGivenListIsEmpty() {
        Assertions.assertNull(cartService.getHighestValueCart(List.of()));
    }
    
    @Test
    void shouldReturnZeroIfGivenCartIsNull() {
        Assertions.assertEquals(cartService.getCartValue(null),0.0);
    }
    
    @Test
    void shouldReturnNullIfGivenCartIsNull() {
    Assertions.assertNull(cartService.getCartOwnerName(null));
    }
}
