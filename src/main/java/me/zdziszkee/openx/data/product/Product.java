package me.zdziszkee.openx.data.product;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Product {
    @JsonProperty
    private int id;
    @JsonProperty
    private String title;
    @JsonProperty
    private double price;
    @JsonProperty
    private String description;
    @JsonProperty
    private String category;
    @JsonProperty
    private String image;
    @JsonProperty
    private Rating rating;
    
    public Product(int id, String title, double price, String description, String category, String image, Rating rating) {
        
        this.id = id;
        this.title = title;
        this.price = price;
        this.description = description;
        this.category = category;
        this.image = image;
        this.rating = rating;
    }
    
    public Product() {
    
    }
    
    public int getId() {
        
        return id;
    }
    
    public String getTitle() {
        
        return title;
    }
    
    public String getDescription() {
        
        return description;
    }
    
    public double getPrice() {
        
        return price;
    }
    
    public String getCategory() {
        
        return category;
    }
    
    public String getImage() {
        
        return image;
    }
    
    public Rating getRating() {
        
        return rating;
    }
}
