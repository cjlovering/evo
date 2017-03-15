package server;

import com.google.gson.Gson;
import controller.Store;
import game.GameEngine;
import learning.RandomModel;

import static spark.Spark.get;
import static spark.Spark.staticFileLocation;

public class Server {
    private Gson g;

    public static void main(String[] args) {
        Server s = new Server();
        s.routes(Store.predatorPeasant());
    }

    public Server() {}

    public void routes(Store d) {
        RandomModel randModel = new RandomModel();
        this.g = new Gson();

        staticFileLocation("/public");

        get("/", (req, rep) -> {
            rep.status(200);
            rep.type("text/html");
            rep.redirect("index.html");
            return null;
        });

        get("/init", (req, rep) -> g.toJson(d.getGameMeta()));

        get("/data", (req, rep) -> {
            d.refresh();
            GameEngine.run(d, randModel);
            return g.toJson(d.getAgents());

        });
    }
}