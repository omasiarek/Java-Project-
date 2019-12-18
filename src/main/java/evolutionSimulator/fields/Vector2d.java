package evolutionSimulator.fields;

import java.util.Objects;

public class Vector2d {
    public final int x;
    public final int y;

    public Vector2d(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public String toString() {
        return "(" + x + "," + y + ")";
    }

    public boolean precedes(Vector2d vector) {
        return this.x <= vector.x && this.y <= vector.y;
    }

    public boolean follows(Vector2d vector) {
        return this.x >= vector.x && this.y >= vector.y;
    }

    public Vector2d add(Vector2d vector) {
        return new Vector2d(this.x + vector.x, this.y + vector.y);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vector2d vector2d = (Vector2d) o;
        return x == vector2d.x &&
                y == vector2d.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
