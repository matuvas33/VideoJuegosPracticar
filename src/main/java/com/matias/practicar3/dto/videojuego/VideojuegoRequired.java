package com.matias.practicar3.dto.videojuego;

import com.matias.practicar3.model.enums.EstadoVideojuego;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record VideojuegoRequired(
        @NotBlank
        String titulo,
        @NotBlank
        String plataforma,
        @NotNull
        EstadoVideojuego estado

) {
}
