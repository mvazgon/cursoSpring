# cursoSpring
Codigo del curso de spring

## Descripción de la app clasecuatro.

La aplicación nos va a servir para almacenar en una capa de persistencia de datos los mensajes que vamos a crear/leer/actualizar y borrar(CRUD) con la API

Esta app es una aplicación desarrollada en springboot.

Tiene como dependencias de maven para el proyecto las librerias registradas en el archivo pom.xml que usará el compilador maven para construir el jar de la app. 
Estas clases son:
- Dependencias de BBDD:
  - hibernate
  - sqlite-jdbc
  - spring-boot-starter-data-jpa
- Otras dependencias:
  - thymeleaf
  - spring-boot-starter-web
  - sprin-boot-devtools
  - lombok
  - bootstrap

## Patrones de diseño.

Las dependencias de los objetos son inversos a la abstracción: los más abstractos son los modelos y los menos los controladores. La secuencia es:
- controladores, en este paquete definimos los endpoints de la API y cuales son los verbos de HTTP que se pueden usar en cada endpoint. Definiremos dos endpoints:
  -  endpoints que manejan listas, estos endpoints son:
    - /app/<nombre_objeto> , que con el verbo GET recupera un array con todos los objetos que existen en la capa de persistencia de datos
    - /app/<nombre_objeto>, que con el verbo POST crea una entrada en la capa de persistencia 
  -  endpoints que no manejan listas, sino unidades.
    - /app/<nombre_objeto/id , que con el verbo GET recupera el elemento identiicado por id y lo devuelve,
    - /app/<nombre_objeto/id, que con el verbo UPDATE actualiza el contenido del elemento identificado por id y devuelve un mensaje en response
    - /app/<nombre_objeto>/id, q con el verbo DELETE borra el registro del elemento del mismo id en la capa de persistencia.
- services, los services nos permiten manipular los objetos más abstractos con respecto a las peticiones del controlador.
- repositorio (capa de persistencia), esta capa dependiendo del profile tenemos una cascada de dependencias:
  - una clase q manipula internamente una clase intermedia de repositorio
  - una clase base que indica cuales son los metodos para implementar la persistencia de datos.
- modelos, esta capa usada por la anterior para obtener los setter y getters del modelo para sobreescribir los métodos indidcados y establecer las acciones a desarrollar por cada servicio de persistencia de datos:
    - base de datos no relacional: mongodb
    - base de datos relacional: mysql
