package com.usergio.retos.retoapp.service;

import com.usergio.retos.retoapp.modelo.entidad.Client;
import com.usergio.retos.retoapp.modelo.repositorio.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {
    @Autowired
    private ClientRepository repository;
    // Método para obtener todos los clientes
    public List<Client> getAll(){
        return repository.findAll();
    }
    // Método para guardar un cliente
    public Client save(Client client){
        return repository.save(client);
    }
    // Método para obtener un cliente por su ID
    public Optional<Client> getFindById(long id){
        return repository.findById(id);
    }
    // Método para actualizar los datos de un cliente
    public Client updateClient(Client client){
        Optional<Client> clientUpdate = getFindById(client.getIdClient());
        if(clientUpdate.isPresent()){
            // Actualizar los atributos del cliente con los valores nuevos
            clientUpdate.get().setEmail(client.getEmail());
            clientUpdate.get().setPassword(client.getPassword());
            clientUpdate.get().setName(client.getName());
            clientUpdate.get().setAge(client.getAge());
            return repository.save(clientUpdate.get());
        }
        else{
            return client;
        }
    }
    // Método para eliminar un cliente por su ID
    public void deleteClient(long id){
        repository.deleteById(id);
    }
}
