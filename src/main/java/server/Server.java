package server;

import com.google.gson.Gson;
import controller.Store;
import game.*;
import learning.RandomModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static spark.Spark.get;
import static spark.Spark.staticFileLocation;
import static spark.SparkBase.port;

public class Server {
    private Gson g;

    public static void main(String[] args) {
        Server s = new Server();
        List<Agent> agents = new ArrayList<>();
        GameMeta gameMeta = new GameMeta(20, "predator vs peasant");
        GameMap map = new GameMap(20, 20);
        for (int i = 0; i < 10; i++) agents.add(new Peasant(Location.randomLocation(20, map)));
        for (int i = 0; i < 2;  i++) agents.add(new Predator(Location.randomLocation(20, map)));
        Store d = new Store(gameMeta, map, agents);
        s.routes(d, map);
    }

    public Server() {}

    public void routes(Store d, GameMap map) {

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

            RandomModel randModel = new RandomModel();
            GameEngine.run(d, randModel);
            return g.toJson(d.getAgents());

        });
    }
}