package learning;

import controller.Store;
import game.Agent;

import java.util.List;
import java.util.Optional;

public class RandomModel implements IModel {
    public void determineActions(Store store) {
        store.getAgents().stream().forEach(Agent::randomAction);
    }

    public Optional<Agent> determineOpponent(Agent fighter, List<Agent> opponents) {
       return Optional.of(opponents.get(0));
    }
}
