package me.zdziszkee.openx.service;

import me.zdziszkee.openx.data.user.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

import static org.mockito.Answers.RETURNS_DEEP_STUBS;

class UserServiceTest {
    private WebClient webClient;
    private UserService userService;
    
    @BeforeEach
    void setUp() {
        
        webClient = WebClient.create("https://fakestoreapi.com/");
        userService = new UserService(webClient);
    }
    
    @Test
    void shouldReturnedMapSizeBeBiggerThanZero() {
        
        Assertions.assertTrue(userService.getUsers().size() > 0);
    }
    
    @Test
    void getUsersLivingFurthest() {
        Assertions.assertNull(userService.getUsersLivingFurthest(null));
    }
    
    @Test
    void getUserCombinations() {
        Assertions.assertEquals(userService.getUserCombinations(List.of(new User(),new User(),new User(),new User())).size(),6);
    }
}