package game;

public class Predator extends Agent {

    public Predator(Location l){
        super(l, "predator", 45, 200, 9, 250, 0.90);
        this.attr = new Attributes(2, 1, 15);
    }

    @Override
    public void hide() {
        // no operation - this unit is not visible when 3 or further away
    }

    @Override
    public void rest() {
        // +1 resting, -1 for turn
        this.attr.deltaHunger(1);

        this.attr.deltaLife(1);
    }

    @Override
    protected void victory() {
        this.attr.deltaHunger(15);
    }

    @Override
    public Agent copy() {
        return new Peasant(this.location);
    }
}
