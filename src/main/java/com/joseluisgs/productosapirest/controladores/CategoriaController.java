package com.joseluisgs.productosapirest.controladores;

import com.joseluisgs.productosapirest.modelos.Categoria;
import com.joseluisgs.productosapirest.repositorios.CategoriaRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
// Indicamos que es un controlador de tipo Rest

@RequestMapping("/api") // Esta va a ser la raiz de donde escuchemos es decir http://localhost/api

@RequiredArgsConstructor
// Si ponemos esta anotación no es necesario el @Autowired, si lo ponemos no pasa nada,
public class CategoriaController {

    private final CategoriaRepositorio categoriaRepositorio; // No es necesario el @Autowired por la notacion, pero pon el final

    /**
     * Lista todas las categorias
     *
     * @return 404 si no hay categorías, 200 y lista de de categorías
     */
    @GetMapping("/categorias")
    public ResponseEntity<?> obetenerTodss() {
        List<Categoria> result = categoriaRepositorio.findAll();
        if (result.isEmpty()) {
            // Con response Status
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No hay categorías registradas");
        } else {
            return ResponseEntity.ok(result);
        }

    }

    /**
     * Obtiene una categoría con in específico
     *
     * @param id id de la categoría
     * @return 404 si no encuentra la categoría, 200 y la categoría si la encuetra
     */
    @GetMapping("/categorias/{id}")
    public Categoria obtenerCategoría(@PathVariable Long id) {
        // Excepciones con ResponseStatus
        return categoriaRepositorio.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No hay categorías registradas con id: " + id));
    }

    /**
     * Insertamos una nueva categoría
     *
     * @param nuevo nueva categoria
     * @return 201 y la categoría
     */
    @PostMapping("/categorias")
    public ResponseEntity<?> nuevoCategoria(@RequestBody Categoria nuevo) {
        if (nuevo.getNombre().isEmpty())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Nombre no puede ser vacío");

        else {
            return ResponseEntity.status(HttpStatus.CREATED).body(categoriaRepositorio.save(nuevo));
        }
    }

    /**
     * Editamos una categoría
     *
     * @param editar categoria a editar
     * @param id     id de la categoria a editar
     * @return 200 Ok si la edición tiene éxito, 404 si no se encuentra la categoria
     */
    @PutMapping("/categorias/{id}")
    public Categoria editarCategoria(@RequestBody Categoria editar, @PathVariable Long id) {

        if (editar.getNombre().isEmpty())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Nombre no puede ser vacío");
        else {
            // Se puede hacer con su asignaciones normales sin usar map, mira nuevo
            return categoriaRepositorio.findById(id).map(p -> {
                p.setNombre(editar.getNombre());
                return categoriaRepositorio.save(p);
            }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No hay categorías registradas con id: " + id));
        }

    }

    /**
     * Borra una categoría con un id espcífico
     *
     * @param id id de la categoría
     * @return Código 204 sin contenido
     */
    @DeleteMapping("/categorias/{id}")
    public ResponseEntity<?> borrarCategoria(@PathVariable Long id) {
        // Con manejo de excepciones
        Categoria categoria = categoriaRepositorio.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No hay categorías registradas con id: " + id));

        // Debemos tener en cueta que categoría es calve externa, y no podemos borrarla si hay productos enlazados
        try {
            categoriaRepositorio.delete(categoria);
            return ResponseEntity.noContent().build();
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No se puede eliminar categoria al existir productos enlzados a ella");
        }
    }


}
