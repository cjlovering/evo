package server;

import com.google.gson.Gson;
import controller.DataStore;
import game.*;

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
        GameMeta gameMeta = new GameMeta(20, "predator vs peasant");
        List<Agent> agents = new ArrayList<>();
        GameMap map = new GameMap(20, 20);
        for (int i = 0; i < 10; i++) agents.add(new Peasant(Location.randomLocation(20, map)));
        for (int i = 0; i < 2;  i++) agents.add(new Predator(Location.randomLocation(20, map)));
        DataStore d = new DataStore(gameMeta, agents);
        s.routes(d, map);
    }

    public Server() {}

    public void routes(DataStore d, GameMap map) {

        this.g = new Gson();
        List<Action> actions = Arrays.asList(Action.values());

        staticFileLocation("/public");

        port(9090);

        get("/", (req, rep) -> {
            rep.status(200);
            rep.type("text/html");
            rep.redirect("index.html");
            return null;
        });

        get("/init", (req, rep) -> g.toJson(d.getGameMeta()));

        get("/data", (req, rep) -> {

            d.updateAgents(GameEngine.run(d.getAgents(),
                                          map,
                                          d.getGameMeta()));

            return g.toJson(d.getAgents());
        });
    }
}