import static java.lang.Math.ceil;

public class Jungle {
    private Vector2d lowerLeft;
    private Vector2d upperRight;

    public Jungle(int width, int height, double percent) {
        int x = (int) ceil(width * percent / 2.0);
        int y = (int) ceil(height * percent / 2.0);
        this.lowerLeft = new Vector2d(width / 2 - x, height / 2 - y);
        this.upperRight = new Vector2d(width / 2 + x, height / 2 - y);
    }

    public boolean isInJungle(Vector2d vector) {
        return this.lowerLeft.precedes(vector) && this.upperRight.follows(vector);
    }
}
