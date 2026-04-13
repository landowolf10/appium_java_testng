FROM eclipse-temurin:21-jdk-jammy

RUN apt-get update && apt-get install -y \
    curl \
    unzip \
    wget \
    && rm -rf /var/lib/apt/lists/*

WORKDIR /app

COPY gradlew .
COPY gradle gradle
COPY build.gradle settings.gradle ./

RUN chmod +x gradlew

RUN ./gradlew dependencies || true

COPY . .

ENV TEST_SUITE=login_test

CMD ["sh", "-c", "./gradlew clean test -P${TEST_SUITE}"]