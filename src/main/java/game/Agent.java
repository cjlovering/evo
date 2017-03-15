package game;

import java.util.*;

abstract public class Agent {
    protected Location location;
    protected Attributes attr;

    protected final int IS_ADULT;
    protected final int IS_OLD;
    protected final int TIME_OUT;
    protected final int MAX_AGE;
    protected final double HURTS_OPPONENT;

    private ArrayList<Agent> children = new ArrayList<>();

    public Agent(Location l, int IS_ADULT, int IS_OLD, int TIME_OUT, int MAX_AGE, double HURTS_OPPONENT){
        this.location = l;
        this.IS_ADULT = IS_ADULT;
        this.IS_OLD = IS_OLD;
        this.TIME_OUT = TIME_OUT;
        this.MAX_AGE = MAX_AGE;
        this.HURTS_OPPONENT = HURTS_OPPONENT;
    }

    /**
     * sets random action
     */
    public void randomAction(){
        List<Action> actions = Arrays.asList(Action.values());
        this.attr.setAction(actions.get((int)(Math.random()*actions.size())));
    }

    public Action getAction() {
        return this.attr.getAction();
    }


    public void losesLife(int i) {
        this.attr.deltaLife(-i);
    }

    /**
     * executes agent's action
     */
    public void executeAction() {
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
            default:
                // invalid action (TODO: log this)
                break;
        }
    }

    abstract public void rest();
    abstract public void hide();

    public void addChild(Agent child) {
        this.children.add(child);
        this.attr.setLastMate();
    }

    public Optional<Agent> mate(Optional<Agent> other) {
        if (other.isPresent()
                && this.canMate()
                && other.get().canMate()
                && this.mateCompatible(other.get())) {

            Agent child = this.copy();
            this.addChild(child);
            other.get().addChild(child);
            return Optional.of(child);
        }
        return Optional.empty();
    }

    abstract public Agent copy();


    public void fight(Optional<Agent> opponent ) {
        if (opponent.isPresent() && Math.random() < this.HURTS_OPPONENT) {
            opponent.get().losesLife(1);
        }
    }

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

    public boolean isDead() { return !this.attr.isAlive(); }


    public boolean canMate() {
        return this.attr.mateEligible(IS_ADULT, IS_OLD, TIME_OUT);
    }

    public boolean mateCompatible(Agent other) {
        return this.getClass().getSimpleName().equals(other.getClass().getSimpleName());
    }

    public void upkeep() {
        this.attr.upkeep();
    }

    public void cleanUp() {
        this.attr.cleanUp(this.MAX_AGE);
    }
}
