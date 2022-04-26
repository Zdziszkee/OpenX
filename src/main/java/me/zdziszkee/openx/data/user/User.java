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
    
    public Address getAddress() {
        
        return address;
    }
    
    public int getId() {
        
        return id;
    }
    
    public String getEmail() {
        
        return email;
    }
    
    public String getUserName() {
        
        return userName;
    }
    
    public String getPassword() {
        
        return password;
    }
    
    public Name getName() {
        
        return name;
    }
    
    public String getPhone() {
        
        return phone;
    }
    
    public int get__v() {
        
        return __v;
    }
    
    @Override
    public String toString() {
        
        return "User{" + "address=" + address + ", id=" + id + ", email='" + email + '\'' + ", userName='" + userName + '\'' + ", password='" + password + '\'' + ", name=" + name + ", phone='" + phone + '\'' + ", __v=" + __v + '}';
    }
}
