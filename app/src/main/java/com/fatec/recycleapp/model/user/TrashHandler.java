package com.fatec.recycleapp.model.user;

import com.fatec.recycleapp.model.collect.Collect;
import com.fatec.recycleapp.model.penality.Penality;
import com.fatec.recycleapp.model.user.attributes.Address;
import com.fatec.recycleapp.model.user.attributes.UserType;

import java.util.List;

public class TrashHandler extends User {
    private String cpf;
    private String pix;

    public TrashHandler() {
    }

    public TrashHandler(Integer id, String name, String email, String password, String phone, UserType userType, Double rate, List<Address> addresses, List<Collect> collects, List<Penality> penalities, String cpf, String pix) {
        super(id, name, email, password, phone, userType, rate, addresses, collects, penalities);
        this.cpf = cpf;
        this.pix = pix;
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

    public String getPix() {
        return pix;
    }

    public void setPix(String pix) {
        this.pix = pix;
    }
}
