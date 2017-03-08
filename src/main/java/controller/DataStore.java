package controller;

import game.Agent;
import game.GameMeta;

import java.util.List;

public class DataStore {
    private GameMeta gameMeta;
    private List<Agent> agents;


    public DataStore(GameMeta gameMeta, List<Agent> agents) {
        this.gameMeta = gameMeta;
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


}
