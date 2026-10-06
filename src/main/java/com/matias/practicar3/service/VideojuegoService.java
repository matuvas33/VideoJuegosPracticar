package com.matias.practicar3.service;

import com.matias.practicar3.dto.videojuego.VideojuegoRequired;
import com.matias.practicar3.dto.videojuego.VideojuegoResponse;
import com.matias.practicar3.model.Videojuego;
import com.matias.practicar3.repository.VideojuegoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VideojuegoService {
    private final VideojuegoRepository videojuegoRepository;

    public List<VideojuegoResponse> obtenerJuegos(){
        List<Videojuego> listaJuegos = videojuegoRepository.findAll();

        return listaJuegos.stream()
                .map(this::mapearDTO)
                .toList();
    }

    public VideojuegoResponse obtenerJuegoId(Long id){
        Videojuego juegoEncontrado = videojuegoRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("No se ha encontrado juego con ID " +id));
        return mapearDTO(juegoEncontrado);
    }

    public VideojuegoResponse crearVideojuego(VideojuegoRequired videojuego){
        Videojuego nuevoVideojuego = new Videojuego();

        nuevoVideojuego.setTitulo(videojuego.titulo());
        nuevoVideojuego.setPlataforma(videojuego.plataforma());
        nuevoVideojuego.setEstado(videojuego.estado());

        Videojuego guardarJuego = videojuegoRepository.save(nuevoVideojuego);

        return mapearDTO(guardarJuego);
    }

    public VideojuegoResponse actualizarVideojuego(Long id,VideojuegoRequired juego){
        Videojuego juegoEncontrado = videojuegoRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("No se ha encontrado juego con ID" +id));
        juegoEncontrado.setTitulo(juego.titulo());
        juegoEncontrado.setPlataforma(juego.plataforma());
        juegoEncontrado.setEstado(juego.estado());

        Videojuego guardarActualizacion = videojuegoRepository.save(juegoEncontrado);

        return mapearDTO(guardarActualizacion);
    }

    public void borrarJuego(Long id){
        Videojuego juegoEncontrado = videojuegoRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("No se ha encontrado juego con ID" +id));
        videojuegoRepository.delete(juegoEncontrado);
    }

    private VideojuegoResponse mapearDTO(Videojuego vd){
       return new VideojuegoResponse(
                vd.getId(),
                vd.getTitulo(),
                vd.getPlataforma(),
                vd.getEstado()
        );
    }
}
