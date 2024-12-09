package com.fatec.recycleapp.model.user;

import com.fatec.recycleapp.model.collect.Collect;
import com.fatec.recycleapp.model.penality.Penality;
import com.fatec.recycleapp.model.user.attributes.Address;
import com.fatec.recycleapp.model.user.attributes.UserGender;
import com.fatec.recycleapp.model.user.attributes.UserType;

import java.util.List;

public class TrashProducer extends User {
    private String birth;
    private String cpf;
    private UserGender gender;

    public TrashProducer() {
        super();
    }

    public TrashProducer(Integer id, String name, String lastName, String email, String password, String phone, UserType userType, Double rate, List<Address> addresses, List<Collect> collects, List<Penality> penalities, String cpf) {
        super(id, name, lastName, email, password, phone, userType, rate, addresses, collects, penalities);
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

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public UserGender getGender() {
        return gender;
    }

    public void setGender(UserGender gender) {
        this.gender = gender;
    }
}
