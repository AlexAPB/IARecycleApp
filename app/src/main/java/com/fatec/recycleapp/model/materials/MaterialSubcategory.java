package com.fatec.recycleapp.model.materials;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public enum MaterialSubcategory {
    //Special subcategories
    OTHERS(1, null),

    //Metals


    ;

    private final Integer id;
    private final MaterialCategory category;

    MaterialSubcategory(Integer id, MaterialCategory category) {
        this.id = id;
        this.category = category;
    }

    public static List<MaterialSubcategory> fromCategory(MaterialCategory materialCategory) {
        List<MaterialSubcategory> subcategories = new ArrayList<>();

        for(MaterialSubcategory materialSubcategory : MaterialSubcategory.values()) {
            if(materialSubcategory.getCategory() == materialCategory || materialSubcategory.getCategory() == null)
                subcategories.add(materialSubcategory);
        }

        return subcategories;
    }

    public MaterialSubcategory fromId(Integer id) {
        for(MaterialSubcategory materialSubcategory : MaterialSubcategory.values()) {
            if(materialSubcategory.getId().equals(id))
                return materialSubcategory;
        }

        throw new IllegalArgumentException("Invalid material subcategory id: " + id);
    }

    public Integer getId() {
        return id;
    }

    public MaterialCategory getCategory() {
        return category;
    }
}
