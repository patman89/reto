package com.example.Reto3.repository.repository.crud;

import com.example.Reto3.entities.Reservation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface ReservationCrudRepository extends CrudRepository<Reservation,Integer> {
    @Query("SELECT c.client, COUNT(c.client) FROM Reservation AS c group by c.client ORDER BY COUNT(c.client) desc")
    public List<Object[]> countTotalReservationByClient();


    public List<Reservation> findAllByStatus(String status);
    public List<Reservation> findAllByStartDateAfterAndStartDateBefore(Date startDate, Date devolutionDate);

}
