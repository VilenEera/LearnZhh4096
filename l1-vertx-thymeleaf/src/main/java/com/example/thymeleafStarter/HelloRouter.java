package com.example.thymeleafStarter;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;

public class HelloRouter {
  public static void main(String[] args) {
    Vertx vertx = Vertx.vertx();

    HttpServer httpServer = vertx.createHttpServer();

    Router router = Router.router(vertx);

    //配置一个/hello路径
    router.route("/hello").handler(routingContext -> {
      routingContext.response().putHeader("Content-Type","text/html; charset=utf-8").end("hello你好");
    });

    //配置一个/router路径
    router.route("/route").handler(routingContext -> {
      routingContext.response().putHeader("Content-Type","text/html; charset=utf-8").end("route路由");
    });

    httpServer.requestHandler(router).listen(8080);
  }
}
