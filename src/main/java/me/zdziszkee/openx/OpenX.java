package me.zdziszkee.openx;

import me.zdziszkee.openx.data.cart.Cart;
import me.zdziszkee.openx.data.product.Product;
import me.zdziszkee.openx.data.user.GeoLocation;
import me.zdziszkee.openx.data.user.User;
import me.zdziszkee.openx.util.CoordinatesUtil;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.util.*;

public class OpenX {
    private static WebClient webClient = null;
    
    static {
        try {
            webClient = WebClient.create("https://fakestoreapi.com/");
            
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
    
    
    }
    
    
    public static void printCategoryValue(String category) {
        
        Map<String, Set<Product>> categoryProductMap = new HashMap<>();
        final List<Product> products = webClient.get().uri("/products").retrieve().bodyToFlux(Product.class).collectList().block();
        
        products.forEach(product -> {
            final String productCategory = product.getCategory();
            categoryProductMap.putIfAbsent(productCategory, new HashSet<>());
            categoryProductMap.get(productCategory).add(product);
        });
        
        final double totalValue = categoryProductMap.get(category).stream().mapToDouble(Product::getPrice).sum();
        System.out.println("Total " + category + "value: " + totalValue);
        
    }
    
    public static void printTotalValue(String category) {
        
        final Flux<Product> productFlux = webClient.get().uri("/products").retrieve().bodyToFlux(Product.class);
        final Double totalValue = productFlux.filter(product -> product.getCategory().equals(category)).map(Product::getPrice).reduce(0.0, Double::sum).block();
        System.out.println(totalValue);
    }
    
    public static void highestValueCart() {
        
        final Map<Integer, Cart> carts = webClient.get().uri("/carts").retrieve().bodyToFlux(Cart.class).collectMap(Cart::getId).block();
        final Map<Integer, User> users = webClient.get().uri("/users").retrieve().bodyToFlux(User.class).collectMap(User::getId).block();
        final Map<Integer, Product> products = webClient.get().uri("/products").retrieve().bodyToFlux(Product.class).collectMap(Product::getId).block();
        
        final Optional<Cart> optionalCart = carts.values().stream().max((first, second) -> {
            
            final double sumFirst = first.getProducts().stream().mapToDouble(productInfo -> {
                if (products != null) {
                    return productInfo.getQuantity() * products.get(productInfo.getProductId()).getPrice();
                }
                return 0;
            }).sum();
            
            final double sumSecond = second.getProducts().stream().mapToDouble(productInfo -> {
                if (products != null) {
                    return productInfo.getQuantity() * products.get(productInfo.getProductId()).getPrice();
                }
                return 0;
            }).sum();
            
            
            if (sumFirst > sumSecond) {
                return -1;
            } else if (sumSecond > sumFirst) {
                return 1;
            }
            return 0;
        });
        if (optionalCart.isEmpty()) {
            return;
        }
        
        final Cart mostExpensiveCart = optionalCart.get();
        System.out.println("Most expensive cart" + mostExpensiveCart);
        final User user = users.get(mostExpensiveCart.getUserId());
        System.out.println("User name: " + user.getName());
    }
    
    /*
       User pairs can be picked in n!/2!(n-2)!
     */
    public static void printUsersLivingFurthest() {
        
        final List<User> users = webClient.get().uri("/users").retrieve().bodyToFlux(User.class).collectList().block();
        
        if (users == null) {
            return;
        }
        final Map<User, User> userCombinations = new HashMap<>();
        
        
        for (int i = 0 ; i < users.size() ; i++) {
            for (int j = users.size() - 1 ; j > 0 ; j--) {
                final User first = users.get(i);
                final User second = users.get(j);
                if (first == second) {
                    continue;
                }
                userCombinations.put(first, second);
            }
        }
        final Optional<Map.Entry<User, User>> biggestDistanceUsersOptional = userCombinations.entrySet().stream().max((first, second) -> {
            final GeoLocation firstUserLocation = first.getKey().getAddress().getGeoLocation();
            final GeoLocation secondUserLocation = first.getValue().getAddress().getGeoLocation();
        
            final double firstPairDistance = CoordinatesUtil.distance(firstUserLocation.getLat(), secondUserLocation.getLat(), firstUserLocation.getaLong(), secondUserLocation.getaLong());
        
            final GeoLocation thirdUserLocation = second.getKey().getAddress().getGeoLocation();
            final GeoLocation fourthUserLocation = second.getValue().getAddress().getGeoLocation();
        
            final double secondPairDistance = CoordinatesUtil.distance(thirdUserLocation.getLat(), fourthUserLocation.getLat(), thirdUserLocation.getaLong(), fourthUserLocation.getaLong());
        
            if (firstPairDistance == secondPairDistance) {
                return 0;
            } else if (firstPairDistance < secondPairDistance) {
                return -1;
            }
            return 1;
        });
        if (biggestDistanceUsersOptional.isEmpty())return;
        final Map.Entry<User, User> biggestDistanceUsers = biggestDistanceUsersOptional.get();
        System.out.println("Furthest living users: ");
        System.out.println("User 1: " + biggestDistanceUsers.getKey().toString());
        System.out.println("User 2: " + biggestDistanceUsers.getValue().toString());
    
    }
}
