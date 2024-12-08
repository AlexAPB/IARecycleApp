package com.fatec.recycleapp.model.user;

import com.fatec.recycleapp.model.collect.Collect;
import com.fatec.recycleapp.model.penality.Penality;
import com.fatec.recycleapp.model.user.attributes.Address;
import com.fatec.recycleapp.model.user.attributes.UserType;

import java.util.List;

public class TrashProducer extends User {
    private String cpf;

    public TrashProducer() {
        super();
    }

    public TrashProducer(Integer id, String name, String email, String password, String phone, UserType userType, Double rate, List<Address> addresses, List<Collect> collects, List<Penality> penalities, String cpf) {
        super(id, name, email, password, phone, userType, rate, addresses, collects, penalities);
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
