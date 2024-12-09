package com.fatec.recycleapp.model.address;

import com.google.gson.annotations.SerializedName;

public class Cep {
    private String cep;

    @SerializedName("logradouro")
    private String street;

    @SerializedName("complemento")
    private String complement;

    @SerializedName("unidade")
    private String unit;

    @SerializedName("bairro")
    private String neighborhood;

    @SerializedName("localidade")
    private String city;

    @SerializedName("uf")
    private String stateAcronym;

    @SerializedName("estado")
    private String state;

    @SerializedName("regiao")
    private String region;
    private String ibge;
    private String gia;
    private String ddd;
    private String siafi;

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStateAcronym() {
        return stateAcronym;
    }

    public void setStateAcronym(String stateAcronym) {
        this.stateAcronym = stateAcronym;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getIbge() {
        return ibge;
    }

    public void setIbge(String ibge) {
        this.ibge = ibge;
    }

    public String getGia() {
        return gia;
    }

    public void setGia(String gia) {
        this.gia = gia;
    }

    public String getDdd() {
        return ddd;
    }

    public void setDdd(String ddd) {
        this.ddd = ddd;
    }

    public String getSiafi() {
        return siafi;
    }

    public void setSiafi(String siafi) {
        this.siafi = siafi;
    }
}
