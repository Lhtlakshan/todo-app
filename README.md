# ToDo Spring Boot backend + PostgreSQL Dockerized

### Creating a docker network
``` bash
docker network create todo_network
``` 
### Postgresql docker setup
``` bash
# Build PostgreSQL image (from the folder containing PostgreSQL Dockerfile)
docker build -t postgres_db

# Run PostgreSQL container from image
docker run --name todo_db --network todo_network postgres_db
```

### Spring Boot docker setup
``` bash
# Build Spring Boot image (from the folder containing Spring Boot Dockerfile)
docker build -t todo_spring_boot_service

# Run backend service and host it on localhost port 8080
docker run -d -p 8080:8080 --name backend --network todo_network todo_spring_boot_service

```
