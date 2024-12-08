package com.fatec.recycleapp.model.penality;

public enum PenalityReason {
    UNSUITABLE_MATERIAL(1),
    COLLECTION_DENIAL(2),
    NO_SHOW(3),
    INAPPROPRIATE_LANGUAGE(4),
    VIOLATION_OF_TERMS(5);

    private Integer id;

    PenalityReason(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

}
