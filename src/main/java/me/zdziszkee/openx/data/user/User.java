package me.zdziszkee.openx.data.user;

import com.fasterxml.jackson.annotation.JsonProperty;

public class User {
    @JsonProperty
    private Address address;
    @JsonProperty
    private int id;
    @JsonProperty
    private String email;
    @JsonProperty( "username" )
    private String userName;
    @JsonProperty
    private String password;
    @JsonProperty
    private Name name;
    @JsonProperty
    private String phone;
    @JsonProperty
    private int __v;
    
    public User(Address address, int id, String email, String userName, String password, Name name, String phone, int __v) {
        
        this.address = address;
        this.id = id;
        this.email = email;
        this.userName = userName;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.__v = __v;
    }
    
    public User() {
    
    }
    
    @Override
    public String toString() {
        
        return "User{" + "address=" + address + ", id=" + id + ", email='" + email + '\'' + ", userName='" + userName + '\'' + ", password='" + password + '\'' + ", name=" + name + ", phone='" + phone + '\'' + ", __v=" + __v + '}';
    }
}
