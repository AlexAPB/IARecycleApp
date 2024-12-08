package com.fatec.recycleapp.model.user.attributes;

public enum AddressType {
    APARTMENT(1),
    HOUSE(2),
    ENTERPRISE(3);

    private final Integer id;

    AddressType(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}
