package com.fatec.recycleapp.model.address;

import com.google.gson.annotations.SerializedName;

public class City {
    private int id;
    @SerializedName("nome")
    private String name;

    public City() {

    }

    public City(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
