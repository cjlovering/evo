package game;

public class Predator extends Agent {
    public final String _class = "predator";
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
}
