package com.example.Reto3.controller;

import com.example.Reto3.entities.Game;
import com.example.Reto3.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Game")
public class GameController {

    @Autowired
    private GameService clientService;

    @GetMapping("/all")
    public List<Game> getAll(){
        return clientService.getAll();
    }
    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Game save(@RequestBody Game c){
        return clientService.save(c);
    }
}
