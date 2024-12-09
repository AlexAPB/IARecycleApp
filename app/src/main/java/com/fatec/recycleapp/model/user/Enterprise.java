package com.fatec.recycleapp.model.user;

import com.fatec.recycleapp.model.collect.Collect;
import com.fatec.recycleapp.model.penality.Penality;
import com.fatec.recycleapp.model.user.attributes.Address;
import com.fatec.recycleapp.model.user.attributes.UserType;

import java.util.List;

public class Enterprise extends User {
    private String legalName; // Razão social
    private String brandName; // Nome fantasia
    private String cnpj;
    private String description;
    private List<User> handlers;

    public Enterprise() {

    }

    public Enterprise(Integer id, String name, String lastName, String email, String password, String phone, UserType userType, Double rate, List<Address> addresses, List<Collect> collects, List<Penality> penalities, String legalName, String brandName, String cnpj) {
        super(id, name, lastName, email, password, phone, userType, rate, addresses, collects, penalities);
        this.legalName = legalName;
        this.brandName = brandName;
        this.cnpj = cnpj;
    }

    public Enterprise(Enterprise user) {
        super(user);
        this.legalName = user.legalName;
        this.brandName = user.brandName;
        this.cnpj = user.cnpj;
        this.handlers = user.handlers;
    }

    public String getLegalName() {
        return legalName;
    }

    public void setLegalName(String legalName) {
        this.legalName = legalName;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public void setHandlers(List<User> handlers) {
        this.handlers = handlers;
    }

    public List<User> getHandlers() {
        return handlers;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
