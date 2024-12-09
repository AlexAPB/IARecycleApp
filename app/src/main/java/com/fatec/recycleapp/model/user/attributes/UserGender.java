package com.fatec.recycleapp.model.user.attributes;

public enum UserGender {
    MALE(1),
    FEMALE(2);

    private final int id;

    UserGender(int id) {
        this.id = id;
    }

    public static UserGender fromId(int id) {
        for (UserGender gender : values()) {
            if (gender.getId() == id) {
                return gender;
            }
        }

        throw new IllegalArgumentException("Invalid id: " + id);
    }

    public static UserGender fromString(String gender) {
        if(gender.equals("Masculino")){
            return MALE;
        }

        if(gender.equals("Feminino")){
            return FEMALE;
        }

        throw new IllegalArgumentException("Invalid gender: " + gender);
    }

    public int getId() {
        return id;
    }
}
