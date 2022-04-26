package me.zdziszkee.openx.data.user;

import com.fasterxml.jackson.annotation.JsonProperty;


public class Name {
    @JsonProperty
    private  String firstName;
    @JsonProperty
    private  String lastName;
    
    public Name(String firstName, String lastName) {
        
        this.firstName = firstName;
        this.lastName = lastName;
    }
    
    public Name() {
    
    }
    
    @Override
    public String toString() {
        
        return "Name{" + "firstName='" + firstName + '\'' + ", lastName='" + lastName + '\'' + '}';
    }
}
