package learning;

import controller.Store;
import game.Agent;

public class RandomModel implements IModel {
    public void determineActions(Store store) {
        store.getAgents().stream().forEach(Agent::randomAction);
    }
}
