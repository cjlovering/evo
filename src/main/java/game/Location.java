package game;

public class Location {
    private int x;
    private int y;
    private GameMap map;

    public Location(int x, int y, GameMap map) {
        this.x = x;
        this.y = y;
        this.map = map;
    }

    public static Location randomLocation(int max, GameMap map) {
        return new Location( (int)(Math.random() * (max - 1)), (int)(Math.random() * (max - 1)), map);
    }

    /**
     * chooses a random amount of movement up to max
     * @param max
     */
    protected void randomDelta(int max) {
        int move = (int)(Math.random() * (max + 1)) * (Math.random() > 0.5 ? -1 : 1);

        if (Math.random() > 0.5) {
            if (this.map.legalMove(this.x + move, this.y)) {
                this.x += move;
            } else {
                this.x -= move;
            }
        } else {
            if (this.map.legalMove(this.x, this.y + move)) {
                this.y += move;
            } else {
                this.y -= move;
            }
        }
    }

    protected Location copy() {
        return new Location(this.x, this.y, this.map);
    }

    protected void left() {this.horizontal(-1);}
    protected void right() {this.horizontal(1);}
    protected void horizontal(int move) {
        if (this.map.legalMove(this.x + move, this.y)) {
            this.x += move;
        }
    }

    protected void down() {this.vertical(-1);}
    protected void up() {this.vertical(1);}
    protected void vertical(int move) {
        if (this.map.legalMove(this.x, this.y + move)) {
            this.y += move;
        }
    }

    protected int getX() { return this.x; }
    protected int getY() { return this.y; }

    protected boolean isAdjacent(Location other) {
        return  Math.abs(this.x - other.x) <= 1 && Math.abs(this.y - other.y) <= 1;
    }

}
