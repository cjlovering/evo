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

        // select a move (side-effect: clear old state)
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
              .forEach(agent -> agent.executeAction(agents.stream()
                                                          .filter(other -> agent.adjacentTo(other))
                                                          .collect(Collectors.toList())));

        // execute actions in correct order
        order.stream()
             .forEach(action -> agents.parallelStream()
                                      .filter(agent -> agent.isDoing(action))
                                      .forEach(Agent::executeAction));

        // get new children and join them TODO: condense this
        List<Agent> maters =  agents.stream()
                                    .filter(agent -> agent.isDoing(Action.MATE))
                                    .collect(Collectors.toList());

        List<Agent> peasantMaters =  maters.stream()
                                           .filter(agent -> agent instanceof Peasant)
                                           .collect(Collectors.toList());

        List<Agent> predatorMaters =  maters.stream()
                                            .filter(agent -> agent instanceof Predator)
                                            .collect(Collectors.toList());

        List<Agent> peasantChildren =
                peasantMaters.stream()
                             .map(agent -> ((Peasant)agent).mate(peasantMaters.stream().filter(other -> agent.adjacentTo(other)).findFirst()))
                             .filter(Optional::isPresent)
                             .map(Optional::get)
                             .collect(Collectors.toList());

        List<Agent> predatorChildren =
                predatorMaters.stream()
                        .map(agent -> ((Predator)agent).mate(predatorMaters.stream().filter(other -> agent.adjacentTo(other)).findFirst()))
                        .filter(Optional::isPresent)
                        .map(Optional::get)
                        .collect(Collectors.toList());

        // remove the dead
        agents.addAll(peasantChildren);
        agents.addAll(predatorChildren);

        return agents.stream()
                     .filter(Agent::isAlive)
                     .collect(Collectors.toList());
    }
}
