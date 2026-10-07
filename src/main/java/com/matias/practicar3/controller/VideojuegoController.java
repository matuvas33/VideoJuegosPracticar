package com.matias.practicar3.controller;

import com.matias.practicar3.dto.videojuego.VideojuegoRequired;
import com.matias.practicar3.dto.videojuego.VideojuegoResponse;
import com.matias.practicar3.service.VideojuegoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/videojuegos")
@RequiredArgsConstructor
public class VideojuegoController {

    private final VideojuegoService service;

    @GetMapping
    public ResponseEntity<Page<VideojuegoResponse>>obtenerJuegos(
            @PageableDefault(size = 5,page = 0)Pageable pageable
            ){

        Page<VideojuegoResponse>paginaJuegos = service.obtenerVideojuegos(pageable);

        return ResponseEntity.ok(paginaJuegos);
    }
    @GetMapping("/{id}")
    public ResponseEntity<VideojuegoResponse>obtenerJuegoId(@PathVariable Long id){
        return ResponseEntity.ok((service.obtenerJuegoId(id)));
    }
    @PostMapping
    public ResponseEntity<VideojuegoResponse>crearVideojuegos(@Valid @RequestBody VideojuegoRequired v){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.crearVideojuego(v));
    }
    @PutMapping("/{id}")
    public ResponseEntity<VideojuegoResponse>actualizarVideojuego(@PathVariable Long id,@Valid @RequestBody VideojuegoRequired v){
        return ResponseEntity.ok(service.actualizarVideojuego(id,v));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> borrarJuego(@PathVariable Long id){
        service.borrarJuego(id);
        return ResponseEntity.noContent().build();
    }
}
