package com.matias.practicar3.exception;

import com.matias.practicar3.dto.error.MensajeError;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    //Atrapamos la excepcion específica
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<MensajeError> argumentoInvalido(IllegalArgumentException ex){

        //Obtenemos el mensaje enviado
        MensajeError errorBody = new MensajeError(
          "Recurso no encontrado",
                ex.getMessage()
        );

        //Devolver el mensaje
        return ResponseEntity.status(404).body(errorBody);

    }
}
