package com.fatec.recycleapp.model.collect.attributes;

import android.graphics.Bitmap;

import com.fatec.recycleapp.model.materials.MaterialSubcategory;

public class CollectMaterial {
    private Integer id;
    private Bitmap image;
    private Integer quantity;
    private Double weight;
    private String description;
    private MaterialSubcategory subcategory;

    public CollectMaterial() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public MaterialSubcategory getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(MaterialSubcategory subcategory) {
        this.subcategory = subcategory;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }
}
