FROM openjdk:17
ARG JAVA_OPTIONS
ARG OTHER_PARAMETERS
WORKDIR /
COPY target/Netty-Reactor.jar /app.jar
EXPOSE 8080
ENTRYPOINT java ${JAVA_OPTIONS} -jar /app.jar ${OTHER_PARAMETERS}