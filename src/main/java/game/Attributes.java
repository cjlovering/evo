package game;

public class Attributes {

    //  status of a unit
    private int life;
    private final int maxLife;
    private int speed; //currently unused
    private int age = 0;
    private boolean alive = true;
    private Action action;
    private int lastMate = 0;//currently unused
    private int reward = 0;
    private int hungerLevel;

    public Attributes(int life, int speed, int hungerLevel) {
        this.life = life;
        this.maxLife = life;
        this.speed = speed;
        this.hungerLevel = hungerLevel;
    }

    public Action getAction() { return action; }

    public boolean isAlive() {
        return alive;
    }

    public void setAction(Action action) { this.action = action; }

    public int getAge() { return this.age; }

    public void setLastMate() { this.lastMate = this.age; }

    /**
     * changes the agent's current health by this number
     *   if the agent is to lose health, the number is negative
     *   if the agent is to gain health, the number is positive
     * @param delta - amount to change by
     */
    public void deltaLife(int delta) {

        if (this.life + delta <= maxLife) {
            this.life += delta;
        }

        if (this.life + delta <= 0)
            this.alive = false;
    }

    /**
     * determines if agent is eligible for mating
     * @param IS_ADULT
     * @param IS_OLD
     * @param TIME_OUT
     * @return whether or not they are eligible for mating
     */
    protected boolean mateEligible(int IS_ADULT, int IS_OLD, int TIME_OUT) {
        //TODO: issue with two of the checks...
        return (this.isAlive() //is alive
                // && (this.getAge() > IS_ADULT ) // because its random they die too fast for this
                && (this.getAge() < IS_OLD * 365));  //is not too hold
               // && (this.getAge() - this.getLastMate() > TIME_OUT) ); //some cost of energy?
    }

    /**
     * Performs necessary updates at the begining of a step.
     */
    public void upkeep() {
        this.age++;
        this.reward = 0;
    }

    /**
     * Called at the end of a step of the game
     * If the unit's age is past the maxLife,
     * it will die with a random chance
     */
    public void cleanUp() {
        if (this.age > (365 * this.maxLife) && Math.random() < 0.35) {
            this.alive = false;
        }
        if (this.hungerLevel < 0 && Math.random() < 0.35) {
            this.alive = false;
        }
        this.hungerLevel--;
    }

    /**
     * Change the reward for the turn
     * @param r
     */
    public void deltaReward(int r) {
        this.reward += r;
    }


    /**
     * Get less hungry
     * @param h
     */
    public void deltaHunger(int h) {
        this.hungerLevel += h;
    }

    /*
     * @return current reward for this time step
     */
    public int getReward() {
        return this.reward;
    }

}
