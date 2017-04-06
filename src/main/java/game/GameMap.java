package game;

public class GameMap {
    //for now just basic square, but this later can become more complicated...
    // with terrain/grass and irregular shapes...
    private final int width;
    private final int height;

    public GameMap(int width, int height) {
        this.width = width;
        this.height = height;
    }

    /**
     * determines if given coordinates are legal
     * @param x
     * @param y
     * @return
     */
    protected boolean legalMove(int x, int y) {
        return (x < this.width && x > 0) && (y < this.height && y > 0);
    }

    /**
     * Creates and returns a random location on this map
     * @return
     */
    public Location getRandomLocation() {
        return new Location( (int)(Math.random() * (width - 1)), (int)(Math.random() * (height - 1)), this);
    }

}
