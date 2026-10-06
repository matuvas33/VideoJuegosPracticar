package com.matias.practicar3.model;

import com.matias.practicar3.model.enums.EstadoVideojuego;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "videojuegos")
public class Videojuego {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "titulo",nullable = false)
    private String titulo;
    @Column(name = "plataforma",nullable = false)
    private String plataforma;
    @Enumerated(EnumType.STRING)
    @Column(name = "estado",nullable = false)
    private EstadoVideojuego estado;

    public Videojuego(String titulo, String plataforma, EstadoVideojuego estado) {
        this.titulo = titulo;
        this.plataforma = plataforma;
        this.estado = estado;
    }
}
