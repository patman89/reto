package com.example.Reto3.controller;

import com.example.Reto3.entities.Reservation;
import com.example.Reto3.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController()
@RequestMapping("/api/Reservation")
public class ReservationController {
    @Autowired
    private ReservationService reservationService;

    @GetMapping("/all")
    public List<Reservation> getAll(){
        return reservationService.getAll();
    }
    @GetMapping("/{id}")
    public Optional<Reservation> getReservation(@PathVariable("id") int id) {
        return reservationService.getReservation(id);
    }
    @PostMapping("/save")
    public Reservation save(@RequestBody Reservation reservation){
        return reservationService.save(reservation);
    }
    @PutMapping("/update")
    public Reservation update(@RequestBody Reservation reservation) {
        return reservationService.update(reservation);
    }
    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable("id") int id){
        return reservationService.delete(id);
    }
}
