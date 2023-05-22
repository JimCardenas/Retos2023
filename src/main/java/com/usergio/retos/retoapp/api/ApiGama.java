package com.usergio.retos.retoapp.api;

import com.usergio.retos.retoapp.modelo.entidad.Gama;
import com.usergio.retos.retoapp.service.GamaService;
import com.usergio.retos.retoapp.util.Mensaje;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

// Anota la clase como un controlador REST.
@RestController

//  Anota la clase con una ruta de solicitud base "/api/Gama".
//  Esto significa que todas las solicitudes a esta API deben comenzar con "/api/Gama".
@RequestMapping("/api/Gama")
// Permite a los clientes solicitar datos desde cualquier origen, lo que es útil para el desarrollo local.
// También puede restringir los orígenes permitidos para mejorar la seguridad en producción.
@CrossOrigin("*")

//  Define la clase controlador.
public class ApiGama {

    // Anota la variable service para inyectar una instancia de GamaService.
    @Autowired

    // Define una variable privada del tipo GamaService para utilizar sus métodos en el controlador.
    private GamaService service;

    // Anota un método para aceptar solicitudes GET a "/api/Gama/all".
    // Este método devuelve una lista de todos los registros Gama almacenados en la base de datos.
    @GetMapping("all")

    // Define un método que devuelve una lista de todos los registros Gama almacenados en la base de datos.
    public List<Gama> getGamaAll() {
        return service.getAll();
    }


    // Anota un método para aceptar solicitudes GET a "/api/Gama/{id}".
    // Este método devuelve un registro Gama específico que coincide con el ID proporcionado en la ruta.
    @GetMapping("/{id}")

    // Define un método que devuelve un objeto ResponseEntity que contiene el registro
    // Gama que coincide con el ID proporcionado en la ruta.
    // Si no se encuentra ningún registro, se devuelve una respuesta de error 404.
    public ResponseEntity getGama(@PathVariable long id) {
        // Busca un registro Gama en la base de datos con el ID proporcionado.
        Optional<Gama> gama = service.getFindById(id);
        // Verifica si se encontró algún registro Gama.
        if (gama.isPresent()) {
            // Devuelve una respuesta 200 OK con el registro Gama encontrado.
            return ResponseEntity.ok(gama.get());
        }
        // Si no se encontró ningún registro Gama.
        else{
            /*
            Mensaje mensaje = new Mensaje(404,
                    "No se encontro Registro id="+id);
            return ResponseEntity.status(404).body(mensaje);

             */
            // Crea un objeto Mensaje utilizando el patrón de diseño Builder para construir un objeto complejo.
            // Este objeto representa un mensaje de error con un código de estado HTTP y un mensaje de texto.
            Mensaje mensaje = Mensaje.builder()
                               .httpCode(404)
                               .mensaje("No se encontro Registro id"+id)
                               .build();
            // Devuelve una respuesta de error 404 con el objeto Mensaje creado.
            return ResponseEntity.status(404).body(mensaje);

        }

    }

    // Anota un método para aceptar solicitudes POST a "/api/Gama/save".
    // Este método guarda un nuevo registro Gama en la base de datos.
    @PostMapping("/save")

    //  Este es el encabezado del método que se ejecutará cuando se haga una solicitud POST a "/save".
    //  El parámetro @RequestBody Gama gama indica que el método espera un objeto JSON de la clase Gama
    //  en el cuerpo de la solicitud HTTP.
    //  El tipo de retorno ResponseEntity se utiliza para devolver una respuesta HTTP personalizada.
    public ResponseEntity saveGama(@RequestBody Gama gama){
        // Este código llama a un método llamado save() en una instancia del servicio, pasándole el objeto
        // Gama que se recibió en la solicitud HTTP POST.
        // Este método probablemente guardará la información del objeto Gama en una base de datos.
        service.save(gama);
        // Este código devuelve una respuesta HTTP con un código de estado 201 (creado) y un cuerpo vacío.
        return ResponseEntity.status(201).build();
    }


    // Este es un anotación de Spring que indica
    // que este método manejará solicitudes HTTP PUT a la ruta "/update".
    @PutMapping("/update")
    // Este es el encabezado del método que se ejecutará cuando se haga una solicitud PUT a "/update".
    // El parámetro @RequestBody Gama gama indica que el método espera un objeto JSON de la clase Gama
    // en el cuerpo de la solicitud HTTP.
    // El tipo de retorno ResponseEntity se utiliza para devolver una respuesta HTTP personalizada.
    public ResponseEntity updateGama(@RequestBody Gama gama){

        //  Este código llama a un método llamado updateGama() en una instancia del servicio, pasándole
        //  el objeto Gama que se recibió en la solicitud HTTP PUT.
        //  Este método probablemente actualizará la información del objeto Gama en una base de datos.
        service.updateGama(gama);

        // Este código devuelve una respuesta HTTP con un código de estado 201 (creado) y un cuerpo vacío.
        return ResponseEntity.status(201).build();
    }
    //  Este es un anotación de Spring que indica que este método manejará solicitudes
    //  HTTP DELETE a la ruta "/{id}", donde {id} es una variable de ruta.
    @DeleteMapping("/{id}")

    //  Este es el encabezado del método que se ejecutará cuando se haga una solicitud DELETE a "/{id}".
    //  El parámetro @PathVariable long id indica que el método espera un parámetro de ruta de tipo long
    //  llamado id.
    //  El tipo de retorno ResponseEntity se utiliza para devolver una respuesta HTTP personalizada.
    public ResponseEntity deleteGama(@PathVariable long id){
        // Este código llama a un método llamado deleteGama() en una instancia del servicio,
        // pasándole el ID del objeto Gama que se va a eliminar de la base de datos.
        service.deleteGama(id);
        // Este código devuelve una respuesta HTTP con un código de estado 204 (sin contenido) y un cuerpo vacío.
        // Esto indica que la operación de eliminación
        // se completó con éxito y que no hay contenido para devolver en la respuesta HTTP.
        return ResponseEntity.status(204).build();
    }
}
