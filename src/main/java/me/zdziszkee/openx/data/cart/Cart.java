package me.zdziszkee.openx.data.cart;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.List;

public class Cart {
    @JsonProperty
    private int id;
    @JsonProperty
    private int userId;
    @JsonProperty
    private LocalDateTime date;
    @JsonProperty
    private List<ProductInfo> products;
    @JsonProperty
    private int __v;
    
    public Cart(int id, int userId, LocalDateTime date, List<ProductInfo> products, int __v) {
        
        this.id = id;
        this.userId = userId;
        this.date = date;
        this.products = products;
        this.__v = __v;
    }
    
    public Cart() {
    
    }
    
    @Override
    public String toString() {
        
        return "Cart{" + "id=" + id + ", userId=" + userId + ", date=" + date + ", products=" + products + ", __v=" + __v + '}';
    }
}
