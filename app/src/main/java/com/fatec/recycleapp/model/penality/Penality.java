package com.fatec.recycleapp.model.penality;

import com.fatec.recycleapp.model.user.User;

import java.time.LocalDateTime;

public class Penality {
    private Integer id;
    private User user;
    private LocalDateTime date;
    private PenalityType type;
    private PenalityReason reason;
    private String description;

    public Penality() {
    }

    public Penality(Integer id, User user, LocalDateTime date, PenalityType type, PenalityReason reason, String description) {
        this.id = id;
        this.user = user;
        this.date = date;
        this.type = type;
        this.reason = reason;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public PenalityType getType() {
        return type;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setType(PenalityType type) {
        this.type = type;
    }

    public PenalityReason getReason() {
        return reason;
    }

    public void setReason(PenalityReason reason) {
        this.reason = reason;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
