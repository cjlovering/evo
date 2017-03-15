package learning;

import controller.Store;
import game.Agent;

import java.util.List;
import java.util.Optional;

public class BasicModel implements IModel {

    /**
     * this model will make very simplistic choices
     * @param store
     */
    public void determineActions(Store store) {
        store.getAgents().stream().forEach(Agent::randomAction);
    }

    public Optional<Agent> determineOpponent(Agent fighter, List<Agent> opponents) {
        return opponents.stream()
                 .filter(opponent -> !fighter.mateCompatible(opponent))
                 .findFirst();
    }

}
