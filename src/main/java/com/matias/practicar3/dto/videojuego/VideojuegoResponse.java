package com.matias.practicar3.dto.videojuego;

import com.matias.practicar3.model.enums.EstadoVideojuego;

public record VideojuegoResponse(
        Long id,
        String titulo,
        String plataforma,
        EstadoVideojuego estado
) {
}
