package game;

import java.util.ArrayList;

public class Peasant extends Agent {
    public final String _class = "peasant";

    private ArrayList<Peasant> children = new ArrayList<>();
    public Peasant(Location l){
        super(l);
    }
    public void randomMove(){
        this.chooseMove((int)(Math.random() * 6));
    }
    public void rest() {} //TODO
    public void hide() {} //TODO
    public Peasant mate() {
        Peasant child =  new Peasant(this.location.copy());
        this.children.add(child);
        return child;
    }
    public int getType() { return 1; }
}
