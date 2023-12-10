# Word API

## Description:
The Word API project is a robust and scalable application designed to manage and serve word-related data through a RESTful API. This project provides a platform for users to interact with a diverse collection of words, their meanings, and associated features. The primary functionalities of the Word API include user registration, word management, and the ability for users to like or favorite specific words.

## Key Features:

- **User Registration**: Users can register and create accounts, enabling them to personalize their experience within the Word API.

- **Word Management**: The API facilitates the management of a comprehensive database of words, each categorized by their level of difficulty (e.g., A1, B2, C2).


- **User Likes**: The API allows users to express their preferences by liking specific words. This feature enhances user engagement and provides a personalized aspect to the application.

- **Daily Word**: Users receive a unique word each day, fostering daily engagement and promoting continuous learning.

- **Security**: The project prioritizes security, implementing user authentication and authorization mechanisms to protect user data and ensure secure interactions.

## Technology Stack:

- **Backend Framework**: Spring Boot
- **Database**: PostgreSQL
- **Persistence**: Hibernate (JPA)
- **Security**: JSON Web Token (JWT) for authentication
- **Database Version Control**: Liquibase

## Goals:

- Provide an intuitive and user-friendly API for managing and exploring words.
- Enhance user engagement through features such as liking and daily word recommendations.
- Foster a community of language enthusiasts by delivering a seamless and enriching experience.


# How to Start

To run the Word API locally, follow these steps:

### Prerequisites

1. **Java:** Ensure you have Java installed on your machine. You can download it [here](https://www.oracle.com/java/technologies/javase-downloads.html).

2. **PostgreSQL Database:** Set up a PostgreSQL database. You can download it [here](https://www.postgresql.org/download/) or use a cloud-based service.

### Configuration

1. Clone the repository:

    ```bash
    git clone https://github.com/nazariiboiko/WordApi.git
    ```

2. Navigate to the project directory:

    ```bash
    cd WordApi
    ```

3. Open the `application-prod.yaml` file in the `src/main/resources` directory and configure the database connection:

    ```properties
      datasource:
        url: **url**
        username: **username**
        password: **password**
      liquibase:
        change-log: classpath:liquibase/db.changelog-master.xml
        url: **url**
        user: **username**
        password:**password**
    ```

### Build and Run

1. Build the project using Maven:

    ```bash
    ./mvnw clean install
    ```

   or on Windows:

    ```bash
    mvnw.cmd clean install
    ```

2. Run the application:

    ```bash
    ./mvnw spring-boot:run
    ```

   or on Windows:

    ```bash
    mvnw.cmd spring-boot:run
    ```

### Access the API

Once the application is running, you can access the Word API at [http://localhost:8086](http://localhost:8086).

### API Documentation

Explore the API using the Swagger documentation available at [http://localhost:8086/swagger-ui/index.html](http://localhost:8086/swagger-ui/index.html).