package com.fatec.recycleapp.model.user.attributes;

public enum UserType {
    TRASH_PRODUCER(1),
    TRASH_HANDLER(2),
    ENTERPRISE(3);

    private final Integer id;

    UserType(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}
