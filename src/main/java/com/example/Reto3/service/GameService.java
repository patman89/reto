package com.example.Reto3.service;

import com.example.Reto3.entities.Game;
import com.example.Reto3.repository.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    public List<Game> getAll(){
        return gameRepository.getAll();
    }
    public Optional<Game> getGame(int id){
        return gameRepository.getGame(id);
    }
    public Game save(Game game){
        if(game.getId()==null){
            return gameRepository.save(game);
        }else{
            Optional<Game> optionalGame = gameRepository.getGame(game.getId());
            if(optionalGame.isPresent()){

                return game;
            }else{
                return gameRepository.save(game);
            }
        }
    }
    public Game update(Game game){
        if(game.getId()!=null){
            Optional<Game> optionalGame = gameRepository.getGame(game.getId());
            if(optionalGame.isPresent()){
                if(game.getName()!=null){
                    optionalGame.get().setName(game.getName());
                }
                if(game.getDeveloper()!=null){
                    optionalGame.get().setDeveloper(game.getDeveloper());
                }
                if(game.getYear()!=null){
                    optionalGame.get().setYear(game.getYear());
                }
                if(game.getDescription()!=null){
                    optionalGame.get().setDescription(game.getDescription());
                }
                if(game.getCategory()!=null){
                    optionalGame.get().setCategory(game.getCategory());
                }
                if(game.getMessages()!=null){
                    optionalGame.get().setMessages(game.getMessages());
                }
                if(game.getReservations()!=null){
                    optionalGame.get().setReservations(game.getReservations());
                }
                gameRepository.save(optionalGame.get());
                return optionalGame.get();
            }else{
                return game;
            }
        }else{
            return game;
        }
    }
    public boolean delete(int id){
        boolean flag=false;
        Optional<Game> repositoryGame = gameRepository.getGame(id);
        if(repositoryGame.isPresent()){
            gameRepository.delete(repositoryGame.get());
            flag=true;
        }
        return flag;

    }


}
