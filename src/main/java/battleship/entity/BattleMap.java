package battleship.entity;

import java.util.List;
import java.util.Objects;

public class BattleMap {

    private final List<Ship> ships;

    public BattleMap(List<Ship> ships) {
        this.ships = ships;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BattleMap battleMap = (BattleMap) o;
        return Objects.equals(ships, battleMap.ships);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ships);
    }

    @Override
    public String toString() {
        return "BattleMap{" +
                "ships=" + ships +
                '}';
    }

    public List<Ship> getShips() {
        return ships;
    }
}
