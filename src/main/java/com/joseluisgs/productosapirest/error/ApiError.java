package com.joseluisgs.productosapirest.error;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Setter
@Getter
// Cramos automaticamente getters & setters
public class ApiError {

    // Código del estado del error en la excpeción
    private HttpStatus estado;

    // Formato de la fecha en JSON
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy hh:mm:ss")
    private LocalDateTime fecha;

    // Mensaje de error
    private String mensaje;

}
