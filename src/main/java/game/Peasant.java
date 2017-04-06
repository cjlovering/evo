package game;

public class Peasant extends Agent {

    public Peasant(Location l){
        super(l, "peasant", 25, 55, 4, 100, 0.10);
        this.attr = new Attributes(1, 1, 5);
    }

    @Override
    public void rest() {
        this.attr.deltaHunger(2);
    }

    @Override
    protected void victory() {
        //no-op
    }

    @Override
    public void hide() {}

    @Override
    public Agent copy() {
        return new Peasant(this.location);
    }
}
