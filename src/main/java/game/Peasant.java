package game;

import java.util.ArrayList;
import java.util.List;

public class Peasant extends Agent {
    public final String _class = "peasant";

    private ArrayList<Peasant> children = new ArrayList<>();
    public Peasant(Location l){
        super(l);
    }

    @Override
    public void rest() {} //TODO

    @Override
    public void hide() {} //TODO

    @Override
    public Peasant mate() {
        Peasant child =  new Peasant(this.location.copy());
        this.children.add(child);
        return child;
    }

    @Override
    public void fight(List<Agent> opponents) {

    }
}
