package game;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GameEngine {

    /**
     * runs an iteration of the game
     * @param agents
     * @param map
     * @param meta
     */
    public static List<Agent> run(List<Agent> agents, GameMap map, GameMeta meta) {

        // select a move (side-effect: clear old state)
        agents.stream().forEach(a -> a.selectAction(agents));

        // filter groups
        Map<Action, List<Agent>> groups =
                agents.stream().collect(Collectors.groupingBy(Agent::getAction));

        // define order of actions
        List<Action> order =
                Arrays.asList(Action.FIGHT, Action.LEFT, Action.DOWN, Action.RIGHT,
                              Action.UP,    Action.REST, Action.HIDE);

        // execute actions in correct order
        // TODO: for non-fight, we want to just give an empty list
        order.stream()
             .forEach(action ->
                     groups.get(action)
                           .forEach(agent -> agent.executeAction(agents.stream()
                                                                       .filter(other -> agent.adjacentTo(other))
                                                                       .collect(Collectors.toList()))));

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
