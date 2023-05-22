package com.usergio.retos.retoapp.service;

import com.usergio.retos.retoapp.custom.CountClient;
import com.usergio.retos.retoapp.custom.StatusAmount;
import com.usergio.retos.retoapp.modelo.entidad.Client;
import com.usergio.retos.retoapp.modelo.entidad.Reservation;
import com.usergio.retos.retoapp.modelo.repositorio.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service   // anotación que indica que la clase es un servicio de la aplicación
public class ReservationService {

    @Autowired  // anotación que permite la inyección de dependencias
    private ReservationRepository repository;   // declaración y asignación de una instancia de ReservationRepository

    public List<Reservation> getAll(){   // método que devuelve una lista de todas las reservas
        return repository.findAll();   // se invoca el método findAll() del objeto repository
    }

    public Reservation save(Reservation reservation){   // método que guarda una reserva
        return repository.save(reservation);   // se invoca el método save() del objeto repository
    }

    public Optional<Reservation> getFindById(long id){   // método que devuelve una reserva por su id
        return repository.findById(id);   // se invoca el método findById() del objeto repository
    }

    public Reservation updateReservation(Reservation reservation){   // método que actualiza una reserva
        Optional<Reservation> reservationUpdate = getFindById(reservation.getIdReservation());   // se obtiene la reserva a actualizar
        if(reservationUpdate.isPresent()){   // si la reserva existe
            reservationUpdate.get().setStartDate(reservation.getStartDate());   // se actualiza la fecha de inicio
            reservationUpdate.get().setDevolutionDate(reservation.getDevolutionDate());   // se actualiza la fecha de devolución
            reservationUpdate.get().setStatus(reservation.getStatus());   // se actualiza el estado de la reserva
            reservationUpdate.get().setCar(reservation.getCar());   // se actualiza el vehículo asociado a la reserva
            reservationUpdate.get().setClient(reservation.getClient());   // se actualiza el cliente asociado a la reserva
            return repository.save(reservationUpdate.get());   // se guarda la reserva actualizada
        }
        else{   // si la reserva no existe
            return reservation;   // se devuelve la reserva original
        }
    }

    public void deleteReservation(long id){   // método que elimina una reserva por su id
        repository.deleteById(id);   // se invoca el método deleteById() del objeto repository
    }

    public List<Reservation> getReservationPeriod(String dateA,String dateB){   // método que devuelve una lista de reservas en un período determinado
        SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd");   // instancia de SimpleDateFormat para parsear fechas
        Date a = new Date();   // fecha a
        Date b = new Date();   // fecha b
        try {   // se intenta parsear las fechas
            a = parser.parse(dateA);   // se parsea la fecha a
            b = parser.parse(dateB);   // se parsea la fecha b
        } catch (ParseException e) {   // si ocurre un error al parsear
            e.printStackTrace();   // se imprime la traza de la excepción
        }
        if(a.before(b)){   // si la fecha a es anterior a la fecha b
            return repository.findAllByStartDateAfterAndStartDateBefore(a,b);   // se invoca el método findAllByStartDateAfterAndStartDateBefore() del objeto repository
        }
        else{   // si la fecha b es anterior o igual a la fecha a
            return new ArrayList<>();   // se devuelve una lista vacía
        }
    }
    public StatusAmount getReservationByStatusReport(){
        // Obtiene todas las reservaciones completadas y canceladas del repositorio
        List<Reservation> completed = repository.findAllByStatus("completed");
        List<Reservation> cancelled = repository.findAllByStatus("cancelled");
        // Crea una nueva instancia de StatusAmount utilizando la clase builder y retorna su resultado
        return  StatusAmount.builder()
                .completed(completed.size())
                .cancelled(cancelled.size())
                .build();
    }

    public List<CountClient> getTopClients(){
        // Crea una nueva lista de CountClient para almacenar los resultados
        List<CountClient> res = new ArrayList<>();
        // Obtiene un informe de la cantidad total de reservaciones por cliente desde el repositorio
        List<Object[]> report = repository.countTotalReservationByClient();
        // Itera sobre cada fila del informe y agrega un nuevo objeto CountClient a la lista de resultados
        for(int i=0;i<report.size();i++){
            res.add(new CountClient((Long)report.get(i)[1],(Client)report.get(i)[0]));
        }
        // Retorna la lista de resultados
        return res;
    }

}
