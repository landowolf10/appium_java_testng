# Android UI Automation Framework (Appium + TestNG)

## Overview

This project implements an end-to-end **Android UI automation framework** for validating a complete checkout workflow using a sample mobile application.

The framework is built using **Java, Appium (UIAutomator2), TestNG**, and follows **Page Object Model (POM)** principles to ensure scalability, maintainability, and clean separation of concerns.

It supports execution in multiple environments:

- Local Android Emulator
- Physical Android Devices
- BrowserStack Cloud Devices
- Dockerized execution
- CI execution via GitHub Actions

---

## Architecture & Design Decisions

The framework is structured using:

- **Page Object Model (POM)** for UI abstraction
- **Centralized Driver Factory (SetUp class)** for local and remote execution
- **Thread-safe driver management using TestNG parallel execution support**
- **Environment-based configuration via system properties and environment variables**

### Key Design Principles

- Separation of test logic and UI interactions
- Reusable page components
- Support for parallel execution
- Cross-platform execution (local + cloud)
- Environment-driven execution switching

---

## Technology Stack

- Java 21
- Appium Java Client 9.x
- Selenium 4.x
- TestNG
- UIAutomator2
- Gradle
- Docker
- BrowserStack

---

## Why UIAutomator2 instead of Espresso?

> Espresso requires access to the application's source code for instrumentation. Since only the APK was provided, UIAutomator2 via Appium was selected as it enables black-box UI automation without source access, while maintaining all framework design principles requested.

---

## Test Scenario Covered

The automation covers a full **checkout workflow**, including:

- App launch
- Login
- Product selection
- Add to cart
- Cart validation
- Checkout process
- Order completion validation

---

## Execution Modes

### 1. Run Locally (Android Emulator or Physical Device)

```powershell
$env:RUN_ON_BROWSERSTACK="false"
./gradlew clean test -Ptestng-local
```

---

### 2. Run on BrowserStack

```powershell
$env:RUN_ON_BROWSERSTACK="true"
./gradlew clean test -Ptestng-browserstack
```

---

### 3. Run via Docker

```bash
docker build -t appium-tests .
docker run --rm   -e RUN_ON_BROWSERSTACK=true   -e BROWSERSTACK_USERNAME=xxx   -e BROWSERSTACK_ACCESS_KEY=xxx   -e BROWSERSTACK_ANDROID_APP=xxx   appium-tests
```

---

### 4. Run via GitHub Actions

The project includes a CI pipeline that:

- Pulls the Docker image
- Executes tests in BrowserStack
- Runs full regression on push to `main`

---

## Parallel Execution

The framework supports parallel execution using TestNG:

```xml
parallel="tests"
thread-count="2"
```

Each test runs on a different device configuration.

---

## Project Structure

```
src
 ├── main/java/org/lando/pages
 ├── main/java/org/lando/utils
 ├── test/java/com/lando/tests
 ├── test/resources/test-suites
```

---

## ⚙️ Configuration Switching

Execution environment is controlled via:

```
RUN_ON_BROWSERSTACK=true | false
```

- false → Local Appium (emulator/device)
- true → BrowserStack cloud execution

---

## 📦 Build Command

```bash
./gradlew clean build
```

---

## 👨‍💻 Author

Orlando Avila  
Automation Engineer | Mobile QA | Appium Specialist
