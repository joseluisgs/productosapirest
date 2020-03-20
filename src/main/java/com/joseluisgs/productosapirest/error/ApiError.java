package com.joseluisgs.productosapirest.error;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Setter
@Getter
@RequiredArgsConstructor // Requere argumentos el constructor
// Cramos automaticamente getters & setters
public class ApiError {

    // Código del estado del error en la excpeción y no puede sr nulo
    @NonNull
    private HttpStatus estado;

    // Formato de la fecha en JSON
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy hh:mm:ss")
    private LocalDateTime fecha = LocalDateTime.now();

    // Mensaje de error y no puede se nulo
    @NonNull
    private String mensaje;

}
