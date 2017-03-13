package game;

public class Attributes {

    // later on have some basic tradeoffs of energy vs strength/vision/intelligence
    // for now, attributes and state
    private int life;
    private final int maxLife;
    private int speed;
    private int age;
    private boolean alive = true;
    private Action action;

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

    public boolean isAlive() {
        return alive;
    }

    public void setAction(Action action) { this.action = action; }

    public int getAge() { return this.age; }

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
}
