package com.joseluisgs.productosapirest.controladores;


import com.joseluisgs.productosapirest.modelos.Producto;
import com.joseluisgs.productosapirest.repositorios.ProductoRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
// Indicamos que es un controlador de tipo Rest

@RequestMapping("/api") // Esta va a ser la raiz de donde escuchemos es decir http://localhost/api

@RequiredArgsConstructor
// Si ponemos esta anotación no es necesario el @Autowired, si lo ponemos no pasa nada,
public class ProductoController {


    @Autowired // Realizamos la inyección de dependecias al repositorio, no es necesaria si ponemos la notación @RequiredArgsConstructor de lambok
    private ProductoRepositorio productoRepositorio;


    /**
     * Lista todos los productos
     * @return 404 si no hay productos, 200 y lista de productos si hay uno o más
     */
    @GetMapping("/productos")
    public ResponseEntity<?> obetenerTodos() {
        // Devolvemos todos
        //return productoRepositorio.findAll();

        // Actualizamos con ResponseEntity
        List<Producto> result = productoRepositorio.findAll();
        if (result.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(result);
        }
    }


    /**
     * Obtiene un producto con un id específico
     * @param id id del producto
     * @return 404 si no encuentra el producto, 200 y el producto si lo encuentra
     */
    @GetMapping("/productos/{id}")
    public ResponseEntity<?> obtenerProducto(@PathVariable Long id) {
        // Devolvemos si existe
        //return productoRepositorio.findById(id).orElse(null);

        Producto result = productoRepositorio.findById(id).orElse(null);
        if (result == null)
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.ok(result);
    }


    /**
     * Insertamos un nuevo producto
     * @param nuevo nuevo producto a insertar
     * @return 201 y el producto insertado
     */
    @PostMapping("/productos")
    public ResponseEntity<?> nuevoProducto(@RequestBody Producto nuevo) {
        // Salvamos
        //return productoRepositorio.save(nuevo);

        Producto saved = productoRepositorio.save(nuevo);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    /**
     * Editamos un producto
     * @param editar producto a editar
     * @param id id del producto a editar
     * @return 200 Ok si la edición tiene éxito, 404 si no se encuentra el producto
     */
    @PutMapping("/productos/{id}")
    public ResponseEntity<?> editarProducto(@RequestBody Producto editar, @PathVariable Long id) {
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
        return productoRepositorio.findById(id).map(p -> {
            p.setNombre(editar.getNombre());
            p.setPrecio(editar.getPrecio());
            return ResponseEntity.ok(productoRepositorio.save(p));
        }).orElseGet(() -> {
            return ResponseEntity.notFound().build();
        });

    }

    /**
     * Borra un producto con un id espcífico
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
        productoRepositorio.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
