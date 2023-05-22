package com.usergio.retos.retoapp.service;

import com.usergio.retos.retoapp.modelo.entidad.Score;
import com.usergio.retos.retoapp.modelo.repositorio.ScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScoreService {
    // Inyecta una instancia de ScoreRepository en la clase mediante la anotación @Autowired
    @Autowired
    private ScoreRepository repository;

    // Retorna una lista de todos los Scores almacenados en el repositorio
    public List<Score> getAll(){
        return repository.findAll();
    }
    // Guarda un nuevo Score en el repositorio y lo retorna
    public Score save(Score score){
        return repository.save(score);
    }
}
