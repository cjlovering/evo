package game;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Peasant extends Agent {
    public final String _class = "peasant";

    private ArrayList<Peasant> children = new ArrayList<>();

    public Peasant(Location l){
        super(l);
        this.attr = new Attributes(1, 1);
    }

    @Override
    public boolean canMate() {
        return ((this.attr.getAge() > 25) && (this.attr.getAge() < 75) );
    }

    @Override
    public void rest() {}

    @Override
    public void hide() {}

    @Override
    public Optional<Agent> mate(Optional<Agent> other) {
        if (other.isPresent()) {
            Peasant child = new Peasant(this.location.copy());
            this.children.add(child);
            return Optional.of(child);
        }
        return Optional.empty();
    }

    @Override
    public void fight(List<Agent> opponents) {
        boolean thisInflicts = Math.random() > 0.1;
        this.fight(thisInflicts, opponents.get((int) (Math.random() * opponents.size())));
    }
}
