# Archivo que representa la información para la creación de
# una imagen para Docker, indicando todos los parametros necesarios.

# Probando el concepto de Multi-stage.
# Instalando Gradle para compilar al aplicación y luego lo necesario a una imagen completa.
FROM gradle:7.1.0-jdk11 AS build
COPY --chown=gradle:gradle src /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle bootJar --no-daemon

# El comando FROM indica la imagen base.
# openjdk:8-jdk-alpine es una imagen de Java 8 muy ligera.
FROM openjdk:11.0.11-slim

# Copiando el archivo jar generado luego de la ejecución del comando
# gradle task bootjar, se crean el jar y se copia a la imagen.
COPY --from=build /home/gradle/src/build/libs/*.jar config_app.jar

#Comando que se ejecuta una vez es iniciada la aplicación.
ENTRYPOINT ["java", "-jar", "config_app.jar"]