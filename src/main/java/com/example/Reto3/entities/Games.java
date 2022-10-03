package com.example.Reto3.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name ="games")
public class Games {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String developer;
    private Integer year;


    /*
    @OneToMany(cascade  = {CascadeType.PERSIST},mappedBy = "client")
    @JsonIgnoreProperties("client")
    private List<Client>
|   */
}
