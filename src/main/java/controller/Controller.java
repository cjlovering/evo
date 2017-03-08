package controller;

import game.*;
import server.Server;

import java.util.ArrayList;
import java.util.List;

public class Controller {
    public static void main(String[] args) {
        Server s = new Server();
        GameMeta gameMeta = new GameMeta(20, "peasant vs predator");
        List<Agent> agents = new ArrayList<>();

        while (true) {
            // train or play
            agents.stream().forEach(a -> a.randomMove());
            // get server

        }
    }
}
