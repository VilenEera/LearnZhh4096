package com.example.thymeleafStarter;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.templ.thymeleaf.ThymeleafTemplateEngine;

public class Server extends AbstractVerticle {
  // Convenience method so you can run it in your IDE
  public static void main(String[] args) {
    Vertx.vertx().deployVerticle(Server.class.getName());
  }
  @Override
  public void start() throws Exception {
    // To simplify the development of the web components we use a Router to route all HTTP requests
    // to organize our code in a reusable way.
    final Router router = Router.router(vertx);
    // In order to use a Thymeleaf template we first need to create an engine
    final ThymeleafTemplateEngine engine = ThymeleafTemplateEngine.create(vertx);
    // Entry point to the application, this will render a custom JADE template.
    router.get().handler(ctx -> {
      // we define a hardcoded title for our application
      JsonObject data = new JsonObject()
        .put("msg", "Hi there!");
      // and now delegate to the engine to render it.
      engine.render(data, "templates/hello.html", res -> {
        if (res.succeeded()) {
          ctx.response().end(res.result());
        } else {
          ctx.fail(res.cause());
        }
      });
    });
    // start a HTTP web server on port 8080
    vertx.createHttpServer().requestHandler(router).listen(8080);
  }
}
