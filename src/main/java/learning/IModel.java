package learning;

import game.Store;
import game.Agent;

import java.util.List;
import java.util.Optional;

/**
 * This IModel interface will make the decisions for each agent of
 * both classes. Internally, likely this will be implemented as a
 * two models and
 */
public interface IModel {
    public void determineActions(Store dataStore);
    public void update(List<Integer> rewards);

    public Optional<Agent> determineMate(Agent mater, List<Agent> candidates);
    public Optional<Agent> determineOpponent(Agent fighter, List<Agent> opponents);
}
