package com.example.Reto3.repository.repository;

import com.example.Reto3.entities.Client;
import com.example.Reto3.entities.Reservation;
import com.example.Reto3.entities.custom.CountClient;
import com.example.Reto3.repository.repository.crud.ReservationCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public class ReservationRepository {
    @Autowired
    private ReservationCrudRepository reservationCrudRepository;

    public List<Reservation> getAll() {
        return (List<Reservation>) reservationCrudRepository.findAll();
    }

    public Optional<Reservation> getReservation(int id) {
        return reservationCrudRepository.findById(id);
    }

    public Reservation save(Reservation reservation) {
        return reservationCrudRepository.save(reservation);
    }

    public void delete(Reservation reservation) {
        reservationCrudRepository.delete(reservation);
    }

    public List<Reservation> getReservationByStatus(String status) {
        return reservationCrudRepository.findAllByStatus(status);
    }

    public List<Reservation> getReservationPeriod(Date startDate, Date devolutionDate) {
        return reservationCrudRepository.findAllByStartDateAfterAndStartDateBefore(startDate, devolutionDate);
    }

    public List<CountClient> getTopClient() {
        List<CountClient> countCategories = new ArrayList<>();
        List<Object[]> report = reservationCrudRepository.countTotalReservationByClient();
        for (int i = 0; i < report.size(); i++) {
            Client client = (Client) report.get(i)[0];
            Long amount = (Long) report.get(i)[1];
            CountClient countCategory = new CountClient(amount, client);
            countCategories.add((countCategory));
        }
        return countCategories;
    }
}
