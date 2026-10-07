package com.matias.practicar3.service;

import com.matias.practicar3.dto.videojuego.VideojuegoRequired;
import com.matias.practicar3.dto.videojuego.VideojuegoResponse;
import com.matias.practicar3.model.Videojuego;
import com.matias.practicar3.model.enums.EstadoVideojuego;
import com.matias.practicar3.repository.VideojuegoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class VideoJuegoServiceTest {

    //Crear el falso repositorio con mock
    @Mock
    private VideojuegoRepository videojuegoRepository;
    //Inyectar el mock dentro de nuestro servicio real
    @InjectMocks
    private VideojuegoService service;

    @Test
    void cuandoIdNoExiste() {

        //Preparar
        //Cuando el metodo encuentre un id 99 devuelva un option vacio
        when(videojuegoRepository.findById(99L)).thenReturn(Optional.empty());

        //Se usa solo cuando se espera un error
        //Comprobar que el codigo falle cuando tiene que fallar
        //El tipo de error que tiene que salir (IllegalArgumentException)
        //El bloque de codigo que JUnit vigilara (()->{})
        assertThrows(IllegalArgumentException.class, () -> {
            service.obtenerJuegoId(99L);
        });
    }

    @Test
    void cuandoIdExiste() {
        //Crear el objeto
        Videojuego v = new Videojuego();
        v.setId(7L);
        v.setTitulo("Prova");
        v.setPlataforma("Prova");
        v.setEstado(EstadoVideojuego.JUGANDO);

        //Preparar
        when(videojuegoRepository.findById(7L)).thenReturn(Optional.of(v));

        VideojuegoResponse resultado = service.obtenerJuegoId(7L);

        //Se usa solo cuando se espera un positivo
        assertEquals(7L, resultado.id());
        assertEquals("Prova", resultado.titulo());
        assertEquals("Prova", resultado.plataforma());
        assertEquals(EstadoVideojuego.JUGANDO, resultado.estado());

    }

    @Test
    void crearJuegoDevuelveCorrectamente() {
        Videojuego v = new Videojuego();
        v.setId(10L);
        v.setTitulo("Prova");
        v.setPlataforma("Prova");
        v.setEstado(EstadoVideojuego.JUGANDO);

        when(videojuegoRepository.save(any(Videojuego.class))).thenReturn(v);

        VideojuegoRequired peticio = new VideojuegoRequired("Prova", "Prova", EstadoVideojuego.JUGANDO);
        VideojuegoResponse videojuego = service.crearVideojuego(peticio);


        assertEquals(10L, videojuego.id());
        assertEquals("Prova", videojuego.titulo());
        assertEquals("Prova", videojuego.plataforma());
        assertEquals(EstadoVideojuego.JUGANDO, videojuego.estado());
    }

    @Test
    void borrarJuegoTest(){
        Videojuego v = new Videojuego();
        v.setId(1L);

        when(videojuegoRepository.findById(1L)).thenReturn(Optional.of(v));

        service.borrarJuego(1L);

        verify(videojuegoRepository).delete(v);
    }
}
