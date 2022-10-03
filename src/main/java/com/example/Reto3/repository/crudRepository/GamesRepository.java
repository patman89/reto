package com.example.Reto3.repository.crudRepository;

import com.example.Reto3.entities.Games;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class GamesRepository {
    @Autowired
    private GamesCrudRepository gamesCrudRepository;

    public List<Games> getAll(){
        return (List<Games>) gamesCrudRepository.findAll();
    }

    public Optional<Games> getGames(int id){
        return gamesCrudRepository.findById(id);
    }

    public Games save(Games reservation){
        return gamesCrudRepository.save(reservation);
    }

    public void delete(Games reservation){
        gamesCrudRepository.delete(reservation);
    }

}
