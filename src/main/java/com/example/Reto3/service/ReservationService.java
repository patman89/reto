package com.example.Reto3.service;

import com.example.Reto3.entities.Reservation;
import com.example.Reto3.repository.crudRepository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {
    @Autowired
    private ReservationRepository reservationRepository;

    public List<Reservation> getAll(){
        return  reservationRepository.getAll();
    }

    public Optional<Reservation> getReservation(int id){
        return reservationRepository.getReservation(id);
    }

    public Reservation save(Reservation reservation){
        if(reservation.getId() == null) {
            return reservationRepository.save(reservation);
        }else{
            Optional<Reservation> optionalReservation = reservationRepository.getReservation(reservation.getId());
            if(optionalReservation.isEmpty()){
                return reservationRepository.save(reservation);
            }else{
                return  reservation;
            }
        }
    }

    public Reservation update(Reservation reservation){
        if(reservation.getId() != null){
            Optional<Reservation> optionalReservation = reservationRepository.getReservation(reservation.getId());
            if(!optionalReservation.isPresent()){
                if(reservation.getStartDate()!=null) {
                    optionalReservation.get().setStartDate(reservation.getStartDate());
                }
                if(reservation.getDevolutionDate()!=null) {
                    optionalReservation.get().setDevolutionDate(reservation.getDevolutionDate());
                }
                reservationRepository.save(optionalReservation.get());
                return  optionalReservation.get();
            }else{
                return reservation;
            }
        }else{
            return reservation;
        }
    }

    public boolean delete(int id){
        boolean flag = false;
        Optional<Reservation> optionalReservation = reservationRepository.getReservation(id);
        if(optionalReservation.isPresent()){
            reservationRepository.delete(optionalReservation.get());
            flag = true;
        }
        return flag;
    }
}
