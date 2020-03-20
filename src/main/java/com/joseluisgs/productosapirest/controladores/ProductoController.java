package com.joseluisgs.productosapirest.controladores;


import com.joseluisgs.productosapirest.dto.CreateProductoDTO;
import com.joseluisgs.productosapirest.dto.ProductoDTO;
import com.joseluisgs.productosapirest.dto.coverter.ProductoDTOConverter;
import com.joseluisgs.productosapirest.error.CategoriaNotFoundException;
import com.joseluisgs.productosapirest.error.ProductoBadRequestException;
import com.joseluisgs.productosapirest.error.ProductoNotFoundException;
import com.joseluisgs.productosapirest.modelos.Categoria;
import com.joseluisgs.productosapirest.modelos.Producto;
import com.joseluisgs.productosapirest.repositorios.CategoriaRepositorio;
import com.joseluisgs.productosapirest.repositorios.ProductoRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
// Indicamos que es un controlador de tipo Rest

@RequestMapping("/api") // Esta va a ser la raiz de donde escuchemos es decir http://localhost/api

@RequiredArgsConstructor
// Si ponemos esta anotación no es necesario el @Autowired, si lo ponemos no pasa nada,
public class ProductoController {


    @Autowired
    // Realizamos la inyección de dependecias al repositorio, no es necesaria si ponemos la notación @RequiredArgsConstructor de lambok
    private final ProductoRepositorio productoRepositorio;
    private final ProductoDTOConverter productoDTOConverter; // No es necesario el @Autowired por la notacion, pero pon el final
    private final CategoriaRepositorio categoriaRepositorio; // No es necesario el @Autowired por la notacion, pero pon el final

    /**
     * Lista todos los productos
     *
     * @return 404 si no hay productos, 200 y lista de productos si hay uno o más
     */

    @CrossOrigin(origins = "http://localhost:8888") //
    // Indicamos sobre que puerto u orignes dejamos que actue (navegador) En nuestro caso no habría problemas
    // Pero es bueno tenerlo en cuenta si tenemos en otro serviror una app en angular o similar
    // Pero es inviable para API consumida por muchos terceros. // Debes probar con un cliente desde ese puerto
    // Mejor hacer un filtro, ver MyConfig.java
    @GetMapping("/productos")
    public ResponseEntity<?> obetenerTodos() {
        // Devolvemos todos
        //return productoRepositorio.findAll();

        // Actualizamos con ResponseEntity
        List<Producto> result = productoRepositorio.findAll();
        if (result.isEmpty()) {
            //return ResponseEntity.notFound().build();
            // Con excepciones propias
            // throw new ProductosNotFoundException();
            // Con response Status
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No hay productos registrados");
        } else {
            //return ResponseEntity.ok(result);
            // Hacemos el DTO para añadir la categoría
            // Devolvemos solo los datos que nos interesan
            //Si no prueba a comentar esto y descomentar lo anterior y verás como obtienes demasaida info de categoria de manera anidada

            List<ProductoDTO> dtoList = result.stream().map(productoDTOConverter::convertToDto)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(dtoList);
        }
    }


    /**
     * Obtiene un producto con un id específico
     *
     * @param id id del producto
     * @return 404 si no encuentra el producto, 200 y el producto si lo encuentra
     */
    @GetMapping("/productos/{id}")
    //public ResponseEntity<?> obtenerProducto(@PathVariable Long id) {
    public Producto obtenerProducto(@PathVariable Long id) {
        // Devolvemos si existe
        //return productoRepositorio.findById(id).orElse(null);
        /*
        Producto result = productoRepositorio.findById(id).orElse(null);
        if (result == null)
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.ok(result);

         */

        // Con manejo de excepciones, por eso devuelve un producto y cambia el tipo del método ResponseEntity<?> -> Producto
        //return productoRepositorio.findById(id)
        //       .orElseThrow(() -> new ProductoNotFoundException(id));

        // Excepciones con ResponseStatus
        try {
            return productoRepositorio.findById(id)
                    .orElseThrow(() -> new ProductoNotFoundException(id));
        } catch (ProductoNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }


    /**
     * Insertamos un nuevo producto
     *
     * @param nuevo nuevo producto a insertar
     * @return 201 y el producto insertado
     */
    @PostMapping("/productos")
    //public ResponseEntity<?> nuevoProducto(@RequestBody Producto nuevo) {
    public ResponseEntity<?> nuevoProducto(@RequestBody CreateProductoDTO nuevo) {
        // Salvamos
        //return productoRepositorio.save(nuevo);

        //Producto saved = productoRepositorio.save(nuevo);
        //return ResponseEntity.status(HttpStatus.CREATED).body(saved);

        // Usando el DTO -- primera forma (manual)

        /*
        Producto nuevoProducto = new Producto();
        nuevoProducto.setNombre(nuevo.getNombre());
        nuevoProducto.setPrecio(nuevo.getPrecio());
        Categoria categoria = categoriaRepositorio.findById(nuevo.getCategoriaId()).orElse(null);
        nuevoProducto.setCategoria(categoria);
        return ResponseEntity.status(HttpStatus.CREATED).body(productoRepositorio.save(nuevoProducto));
        */

        // Usando el DTO --segunda forma como un metodo del conversor y mapeo (esto último no es necesario)

        // Con manejo de excepciones
        // Response Status con el try/catch
        try {
            if (nuevo.getNombre().isEmpty())
                throw new ProductoBadRequestException("Nombre", "Nombre vacío");
            else if (nuevo.getPrecio() < 0)
                throw new ProductoBadRequestException("Precio", "Precio no puede ser negativo");
            else {
                /**
                 Producto nuevoProducto = productoDTOConverter.convertToProducto(nuevo); // Esto si es interesante
                 return categoriaRepositorio.findById(nuevoProducto.getCategoria().getId())
                 .map(o -> {
                 return ResponseEntity.status(HttpStatus.CREATED).body(productoRepositorio.save(nuevoProducto));
                 }).orElseThrow(() -> new CategoriaNotFoundException(nuevoProducto.getCategoria().getId()));
                 */

                Producto nuevoProducto = productoDTOConverter.convertToProducto(nuevo);
                Categoria categoria = categoriaRepositorio.findById(nuevo.getCategoriaId()).orElseThrow(() -> new CategoriaNotFoundException(nuevo.getCategoriaId()));
                nuevoProducto.setCategoria(categoria);
                return ResponseEntity.status(HttpStatus.CREATED).body(productoRepositorio.save(nuevoProducto));

            }
        } catch (ProductoNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }

    }


    /**
     * Editamos un producto
     *
     * @param editar producto a editar
     * @param id     id del producto a editar
     * @return 200 Ok si la edición tiene éxito, 404 si no se encuentra el producto
     */
    @PutMapping("/productos/{id}")
    //public ResponseEntity<?> editarProducto(@RequestBody Producto editar, @PathVariable Long id) {
    public Producto editarProducto(@RequestBody Producto editar, @PathVariable Long id) {
        /*
        // Si existe
        if (productoRepositorio.existsById(id)) {
            // Le ponemos el id que teníamos
            editar.setId(id);
            // Salvamos
            return productoRepositorio.save(editar);
        } else {
            // Devolvemos null
            return null;
        }
        */

        // Utilizamos Stream de Java 8 con notacion funcional
        // Podriamos haber cambiado el código anterior
        /*
        return productoRepositorio.findById(id).map(p -> {
            p.setNombre(editar.getNombre());
            p.setPrecio(editar.getPrecio());
            return ResponseEntity.ok(productoRepositorio.save(p));
        }).orElseGet(() -> {
            return ResponseEntity.notFound().build();
        });
        */

        // Con manejo de excepciones, por eso devuelve un producto y cambia el tipo del método ResponseEntity<?> -> Producto

        // Comprobamos que los campos no sean vacios antes o el precio negativo
        if (editar.getNombre().isEmpty())
            throw new ProductoBadRequestException("Nombre", "Nombre vacío");
        else if (editar.getPrecio() <= 0)
            throw new ProductoBadRequestException("Precio", "Precio no puede ser negativo");
        else {
            // Se puede hacer con su asignaciones normales sin usar map, mira nuevo
            return productoRepositorio.findById(id).map(p -> {
                p.setNombre(editar.getNombre());
                p.setPrecio(editar.getPrecio());
                return productoRepositorio.save(p);
            }).orElseThrow(() -> new ProductoNotFoundException(id));
        }

    }

    /**
     * Borra un producto con un id espcífico
     *
     * @param id id del producto a borrar
     * @return Código 204 sin contenido
     */
    @DeleteMapping("/productos/{id}")
    public ResponseEntity<?> borrarProducto(@PathVariable Long id) {
        /*
        // Si existe
        if (productoRepositorio.existsById(id)) {
            // Lo encotramos
            Producto result = productoRepositorio.findById(id).get();
            // Lo borramos
            productoRepositorio.deleteById(id);
            return result;
        } else
            // devolvemos null
            return null;
    }
         */

        // Se puede usar el código anterior incluyendo los RespnseEntity
        //productoRepositorio.deleteById(id);
        //return ResponseEntity.noContent().build();

        // Con manejo de excepciones
        Producto producto = productoRepositorio.findById(id)
                .orElseThrow(() -> new ProductoNotFoundException(id));
        productoRepositorio.delete(producto);
        return ResponseEntity.noContent().build();
    }

        /*
    // Nos los llevamos a GlobalCotrollerAdvice para poder usarlos en distintos controladores

    // Excepciones con HandlerException.
    // En vez de hacer el tratamiento por defecto cuando salta la excepción idncada se viene a este

    // Producto no encotrado
    @ExceptionHandler(ProductoNotFoundException.class)
    public ResponseEntity<ApiError> handleProductoNoEncontrado(ProductoNotFoundException ex) {
        ApiError apiError = new ApiError();
        apiError.setEstado(HttpStatus.NOT_FOUND);
        apiError.setFecha(LocalDateTime.now());
        apiError.setMensaje(ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
    }

    // Lista de productos no encontradas
    @ExceptionHandler(ProductosNotFoundException.class)
    public ResponseEntity<ApiError> handleProductosNoEncontrado(ProductosNotFoundException ex) {
        ApiError apiError = new ApiError();
        apiError.setEstado(HttpStatus.NOT_FOUND);
        apiError.setFecha(LocalDateTime.now());
        apiError.setMensaje(ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
    }



    // Categoría no encotrada
    @ExceptionHandler(CategoriaNotFoundException.class)
    public ResponseEntity<ApiError> handleCategoriaNoEncontrado(CategoriaNotFoundException ex) {
        ApiError apiError = new ApiError();
        apiError.setEstado(HttpStatus.NOT_FOUND);
        apiError.setFecha(LocalDateTime.now());
        apiError.setMensaje(ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
    }

    @ExceptionHandler(ProductoBadRequestException.class)
    public ResponseEntity<ApiError> handleProductoPeticionIncorrecta(ProductoBadRequestException ex) {
        ApiError apiError = new ApiError();
        apiError.setEstado(HttpStatus.NOT_FOUND);
        apiError.setFecha(LocalDateTime.now());
        apiError.setMensaje(ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
    }


    // Formato de Json a la hora de pasarle datos a la API
    @ExceptionHandler(JsonMappingException.class)
    public ResponseEntity<ApiError> handleJsonMappingException(JsonMappingException ex) {
        ApiError apiError = new ApiError();
        apiError.setEstado(HttpStatus.BAD_REQUEST);
        apiError.setFecha(LocalDateTime.now());
        apiError.setMensaje(ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
    }


     */

}
