package com.fatec.recycleapp.model;

public class Enterprise extends User {
    private String legalName; // Razão social
    private String brandName; // Nome fantasia
    private String cnpj;

    public Enterprise() {

    }

    public Enterprise(Integer id, String name, String email, String password, String phone, UserType userType, String legalName, String brandName, String cnpj) {
        super(id, name, email, password, phone, userType);
        this.legalName = legalName;
        this.brandName = brandName;
        this.cnpj = cnpj;
    }

    public Enterprise(Enterprise user) {
        super(user);
        this.legalName = user.legalName;
        this.brandName = user.brandName;
        this.cnpj = user.cnpj;
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
}
