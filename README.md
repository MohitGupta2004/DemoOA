# Webhook Application

A Spring Boot application that:
1. On startup, sends a POST request to generate a webhook
2. Based on the response, solves a SQL problem
3. Sends the solution to the returned webhook URL using a JWT token

## Features

- Automatically runs on application startup (no controller/endpoint needed)
- Uses RestTemplate for API communication
- Implements JWT authentication for the webhook submission

## How It Works

1. On startup, the application sends a POST request to generate a webhook
2. The application receives a webhook URL and access token
3. It submits the solution to the webhook URL with the JWT token in the Authorization header

## Running the Application

```bash
./gradlew bootRun
```

## Technology Stack

- Java 17
- Spring Boot 3.4.5
- Spring Web (RestTemplate)
- Lombok for reducing boilerplate code 