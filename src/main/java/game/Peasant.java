package game;

public class Peasant extends Agent {
    public final String _class = "peasant";
    public Peasant(Location l){
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
