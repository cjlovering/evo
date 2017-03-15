package game;

import controller.Store;
import learning.IModel;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class GameEngine {

    /**
     * runs an iteration of the game
     * @param store - contains the agents, map & meta data
     * @param model - the policy maker
     */
    public static List<Agent> run(Store store, IModel model) {
        List<Agent> agents = store.getAgents();
        GameMap map = store.getGameMap();
        GameMeta meta = store.getGameMeta();

        // upkeep
        agents.stream().forEach(Agent::upkeep);

        // select a move
        model.determineActions(store);

        // filter groups
        Map<Action, List<Agent>> groups =
                agents.stream().collect(Collectors.groupingBy(Agent::getAction));

        // define order of actions
        List<Action> order =
                Arrays.asList(Action.LEFT, Action.DOWN, Action.RIGHT,
                              Action.UP,   Action.REST, Action.HIDE);

        // fight require adjacent agents (actually it may depend on more than that depending on rules...)
        agents.stream()
              .filter(agent -> agent.isDoing(Action.FIGHT))
              .forEach(agent -> agent.fight(model.determineOpponent(agent, agents.stream()
                                                                  .filter(other -> agent.adjacentTo(other))
                                                                  .collect(Collectors.toList()))));

        // execute actions in correct order
        order.stream()
             .forEach(action -> agents.parallelStream()
                                      .filter(agent -> agent.isDoing(action))
                                      .forEach(Agent::executeAction));

        // get new children and join them
        List<Agent> maters =  agents.stream()
                                    .filter(agent -> agent.isDoing(Action.MATE))
                                    .collect(Collectors.toList());

        List<Agent> children =
                maters.stream()
                             .map(agent -> agent.mate(maters.stream().filter(other -> agent.adjacentTo(other)).findFirst()))
                             .filter(Optional::isPresent)
                             .map(Optional::get)
                             .collect(Collectors.toList());

        // add the new
        agents.addAll(children);

        // clear the dead & the old
        agents.stream().forEach(Agent::cleanUp);

        // remove the dead
        agents.removeAll(agents.parallelStream()
                                .filter(Agent::isDead)
                                .collect(Collectors.toList()));

        return agents;
    }
}
