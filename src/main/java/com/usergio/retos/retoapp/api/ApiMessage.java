package com.usergio.retos.retoapp.api;

import com.usergio.retos.retoapp.modelo.entidad.Client;
import com.usergio.retos.retoapp.modelo.entidad.Message;
import com.usergio.retos.retoapp.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

// es una anotación que indica que esta clase es un controlador REST.
@RestController

// es una anotación que indica la URL base de este controlador, que es "api/Message".
// Todas las solicitudes a este controlador deben comenzar con esta URL.
@RequestMapping("api/Message")

// es una anotación que indica que se permiten solicitudes de cualquier origen para este controlador.
@CrossOrigin("*")

// es la definición de la clase Java que contiene los métodos de controlador.
public class ApiMessage {

    // es una anotación de inyección de dependencia que se utiliza para
    // inyectar la dependencia del objeto MessageService en esta clase.
    @Autowired

    // es una declaración de variable que contiene una instancia del objeto MessageService.
    private MessageService service;

    // es una anotación que indica que el método getAll() maneja una solicitud HTTP GET con la URL "/all". Este método devuelve una lista de mensajes.
    //
    //public List<Message> getAll(){...}: es la implementación del método getAll().
    // Este método llama al método getAll() de MessageService y devuelve la lista de mensajes.
    @GetMapping("/all")
    public List<Message> getAll(){
        return service.getAll();
    }


    // es una anotación que indica que el método getMessage() maneja una solicitud HTTP GET con la
    // URL "/{id}". El valor de la variable "id" en la URL se asignará al parámetro long id del método.
    // Este método devuelve un mensaje con el ID especificado.
    @GetMapping("/{id}")

    //  es la implementación del método getMessage(). Este método llama al método findById() de
    //  MessageService con el
    //  ID del mensaje y devuelve el mensaje encontrado o un valor nulo.
    public Optional<Message> getMessage(@PathVariable long id){
        return service.findById(id);
    }

    // es una anotación que indica que el método save() maneja una solicitud HTTP POST con la URL "/save".
    // Este método guarda un mensaje en la base de datos.
    @PostMapping("/save")

    // es la implementación del método save(). Este método llama al método save() de MessageService con
    // el mensaje
    // enviado en la solicitud y devuelve una respuesta HTTP con el código de estado 201 (creado).
    public ResponseEntity save(@RequestBody Message message){
        service.save(message);
        return ResponseEntity.status(201).build();
    }

    // es una anotación que indica que el método update() maneja una solicitud HTTP PUT con la URL "/update".
    // Este método actualiza un mensaje existente en la base de datos.
    @PutMapping("/update")

    // es la implementación del método update(). Este método llama al método updateMessage() de
    // MessageService con el mensaje enviado en la
    // solicitud y devuelve una respuesta HTTP con el código de estado 201 (creado).
    public ResponseEntity update(@RequestBody Message message){
        service.updateMessage(message);
        return ResponseEntity.status(201).build();
    }

    // es una anotación que indica que el método delete() maneja una solicitud HTTP DELETE con la URL
    // "/{id}". El valor de la variable "id" en la URL se asignará al parámetro long id del método.
    // Este método elimina un mensaje con el ID especificado de la base de datos.
    @DeleteMapping("/{id}")

    // Este es un método que recibe como parámetro una variable de tipo long llamada id.
    // La anotación @PathVariable indica que el valor de la variable se extrae de la URL.
    public ResponseEntity delete(@PathVariable long id){
        // e llama al método deleteMessage() del objeto service para eliminar un
        // mensaje de la base de datos con el id especificado.
        service.deleteMessage(id);

        // Este es el resultado de la función. Se devuelve una ResponseEntity con el código de estado HTTP
        // 204 (sin contenido) y sin cuerpo. El código de estado 204 significa que la solicitud se ha
        // completado con éxito, pero no hay contenido para devolver en la respuesta.
        // En este caso, el método delete() no devuelve ningún mensaje en el cuerpo de la respuesta.
        return ResponseEntity.status(204).build();
    }
}
