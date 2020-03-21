package com.joseluisgs.productosapirest.dto;

import lombok.Getter;
import lombok.Setter;


// Los DTO nos sirven entre otras cosas para filtrar información de una o varias clases, podría ser similar a las vistas
@Getter
@Setter
// Solo tiene getter&setter
public class ProductoDTO {

    private long id;
    private String nombre;
    private float precio;
    private String imagen;
    private String categoriaNombre;
    //private String categoria;

}
