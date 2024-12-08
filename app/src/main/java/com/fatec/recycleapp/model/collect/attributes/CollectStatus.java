package com.fatec.recycleapp.model.collect.attributes;

public enum CollectStatus {
    CREATED(1),
    PENDING(2),
    ACCEPTED(3),
    PROCESSING(4),
    COMPLETE(5),
    CANCELLED(6);

    private Integer id;

    CollectStatus(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}
