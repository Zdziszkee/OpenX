package me.zdziszkee.openx;

import me.zdziszkee.openx.data.cart.Cart;
import me.zdziszkee.openx.data.product.Product;
import me.zdziszkee.openx.data.user.GeoLocation;
import me.zdziszkee.openx.data.user.User;
import me.zdziszkee.openx.util.CoordinatesUtil;
import me.zdziszkee.openx.util.Pair;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.*;
import java.util.stream.Collectors;

public class OpenX {
    private static WebClient webClient = null;
    
    static {
        try {
            webClient = WebClient.create("https://fakestoreapi.com/");
            
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
    
    public Map<Integer, User> getUsers() {
        
        final Map<Integer, User> users = webClient.get().uri("/users").retrieve().bodyToFlux(User.class).collectMap(User::getId).block();
        if (users == null) {
            return new HashMap<>();
        }
        return users;
    }
    
    public Map<Integer, Product> getProducts() {
        
        final Map<Integer, Product> products = webClient.get().uri("/products").retrieve().bodyToFlux(Product.class).collectMap(Product::getId).block();
        if (products == null) {
            return new HashMap<>();
        }
        return products;
    }
    
    public Map<Integer, Cart> getCarts() {
        
        final Map<Integer, Cart> carts = webClient.get().uri("/carts").retrieve().bodyToFlux(Cart.class).collectMap(Cart::getId).block();
        if (carts == null) {
            return new HashMap<>();
        }
        return carts;
    }
    
    public Set<String> getProductCategories() {
        
        return getProducts().values().stream().map(Product::getCategory).collect(Collectors.toSet());
    }
    
    public double getTotalCategoryValue(String category) {
        
        return getProducts().values().stream().filter(product -> product.getCategory().equals(category)).map(Product::getPrice).reduce(0.0, Double::sum);
    }
    
    public Cart getHighestValueCart() {
        
        
        final Optional<Cart> optionalCart = getCarts().values().stream().max((first, second) -> {
            
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
        final Map<Integer, Product> products = getProducts();
        return cart.getProducts().stream().mapToDouble(productInfo -> productInfo.getQuantity() * products.get(productInfo.getProductId()).getPrice()).sum();
    }
    
    public String getCartOwnerName(Cart cart) {
        
        if (cart == null) {
            return null;
        }
        
        final int userId = cart.getUserId();
        final Map<Integer, User> users = getUsers();
        final User user = users.get(userId);
        
        if (user == null) {
            return null;
        }
        return user.getName().toString();
    }
    
    /*
       User pairs can be picked in n!/2!(n-2)!
     */
    public  Pair<User, User> getUsersLivingFurthest() {
    
        final List<User> users = new ArrayList<>(getUsers().values());
    
        final Set<Pair<User, User>> userCombinations = new HashSet<>();
        
        
        for (int i = 0 ; i < users.size() ; i++) {
            final User first = users.get(i);
            for (int j = i + 1 ; j < users.size() ; j++) {
                final User second = users.get(j);
                if (first != second) {
                    userCombinations.add(new Pair<>(first, second));
                }
            }
        }

        
        final Optional<Pair<User, User>> biggestDistanceUsersOptional = userCombinations.stream().max((firstUserPair, secondUserPair) -> {
            final GeoLocation firstUserLocation = firstUserPair.getFirst().getAddress().getGeoLocation();
            final GeoLocation secondUserLocation = firstUserPair.getSecond().getAddress().getGeoLocation();
            
            final double firstPairDistance = CoordinatesUtil.distance(firstUserLocation.getLat(), secondUserLocation.getLat(), firstUserLocation.getaLong(), secondUserLocation.getaLong());
            
            final GeoLocation thirdUserLocation = secondUserPair.getFirst().getAddress().getGeoLocation();
            final GeoLocation fourthUserLocation = secondUserPair.getSecond().getAddress().getGeoLocation();
            
            final double secondPairDistance = CoordinatesUtil.distance(thirdUserLocation.getLat(), fourthUserLocation.getLat(), thirdUserLocation.getaLong(), fourthUserLocation.getaLong());
            
            if (firstPairDistance == secondPairDistance) {
                return 0;
            } else if (firstPairDistance < secondPairDistance) {
                return -1;
            }
            return 1;
        });
        if (biggestDistanceUsersOptional.isEmpty()) {
            return null;
        }
        return biggestDistanceUsersOptional.get();
        
    }
}
