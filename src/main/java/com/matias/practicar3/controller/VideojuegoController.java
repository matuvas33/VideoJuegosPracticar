package com.matias.practicar3.controller;

import com.matias.practicar3.dto.videojuego.VideojuegoRequired;
import com.matias.practicar3.dto.videojuego.VideojuegoResponse;
import com.matias.practicar3.service.VideojuegoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/videojuegos")
@RequiredArgsConstructor
public class VideojuegoController {

    private final VideojuegoService videojuego;

    @GetMapping
    public ResponseEntity<List<VideojuegoResponse>>obtenerJuegos(){
        return ResponseEntity.ok(videojuego.obtenerJuegos());
    }
    @GetMapping("/{id}")
    public ResponseEntity<VideojuegoResponse>obtenerJuegoId(@PathVariable Long id){
        return ResponseEntity.ok((videojuego.obtenerJuegoId(id)));
    }
    @PostMapping
    public ResponseEntity<VideojuegoResponse>crearVideojuegos(@Valid @RequestBody VideojuegoRequired v){
        return ResponseEntity.status(HttpStatus.CREATED).body(videojuego.crearVideojuego(v));
    }
    @PutMapping("/{id}")
    public ResponseEntity<VideojuegoResponse>actualizarVideojuego(@PathVariable Long id,@Valid @RequestBody VideojuegoRequired v){
        return ResponseEntity.ok(videojuego.actualizarVideojuego(id,v));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> borrarJuego(@PathVariable Long id){
        videojuego.borrarJuego(id);
        return ResponseEntity.noContent().build();
    }
}
