package com.example.diplomaapi.entities;

import javax.persistence.*;

@Entity(name = "token_types")
@Table(name = "token_types")
public class TokenTypesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    public TokenTypesEntity() {
    }

    public TokenTypesEntity(Long id, String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
