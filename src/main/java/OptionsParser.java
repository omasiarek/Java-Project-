public class OptionsParser {

    public MapDirection parseToMapDirection (int direction){
        switch(direction){
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
            default :
                throw new IllegalArgumentException(direction + " isn't correct");
        }
    }

    public int parseToInt (MapDirection direction){
        switch (direction) {
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
