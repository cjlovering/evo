package game;

import java.util.*;

abstract public class Agent {
    /**
     * this string is used for CSS class names in the front end
     */
    public final String _class;

    /**
     * this location is where the agent is located
     */
    protected Location location;

    /**
     * these attributes describe the agent's status
     */
    protected Attributes attr;

    /**
     *
     */
    protected final int IS_ADULT;
    protected final int IS_OLD;
    protected final int TIME_OUT;
    protected final int MAX_AGE;
    protected final double HURTS_OPPONENT;

    private int lastChildrenSize = 0;

    /**
     * list of children, to be used for calculating rewards
     */
    private ArrayList<Agent> children = new ArrayList<>();

    public Agent(Location l, String _class, int IS_ADULT, int IS_OLD, int TIME_OUT, int MAX_AGE, double HURTS_OPPONENT){
        this.location = l;
        this._class = _class;
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

    public String getClassName() { return this._class; }

    public Action getAction() {
        return this.attr.getAction();
    }

    public void losesLife(int i) {
        this.attr.deltaLife(-i);
    }

    /**
     * this function determines the reward for this step
     *
     * TODO: we can make this more interesting
     * I want to avoid rewarding actions that I think are good,
     * and instead rewards having this type of agent survive.
     * To do this right it would be better to more directly reward
     * surviving longer than the other race.
     */
    public void determineReward() {

        if (this.children.size() > this.lastChildrenSize )
            this.attr.deltaReward(5);

        this.children.parallelStream()
                     .filter(Agent::isDead)
                     .forEach(agent -> this.attr.deltaReward(-10));
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

    public void rest() {
        this.attr.deltaLife(1);
    };

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
            victory();
        }
    }

    /**
     * What does winning a fight do to the agent?
     */
    abstract protected void victory();

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
        return this.getClassName().equals(other.getClassName());
    }

    public void upkeep() {
        this.attr.upkeep();
        this.lastChildrenSize = children.size();
    }

    public void cleanUp() {
        this.attr.cleanUp();
    }

    public Integer getReward() {
        return this.attr.getReward();
    }
}
