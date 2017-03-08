package game;

public class Attributes {
    // later on have some basic tradeoffs of energy vs strength/vision/intelligence
    // for now, attributes and state
    private int life;
    private int speed;
    private boolean alive = true;

    public Attributes(int life, int speed) {
        this.life = life;
        this.speed = speed;
    }

    public int getLife() {
        return life;
    }

    public int getSpeed() {
        return speed;
    }

    public boolean isAlive() {
        return alive;
    }

    public void deltaLife(int delta) {
        if (this.life + delta < 0) {
            this.life = -1;
            this.alive = false;
        } else {
            this.life += delta; // we can gain life...
        }
    }
}
