package io.archura;

import reactor.netty.ByteBufMono;
import reactor.netty.http.client.HttpClient;
import reactor.netty.http.server.HttpServer;

import java.util.Optional;

public class Main {

    public static final String REMOTE_SERVER_URL = Optional.ofNullable(System.getenv("REMOTE_SERVER_URL")).orElse("http://centos:3000/city");

    public static void main(String[] args) {
        System.out.println("REMOTE_SERVER_URL = " + REMOTE_SERVER_URL);
        HttpClient client =
                HttpClient.create();

        HttpServer server =
                HttpServer.create()
                        .port(8080)
                        .route(r -> r.get(
                                        "/",
                                        (req, res) -> {
                                            ByteBufMono clientBuffer = client.get()
                                                    .uri(REMOTE_SERVER_URL)
                                                    .responseContent()
                                                    .aggregate();
                                            return res.send(clientBuffer.retain());
                                        }
                                )
                        );

        server.port(8080).bindNow().onDispose().block();
    }

}
