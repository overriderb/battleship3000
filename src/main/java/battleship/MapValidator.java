package battleship;

import battleship.entity.BattleMap;
import battleship.entity.Coordinate;
import battleship.entity.Decker;
import battleship.entity.Rules;
import battleship.entity.Ship;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MapValidator {

    private MapValidator() {
    }

    public static boolean isValidMap(BattleMap map) {
        List<Ship> ships = map.getShips();

        return isAllShipsInsideMap(ships) && isAllShipsOnMap(ships) && isDistanceSufficientBetweenShips(ships) && isAllShipsCorrectShape(ships);
    }

    private static boolean isAllShipsInsideMap(List<Ship> ships) {
        return ships.stream()
                .map(Ship::getDeckers)
                .flatMap(List::stream)
                .allMatch(decker -> isCoordinateInsideMap(decker.getPosition(), Rules.mapSize));
    }

    private static boolean isCoordinateInsideMap(Coordinate position, int mapSize) {
        int x = position.getHorizontal();
        int y = position.getVertical();
        return (x >= 1 && x <= mapSize) && (y >= 1 && y <= mapSize);
    }

    private static boolean isAllShipsOnMap(List<Ship> ships) {
        Map<Integer, Integer> shipsBySize = ships.stream()
                .collect(Collectors.groupingBy(ship -> ship.getDeckers().size()))
                .entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, entry -> entry.getValue().size()));

        Map<Integer, Integer> rules = Rules.ships;
        boolean result = true;
        for (Map.Entry<Integer, Integer> entry : rules.entrySet()) {
            int numberOfShipsOnMap = shipsBySize.getOrDefault(entry.getKey(), -1);
            result &= numberOfShipsOnMap == entry.getValue();
        }

        return result;
    }

    private static boolean isDistanceSufficientBetweenShips(List<Ship> ships) {
        return ships.stream().allMatch(ship -> {
            List<Ship> others = new ArrayList<>(ships);
            others.remove(ship);
            return others.stream().allMatch(otherShip ->
                    otherShip.getDeckers().stream().allMatch(otherDecker ->
                            ship.getDeckers().stream().allMatch(decker ->
                                    isDistanceSufficientBetweenCoordinates(decker.getPosition(), otherDecker.getPosition()))
                    )
            );
        });
    }

    private static boolean isDistanceSufficientBetweenCoordinates(Coordinate coordinate1, Coordinate coordinate2) {
        boolean horizontalDistanceSufficient = Math.abs(coordinate1.getHorizontal() - coordinate2.getHorizontal()) >= 2;
        boolean verticalDistanceSufficient = Math.abs(coordinate1.getVertical() - coordinate2.getVertical()) >= 2;
        return horizontalDistanceSufficient || verticalDistanceSufficient;
    }

    private static boolean isAllShipsCorrectShape(List<Ship> ships) {
        boolean shipsIndissoluble = ships.stream()
                .allMatch(ship -> isShipsIndissoluble(ship.getDeckers().get(0),
                        ship.getDeckers().stream()
                                .filter(decker -> !decker.equals(ship.getDeckers().get(0))).collect(Collectors.toList())));
        boolean shipsHaveStraightShape = ships.stream().allMatch(MapValidator::isShipHasStraightShape);

        return shipsIndissoluble && shipsHaveStraightShape;
    }

    private static boolean isPartOfOneShip(Coordinate position1, Coordinate position2) {
        boolean verticalShip = position1.getHorizontal() == position2.getHorizontal() && Math.abs(position1.getVertical() - position2.getVertical()) == 1;
        boolean horizontalShip = position1.getVertical() == position2.getVertical() && Math.abs(position1.getHorizontal() - position2.getHorizontal()) == 1;
        return verticalShip || horizontalShip;
    }

    private static boolean isShipsIndissoluble(Decker prevDecker, List<Decker> deckers) {
        if (deckers.isEmpty()) {
            return true;
        } else {
            return isPartOfOneShip(prevDecker.getPosition(), deckers.get(0).getPosition())
                    && isShipsIndissoluble(deckers.get(0), deckers.stream().filter(decker -> !decker.equals(deckers.get(0))).collect(Collectors.toList()));
        }
    }

    private static boolean isShipHasStraightShape(Ship ship) {
        Decker firstDecker = ship.getDeckers().get(0);
        boolean horizontalPositionEqual = ship.getDeckers().stream()
                .allMatch(decker -> decker.getPosition().getHorizontal() == firstDecker.getPosition().getHorizontal());
        boolean verticalPositionEqual = ship.getDeckers().stream()
                .allMatch(decker -> decker.getPosition().getVertical() == firstDecker.getPosition().getVertical());

        return horizontalPositionEqual || verticalPositionEqual;
    }
}
