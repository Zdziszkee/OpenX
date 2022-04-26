package me.zdziszkee.openx.data.product;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Product {
    @JsonProperty
    private int id;
    @JsonProperty
    private String title;
    @JsonProperty
    private String description;
    @JsonProperty
    private String category;
    @JsonProperty
    private String image;
    @JsonProperty
    private Rating rating;
    
    public Product(int id, String title, String description, String category, String image, Rating rating) {
        
        this.id = id;
        this.title = title;
        this.description = description;
        this.category = category;
        this.image = image;
        this.rating = rating;
    }
    
    public Product() {
    
    }
}
