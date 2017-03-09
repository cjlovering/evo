package game;

import java.util.List;

public class GameEngine {
    /**
     * runs an iteration of the game
     * TODO: make this more general, so that rules are definable
     * @param agents
     * @param map
     * @param meta
     */
    public static void run(List<Agent> agents, GameMap map, GameMeta meta) {

        // select a move


        //

        //for ease track agents in a representation of the map
        agents.stream().forEach(a -> map.insertLocation(a.getLocation(), a.getType()));

        // fight
        agents.stream().forEach(a -> a.chooseMove(5));


        // height


        // move


        // rest


        // hide


        // mate


        // rm the dead

    }
}
