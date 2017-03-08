package game;

abstract public class Agent {
    protected Location location;
    protected Attributes attr;

    public Agent(Location l){
        this.location = l;
    }

    abstract public void randomMove();

    public void chooseMove(int action) {
        switch (action) {
            case 1:
                // do something
                this.location.right();
                break;
            case 2:
                this.location.left();
                break;
            case 3:
                this.location.up();
                break;
            case 4:
                this.location.down();
                break;
            case 5:
                // stay still... use less energy
                this.rest();
                break;
            case 6:
                // actively hide
                // (50% chance to be unseen unless within 2)
                this.hide();
                break;
        }
    }

    abstract public void rest();
    abstract public void hide();
}
