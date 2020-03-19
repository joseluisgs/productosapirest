package com.joseluisgs.productosapirest.repositorios;

import com.joseluisgs.productosapirest.modelos.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepositorio extends JpaRepository<Categoria, Long> {

}
