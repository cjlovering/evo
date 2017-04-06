package game;

import java.util.ArrayList;
import java.util.List;

public class Store {
    private GameMeta gameMeta;
    private GameMap gameMap;
    private List<Agent> agents;
    private final int peasants;
    private final int predators;


    public Store(int x, int y, int predators, int peasants, String name) {
        this.agents = new ArrayList<>();
        this.gameMeta = new GameMeta(x, name);
        this.gameMap = new GameMap(x, y);
        this.peasants = peasants;
        this.predators = predators;
        this.resetAgents();
    }

    public static Store predatorPeasant(){
        return new Store( 20, 20, 2, 10, "predator vs peasant");
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

    /**
     * Clears and resets the list of agents to their original amounts
     * TODO: should it be a slightly random amount?
     */
    public void resetAgents() {
        agents.clear();

        for(int i = 0; i < this.predators; i++) {
            agents.add(new Predator(gameMap.getRandomLocation()));
        }

        for(int i = 0; i < this.peasants; i++) {
            agents.add(new Peasant(gameMap.getRandomLocation()));
        }
    }

    /**
     * Restart the game at the end of the step, if everyone is dead
     */
    public void step() {
        if (this.agents.isEmpty()) {
            //TODO: track/log information for UI
            resetAgents();
        }
    }

}
