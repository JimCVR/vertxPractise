package com.vertxpractise.starter;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;

public class PongVerticle extends AbstractVerticle {
    @Override
    public void start() throws Exception {
        EventBus bus = vertx.eventBus();
        bus.consumer("table.tennis").handler( message -> {
            message.reply("pong!");
        });
    }
}
