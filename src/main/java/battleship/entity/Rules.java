package battleship.entity;

import java.util.HashMap;
import java.util.Map;

public class Rules {

    public static final int mapSize = 10;
    public static final Map<Integer, Integer> ships = new HashMap<>();

    static {
        ships.put(1, 4);
        ships.put(2, 3);
        ships.put(3, 2);
        ships.put(4, 1);
    }
}
