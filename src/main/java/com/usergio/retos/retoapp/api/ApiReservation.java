package com.usergio.retos.retoapp.api;

import com.usergio.retos.retoapp.custom.CountClient;
import com.usergio.retos.retoapp.custom.StatusAmount;
import com.usergio.retos.retoapp.modelo.entidad.Message;
import com.usergio.retos.retoapp.modelo.entidad.Reservation;
import com.usergio.retos.retoapp.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

//  Esta anotación indica que la clase es un controlador que
//  maneja solicitudes HTTP entrantes y produce respuestas HTTP salientes.
@RestController

// Esta anotación indica que todas las solicitudes que
// comienzan con api/Reservation serán manejadas por este controlador.
@RequestMapping("api/Reservation")

// Esta anotación permite que los recursos del servidor se compartan con cualquier origen.
// Es decir, permite que los recursos de este controlador se accedan desde cualquier dominio.
@CrossOrigin("*")

// Esta es la declaración de la clase ApiReservation.
public class ApiReservation {

    // Esta anotación se usa para inyectar una dependencia en la clase.
    // En este caso, se está inyectando una instancia de ReservationService en el controlador.
    @Autowired

    // Se declara una variable de instancia llamada service de tipo ReservationService.
    private ReservationService service;

    // Esta anotación indica que el método maneja solicitudes HTTP GET en la URL /all.
    @GetMapping("/all")

    // Este es un método que devuelve una lista de objetos Reservation.
    // Este método llama al método getAll() del objeto service.
    public List<Reservation> getAll()
    {
        return service.getAll();
    }

    // Esta anotación indica que el método maneja solicitudes HTTP
    // GET en la URL /{id}, donde id es una variable de ruta.
    @GetMapping("/{id}")

    // ste es un método que devuelve un objeto Reservation opcional.
    // Este método llama al método getFindById() del objeto service con el id especificado.
    public Optional<Reservation> getReservation(@PathVariable long id){
        return service.getFindById(id);
    }

    // Esta anotación indica que el método maneja solicitudes HTTP POST en la URL /save.
    @PostMapping("/save")

    //  Este es un método que recibe un objeto Reservation como parámetro en el cuerpo de la solicitud.
    //  Este método llama al método save() del objeto service para guardar una nueva reserva
    //  en la base de datos.
    //  Luego, devuelve una ResponseEntity con el código de estado HTTP 201 (creado) y sin cuerpo.
    public ResponseEntity save(@RequestBody Reservation reservation){
        service.save(reservation);
        return ResponseEntity.status(201).build();
    }

    // Esta anotación indica que el método maneja solicitudes HTTP PUT en la URL /update.
    @PutMapping("/update")

    // Este es un método que recibe un objeto Reservation como parámetro en el cuerpo de la solicitud.
    // Este método llama al método updateReservation() del objeto service para actualizar una reserva
    // existente en la base de datos.
    // Luego, devuelve una ResponseEntity con el código de estado HTTP 201 (creado) y sin cuerpo.
    public ResponseEntity update(@RequestBody Reservation reservation){
        service.updateReservation(reservation);
        return ResponseEntity.status(201).build();
    }

    //  Esta anotación indica que el método maneja solicitudes
    //  HTTP DELETE en la URL /{id}, donde id es una variable de ruta.
    @DeleteMapping("/{id}")

    // Este es un método que recibe como parámetro una variable de tipo long llamada id.
    // Este método llama al método deleteReservation() del objeto service para eliminar una reserva
    // de la base de datos con el id especificado.
    // Luego, devuelve una ResponseEntity con el código de estado HTTP 204 (sin contenido) y sin cuerpo.
    public ResponseEntity delete(@PathVariable long id){
        service.deleteReservation(id);
        return ResponseEntity.status(204).build();
    }

    // ste método maneja solicitudes HTTP GET en la URL /api/Reservation/report-dates/{dateOne}/{dateTwo},
    // donde dateOne y dateTwo son variables de ruta que representan las fechas de inicio y
    // fin de un período de tiempo. El método llama al método getReservationPeriod() del
    // servicio ReservationService y devuelve una lista de objetos Reservation.
    @GetMapping("/report-dates/{dateOne}/{dateTwo}")
    public List<Reservation> getReservationsReportDates(@PathVariable String dateOne,
                                                        @PathVariable String dateTwo){
        return service.getReservationPeriod(dateOne,dateTwo);
    }

    // Este método maneja solicitudes HTTP GET en la URL /api/Reservation/report-status.
    // El método llama al método getReservationByStatusReport() del servicio ReservationService
    // y devuelve un objeto StatusAmount, que contiene la cantidad de reservas en cada estado.
    @GetMapping("/report-status")
    public StatusAmount getReservationStatusReport(){
        return service.getReservationByStatusReport();
    }

    // Este método maneja solicitudes HTTP GET en la URL /api/Reservation/report-clients.
    // El método llama al método getTopClients() del servicio ReservationService y devuelve
    // una lista de objetos CountClient,
    // que contienen información sobre los clientes que han realizado más reservas.
    @GetMapping("/report-clients")
    public List<CountClient> getReservationReportClient(){
        return service.getTopClients();
    }
}
