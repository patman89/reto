package com.example.Reto3.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name ="reservation")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String startDate;
    private String devolutionDate;

    private String status = "created";
    @ManyToOne()
    @JoinColumn(name = "game")
    @JsonIgnoreProperties("reservations")
    private Game game;
    @ManyToOne()
    @JoinColumn(name = "idClient")
    @JsonIgnoreProperties({"reservations","messages"})
    private Client client;


    private String score;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getDevolutionDate() {
        return devolutionDate;
    }

    public void setDevolutionDate(String devolutionDate) {
        this.devolutionDate = devolutionDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }
}
