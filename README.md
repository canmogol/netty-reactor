# Netty and Reactor app

A simple proxy app with Netty and Reactor.

In order to create an executable jar, run this command.
```
mvn clean package
```

To build the Docker image and run the docker container.
```
docker build -t netty-reactor .
docker run -d --rm --memory="128MB" --cpus="1.0" --name netty-reactor -e "REMOTE_SERVER_URL=http://192.168.68.112:3000/city" -p 8080:8080 netty-reactor:latest
```
