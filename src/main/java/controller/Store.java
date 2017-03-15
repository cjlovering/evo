package controller;

import game.*;

import java.util.ArrayList;
import java.util.List;

public class Store {
    private GameMeta gameMeta;
    private GameMap gameMap;
    private List<Agent> agents;


    public Store(GameMeta gameMeta, GameMap gameMap, List<Agent> agents) {
        this.gameMeta = gameMeta;
        this.gameMap = gameMap;
        this.agents = agents;
    };

    public void updateAgents(List<Agent> agents) {
        this.agents = agents;
    }

    public void updateMeta(GameMeta gameMeta) {
        this.gameMeta = gameMeta;
    }

    public List<Agent> getAgents() {
        return this.agents;
    }

    public void refresh() {
        if (this.agents.size() <= 0) {
            for (int i = 0; i < 10; i++) agents.add(new Peasant(Location.randomLocation(20, gameMap)));
            for (int i = 0; i < 2;  i++) agents.add(new Predator(Location.randomLocation(20, gameMap)));
        }
    }


    public static Store predatorPeasant(){
        return generateNewStore( 20, 20, 2, 10, "predator vs peasant");

    }

    public static Store generateNewStore(int x, int y, int preds, int peasants, String name) {
        List<Agent> agents = new ArrayList<>();
        GameMeta gameMeta = new GameMeta(x, name);
        GameMap map = new GameMap(x, y);
        for (int i = 0; i < peasants; i++) agents.add(new Peasant(Location.randomLocation(20, map)));
        for (int i = 0; i < preds;  i++) agents.add(new Predator(Location.randomLocation(20, map)));
        Store d = new Store(gameMeta, map, agents);
        return d;
    }

    public GameMeta getGameMeta() {
        return this.gameMeta;
    }

    public GameMap getGameMap() {
        return this.gameMap;
    }


}
