package com.example.Reto3.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "games")
public class Game implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String developer;
    private int releaseYear;

    public Integer getYear() {
        return releaseYear;
    }

    public void setYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDeveloper() {
        return developer;
    }

    public void setDeveloper(String developer) {
        this.developer = developer;
    }

}



