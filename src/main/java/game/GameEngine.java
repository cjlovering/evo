package game;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GameEngine {

    /**
     * runs an iteration of the game
     * TODO: make this more general, so that rules are definable
     * @param agents
     * @param map
     * @param meta
     */
    public static void run(List<Agent> agents, GameMap map, GameMeta meta) {

        // select a move (side-effect: clear old state)
        agents.stream().forEach(a -> a.selectAction(agents));

        // filter groups
        Map<Action, List<Agent>> groups =
                agents.stream().collect(Collectors.groupingBy(Agent::getAction));


        // fight (only preds can fight)


        // move


        // rest


        // hide


        // mate


        // die

    }
}
