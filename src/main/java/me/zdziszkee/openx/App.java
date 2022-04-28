package me.zdziszkee.openx;

import me.zdziszkee.openx.data.cart.Cart;
import me.zdziszkee.openx.data.product.Product;
import me.zdziszkee.openx.data.user.User;
import me.zdziszkee.openx.service.CartService;
import me.zdziszkee.openx.service.ProductService;
import me.zdziszkee.openx.service.UserService;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) {
    
        WebClient webClient = WebClient.create("https://fakestoreapi.com/");
      
        final ProductService productService = new ProductService(webClient);
        final UserService userService = new UserService(webClient);
        final CartService cartService = new CartService(webClient,productService,userService);
    
        final Map<Integer, Product> productMap = productService.getProducts();
        final List<Product> products = new ArrayList<>(productMap.values());
    
        System.out.println(productMap);
        System.out.println(productService.getProductCategories(products).toString());
        System.out.println(productService.getTotalCategoryValue(products, "electronics"));
    
    
        final Map<Integer, User> userMap = userService.getUsers();
        final List<User> users = new ArrayList<>(userMap.values());
        
        System.out.println(userService.getUsersLivingFurthest(users));
    
        
        final Map<Integer, Cart> cartMap = cartService.getCarts();
        final List<Cart> carts = new ArrayList<>(cartMap.values());
    
        System.out.println(cartService.getCartOwnerName(carts.stream().findAny().get()));
        System.out.println(cartService.getCartValue(carts.stream().findAny().get()));
        System.out.println(cartService.getHighestValueCart(carts));
    }
}
