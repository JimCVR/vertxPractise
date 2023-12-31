package com.vertxpractise.starter;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vertxpractise.starter.models.Order;
import com.vertxpractise.starter.models.Product;
import com.vertxpractise.starter.models.User;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpClientOptions;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class Main {

    public static void main(String[] args) throws Exception {
        var vertx = Vertx.vertx();
        var options = new HttpClientOptions().setDefaultPort(8080);
        var client =  vertx.createHttpClient(options);
        HttpRequest request = HttpRequest
            .newBuilder(new URI("http://localhost:8080/orders"))
            .GET()
            .build();
        HttpRequest request2 = HttpRequest
            .newBuilder(new URI("http://localhost:8080/products"))
            .GET()
            .build();
        HttpRequest request3 = HttpRequest
            .newBuilder(new URI("http://localhost:8080/users"))
            .GET()
            .build();
        CompletableFuture<HttpResponse<String>> orders = HttpClient.newHttpClient().sendAsync(request,HttpResponse.BodyHandlers.ofString());
        CompletableFuture <HttpResponse<String>> products = HttpClient.newHttpClient().sendAsync(request2,HttpResponse.BodyHandlers.ofString());
        CompletableFuture <HttpResponse<String>> users = HttpClient.newHttpClient().sendAsync(request3,HttpResponse.BodyHandlers.ofString());
        ObjectMapper objectMapper = new ObjectMapper();
        List<Order> orderResponse = objectMapper.readValue(orders.get().body(), new TypeReference<>(){});
        List<Product> productResponse = objectMapper.readValue(products.get().body(), new TypeReference<>(){});
        List<User> userResponse = objectMapper.readValue(users.get().body(), new TypeReference<>(){});
        System.out.println(orderResponse.toString());
        System.out.println(productResponse.toString());
        System.out.println(userResponse.toString());
    }
}
