package com.example.Reto3.repository.crudRepository;


import com.example.Reto3.entities.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class GameRepository {

    @Autowired
    private GameCrudRepository clientCrudRepository;

    public List<Game> getAll(){
        return (List<Game>) clientCrudRepository.findAll();
    }
    public Optional<Game> getGame(int id){
        return clientCrudRepository.findById(id);
    }
    public Game save(Game c){
        return clientCrudRepository.save(c);
    }
    public void delete(Game c){
        clientCrudRepository.delete(c);
    }

}
