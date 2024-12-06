package com.fatec.recycleapp.model.bot;

public enum BotCategory {
    ALUMINIUM_FOIL(1, "Folha de alumínio"),
    BATTERY(2, "Bateria"),
    ALUMINIUM_BLISTER_PACK(3, "Blister de alumínio"),
    CARDED_BLISTER_PACK(4, "Blister com cartão"),
    OTHER_PLASTIC_BOTTLE(5, "Outra garrafa de plástico"),
    CLEAR_PLASTIC_BOTTLE(6, "Garrafa plástica transparente"),
    GLASS_BOTTLE(7, "Garrafa de vidro"),
    PLASTIC_BOTTLE_CAP(8, "Tampa plástica"),
    METAL_BOTTLE_CAP(9, "Tampa de metal"),
    BROKEN_GLASS(10, "Vidro quebrado"),
    FOOD_CAN(11, "Lata de alimentos"),
    AEROSOL(12, "Aerossol"),
    DRINK_CAN(13, "Lata de bebida"),
    TOILET_TUBE(14, "Tubo de papel higiênico"),
    OTHER_CARTON(15, "Outro tipo de papelão"),
    EGG_CARTON(16, "Cartela de ovos"),
    DRINK_CARTON(17, "Caixa de bebida"),
    CORRUGATED_CARTON(18, "Caixa de papelão ondulado"),
    MEAL_CARTON(19, "Caixa de refeição"),
    PIZZA_BOX(20, "Caixa de pizza"),
    PAPER_CUP(21, "Copo de papel"),
    DISPOSABLE_PLASTIC_CUP(22, "Copo plástico descartável"),
    FOAM_CUP(23, "Copo de espuma"),
    GLASS_CUP(24, "Copo de vidro"),
    OTHER_PLASTIC_CUP(25, "Outro copo de plástico"),
    FOOD_WASTE(26, "Resíduo alimentar"),
    GLASS_JAR(27, "Pote de vidro"),
    PLASTIC_LID(28, "Tampa plástica"),
    METAL_LID(29, "Tampa de metal"),
    OTHER_PLASTIC(30, "Outro plástico"),
    MAGAZINE_PAPER(31, "Papel de revista"),
    TISSUES(32, "Lenços"),
    WRAPPING_PAPER(33, "Papel de embrulho"),
    NORMAL_PAPER(34, "Papel comum"),
    PAPER_BAG(35, "Saco de papel"),
    PLASTIFIED_PAPER_BAG(36, "Saco de papel plastificado"),
    PLASTIC_FILM(37, "Filme plástico"),
    SIX_PACK_RINGS(38, "Anéis de seis unidades"),
    GARBAGE_BAG(39, "Saco de lixo"),
    OTHER_PLASTIC_WRAPPER(40, "Outro tipo de embalagem plástica"),
    SINGLE_USE_CARRIER_BAG(41, "Sacola plástica descartável"),
    POLYPROPYLENE_BAG(42, "Saco de polipropileno"),
    CRISP_PACKET(43, "Pacote de salgadinho"),
    SPREAD_TUB(44, "Pote de creme"),
    TUPPERWARE(45, "Tupperware"),
    DISPOSABLE_FOOD_CONTAINER(46, "Embalagem descartável para alimentos"),
    FOAM_FOOD_CONTAINER(47, "Embalagem de espuma para alimentos"),
    OTHER_PLASTIC_CONTAINER(48, "Outro recipiente de plástico"),
    PLASTIC_GLOOVES(49, "Luvas plásticas"),
    PLASTIC_UTENSILS(50, "Talheres plásticos"),
    POP_TAB(51, "Lacre de lata"),
    ROPE_AND_STRINGS(52, "Corda e barbante"),
    SCRAP_METAL(53, "Sucata metálica"),
    SHOE(54, "Sapato"),
    SQUEEZABLE_TUBE(55, "Tubo flexível"),
    PLASTIC_STRAW(56, "Canudo plástico"),
    PAPER_STRAW(57, "Canudo de papel"),
    STYROFOAM_PIECE(58, "Peça de isopor"),
    UNLABELED_LITTER(59, "Lixo sem rótulo"),
    CIGARETTE(60, "Cigarro");

    private final int id;
    private final String name;

    BotCategory(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
