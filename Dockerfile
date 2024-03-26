# Use a base image with Java
FROM openjdk:17-jdk-slim-buster

# Install necessary Linux packages
RUN apt-get update && apt-get install -y curl p7zip p7zip-full unace zip unzip bzip2 wget

# Install Gradle
RUN curl -sL https://services.gradle.org/distributions/gradle-8.0-bin.zip -o gradle.zip && \
    unzip gradle.zip && \
    mv gradle-8.0 /opt/gradle && \
    rm gradle.zip && \
    ln -s /opt/gradle/bin/gradle /usr/bin/gradle

# Set up PATH to include /usr/local/bin
#ENV PATH="/usr/local/bin:${PATH}"

# Set up your working directory
WORKDIR /usr/src/app

# Copy your project files
COPY . .

# Set permissions
RUN chmod +x gradlew
#RUN chmod +x /usr/src/app/src/test/resources/drivers/geckodriver

# Define a default value for the test runner
ARG TEST_RUNNER="LoginRunner"

# Run the test runner with gradlew
CMD ["sh", "-c", "./gradlew clean test \"$TEST_RUNNER\""]