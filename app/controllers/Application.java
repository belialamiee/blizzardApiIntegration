package controllers;

import actors.Setup;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import play.mvc.Controller;
import play.mvc.Result;
import scala.compat.java8.FutureConverters;
import play.libs.ws.*;

import java.util.concurrent.CompletionStage;

import static akka.pattern.Patterns.ask;

@Singleton
public class Application extends Controller {

    Setup actorSetup;
    WSClient wsClient;

    @Inject
    public Application(Setup actorSetup, WSClient wsClient) {
        this.actorSetup = actorSetup;
        this.wsClient = wsClient;
    }

    /**
     * Play framework supports asynchronous controller methods -- that is, methods that instead of returning a Result
     * return a CompletionStage, which will produce a Result some time in the future
     */
    public Result index() {
        if (session("userId") != null) {
            return redirect("/characters");
        }
        return ok(views.application.html.index.render());
    }
}