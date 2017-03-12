package game;

import controller.Store;
import learning.IModel;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GameEngine {

    /**
     * runs an iteration of the game
     * @param store - contains the agents, map & meta data
     * @param model - the po
     */
    public static List<Agent> run(Store store, IModel model) {
        List<Agent> agents = store.getAgents();
        GameMap map = store.getGameMap();
        GameMeta meta = store.getGameMeta();

        // select a move (side-effect: clear old state)
        model.determineActions(store);

        agents.stream().forEach(a -> a.selectAction(agents));

        // filter groups
        Map<Action, List<Agent>> groups =
                agents.stream().collect(Collectors.groupingBy(Agent::getAction));

        // define order of actions
        List<Action> order =
                Arrays.asList(Action.LEFT, Action.DOWN, Action.RIGHT,
                              Action.UP,   Action.REST, Action.HIDE);

        // fight require adjacent agents (actually it may depend on more than that depending on rules...)
        groups.get(Action.FIGHT)
              .forEach(agent -> agent.executeAction(agents.stream()
                                                          .filter(other -> agent.adjacentTo(other))
                                                          .collect(Collectors.toList())));

        // execute actions in correct order
        order.stream()
             .forEach(action ->
                     groups.get(action)
                           .forEach(Agent::executeAction));

        // get new children and join them
        List<Agent> children =
                groups.get(Action.MATE)
                      .stream()
                      .map(Agent::mate)
                      .collect(Collectors.toList());

        agents.addAll(children);
        return agents.stream()
                     .filter(Agent::isAlive)
                     .collect(Collectors.toList());
    }
}
