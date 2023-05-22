package com.usergio.retos.retoapp.service;

import com.usergio.retos.retoapp.modelo.entidad.Gama;
import com.usergio.retos.retoapp.modelo.repositorio.GamaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GamaService {
    /**
     * Metodo getAll listo los registros de la tabla gama
     * @return List Objeto Gama
     */
    @Autowired  // Inyección de dependencia de GamaRepository

    private GamaRepository repository; // Creación de un objeto de tipo GamaRepository

    public List<Gama> getAll(){ // Método que devuelve una lista de objetos de tipo Gama
        return repository.findAll(); // Retorna todos los objetos Gama almacenados en la base de datos
    }

    public Gama save (Gama gama){ // Método que guarda un objeto de tipo Gama en la base de datos
        return repository.save(gama);
    }

    /**
     * Método para encontrar un objeto por id(primary key) tipo long
     * @param id (primary key)
     * @return Optional<Gama>
     */
    public Optional<Gama> getFindById(Long id){ // Método que encuentra un objeto Gama por su id
        return repository.findById(id);
    }
    public Gama updateGama(Gama gama){ // Método que actualiza un objeto de tipo Gama en la base de datos
        Optional<Gama> gamaUpdate = getFindById(gama.getIdGama()); // Busca el objeto a actualizar por su id
        if(gamaUpdate.isPresent()){ // Si el objeto existe en la base de datos
            gamaUpdate.get().setDescription(gama.getDescription()); // Actualiza la descripción del objeto
            gamaUpdate.get().setName(gama.getName()); // Actualiza el nombre del objeto
            return repository.save(gamaUpdate.get()); // Retorna el objeto actualizado guardado en la base de datos
        }
        else{ // Si el objeto no existe en la base de datos
            return gama; // Retorna el objeto original sin cambios
        }
    }
    public void deleteGama(long id){ // Método que elimina un objeto de tipo Gama por su id
        repository.deleteById(id);
    }
}
