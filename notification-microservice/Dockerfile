# Archivo que representa la información para la creación de
# una imagen para Docker, indicando todos los parametros necesarios.

# Probando el concepto de Multi-stage.
# Instalando Gradle para compilar al aplicación y luego lo necesario a una imagen completa.
FROM openjdk:11.0.11-slim

# Quien mantiene la versión.
LABEL maintainer="Alicia Cruz <20170465@ce.pucmm.edu.do>"




ENV DB_NAME=user-data
# Puedo pasar las variables directas que serán tomadas por Spring Boot.
ENV spring.datasource.url='jdbc:postgresql://db:5432/notification_data'
ENV spring.datasource.username='postgres'
ENV spring.datasource.password='password'



# Añadiendo el punto de montaje en el host
# Por defecto Tomcat crea los archivo temporales en esa ruta,
# lo habilitamos para ver los log, no es necesario para nuestro ejemplo.
#VOLUME /tmp

# Puertos que estarán disponibles de nuestra aplicación.
EXPOSE 8080

# Copiando el archivo jar generado luego de la ejecución del comando
# gradle task bootjar, se crean el jar y se copia a la imagen.
COPY build/libs/*.jar notification_app.jar

#Comando que se ejecuta una vez es iniciada la aplicación.
ENTRYPOINT ["java", "-jar", "notification_app.jar"]