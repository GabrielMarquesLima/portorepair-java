package br.com.fiap;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.net.URI;

public class Main {
    public static final String BASE_URI = "http://localhost:8080/api/";

    public static HttpServer startServer() {
        final ResourceConfig rc = new ResourceConfig().packages("br.com.fiap");
        rc.register(CorsFilter.class);
        return GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);
    }

    public static void main(String[] args) {
        final HttpServer server = startServer();
        System.out.println(String.format("API rodando em %s", BASE_URI));
        System.out.println("Pressione Ctrl+C para parar o servidor...");

        try {
            Thread.currentThread().join();
        } catch (InterruptedException e) {
            System.err.println("Erro ao executar o servidor: " + e.getMessage());
        }
    }
}