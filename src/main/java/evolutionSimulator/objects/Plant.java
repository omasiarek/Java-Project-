package evolutionSimulator.objects;

import evolutionSimulator.fields.Vector2d;

public class Plant implements IMapElement {
    private Vector2d position;
    public static final int ENERGY = 10;


    public Plant(Vector2d position) {
        this.position = position;
    }

    @Override
    public Vector2d getPosition() {
        return this.position;
    }

}
