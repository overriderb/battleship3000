package battleship.entity;

import java.util.Objects;

public class Coordinate {

    private final int horizontal;
    private final int vertical;

    public Coordinate(int horizontal, int vertical) {
        this.horizontal = horizontal;
        this.vertical = vertical;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinate that = (Coordinate) o;
        return horizontal == that.horizontal &&
                vertical == that.vertical;
    }

    @Override
    public int hashCode() {
        return Objects.hash(horizontal, vertical);
    }

    @Override
    public String toString() {
        return "Coordinate{" +
                "horizontal=" + horizontal +
                ", vertical=" + vertical +
                '}';
    }

    public int getHorizontal() {
        return horizontal;
    }

    public int getVertical() {
        return vertical;
    }
}
