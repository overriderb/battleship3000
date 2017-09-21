package battleship.entity;

import java.util.List;
import java.util.Objects;

public class Ship {

    private final List<Decker> deckers;

    public Ship(List<Decker> deckers) {
        this.deckers = deckers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ship ship = (Ship) o;
        return Objects.equals(deckers, ship.deckers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(deckers);
    }

    @Override
    public String toString() {
        return "Ship{" +
                "deckers=" + deckers +
                '}';
    }

    public List<Decker> getDeckers() {
        return deckers;
    }
}
