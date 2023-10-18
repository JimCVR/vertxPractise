package com.vertxpractise.starter;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;

public class PingVerticle extends AbstractVerticle {

    @Override
    public void start() throws Exception {
        EventBus bus = vertx.eventBus();
        //bus.send("table.tennis","ping!");

        bus.request("table.tennis", "ping!")
            .onComplete(result -> {
                if (result.succeeded()) {
                    System.out.println(result.result().body());
                }
            });
    }
}
