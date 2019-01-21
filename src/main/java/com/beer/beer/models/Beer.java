package com.beer.beer.models;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Beer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;


    @Column(length = 200)
    private String description;

    public Beer(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Beer() {
    }
}
