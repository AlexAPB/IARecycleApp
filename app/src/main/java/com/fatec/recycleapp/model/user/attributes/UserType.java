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

    public static UserType fromId(Integer id) {
        for (UserType type : values()) {
            if (type.getId().equals(id)) {
                return type;
            }
        }

        throw new IllegalArgumentException("Invalid id: " + id);
    }
}
