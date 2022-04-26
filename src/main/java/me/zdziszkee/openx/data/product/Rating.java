package me.zdziszkee.openx.data.product;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Rating {
    @JsonProperty
    private float rate;
    @JsonProperty
    private int count;
    
    public Rating(float rate, int count) {
        
        this.rate = rate;
        this.count = count;
    }
    
    public Rating() {
    
    }
}
