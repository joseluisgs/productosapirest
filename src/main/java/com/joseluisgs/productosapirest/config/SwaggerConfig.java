package com.joseluisgs.productosapirest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                // Indicamos lo que queremos documentar
                .apis(RequestHandlerSelectors.basePackage("com.joseluisgs.productosapirest.controladores"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    @Bean
    public ApiInfo apiInfo() {

        return new ApiInfoBuilder()
                .title("API REST demo")
                .description("API de ejemplo del curso Desarrollo de un API REST con Spring Boot")
                .version("1.0")
                .contact(new Contact("José Luis González", "https://github.com/joseluisgs", "https://twitter.com/joseluisgonsan"))
                .build();


    }

}
