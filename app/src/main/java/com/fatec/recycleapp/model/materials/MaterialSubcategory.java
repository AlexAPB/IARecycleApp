package com.fatec.recycleapp.model.materials;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public enum MaterialSubcategory {
    // Papéis
    NEWSPAPER(1, "Jornal", MaterialCategory.PAPER),
    MAGAZINE(2, "Revistas", MaterialCategory.PAPER),
    BOOK(3, "Livros", MaterialCategory.PAPER),
    OFFICE_PAPER(4, "Papel de escritório", MaterialCategory.PAPER),
    KRAFT_PAPER(5, "Papel kraft", MaterialCategory.PAPER),
    WRAPPING_PAPER(6, "Papel de embrulho", MaterialCategory.PAPER),

    // Papelão
    PACKAGING_BOX(30, "Caixas de embalagens", MaterialCategory.CARDBOARD),
    CEREAL_BOX(31, "Caixas de cereais", MaterialCategory.CARDBOARD),
    SHOE_BOX(32, "Embalagens de sapatos", MaterialCategory.CARDBOARD),
    CARDBOARD_TUBE(33, "Tubos de papelão", MaterialCategory.CARDBOARD),

    // Plástico
    PET_BOTTLE(60, "Garrafas PET", MaterialCategory.PLASTIC),
    DETERGENT_BOTTLE(61, "Garrafas de detergente", MaterialCategory.PLASTIC),
    COSMETIC_BOTTLE(62, "Frascos de cosméticos", MaterialCategory.PLASTIC),
    FOOD_JAR(63, "Potes de alimentos", MaterialCategory.PLASTIC),
    CLEANING_CONTAINER(64, "Recipientes de limpeza", MaterialCategory.PLASTIC),
    PLASTIC_BAG(65, "Sacolas plásticas", MaterialCategory.PLASTIC),
    FOOD_WRAPPER(66, "Embalagens de alimentos", MaterialCategory.PLASTIC),
    PLASTIC_FILM(67, "Filmes plásticos", MaterialCategory.PLASTIC),
    CLEANING_WRAPPER(68, "Embalagens de produtos de limpeza", MaterialCategory.PLASTIC),
    DISPOSABLE_PLASTIC_UTENSIL(69, "Utensílios plásticos descartáveis", MaterialCategory.PLASTIC),

    // Vidro
    BEVERAGE_BOTTLE(100, "Garrafas de bebidas", MaterialCategory.GLASS),
    PRESERVE_JAR(101, "Frascos de conservas", MaterialCategory.GLASS),
    CLEANING_JAR(103, "Frascos de produtos de limpeza", MaterialCategory.GLASS),
    WINDOW_GLASS(102, "Vidros de janelas", MaterialCategory.GLASS),
    MIRROR(104, "Espelhos", MaterialCategory.GLASS),

    // Metal
    BEVERAGE_CAN(130, "Latas de bebidas", MaterialCategory.METAL),
    ALUMINUM_FOIL(131, "Folhas de alumínio", MaterialCategory.METAL),
    FOOD_PACKAGE(132, "Embalagens de alimentos", MaterialCategory.METAL),
    FOOD_CAN(133, "Latas de alimentos", MaterialCategory.METAL),
    METAL_CAP(134, "Tampas de metal", MaterialCategory.METAL),
    METAL_KITCHEN_UTENSIL(135, "Utensílios de cozinha metálicos", MaterialCategory.METAL),

    // Eletrônicos e Eletrodomésticos
    MOBILE_AND_TABLET(160, "Celulares e tablets", MaterialCategory.ELECTRONIC),
    COMPUTER_AND_LAPTOP(161, "Computadores e laptops", MaterialCategory.ELECTRONIC),
    TV(162, "Televisores", MaterialCategory.ELECTRONIC),
    GAME_CONSOLE(163, "Consoles de videogame", MaterialCategory.ELECTRONIC),
    FRIDGE(164, "Geladeiras", MaterialCategory.ELECTRONIC),
    STOVE(165, "Fogões", MaterialCategory.ELECTRONIC),
    MICROWAVE(166, "Micro-ondas", MaterialCategory.ELECTRONIC),
    FAN(167, "Ventiladores", MaterialCategory.ELECTRONIC),
    WASHING_MACHINE(168, "Máquinas de lavar", MaterialCategory.ELECTRONIC),
    CD_AND_DVD(169, "CDs e DVDs", MaterialCategory.ELECTRONIC),

    // Têxteis e Vestuários
    T_SHIRT(200, "Camisetas", MaterialCategory.CLOTHING),
    PANTS(201, "Calças", MaterialCategory.CLOTHING),
    DRESS(202, "Vestidos", MaterialCategory.CLOTHING),
    JACKET(203, "Casacos", MaterialCategory.CLOTHING),
    SHEET(204, "Lençóis", MaterialCategory.CLOTHING),
    TOWEL(205, "Toalhas", MaterialCategory.CLOTHING),
    CURTAIN(206, "Cortinas", MaterialCategory.CLOTHING),
    SHOE(207, "Sapatos", MaterialCategory.CLOTHING),

    // Resíduos Orgânicos
    FOOD_WASTE(230, "Restos de comida", MaterialCategory.ORGANIC),
    FRUIT_AND_VEG_PEEL(231, "Cascas de frutas e vegetais", MaterialCategory.ORGANIC),
    COFFEE_GROUNDS(232, "Borra de café", MaterialCategory.ORGANIC),
    DRY_LEAF(233, "Folhas secas", MaterialCategory.ORGANIC),
    SMALL_BRANCH(234, "Galhos pequenos", MaterialCategory.ORGANIC),
    GRASS_CLIPPING(235, "Aparas de grama", MaterialCategory.ORGANIC),

    // Baterias
    RECHARGEABLE_BATTERY(260, "Baterias recarregáveis", MaterialCategory.BATTERY),
    NON_RECHARGEABLE_BATTERY(261, "Baterias não recarregáveis", MaterialCategory.BATTERY),
    COMMON_BATTERY(262, "Pilhas comuns", MaterialCategory.BATTERY),
    SPECIAL_BATTERY(263, "Pilhas especiais", MaterialCategory.BATTERY),

    // Outros Resíduos
    USED_COOKING_OIL(290, "Óleo de Cozinha Usado", MaterialCategory.OTHER),
    EXPIRED_MEDICINE(291, "Medicamentos Vencidos", MaterialCategory.OTHER),
    PAINT(292, "Tintas", MaterialCategory.OTHER),
    SOLVENT(293, "Solventes", MaterialCategory.OTHER),
    PESTICIDE(294, "Pesticidas", MaterialCategory.OTHER),
    SUPPLEMENT(295, "Suplementos", MaterialCategory.OTHER),
    GREASY_PIZZA_BOX(296, "Caixas de pizza com gordura", MaterialCategory.OTHER),

    // Special subcategories
    OTHERS(500, "Outros", null);

    private final Integer id;
    private final String name;
    private final MaterialCategory category;

    MaterialSubcategory(Integer id, String name, MaterialCategory category) {
        this.id = id;
        this.name = name;
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

    public String getName() {
        return name;
    }

    public MaterialCategory getCategory() {
        return category;
    }
}
