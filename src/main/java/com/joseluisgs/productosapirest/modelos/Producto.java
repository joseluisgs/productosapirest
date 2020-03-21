package com.joseluisgs.productosapirest.modelos;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
// Nos hace automaticamente:  A shortcut for @ToString, @EqualsAndHashCode, @Getter on all fields, and @Setter on all non-final fields, and @RequiredArgsConstructor

@NoArgsConstructor
@AllArgsConstructor
//Constructors made to order: Generates constructors that take no arguments, one argument per final / non-nullfield, or one argument for every field.

@Entity // JPA, entidad, se llamará igual salvo que la cambiemos
public class Producto {

    @Id  // Es el campo ID
    @GeneratedValue // Autoincrementa el valor
    @ApiModelProperty(value = "ID del Producto", dataType = "long", example = "1", position = 1)
    private Long id;

    // Si no ponemos nada, las calumnas se llamarán igual que estas propiedades
    @ApiModelProperty(value = "Nombre del producto", dataType = "String", example = "Jamón ibérico de bellota", position = 2)
    private String nombre;

    // Precio
    @ApiModelProperty(value = "Precio del producto", dataType = "float", example = "253.27", position = 3)
    private float precio;

    // Imagen
    @ApiModelProperty(value = "Imagen del producto", dataType = "String", example = "http://www.midominio.com/files/12345-imagen.jpg", position = 4)
    private String imagen;

    // ENlazamos un producto tiene una categoría
    @ManyToOne
    @JoinColumn(name = "categoria_id") // Así la vamos a llamar en la BB.DD
    @ApiModelProperty(value = "Categoría del producto", dataType = "Categoria", position = 5)
    private Categoria categoria;

}
