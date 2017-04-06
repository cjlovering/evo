package game;

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
        List<Agent> children =
                agents.stream()
                        .filter(agent -> agent.isDoing(Action.MATE)) //get all maters
                        .map(agent -> agent.mate(model.determineMate(agent, //for each determine who is mating
                            agents.stream().filter(other -> agent.adjacentTo(other)).collect(Collectors.toList()))))
                        .filter(Optional::isPresent)    //filter for all present children
                        .map(opt -> opt.get())          //can't be reduced
                        .collect(Collectors.toList());

        // add the new
        agents.addAll(children);

        // clear the dead & the old
        agents.stream().forEach(Agent::cleanUp);

        // determine rewards
        agents.stream().forEach(Agent::determineReward);

        // over population - any agent that has 4 neighbors of the same race dies
        // this could be implemented in a far better way...
        agents.stream()
                .forEach(agent -> agent.overpopulation((int)
                        agents.stream()
                                .filter(other -> agent.adjacentTo(other))
                                .filter(other -> agent.mateCompatible(other))
                                .count()));

        // remove the dead
        agents.removeAll(agents.parallelStream()
                                .filter(Agent::isDead)
                                .collect(Collectors.toList()));

        // update model
        model.update(agents.parallelStream()
                           .map(Agent::getReward)
                           .collect(Collectors.toList()));

        // complete the step in the game
        store.step();

        return agents;
    }
}
