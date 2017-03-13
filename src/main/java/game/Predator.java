package game;

import java.util.List;
import java.util.Optional;

public class Predator extends Agent {
    public final String _class = "predator"; //this is used for json conversion & css classes

    public Predator(Location l){
        super(l);
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
        // TODO: I'd like it to be easy to change the rules...
        // lets start with no mathematical incentive of helping each other
        boolean thisInflicts = Math.random() > 0.1;
        this.fight(thisInflicts, opponents.get((int) (Math.random() * opponents.size())));
    }

    @Override
    public Optional<Agent> mate(Optional<Agent> other) {
        return Optional.of(new Predator(this.location.copy()));
    }

}
