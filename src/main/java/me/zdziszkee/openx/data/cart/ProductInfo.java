package me.zdziszkee.openx.data.cart;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductInfo {
    @JsonProperty
    private int productId;
    @JsonProperty
    private int quantity;
    
    public ProductInfo(int productId, int quantity) {
        
        this.productId = productId;
        this.quantity = quantity;
    }
    
    public ProductInfo() {
    
    }
    
    @Override
    public String toString() {
        
        return "ProductInfo{" + "productId=" + productId + ", quantity=" + quantity + '}';
    }
}
