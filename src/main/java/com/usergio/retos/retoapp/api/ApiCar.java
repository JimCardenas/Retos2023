package com.usergio.retos.retoapp.api;

import com.usergio.retos.retoapp.modelo.entidad.Car;
import com.usergio.retos.retoapp.modelo.entidad.Client;
import com.usergio.retos.retoapp.modelo.entidad.Gama;
import com.usergio.retos.retoapp.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

// indica que esta es una clase controlador REST.
@RestController
//  establece la ruta base para todas las solicitudes de esta
//  API de Carros. Todas las solicitudes deben comenzar con "api/Car".
    @RequestMapping("api/Car")
//  permite que esta API de Carros sea accesible desde cualquier origen.
    @CrossOrigin("*")

// es la clase de controlador REST que se encarga de manejar las solicitudes HTTP.
    public class ApiCar {

    // se utiliza para inyectar la dependencia "CarService" en el controlador.
        @Autowired
        private CarService service;

        // es un método GET que devuelve todos los carros existentes.
        @GetMapping("/all")
        public List<Car> getAll(){return service.getAll();}

    // es un método GET que devuelve el carro con el ID especificado.
    @GetMapping("/{id}")
    public Optional<Car> getCar(@PathVariable long id) {
        return service.getFindById(id);
    }
    //  es un método POST que guarda el carro proporcionado en la base de datos.
        @PostMapping("/save")
        public ResponseEntity save(@RequestBody Car car){
            service.save(car);
            return ResponseEntity.status(201).build();
        }

        // es un método PUT que actualiza el carro proporcionado en la base de datos.
        @PutMapping("/update")
        public ResponseEntity update(@RequestBody Car car){
            service.updateCar(car);
            return ResponseEntity.status(201).build();
        }

        // es un método DELETE que elimina el carro con el ID proporcionado de la base de datos.
        @DeleteMapping("/{id}")
        public ResponseEntity delete(@PathVariable long id){
            service.deleteCar(id);
            return ResponseEntity.status(204).build();
        }
    }

