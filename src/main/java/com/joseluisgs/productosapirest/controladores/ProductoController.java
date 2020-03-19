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
// Si ponemos esto no es necesario el @Autowired
public class ProductoController {


    //@Autowired // Realizamos la inyección de dependecias al repositorio
    private ProductoRepositorio productoRepositorio;


    /**
     * Lista todos los productos
     * @return Lista todos los productos
     */
    @GetMapping("/productos")
    public List<Producto> obetenerTodos() {
        // Vamos a modificar este código
        return null;
    }


    /**
     * Obtiene un producto con un id específico
     * @param id id del producto
     * @return Producto si lo encuetra o nulo
     */
    @GetMapping("/productos/{id}")
    public Producto obtenerProducto(@PathVariable Long id) {
        // Vamos a modificar este código
        return null;
    }


    /**
     * Insertamos un nuevo producto
     * @param nuevo nuevo producto a insertar
     * @return producto insertado correctamente
     */
    @PostMapping("/productos")
    public Producto nuevoProducto(@RequestBody Producto nuevo) {
        // Vamos a modificar este código
        return null;
    }

    /**
     * Editamos un producto
     * @param editar producto a editar
     * @param id id del producto a editar
     * @return producto editar
     */
    @PutMapping("/productos/{id}")
    public Producto editarProducto(@RequestBody Producto editar, @PathVariable Long id) {
        // Vamos a modificar este código
        return null;
    }

    /**
     * Borra un producto con un id espcífico
     * @param id id del producto a borrar
     * @return producto borra
     */
    @DeleteMapping("/productos/{id}")
    public Producto borrarProducto(@PathVariable Long id) {
        // Vamos a modificar este código
        return null;
    }

}
