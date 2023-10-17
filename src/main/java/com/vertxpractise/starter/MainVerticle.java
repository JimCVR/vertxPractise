package com.vertxpractise.starter;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.vertxpractise.starter.modules.Order;
import com.vertxpractise.starter.modules.Product;
import com.vertxpractise.starter.modules.User;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.*;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import lombok.val;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class MainVerticle extends AbstractVerticle {
    private Director director = new Director();
    private List<Order> orderList = director.createOrderList();
    private List<Product> productList = director.createProductList();
    private List<User> userList = director.createUserList();

    @Override
    public void start() throws Exception {

        HttpServer server = vertx.createHttpServer();
        Router router = Router.router(vertx);
        router.route("/orders").handler(ctx -> handleOrders(ctx));
        router.route("/products").handler( ctx -> handleProducts(ctx));
        router.route("/users").handler(ctx -> handleUsers(ctx));
        server.requestHandler(router);
        server.listen(8080);
    }

    private void handleOrders(RoutingContext context) {
            JsonArray jsonArray = new JsonArray(Arrays.asList(orderList.stream().map(it -> new JsonObject(Json.encode(it))).toArray()));
            HttpServerResponse response = context.response();
            response.putHeader("content-type", "application/json").end(jsonArray.toString());
    }

    private void handleProducts(RoutingContext context) {
        JsonArray jsonArray = new JsonArray(Arrays.asList(productList.stream().map(it -> new JsonObject(Json.encode(it))).toArray()));
        HttpServerResponse response = context.response();
        response.putHeader("content-type", "application/json").end(jsonArray.toString());
    }

    private void handleUsers(RoutingContext context) {
        JsonArray jsonArray = new JsonArray(Arrays.asList(userList.stream().map(it -> new JsonObject(Json.encode(it))).toArray()));
        HttpServerResponse response = context.response();
        response.putHeader("content-type","application/json").end(jsonArray.toString());
    }

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
        CompletableFuture <HttpResponse<String>> orders = HttpClient.newHttpClient().sendAsync(request,HttpResponse.BodyHandlers.ofString());
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

