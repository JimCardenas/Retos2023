package com.usergio.retos.retoapp.modelo.repositorio;

import com.usergio.retos.retoapp.modelo.entidad.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation,Long> {
    //select clientId,count(clientId) as total from reservation group by clientid orde by total desc,
    @Query("select c.client,count(c.client) from Reservation as c group by c.client order by count(c.client) DESC")
    public List<Object[]> countTotalReservationByClient();
    public List<Reservation> findAllByStartDateAfterAndStartDateBefore(Date dateOne,
                                                                       Date dateTwo);
    public List<Reservation> findAllByStatus(String status);



}
