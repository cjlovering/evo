package server;

import com.google.gson.Gson;
import game.Store;
import game.GameEngine;
import learning.BasicModel;
import learning.IModel;

import static spark.Spark.get;
import static spark.Spark.staticFileLocation;

public class Server {
    private Gson g;

    public static void main(String[] args) {
        Server s = new Server();
        s.routes(Store.predatorPeasant());
    }

    public Server() {}

    public void routes(Store store) {
        IModel model = new BasicModel();
        this.g = new Gson();

        staticFileLocation("/public");

        get("/", (req, rep) -> {
            rep.status(200);
            rep.type("text/html");
            rep.redirect("index.html");
            return null;
        });

        get("/init", (req, rep) -> {
            store.resetAgents();
            return g.toJson(store.getGameMeta());
        });

        get("/data", (req, rep) -> {
            GameEngine.run(store, model);
            return g.toJson(store.getAgents());
        });
    }
}