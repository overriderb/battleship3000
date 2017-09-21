package battleship.entity;

import java.util.Objects;

public class Player {

    private final String name;
    private final BattleMap map;

    public Player(String name, BattleMap map) {
        this.name = name;
        this.map = map;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(name, player.name) &&
                Objects.equals(map, player.map);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, map);
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", map=" + map +
                '}';
    }

    public String getName() {
        return name;
    }

    public BattleMap getMap() {
        return map;
    }
}
