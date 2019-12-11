public enum MapDirection {
    UP,
    UPRIGHT,
    RIGTH,
    DOWNRIGHT,
    DOWN,
    DOWNLEFT,
    LEFT,
    UPLEFT;

    public Vector2d toUnitVector() {
        switch (this) {
            case UP:
                return new Vector2d(0, 1);
            case UPRIGHT:
                return new Vector2d(1, 1);
            case RIGTH:
                return new Vector2d(1, 0);
            case DOWNRIGHT:
                return new Vector2d(1, -1);
            case DOWN:
                return new Vector2d(0, -1);
            case DOWNLEFT:
                return new Vector2d(-1, -1);
            case LEFT:
                return new Vector2d(-1, 0);
            case UPLEFT:
                return new Vector2d(-1, 1);
            default:
                return new Vector2d(0, 0);
        }

    }
}
