# Prueba Tecnica William 

## Descripción
Creación de microservicios relacionados con la gestión de 
productos y ventas, que interactúan con una base
de datos compartida y utilizan una cola de
mensajería (RabbitMQ) para actualizar el stock de
productos de manera asíncrona..

## Configuración del Entorno de Desarrollo
1. **Java JDK**: Asegúrate de tener instalado Java JDK.
2. **Maven**: Asegúrate de tener instalado Maven.
3. **RabbitMQ**: Asegúrate de tener instalado Maven.
4. **PostgreSQL**: Asegúrate de tener PostgreSQL instalado y configurado en tu entorno de desarrollo.

## Descarga del Código Fuente
Clona o descarga el código fuente desde el repositorio.

## Compilación y Empaquetado
1. **Compilación del Código**: Ejecuta el comando `mvn clean install package` desde la raíz del proyecto para compilar y empaquetar los microservicios.

Una vez que el proyecto esté compilado, puedes ejecutarlo usando el plugin de Spring Boot Maven. Ejecuta el siguiente comando en la raíz de tu proyecto: mvn spring-boot:run


