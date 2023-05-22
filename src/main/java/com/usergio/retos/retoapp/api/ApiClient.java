package com.usergio.retos.retoapp.api;

import com.usergio.retos.retoapp.modelo.entidad.Client;
import com.usergio.retos.retoapp.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

//  indica que esta es una clase controlador REST.
@RestController

// establece la ruta base para todas las solicitudes de
// esta API de Clientes. Todas las solicitudes deben comenzar con "api/Client".
@RequestMapping("api/Client")

// permite que esta API de Clientes sea accesible desde cualquier origen.
@CrossOrigin("*")

// es la clase de controlador REST que se encarga de manejar las solicitudes HTTP.
public class ApiClient {

    // se utiliza para inyectar la dependencia "ClientService" en el controlador.
    @Autowired
    private ClientService service;
    // es un método GET que devuelve todos los clientes existentes.
    @GetMapping("/all")
    public List<Client> getAll(){
        return service.getAll();
    }

    //  es un método GET que devuelve el cliente con el ID especificado.
    @GetMapping("/{id}")
    public Optional<Client> getClient(@PathVariable long id){
        return service.getFindById(id);
    }

    // es un método POST que guarda el cliente proporcionado en la base de datos.
    @PostMapping("/save")
    public ResponseEntity save(@RequestBody Client client){
        service.save(client);
        return ResponseEntity.status(201).build();
    }

    // es un método PUT que actualiza el cliente proporcionado en la base de datos.
    @PutMapping("/update")
    public ResponseEntity update(@RequestBody Client client){
        service.updateClient(client);
        return ResponseEntity.status(201).build();
    }
    //  es un método DELETE que elimina el cliente con el ID proporcionado de la base de datos.
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable long id){
        service.deleteClient(id);
        return ResponseEntity.status(204).build();
    }
}
