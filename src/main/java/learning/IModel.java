package learning;

import controller.Store;
import game.Agent;

import java.util.List;
import java.util.Optional;

public interface IModel {
    public void determineActions(Store dataStore);
    public Optional<Agent> determineOpponent(Agent fighter, List<Agent> opponents);
}
