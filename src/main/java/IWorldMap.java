/**
 * The interface responsible for interacting with the map of the world.
 * Assumes that Position and MoveDirection classes are defined.
 *
 * @author apohllo
 *
 */
public interface IWorldMap {
    /**
     * Indicate if any object can move to the given position.
     *
     * @param vector
     *            The position checked for the movement possibility.
     * @return True if the object can move to that position.
     */
    boolean canMoveTo(Vector2d vector);

    /**
     * Place a car on the map.
     *
     * @param animal
     *            The car to place on the map.
     * @return True if the car was placed. The car cannot be placed if the map is already occupied.
     */
    boolean place(Animal animal);

    /**
     * Move the cars on the map according to the provided move directions. Every
     * n-th direction should be sent to the n-th car on the map.
     *
     * @param directions
     *            Array of move directions.
     */
    void run(int directions);

    /**
     * Return true if given position on the map is occupied. Should not be
     * confused with canMove since there might be empty positions where the car
     * cannot move.
     *
     * @param vector
     *            Position to check.
     * @return True if the position is occupied.
     */
    boolean isOccupied(Vector2d vector);

    /**
     * Return an object at a given position.
     *
     * @param vector
     *            The position of the object.
     * @return Object or null if the position is not occupied.
     */
    Object objectAt(Vector2d vector);

    Vector2d placeAtMap (Vector2d vector);
}
