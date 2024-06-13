# ClusteredData Warehouse

# Overview

This project is part of a Scrum team developing a data warehouse for Bloomberg to analyze FX deals. The application
accepts deal details and persists them into a database.

## Features

- Accepts FX deals details and persists them in a database
- Validates input data
- Prevents duplicate deal entries
- No rollback allowed: every imported row is saved in the database

## Technologies Used

- Java
- Spring Boot
- Spring Data JPA
- MySQL
- Docker
- Docker Compose
- Gradle
- JUnit

### Sample Request

- **POST /deal/create**

    ```json
    {
      "id": 123,
      "fromCurrencyCode": "JOD",
      "toCurrencyCode": "USD",
      "timestamp": "2024-06-12 16:00:00",
      "amount": "1000"
    }
    ```

### Sample Response

- ```json
    {
      "isSuccess": true,
      "deal": {
        "id": 123,
         "fromCurrencyCode": "JOD",
         "toCurrencyCode": "USD",
         "timestamp": "2024-06-12 16:00:00",
          "amount": "1000"
      }
    }
    ```
- ```json
    {
      "isSuccess": false,
      "errorMessage": "Deal Unique Id is Required"
    }
  ```