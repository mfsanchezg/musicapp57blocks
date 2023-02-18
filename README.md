# Music APP 57blocks

## Tech Stack

Spring, Java 17, Docker

## Installation

For run in your machine, requires Docker to be installed.

1. Create the image for the Spring Boot using the Dockerfile
```bash
cd music-app
docker build -t music-app-57blocks.jar .
```

2. Execute the docker-compose.yml to recreate the database and run the app.
```bash
docker-compose up -d
```

3. The app is running in the port 8080.


## Author

- LinkedIn: [Manuel Fernando Sánchez González](https://www.linkedin.com/in/mfsanchezg/)