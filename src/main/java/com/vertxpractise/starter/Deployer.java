package com.vertxpractise.starter;

import io.vertx.core.Verticle;
import io.vertx.core.Vertx;

public class Deployer {
    public static void main(String[] args) throws Exception {
        Vertx vertx = Vertx.vertx();
        Verticle pongVerticle = new PongVerticle();
        Verticle pingVerticle = new PingVerticle();
        vertx.deployVerticle(pongVerticle);
        vertx.deployVerticle(pingVerticle);
    }
}
