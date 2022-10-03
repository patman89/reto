package com.example.Reto3.controller;

import com.example.Reto3.entities.Games;
import com.example.Reto3.service.GamesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController()
@RequestMapping("/api/Games")
public class GamesController {
    @Autowired
    private GamesService gamesService;

    @GetMapping("/all")
    public List<Games> getAll(){
        return gamesService.getAll();
    }
    @GetMapping("/{id}")
    public Optional<Games> getGame(@PathVariable("id") int id) {
        return gamesService.getGame(id);
    }
    @PostMapping("/save")
    public Games save(@RequestBody Games games){
        return gamesService.save(games);
    }
    @PutMapping("/update")
    public Games update(@RequestBody Games games) {
        return gamesService.update(games);
    }
    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable("id") int id){
        return gamesService.delete(id);
    }
}
