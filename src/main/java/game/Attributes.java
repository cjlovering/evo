package game;

public class Attributes {

    // later on have some basic tradeoffs of energy vs strength/vision/intelligence
    // for now, attributes and state
    private int life;
    private final int maxLife;
    private int speed;
    private int age = 0;
    private boolean alive = true;
    private Action action;
    private int lastMate = 0;

    public Attributes(int life, int speed) {
        this.life = life;
        this.maxLife = life;
        this.speed = speed;
    }

    public Action getAction() { return action; }

    public int getLife() {
        return life;
    }

    public int getSpeed() {
        return speed;
    }

    public int getLastMate() { return lastMate; }

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

        if (this.life + delta <= maxLife)
            this.life += delta;

        if (this.life + delta <= 0)
            this.alive = false;
    }

    protected boolean mateEligible(int IS_ADULT, int IS_OLD, int TIME_OUT) {
        return (this.isAlive()
                && (this.getAge() > IS_ADULT)
                && (this.getAge() < IS_OLD)
                && (this.getLastMate() - this.getAge() > TIME_OUT) );
    }

    public void upkeep() {
        this.age++;
    }

    public void cleanUp(int MAX_AGE) {
        if (this.life > MAX_AGE && Math.random() < 0.55) {
            this.alive = false;
        }
    }

}
