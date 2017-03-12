package controller;

import game.Agent;
import game.GameMap;
import game.GameMeta;

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

    public GameMeta getGameMeta() {
        return this.gameMeta;
    }

    public GameMap getGameMap() {
        return this.gameMap;
    }


}
