package com.example.Reto3.controller;

import com.example.Reto3.entities.Reservation;
import com.example.Reto3.entities.custom.CountClient;
import com.example.Reto3.entities.custom.StatusAmount;
import com.example.Reto3.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController()
@RequestMapping("/api/Reservation")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class ReservationController {
    @Autowired
    private ReservationService reservationService;

    @GetMapping("/all")
    public List<Reservation> getAll() {
        return reservationService.getAll();
    }

    /*
    @GetMapping("/{id}")
    public Optional<Reservation> getReservation(@PathVariable("id") int id) {
        return reservationService.getReservation(id);
    }
*/
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/save")
    public Reservation save(@RequestBody Reservation reservation) {
        return reservationService.save(reservation);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping("/update")
    public Reservation update(@RequestBody Reservation reservation) {
        return reservationService.update(reservation);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable("id") int id) {
        return reservationService.delete(id);
    }

    @GetMapping("/report-status")
    public StatusAmount getReservationDescriptionAmount() {
        return reservationService.getStatusReport();
    }

    @GetMapping("/report-clients")
    public List<CountClient> getCountClient() {
        return reservationService.getTopClient();
    }

    @GetMapping("report-dates/{startDate}/{devolutionDate}")
    public List<Reservation> getReservationReport(@PathVariable("startDate") String startDateString, @PathVariable("devolutionDate") String devolutionDateString) {
        return reservationService.getReservationPeriod(startDateString, devolutionDateString);
    }

}
