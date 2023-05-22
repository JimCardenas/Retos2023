package com.usergio.retos.retoapp.service;

import com.usergio.retos.retoapp.modelo.entidad.Car;
import com.usergio.retos.retoapp.modelo.repositorio.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service // Indica que esta clase es un componente de servicio que puede ser inyectado en otras clases
public class CarService {
    @Autowired // Inyección de dependencia de la clase CarRepository
    private CarRepository repository;
    public List<Car> getAll(){ // Método para obtener todos los carros
        return repository.findAll(); // Utiliza el método findAll() del repositorio para obtener
        // todos los carros
    }
    public Car save(Car car){ // Método para guardar un carro
        return repository.save(car); // Utiliza el método save() del repositorio para guardar el carro
    }
    public Optional<Car> getFindById(Long id){ // Método para obtener un carro por su identificador
        return repository.findById(id); // Utiliza el método findById() del repositorio para obtener el
        // carro con el identificador especificado
    }
    public Car updateCar(Car car){ // Método para actualizar un carro
        Optional<Car> carUpdate = getFindById(car.getIdCar()); // Obtiene el carro a actualizar por su
        // identificador
        if(carUpdate.isPresent()){ // Verifica que el carro a actualizar exista
            carUpdate.get().setName(car.getName()); // Actualiza el nombre del carro
            carUpdate.get().setBrand(car.getBrand()); // Actualiza la marca del carro
            carUpdate.get().setYear(car.getYear()); // Actualiza el año del carro
            carUpdate.get().setDescription(car.getDescription()); // Actualiza la descripción del carro
            //carUpdate.get().setGama(car.getGama()); // Actualiza la gama del carro (comentado)
            return repository.save(carUpdate.get()); // Utiliza el método save() del repositorio para
            // guardar el carro actualizado
        }
        else{ // Si el carro a actualizar no existe
            return car; // Retorna el carro sin actualizar
        }
    }
    public void deleteCar(long id){ // Método para eliminar un carro por su identificador
        repository.deleteById(id); // Utiliza el método deleteById() del repositorio para eliminar el
        // carro con el identificador especificado
    }
}

