package me.zdziszkee.openx.data.user;

import com.fasterxml.jackson.annotation.JsonProperty;
public class GeoLocation {
    @JsonProperty
    private  float lat;
    @JsonProperty("long")
    private  float aLong;
    
    public GeoLocation(float lat, float aLong) {
        
        this.lat = lat;
        this.aLong = aLong;
    }
    
    public GeoLocation() {
    
    }
    
    @Override
    public String toString() {
        
        return "GeoLocation{" + "lat=" + lat + ", aLong=" + aLong + '}';
    }
}
