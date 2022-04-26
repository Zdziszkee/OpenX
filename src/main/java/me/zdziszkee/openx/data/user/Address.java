package me.zdziszkee.openx.data.user;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Address {
    @JsonProperty("geolocation")
    private  GeoLocation geoLocation;
    @JsonProperty
    private  String city;
    @JsonProperty
    private  String street;
    @JsonProperty
    private  int number;
    @JsonProperty
    private  String zipcode;
    
    public Address(GeoLocation geoLocation, String city, String street, int number, String zipcode) {
        
        this.geoLocation = geoLocation;
        this.city = city;
        this.street = street;
        this.number = number;
        this.zipcode = zipcode;
    }
    
    public Address() {
    
    }
    
    @Override
    public String toString() {
        
        return "Address{" + "geoLocation=" + geoLocation + ", city='" + city + '\'' + ", street='" + street + '\'' + ", number=" + number + ", zipcode='" + zipcode + '\'' + '}';
    }
}
