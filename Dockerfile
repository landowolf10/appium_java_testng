# Imagen base con Java 21
FROM eclipse-temurin:21-jdk-jammy

# Instalar dependencias necesarias
RUN apt-get update && apt-get install -y \
    curl \
    unzip \
    wget \
    && rm -rf /var/lib/apt/lists/*

# Directorio de trabajo
WORKDIR /app

# Copiar solo archivos necesarios primero (mejor cache)
COPY gradlew .
COPY gradle gradle
COPY build.gradle settings.gradle ./

# Dar permisos
RUN chmod +x gradlew

# Descargar dependencias (cache layer)
RUN ./gradlew dependencies || true

# Copiar el resto del proyecto
COPY . .

# Variable para suite
ARG TEST_SUITE=login_test

# Ejecutar tests
CMD ["sh", "-c", "./gradlew clean test -P${TEST_SUITE}"]