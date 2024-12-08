package com.fatec.recycleapp.model.materials;

public enum MaterialCategory {
    PAPER(1, "Papéis"),
    CARDBOARD(2, "Papelão"),
    PLASTIC(3, "Plásticos"),
    GLASS(4, "Vidros"),
    METAL(5, "Metais"),
    ELECTRONIC(6, "Eletrônicos e eletrodomésticos"),
    CLOTHING(7, "Têxteis e vestuários"),
    ORGANIC(8, "Resíduos orgânicos"),
    BATTERY(9, "Baterias"),
    OTHER(10, "Outros");

    private final Integer id;
    private final String name;

    MaterialCategory(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public static MaterialCategory fromId(Integer id) {
        for(MaterialCategory materialCategory : MaterialCategory.values()) {
            if(materialCategory.getId().equals(id))
                return materialCategory;
        }

        throw new IllegalArgumentException("Invalid material category id: " + id);
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
