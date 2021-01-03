package com.example.thymeleafStarter;

import io.vertx.core.Vertx;

public class HelloWorld {
  public static void main(String[] args) {
    Vertx.vertx().createHttpServer().requestHandler(httpServerRequest -> {
      httpServerRequest.response().putHeader("Content-Type","text/html; charset=utf-8").end("Hello World,这是我第一个 vertx 程序");
    }).listen(8080);
  }
}
