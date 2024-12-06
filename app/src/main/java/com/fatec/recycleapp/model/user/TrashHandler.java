package com.fatec.recycleapp.model.user;

import com.fatec.recycleapp.model.user.attributes.UserType;

public class TrashHandler extends User {
    private String cpf;

    public TrashHandler() {
    }

    public TrashHandler(Integer id, String name, String email, String password, String phone, UserType userType, String cpf) {
        super(id, name, email, password, phone, userType);
        this.cpf = cpf;
    }

    public TrashHandler(TrashHandler user) {
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
