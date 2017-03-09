package game;

public class GameMap {
    //for now just basic square, but this later can become more complicated...
    // with terrain/grass and irregular shapes...
    private final int width;
    private final int height;
    private int[][] map;

    public GameMap(int width, int height) {
        this.width = width;
        this.height = height;
    }

    protected boolean legalMove(int x, int y) {
        return (x < this.width && x > 0) && (y < this.height && y > 0);
    }

    protected void generate() {
        this.map = new int[this.height][this.width];
    }

    protected void insertLocation(Location location, int type) {
        this.map[location.getY()][location.getX()] = type;
    }
}
