package com.joseluisgs.productosapirest.modelos;

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
    private Long id;

    // Si no ponemos nada, las calumnas se llamarán igual que estas propiedades
    private String nombre;
    private float precio;

    // ENlazamos un producto tiene una categoría
    @ManyToOne
    @JoinColumn(name="categoria_id") // Así la vamos a llamar en la BB.DD
    private Categoria categoria;

}
