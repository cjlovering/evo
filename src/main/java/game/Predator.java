package game;

import java.util.List;

public class Predator extends Agent {
    public final String _class = "predator"; //this is used for json conversion & css classes

    public Predator(Location l){
        super(l);
    }

    @Override
    public void rest() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void fight(List<Agent> opponents) {

    }

    @Override
    public Predator mate() {
        return new Predator(this.location.copy());
    }

}
