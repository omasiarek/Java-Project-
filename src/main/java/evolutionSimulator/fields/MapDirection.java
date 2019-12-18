package evolutionSimulator.fields;

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

    public static MapDirection parseToMapDirection(int direction) {
        switch (direction) {
            case 0:
                return MapDirection.UP;
            case 1:
                return MapDirection.UPRIGHT;
            case 2:
                return MapDirection.RIGTH;
            case 3:
                return MapDirection.DOWNRIGHT;
            case 4:
                return MapDirection.DOWN;
            case 5:
                return MapDirection.DOWNLEFT;
            case 6:
                return MapDirection.LEFT;
            case 7:
                return MapDirection.UPLEFT;
            default:
                throw new IllegalArgumentException(direction + " isn't correct");
        }
    }

    public int toInt() {
        switch (this) {
            case UP:
                return 0;
            case UPRIGHT:
                return 1;
            case RIGTH:
                return 2;
            case DOWNRIGHT:
                return 3;
            case DOWN:
                return 4;
            case DOWNLEFT:
                return 5;
            case LEFT:
                return 6;
            case UPLEFT:
                return 7;
        }
        return -1;
    }


}
