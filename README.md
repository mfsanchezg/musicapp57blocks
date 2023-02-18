# Music APP 57blocks
![Logo](https://57blocks.io/static/media/blackLogo.c93d4b52.svg)

## Tech Stack

Spring 3, Spring Security, Java 17, Docker

## Installation

For run in your machine, requires Docker to be installed.

1. Open a terminal in the project root where is the Dockerfile and the docker-compose.yml.
 
2. Create the image for the Spring Boot using the Dockerfile
```bash
docker build -t music-app-57blocks.jar .
```

3. Execute the docker-compose.yml to recreate the database and run the app.
```bash
docker-compose up -d
```

4. The app is running in the port 8080. Now you can use the postman collection located in the /docs folder to execute the endpoints.


## Author
#### Manuel Fernando Sánchez González
[![linkedin](https://img.shields.io/badge/linkedin-0A66C2?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/mfsanchezg/)
