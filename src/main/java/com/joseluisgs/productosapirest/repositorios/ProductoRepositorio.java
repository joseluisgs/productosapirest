package com.joseluisgs.productosapirest.repositorios;

import com.joseluisgs.productosapirest.modelos.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

// Creamos el repositorio extendéndolo de JPA, siguiendo DAO
// Con ello ya tenemos las operaciones básicas de CRUD y Paginación
public interface ProductoRepositorio extends JpaRepository<Producto, Long> {
}
