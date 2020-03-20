package com.joseluisgs.productosapirest.error;

import com.fasterxml.jackson.databind.JsonMappingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalControllerAdvice {

    // Producto no encotrado
    @ExceptionHandler(ProductoNotFoundException.class)
    public ResponseEntity<ApiError> handleProductoNoEncontrado(ProductoNotFoundException ex) {
        /*
        ApiError apiError = new ApiError();
        apiError.setEstado(HttpStatus.NOT_FOUND);
        apiError.setFecha(LocalDateTime.now());
        apiError.setMensaje(ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);

         */
        // Aplicamos el nuevo constructor indicado an APIREST
        ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
    }

    // Lista de productos no encontradas
    @ExceptionHandler(ProductosNotFoundException.class)
    public ResponseEntity<ApiError> handleProductosNoEncontrado(ProductosNotFoundException ex) {
        /*
        ApiError apiError = new ApiError();
        apiError.setEstado(HttpStatus.NOT_FOUND);
        apiError.setFecha(LocalDateTime.now());
        apiError.setMensaje(ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);

         */
        // Aplicamos el nuevo constructor indicado an APIREST
        ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
    }


    // Categoría no encotrada
    @ExceptionHandler(CategoriaNotFoundException.class)
    public ResponseEntity<ApiError> handleCategoriaNoEncontrado(CategoriaNotFoundException ex) {
        /*
        ApiError apiError = new ApiError();
        apiError.setEstado(HttpStatus.NOT_FOUND);
        apiError.setFecha(LocalDateTime.now());
        apiError.setMensaje(ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);

         */
        // Aplicamos el nuevo constructor indicado an APIREST
        ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
    }

    @ExceptionHandler(ProductoBadRequestException.class)
    public ResponseEntity<ApiError> handleProductoPeticionIncorrecta(ProductoBadRequestException ex) {
        /*
        ApiError apiError = new ApiError();
        apiError.setEstado(HttpStatus.BAD_REQUEST);
        apiError.setFecha(LocalDateTime.now());
        apiError.setMensaje(ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);

         */
        // Aplicamos el nuevo constructor indicado an APIREST
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
    }


    // Formato de Json a la hora de pasarle datos a la API
    @ExceptionHandler(JsonMappingException.class)
    public ResponseEntity<ApiError> handleJsonMappingException(JsonMappingException ex) {
        /*
        ApiError apiError = new ApiError();
        apiError.setEstado(HttpStatus.BAD_REQUEST);
        apiError.setFecha(LocalDateTime.now());
        apiError.setMensaje(ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);

         */
        // Aplicamos el nuevo constructor indicado an APIREST
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
    }

}
