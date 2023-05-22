package com.usergio.retos.retoapp.service;

import com.usergio.retos.retoapp.modelo.entidad.Message;
import com.usergio.retos.retoapp.modelo.repositorio.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MessageService {
    @Autowired  // Inyección de dependencia de MessageRepository
    private MessageRepository repository; // Creación de un objeto de tipo MessageRepository

    public List<Message> getAll(){ // Método que devuelve una lista de objetos de tipo Message
        return repository.findAll(); // Retorna todos los objetos Message almacenados en la base de datos
    }

    public Message save(Message message){ // Método que guarda un objeto de tipo Message en la base de datos
        return repository.save(message);
    }

    public Optional<Message> findById(long id){ // Método que encuentra un objeto Message por su id
        return repository.findById(id);
    }

    public Message updateMessage(Message message){ // Método que actualiza un objeto de tipo Message
        // en la base de datos
        Optional<Message>messageUpdate = findById(message.getIdMessage()); // Busca el objeto a actualizar
        // por su id
        if(messageUpdate.isPresent()){ // Si el objeto existe en la base de datos
            messageUpdate.get().setMessageText(message.getMessageText()); // Actualiza el texto del mensaje
            messageUpdate.get().setCar(message.getCar()); // Actualiza el objeto Car asociado al mensaje
            messageUpdate.get().setClient(message.getClient()); // Actualiza el objeto Client asociado
            // al mensaje
            return repository.save(messageUpdate.get()); // Retorna el objeto actualizado guardado
            // en la base de datos
        }
        else{ // Si el objeto no existe en la base de datos
            return message; // Retorna el objeto original sin cambios
        }
    }

    public void deleteMessage(long id){ // Método que elimina un objeto de tipo Message por su id
        repository.deleteById(id);
    }
}
