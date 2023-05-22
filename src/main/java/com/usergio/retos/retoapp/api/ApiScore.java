package com.usergio.retos.retoapp.api;

import com.usergio.retos.retoapp.modelo.entidad.Score;
import com.usergio.retos.retoapp.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Se define con la anotación @RestController
@RestController
// se asigna a la ruta base
@RequestMapping("api/Score")
// Se permite el acceso de cualquier origen a través de
@CrossOrigin("*")


// La clase ApiScore tiene una dependencia del servicio
// ScoreService que se inyecta automáticamente mediante la anotación @Autowired.
public class ApiScore {
    @Autowired
    private ScoreService service;

    // Este método maneja solicitudes HTTP GET en la URL /api/Score/all y
    // devuelve una lista de todas las instancias de Score a través del método getAll() del servicio.
    @GetMapping("/all")
    public List<Score> getAll(){
        return service.getAll();
    }

    // Este método maneja solicitudes HTTP POST en la URL /api/Score/save.
    // El método guarda una nueva instancia de Score a través del método save() del servicio y
    // devuelve un objeto
    // ResponseEntity con un código de estado 201 para indicar que la operación ha sido exitosa.
    @PostMapping("/save")
    public ResponseEntity save(@RequestBody Score score){
        service.save(score);
        return ResponseEntity.status(201).build();
    }
}
