package com.example.Reto3.service;

import com.example.Reto3.entities.Games;
import com.example.Reto3.repository.crudRepository.GamesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GamesService {
    @Autowired
    private GamesRepository gamesRepository;

    public List<Games> getAll(){
        return  gamesRepository.getAll();
    }

    public Optional<Games> getGame(int id){
        return gamesRepository.getGames(id);
    }

    public Games save(Games games){
        if(games.getId() == null) {
            return gamesRepository.save(games);
        }else{
            Optional<Games> optionalGames = gamesRepository.getGames(games.getId());
            if(optionalGames.isEmpty()){
                return gamesRepository.save(games);
            }else{
                return  games;
            }
        }
    }

    public Games update(Games games){
        if(games.getId() != null){
            Optional<Games> optionalGames = gamesRepository.getGames(games.getId());
            if(!optionalGames.isPresent()){
                if(games.getName()!=null) {
                    optionalGames.get().setName(games.getName());
                }
                if(games.getDeveloper()!=null) {
                    optionalGames.get().setDeveloper(games.getDeveloper());
                }
                if(games.getYear()!=null) {
                    optionalGames.get().setYear(games.getYear());
                }
                gamesRepository.save(optionalGames.get());
                return  optionalGames.get();
            }else{
                return games;
            }
        }else{
            return games;
        }
    }

    public boolean delete(int id){
        boolean flag = false;
        Optional<Games> optionalGames = gamesRepository.getGames(id);
        if(optionalGames.isPresent()){
            gamesRepository.delete(optionalGames.get());
            flag = true;
        }
        return flag;
    }
}
