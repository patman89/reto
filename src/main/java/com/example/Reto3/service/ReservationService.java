package com.example.Reto3.service;

import com.example.Reto3.entities.Reservation;
import com.example.Reto3.entities.custom.CountClient;
import com.example.Reto3.entities.custom.StatusAmount;
import com.example.Reto3.repository.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {
    @Autowired
    private ReservationRepository reservationRepository;

    public List<Reservation> getAll() {
        return reservationRepository.getAll();
    }

    public Optional<Reservation> getReservation(int id) {
        return reservationRepository.getReservation(id);
    }

    public Reservation save(Reservation reservation) {
        if (reservation.getIdReservation() == null) {
            return reservationRepository.save(reservation);
        } else {
            Optional<Reservation> optionalReservation = reservationRepository.getReservation(reservation.getIdReservation());
            if (optionalReservation.isEmpty()) {
                return reservationRepository.save(reservation);
            } else {
                return reservation;
            }
        }
    }

    public Reservation update(Reservation reservation) {
        if (reservation.getIdReservation() != null) {
            Optional<Reservation> optionalReservation = reservationRepository.getReservation(reservation.getIdReservation());
            if (!optionalReservation.isPresent()) {
                if (reservation.getStartDate() != null) {
                    optionalReservation.get().setStartDate(reservation.getStartDate());
                }
                if (reservation.getDevolutionDate() != null) {
                    optionalReservation.get().setDevolutionDate(reservation.getDevolutionDate());
                }
                if (reservation.getStatus() != null) {
                    optionalReservation.get().setStatus(reservation.getStatus());
                }
                if (reservation.getGame() != null) {
                    optionalReservation.get().setGame(reservation.getGame());
                }
                if (reservation.getClient() != null) {
                    optionalReservation.get().setClient(reservation.getClient());
                }
                if (reservation.getScore() != null) {
                    optionalReservation.get().setScore(reservation.getScore());
                }
                reservationRepository.save(optionalReservation.get());
                return optionalReservation.get();
            } else {
                return reservation;
            }
        } else {
            return reservation;
        }
    }

    public boolean delete(int id) {
        boolean flag = false;
        Optional<Reservation> optionalReservation = reservationRepository.getReservation(id);
        if (optionalReservation.isPresent()) {
            reservationRepository.delete(optionalReservation.get());
            flag = true;
        }
        return flag;
    }

    public List<CountClient> getTopClient() {
        return reservationRepository.getTopClient();
    }

    public StatusAmount getStatusReport() {
        List<Reservation> completed = reservationRepository.getReservationByStatus("completed");
        List<Reservation> cancelled = reservationRepository.getReservationByStatus("cancelled");
        StatusAmount statusAmount = new StatusAmount(completed.size(), cancelled.size());
        return statusAmount;
    }

    public List<Reservation> getReservationPeriod(String startDateString, String devolutionDateString) {
        SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = new Date();
        Date devolutionDate = new Date();
        try {
            startDate = parser.parse(startDateString);
            devolutionDate = parser.parse(devolutionDateString);
        } catch (ParseException e) {
        }
        if (startDate.before(devolutionDate)) {
            return reservationRepository.getReservationPeriod(startDate, devolutionDate);
        }
        return new ArrayList<>();
    }
}
