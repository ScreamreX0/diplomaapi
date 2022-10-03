package com.example.diplomaapi.entities;

import javax.persistence.*;

@Entity(name = "tokens")
@Table(name = "tokens")
public class TokenEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "token")
    private String token;

    @Column(name = "type")
    private int type;

    @Column(name = "owner")
    private int owner;

    public TokenEntity() {
    }

    public TokenEntity(String token, int type, int owner) {
        this.token = token;
        this.type = type;
        this.owner = owner;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getOwner() {
        return owner;
    }

    public void setOwner(int owner) {
        this.owner = owner;
    }
}

