package com.fatec.recycleapp.model.penality;

public enum PenalityType {
    WARNING(1),
    REDUCED_ACCESS(2),
    TEMPORARY_BAN(3),
    PERMANENT_BAN(4);

    private Integer id;

    PenalityType(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}
