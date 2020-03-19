package com.joseluisgs.productosapirest.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND) // Nos permite devolver un estado cuando salta la excepción
public class CategoriaNotFoundException extends RuntimeException {

    // Por si debemos serializar
    private static final long serialVersionUID = 43876691117560211L;

    public CategoriaNotFoundException(Long id) {
        super("No se puede encontrar La categoria con la ID: " + id);
    }


}
