package com.fatec.recycleapp.model.user;

import com.fatec.recycleapp.model.collect.Collect;
import com.fatec.recycleapp.model.penality.Penality;
import com.fatec.recycleapp.model.user.attributes.Address;
import com.fatec.recycleapp.model.user.attributes.UserType;

import java.util.List;

public abstract class User {
    private Integer id;
    private String name;
    private String email;
    private String password;
    private String phone;
    private UserType userType;
    private Double rate;
    private List<Address> addresses;
    private List<Collect> collects;
    private List<Penality> penalities;

    public User() {

    }

    public User(Integer id, String name, String email, String password, String phone, UserType userType, Double rate, List<Address> addresses, List<Collect> collects, List<Penality> penalities) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.userType = userType;
        this.rate = rate;
        this.addresses = addresses;
        this.collects = collects;
        this.penalities = penalities;
    }

    public User(User user) {
        this.id = user.id;
        this.name = user.name;
        this.email = user.email;
        this.password = user.password;
        this.phone = user.phone;
        this.addresses = user.addresses;
        this.collects = user.collects;
        this.userType = user.userType;
        this.rate = user.rate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setCollects(List<Collect> collects) {
        this.collects = collects;
    }

    public List<Collect> getCollects() {
        return collects;
    }

    public void setPenalities(List<Penality> penalities) {
        this.penalities = penalities;
    }

    public List<Penality> getPenalities() {
        return penalities;
    }
}
