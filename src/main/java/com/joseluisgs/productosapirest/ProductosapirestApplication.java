package com.joseluisgs.productosapirest;

import com.joseluisgs.productosapirest.upload.StorageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ProductosapirestApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductosapirestApplication.class, args);
    }

    // Inicializamos el sistema de ficheros
    @Bean
    public CommandLineRunner init(StorageService storageService) {
        return args -> {
            // Inicializamos el servicio de ficheros
            storageService.deleteAll(); // Borramos todos (esto deberíamos quitarlo)
            storageService.init(); // Nicializamos

        };

    }

}
