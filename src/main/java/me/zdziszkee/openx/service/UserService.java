package me.zdziszkee.openx.service;

import me.zdziszkee.openx.data.user.GeoLocation;
import me.zdziszkee.openx.data.user.User;
import me.zdziszkee.openx.util.CoordinatesUtil;
import me.zdziszkee.openx.util.Pair;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.*;

public class UserService {
    private final WebClient webClient;
    
    public UserService(WebClient webClient) {
        
        this.webClient = webClient;
    }
    
    public Map<Integer, User> getUsers() {
        
        final Map<Integer, User> users = webClient.get().uri("/users").retrieve().bodyToFlux(User.class).collectMap(User::getId).block();
        if (users == null) {
            return new HashMap<>();
        }
        return users;
    }
    
    
    protected Set<Pair<User, User>> getUserCombinations(List<User> users) {
        
        final Set<Pair<User, User>> userCombinations = new HashSet<>();
        
        if (users == null) {
            return userCombinations;
        }
        
        for (int i = 0 ; i < users.size() ; i++) {
            final User first = users.get(i);
            for (int j = i + 1 ; j < users.size() ; j++) {
                final User second = users.get(j);
                if (first == null || second == null) {
                    continue;
                }
                if (first != second) {
                    userCombinations.add(new Pair<>(first, second));
                }
            }
        }
        return userCombinations;
    }
    
    /*
       User pairs can be picked in n!/2!(n-2)!
     */
    public Pair<User, User> getUsersLivingFurthest(List<User> users) {
        
        final Set<Pair<User, User>> userCombinations = getUserCombinations(users);
        
        
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
