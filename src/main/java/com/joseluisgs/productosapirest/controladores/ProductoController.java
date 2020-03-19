package com.joseluisgs.productosapirest.controladores;


import com.joseluisgs.productosapirest.modelos.Producto;
import com.joseluisgs.productosapirest.repositorios.ProductoRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
     * @return Lista todos los productos
     */
    @GetMapping("/productos")
    public List<Producto> obetenerTodos() {
        // Devolvemos todos
        return productoRepositorio.findAll();
    }


    /**
     * Obtiene un producto con un id específico
     * @param id id del producto
     * @return Producto si lo encuetra o nulo
     */
    @GetMapping("/productos/{id}")
    public Producto obtenerProducto(@PathVariable Long id) {
        // Devolvemos si existe
        return productoRepositorio.findById(id).orElse(null);
    }


    /**
     * Insertamos un nuevo producto
     * @param nuevo nuevo producto a insertar
     * @return producto insertado correctamente
     */
    @PostMapping("/productos")
    public Producto nuevoProducto(@RequestBody Producto nuevo) {
        // Salvamos
        return productoRepositorio.save(nuevo);
    }

    /**
     * Editamos un producto
     * @param editar producto a editar
     * @param id id del producto a editar
     * @return producto editar
     */
    @PutMapping("/productos/{id}")
    public Producto editarProducto(@RequestBody Producto editar, @PathVariable Long id) {
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
    }

    /**
     * Borra un producto con un id espcífico
     * @param id id del producto a borrar
     * @return producto borrado si existe
     */
    @DeleteMapping("/productos/{id}")
    public Producto borrarProducto(@PathVariable Long id) {
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

}
