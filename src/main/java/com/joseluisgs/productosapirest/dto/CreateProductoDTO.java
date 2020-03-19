package com.joseluisgs.productosapirest.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

// Getter & setter
public class CreateProductoDTO {

    private String nombre;
    private float precio;
    private long categoriaId;

}
