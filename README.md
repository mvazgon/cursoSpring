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
- controladores
- services
- repositorio (capa de persistencia), esta capa dependiendo del profile tenemos una cascada de dependencias:
  - una clase q manipula internamente una clase intermedia de repositorio
  - una clase base que indica cuales son los metodos para implementar la persistencia de datos.
- modelos, esta capa usada por la anterior para obtener los setter y getters del modelo para sobreescribir los métodos indidcados y establecer las acciones a desarrollar por cada servicio de persistencia de datos:
    - base de datos no relacional: mongodb
    - base de datos relacional: mysql
 
