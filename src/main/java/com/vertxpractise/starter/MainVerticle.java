package com.vertxpractise.starter;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vertxpractise.starter.models.Order;
import com.vertxpractise.starter.models.Product;
import com.vertxpractise.starter.models.User;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.http.*;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;

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


}

