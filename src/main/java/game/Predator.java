package game;

import java.util.List;

public class Predator extends Agent {
    public final String _class = "predator"; //this is used for json conversion & css classes

    public Predator(Location l){
        super(l, 45, 200, 9, 250);
        this.attr = new Attributes(2, 1);
    }

    @Override
    public boolean canMate() {
        return (this.attr.getAge() > 35);
    }

    @Override
    public void rest() {
        this.attr.deltaLife(1);
    }

    @Override
    public void hide() {
        // no operation - this unit is not visible when 3 or further away
    }

    @Override
    public void fight(List<Agent> opponents) {
        // lets start with no mathematical incentive of helping each other
        boolean thisInflicts = false; //Math.random() < 0.9;
        this.fight(thisInflicts, opponents.get((int) (Math.random() * opponents.size())));
    }

    @Override
    public Agent copy() {
        return new Peasant(this.location);
    }
}
