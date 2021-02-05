package com.example.thymeleafStarter;


import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.templ.thymeleaf.ThymeleafTemplateEngine;

public class HelloThymeleaf {
  public static void main(String[] args) {
    Vertx vertx = Vertx.vertx();
    HttpServer httpServer = vertx.createHttpServer();
    Router router = Router.router(vertx);

    ThymeleafTemplateEngine templateEngine = ThymeleafTemplateEngine.create(vertx);

    router.route("/hello").handler(routingContext -> {

      JsonObject data = new JsonObject().put("msg","Hello Thymeleaf! 你好");

      templateEngine.render(data, "templates/hello.html", res -> {
        if (res.succeeded()) {
          routingContext.response().putHeader("Content-Type","text/html; charset=utf-8").end(res.result());
        }else {
          routingContext.fail(res.cause());
        }
      });
    });
    httpServer.requestHandler(router).listen(8080);
  }
}
