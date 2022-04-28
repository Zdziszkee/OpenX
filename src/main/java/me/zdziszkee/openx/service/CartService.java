package me.zdziszkee.openx.service;

import me.zdziszkee.openx.data.cart.Cart;
import me.zdziszkee.openx.data.product.Product;
import me.zdziszkee.openx.data.user.User;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class CartService {
    private final WebClient webClient;
    private final ProductService productService;
    private final UserService userService;
    public CartService(WebClient webClient, ProductService productService, UserService userService) {
        
        this.webClient = webClient;
        this.productService = productService;
        this.userService = userService;
    }
    
    public Map<Integer, Cart> getCarts() {
        
        final Map<Integer, Cart> carts = webClient.get().uri("/carts").retrieve().bodyToFlux(Cart.class).collectMap(Cart::getId).block();
        if (carts == null) {
            return new HashMap<>();
        }
        return carts;
    }
    
    public Cart getHighestValueCart(List<Cart> carts) {
        
        
        final Optional<Cart> optionalCart = carts.stream().max((first, second) -> {
            
            final double sumFirst = getCartValue(first);
            
            final double sumSecond = getCartValue(second);
            
            
            if (sumFirst > sumSecond) {
                return -1;
            } else if (sumSecond > sumFirst) {
                return 1;
            }
            return 0;
        });
        
        if (optionalCart.isEmpty()) {
            return null;
        }
        
        return optionalCart.get();
    }
    
    public double getCartValue(Cart cart) {
        
        if (cart == null) {
            return 0;
        }
        final Map<Integer, Product> products = productService.getProducts();
        return cart.getProducts().stream().mapToDouble(productInfo -> productInfo.getQuantity() * products.get(productInfo.getProductId()).getPrice()).sum();
    }
    
    public String getCartOwnerName(Cart cart) {
        
        if (cart == null) {
            return null;
        }
        
        final int userId = cart.getUserId();
        final Map<Integer, User> users = userService.getUsers();
        final User user = users.get(userId);
        
        if (user == null) {
            return null;
        }
        return user.getName().toString();
    }
}
