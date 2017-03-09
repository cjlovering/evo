package game;

public class Predator extends Agent {
    public final String _class = "predator";
    //public final Agents _class = Agents.peasant;

    public Predator(Location l){
        super(l);
    }
    public void randomMove(){
        this.chooseMove((int)(Math.random() * 6));
    }
    public void rest() {

    }
    public void hide() {

    }
    public Predator mate() {
        return new Predator(this.location.copy());
    }

    public int getType() { return 2; }

}
