package com.usergio.retos.retoapp.service;

import com.usergio.retos.retoapp.modelo.entidad.Gama;
import jakarta.annotation.Resource;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional
class GamaServiceTest {
    @Resource
    GamaService gamaService;

    @Test
    @DisplayName("Prueba Guardar Gama")
    void save() {
         Gama gama = Gama.builder()
                .name("Familiar")
                .description("Auto Familiar")
                .build();
        assertEquals(gama, gamaService.save(gama));
    }
    @Test
    void getAll() {
        Gama gama =  Gama.builder()
                .idGama(1L)
                .name("Familiar")
                .description("Auto Familiar")
                .build();
        List<Gama> gamasexpect = new ArrayList<Gama>();
       // gamasexpect.add(gama);
        List<Gama> gamaAll = gamaService.getAll();
        assertEquals(gamasexpect,gamaAll);
    }

    //@Test
    void updateGama() {
    }

    //@Test
    void deleteGama() {
    }
}