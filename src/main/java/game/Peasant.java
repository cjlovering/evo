package game;

public class Peasant extends Agent {
    private int neighbors = 0;

    public Peasant(Location l){
        super(l, "peasant", 20, 60, 10, 75, 0.05);
        this.attr = new Attributes(1, 1, 5);
    }

    @Override
    public void overpopulation(int neighbors) {
        // we want to emulate scarcity of resources
        // general idea of rest is that when the peasant is still it
        // can gather food, not true if there are a lot of peasants
        // rather than handle amount of resources directly
        // we'll just reduce amount of food available
        this.neighbors = neighbors;
    }

    @Override
    public void rest() {
        this.attr.deltaHunger(2 - neighbors);
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
