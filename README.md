# productosapirest
Ejemplo de como hacer un API REST en Springboot

## Tecnologías
* H2 como base de datos para que quien lo quiera no usar MySQL, luego lo cambiaré
* Lombok para potenciar las anotaciones: https://projectlombok.org/features/all

### Desarrollo
* 19/03/2020: Inicio del proyecto y configuración del mismo: application.properties
* 19/03/2020: Configuracion de pomxm. H2 y Lombok. Carga de datos en H2 y data.sql
* 19/03/2020: Creación del Modelo, Repositorio DAO y Controlador REST de Producto. a nivel básico
* 19/03/2020: Ficheros de consulta de Postman. Controlador: ResponseEntity
* 19/03/2020: DTO de Objeto con ModeloMapper. Trabajando con Productos y Categorías
* 20/03/2020: Manejo de Errores con: ExceptionHandler, GlobalControllerAdvice, ResponseEntityExceptionHandler,  ResponseStatusException
* 20/03/2020: CRUD de Categoria
* 20/03/2020: CORS: Cliente Ajax, ejecutable desde http://localhost:8080/ Configuración de seguridad en metodo y global
* 21/03/2020: Sistema de ficheros.
* 21/03/2020: Documentación con Swagger: http://localhost:8080/swagger-ui.html

##### Ejecución
http://localhost:8080/
H2: http://localhost:8080/h2-console. 
* user:sa
* db: jdbc:h2:./productosapirest

## Acerca de
José Luis González Sánchez: [@joseluisgonsan](https://twitter.com/joseluisgonsan)

##### Otros
Inspirado en el curso de [OpenWebinars](https://openwebinars.net/academia/portada/api-rest-spring-boot/) que se recomendó al alumnado.
