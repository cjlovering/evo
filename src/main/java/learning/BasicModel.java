package learning;

import game.Store;
import game.Agent;

import java.util.List;
import java.util.Optional;

public class BasicModel implements IModel {

    /**
     * this model will make very simplistic choices
     * its random, but will only attack agents of the other type
     *             and only attempt to mate agents of the same type
     * @param store
     */
    public void determineActions(Store store) {
        store.getAgents().stream().forEach(Agent::randomAction);
    }

    /**
     * this model does not update or learn
     */
    public void update(List<Integer> noOp) {}

    /**
     * attacks the first agent next to it that is not of its type of agent
     * @param fighter
     * @param opponents
     * @return
     */
    public Optional<Agent> determineOpponent(Agent fighter, List<Agent> opponents) {
        return opponents.stream()
                 .filter(opponent -> !fighter.mateCompatible(opponent))
                 .findFirst();
    }

    /**
     * attempts to mate with the first agent next to it that is not its type of agent
     * @param mater
     * @param candidates
     * @return
     */
    public Optional<Agent> determineMate(Agent mater, List<Agent> candidates) {
        return candidates.stream()
                .filter(other -> mater.mateCompatible(other))
                .findFirst();
    }

}
