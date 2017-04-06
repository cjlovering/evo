package learning;

import game.Store;
import game.Agent;

import java.util.List;
import java.util.Optional;

public class RandomModel implements IModel {
    public void determineActions(Store store) {
        store.getAgents().stream().forEach(Agent::randomAction);
    }

    /**
     * no updates as this model is purely random
     */
    public void update(List<Integer> rewards) {}

    public Optional<Agent> determineOpponent(Agent fighter, List<Agent> opponents) {
       return Optional.of(opponents.get(0));
    }

    public Optional<Agent> determineMate(Agent mater, List<Agent> candidates) {
        return Optional.of(candidates.get(0));
    }
}
