package com.example.Reto3.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name ="reservation")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String startDate;
    private String devolutionDate;

    @ManyToOne()
    @JoinColumn(name = "idGame")
    @JsonIgnoreProperties("game")
    private Game game;
}
