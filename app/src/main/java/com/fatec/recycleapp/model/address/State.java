package com.fatec.recycleapp.model.address;

import com.google.gson.annotations.SerializedName;

public class State {
    private int id;
    @SerializedName("nome")
    private String name;
    @SerializedName("sigla")
    private String acronym;

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

    public String getAcronym() {
        return acronym;
    }
    public void setAcronym(String acronym) {
        this.acronym = acronym;
    }
}
