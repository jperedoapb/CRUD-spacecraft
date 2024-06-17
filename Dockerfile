# imagen base de OpenJDK 21
FROM adoptopenjdk:21-jdk-hotspot
LABEL authors="jalva"

# Establece el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copia el JAR generado por Maven a la imagen Docker
COPY target/spacecraftservice-0.0.1-SNAPSHOT.jar /app/app.jar

# Comando para ejecutar la aplicación al iniciar el contenedor
CMD ["java", "-jar", "app.jar"]