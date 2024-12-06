package com.fatec.recycleapp.model.user;

import com.fatec.recycleapp.model.user.attributes.UserType;

public class TrashProducer extends User {
    private String cpf;

    public TrashProducer() {
    }

    public TrashProducer(Integer id, String name, String email, String password, String phone, UserType userType, String cpf) {
        super(id, name, email, password, phone, userType);
        this.cpf = cpf;
    }

    public TrashProducer(TrashProducer user) {
        super(user);
        this.cpf = user.cpf;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
