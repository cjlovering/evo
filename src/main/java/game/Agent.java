package game;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

abstract public class Agent {
    protected Location location;
    protected Attributes attr;

    public Agent(Location l){
        this.location = l;
    }

    /**
     * Executes random action
     */
    public void randomAction(){
        List<Action> actions = Arrays.asList(Action.values());
        this.attr.setAction(actions.get((int)Math.random()*actions.size()));
        this.executeAction();
    }

    public Action getAction() {
        return this.attr.getAction();
    }

    public void executeAction() {
        this.executeAction(Collections.emptyList());
    }

    /**
     * Executes agent's action
     * @param opponents - if they fight, the opponents to fight
     */
    public void executeAction(List<Agent> opponents) {
        switch (this.attr.getAction()) {
            case RIGHT:
                this.location.right();
                break;
            case LEFT:
                this.location.left();
                break;
            case UP:
                this.location.up();
                break;
            case DOWN:
                this.location.down();
                break;
            case REST:
                this.rest();
                break;
            case HIDE:
                this.hide();
                break;
            case MATE:
                this.mate();
                break;
            case FIGHT:
                this.fight(opponents);
                break;
            default:
                // invalid action (TODO: log this)
                break;
        }
    }

    public void selectAction(List<Agent> agents) {
        //policy implemented here somehow...
        List<Action> actions = Arrays.asList(Action.values());
        this.attr.setAction(actions.get((int)(Math.random() * actions.size())));
    }

    abstract public void rest();
    abstract public void hide();
    abstract public Agent mate();
    abstract public void fight(List<Agent> opponents);

    protected Location getLocation() { return this.location; }

    /**
     * Determines if agent is doing a certain action
     * @param action
     * @return - if actions match
     */
    public boolean isDoing(Action action) {
        return action == this.attr.getAction();
    }

    public boolean adjacentTo(Agent other) {
        return this.location.isAdjacent(other.getLocation());
    }

    public boolean isAlive() { return this.attr.isAlive(); }
}
