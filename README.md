# Music APP 57blocks
![Logo](https://57blocks.io/static/media/blackLogo.c93d4b52.svg)

## Tech Stack

- Spring 3
- Spring Security
- Java 17
- Docker

## Installation

For run the app in your machine, it requires Docker to be installed.

1. Open a Command Prompt (terminal) in the project root.

    Example:
```bash
   manuel@manuel-Latitude-5491:~/Documents/57blocksBackendHomework/music-app$
```

Or if you want, you can copy the next files to another folder and open the terminal there.
```bash
manuel@manuel-Latitude-5491:~/Documents/music-app$ ls -l
total 44460
-rw-rw-r-- 1 manuel manuel      692 feb 17 20:32 docker-compose.yml
-rw-rw-r-- 1 manuel manuel      184 feb 17 20:32 Dockerfile
-rw-rw-r-- 1 manuel manuel 45515982 feb 17 22:16 music-app-57blocks.jar
manuel@manuel-Latitude-5491:~/Documents/music-app$
```

2. Run the next command to build the image for the Spring Boot app using the Dockerfile file. It uses the music-app-57blocks.jar. Don't forget the final dot in the command.
```bash
docker build -t music-app-57blocks.jar .
```
Verify that the image was created successfully.
```bash
manuel@manuel-Latitude-5491:~/Documents/music-app$ docker build -t music-app-57blocks.jar .
Sending build context to Docker daemon  45.52MB
Step 1/6 : FROM openjdk:17-jdk-slim-buster
---> b97928de0b87
Step 2/6 : VOLUME /tmp
---> Running in 08d22410ca1e
Removing intermediate container 08d22410ca1e
---> 384bd2ceaa82
Step 3/6 : EXPOSE 8080
---> Running in 172d1da0a890
Removing intermediate container 172d1da0a890
---> 2d28e9a868f4
Step 4/6 : ARG JAR_FILE=music-app-57blocks.jar
---> Running in 052afc6a1cf9
Removing intermediate container 052afc6a1cf9
---> b17896ca58fb
Step 5/6 : ADD ${JAR_FILE} music-app-57blocks.jar
---> ec33ba902489
Step 6/6 : ENTRYPOINT ["java","-jar","/music-app-57blocks.jar"]
---> Running in 6b733160db56
Removing intermediate container 6b733160db56
---> 28d88001fed3
Successfully built 28d88001fed3
Successfully tagged music-app-57blocks.jar:latest
manuel@manuel-Latitude-5491:~/Documents/music-app$
```

Verify the image was created with the command ```docker images```
```bash
manuel@manuel-Latitude-5491:~/Documents/music-app$ docker images
REPOSITORY               TAG                  IMAGE ID       CREATED          SIZE
music-app-57blocks.jar   latest               28d88001fed3   52 seconds ago   446MB
```

3. Execute the docker-compose.yml using the command ```docker-compose up -d``` to recreate the database and run the app.
   It creates the postgres image and run both (the postgres and the app).
```bash
manuel@manuel-Latitude-5491:~/Documents/music-app$ docker-compose up -d
Creating network "music-app_default" with the default driver
Creating music-app_PostgreSQL_1 ... done
Creating music-app_APP_1        ... done
manuel@manuel-Latitude-5491:~/Documents/music-app$ 
```

4. The app is running in the port 8080. Now you can use the postman collection located in the /docs folder to execute the endpoints.
```bash
CONTAINER ID   IMAGE                    COMMAND                  PORTS                                                 NAMES
e9b6dd11208c   music-app-57blocks.jar   "java -jar /music-ap…"   8080/tcp, 0.0.0.0:8080->5678/tcp, :::8080->5678/tcp   music-app_APP_1
e7d31dce6da4   postgres                 "docker-entrypoint.s…"   0.0.0.0:5433->5432/tcp, :::5433->5432/tcp             music-app_PostgreSQL_1
```

## API Reference

### Register a user
You can register a user using this endpoint. It's a public API, no requires authentication.

```http
  POST /api/v1/auth/register
```
```json
{
"firstname" : "Manuel",
"lastname" : "Sánchez",
"email": "manuelf@57blocks.com",
"password": "Aa@0123456"
}
```
#### Response
Message with the successful user registration.
```http
  200
```
```json
{
   "message": "User manuelf@57blocks.com created successfully."
}
```
### Login a user
You can login a user using the next endpoint with the email created when register the user. It's a public API, no requires authentication.

```http
  POST /api/v1/auth/login
```
```json
{
   "email": "manuelf@57blocks.com",
   "password": "Aa@0123456"
}
```
#### Response
The user token to retrieve, insert and update songs. This token expires after 20 minutes. 
```http
  200
```
```json
{
   "message": "User manuelf@57blocks.com logged successfully",
   "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJtYW51ZWxmQDU3YmxvY2tzLmNvbSIsImlhdCI6MTY3Njc1ODQ1OSwiZXhwIjoxNjc2NzU5NjU5fQ.iCV7KRjjm5AzwNzR_oo4X7rTa8_bmb3N46gzPpVUfz4"
}
```
### Get Public Songs
You can retrieve the all user public songs. This API allow pagination, you can send the page and the elements size in the page that you want. It's a public API, no requires authentication.

```http
  GET /api/v1/public/songs?page=0&size=2
```

#### Response
A list of the songs in json format, specifying which user the song belongs to.
```http
  200
```
```json
[
   {
      "name": "Experiencias Vividas",
      "artist": "Diomedes Díaz",
      "genre": "Vallenato",
      "year": "2006",
      "country": "Colombia",
      "isPrivate": false,
      "username": "manuelf@57blocks.com"
   },
   {
      "name": "El Cantante",
      "artist": "Héctor Lavoe",
      "genre": "Salsa",
      "year": "1978",
      "country": "Puerto Rico",
      "isPrivate": false,
      "username": "manuelf@57blocks.com"
   }
]
```
### Get Private Songs
You can retrieve the private songs for a user. This API allow pagination, you can send the page and the elements size in the page that you want. It's a secure API, requires the logged user bearer token.  

```http
  GET /api/v1/private/songs?page=0&size=2
```

#### Response
A list of the songs that belong to the logged user. If you have not inserted songs for the user, the result is void [].
```http
  200
```
```json
[
   {
      "name": "Paradise",
      "artist": "Coldplay",
      "genre": "Pop Rock",
      "year": "2011",
      "country": "England",
      "isPrivate": true,
      "username": "manuelf@57blocks.com"
   }
]
```
### Create Songs
You can create songs for a user. It's a secure API, requires the logged user bearer token.

```http
  POST /api/v1/private/song
```
```json
{
   "name": "Lost on You",
   "artist": "LP",
   "genre": "Pop",
   "year": "2016",
   "country": "United States",
   "isPrivate": false
}
```
#### Response
A message and a status that the song was created successfully for the user.
```http
  200
```
```json
{
   "status": 200,
   "message": "Song <Lost on You> was created successfully for the user <manuelf@57blocks.com>."
}
```

### Update Songs
You can update songs for a user sending the id of the song. It's a secure API, requires the logged user bearer token.

```http
  PATCH /api/v1/private/song
```
```json
{
   "id": 30,
   "name": "Lost on You",
   "artist": "LP",
   "genre": "Pop",
   "year": "2016",
   "country": "United States",
   "isPrivate": false
}
```
#### Response
A message and a status that the song was updated successfully for the user.
```http
  200
```
```json
{
   "status": 200,
   "message": "Song <Lost on You> was updated successfully for the user <manuelf@57blocks.com>."
}
```

## AWS instance

The project is also running in an AWS instance located in this url, therefore you can use this.
```http
http://57blocksmusicapp.us-east-1.elasticbeanstalk.com/
```

You can verify the health of the server executing this URL.

```http
http://57blocksmusicapp.us-east-1.elasticbeanstalk.com/api/v1/public/health
```
You can retrieve the public songs in the next URL.
```http
http://57blocksmusicapp.us-east-1.elasticbeanstalk.com/api/v1/public/songs?page=0&size=20
```

## Author
#### Manuel Fernando Sánchez González
[![linkedin](https://img.shields.io/badge/linkedin-0A66C2?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/mfsanchezg/)
