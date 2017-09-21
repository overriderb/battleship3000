package battleship.entity;

import java.util.Objects;

public class Decker {

    private final Coordinate position;

    public Decker(Coordinate position) {
        this.position = position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Decker decker = (Decker) o;
        return Objects.equals(position, decker.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(position);
    }

    @Override
    public String toString() {
        return "Decker{" +
                "position=" + position +
                '}';
    }

    public Coordinate getPosition() {
        return position;
    }
}
