package com.joseluisgs.productosapirest.config;

/*
Clase de configuración
 */

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfig {

    // Creamos el bean para el wrapper
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}

