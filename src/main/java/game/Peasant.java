package game;

public class Peasant extends Agent {
    public final String _class = "peasant";


    public Peasant(Location l){
        super(l, 25, 55, 4, 100, 0.01);
        this.attr = new Attributes(1, 1);
    }

    @Override
    public void rest() {}

    @Override
    public void hide() {}

    @Override
    public Agent copy() {
        return new Peasant(this.location);
    }
}
