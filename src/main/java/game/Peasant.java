package game;

import java.util.List;

public class Peasant extends Agent {
    public final String _class = "peasant";


    public Peasant(Location l){
        super(l, 25, 55, 4, 100);
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

    @Override
    public void fight(List<Agent> opponents) {
        boolean thisInflicts = true; //Math.random() < 0.05;
        this.fight(thisInflicts, opponents.get((int) (Math.random() * opponents.size())));
    }
}
