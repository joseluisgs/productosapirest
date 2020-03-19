package com.joseluisgs.productosapirest.modelos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class Categoria {

    @Id
    @GeneratedValue
    private Long id;
    private String nombre;

}
