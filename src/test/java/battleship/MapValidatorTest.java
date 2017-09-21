package battleship;

import battleship.entity.BattleMap;
import battleship.entity.Coordinate;
import battleship.entity.Decker;
import battleship.entity.Ship;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class MapValidatorTest {

    @Test
    public void validatorSuccessfullyChecksThatAllShipInFieldOfTheMap() throws Exception {
        BattleMap map = validMap();
        assertTrue(MapValidator.isValidMap(map));
    }

    @Test
    public void validatorMarksMapAsInvalidBecauseOfOneShipOutOfFieldOfTheMap() throws Exception {
        Decker decker1 = new Decker(new Coordinate(11,1));
        Decker decker2 = new Decker(new Coordinate(11,2));
        Decker decker3 = new Decker(new Coordinate(11,3));
        Decker decker4 = new Decker(new Coordinate(11,4));
        List<Decker> deckers = new ArrayList<>();
        deckers.add(decker1);
        deckers.add(decker2);
        deckers.add(decker3);
        deckers.add(decker4);
        Ship invalidFourDecker = new Ship(deckers);

        List<Ship> ships = new ArrayList<>();
        ships.add(invalidFourDecker);
        ships.add(threeDecker1());
        ships.add(threeDecker2());
        ships.add(twoDecker1());
        ships.add(twoDecker2());
        ships.add(twoDecker3());
        ships.add(oneDecker1());
        ships.add(oneDecker2());
        ships.add(oneDecker3());
        ships.add(oneDecker4());

        BattleMap map = new BattleMap(ships);
        assertFalse(MapValidator.isValidMap(map));
    }

    @Test
    public void validatorSuccessfullyChecksThatMapContainsOnly4OneDecker3TwoDecker2ThreeDecker1FourDeckerShips() throws Exception {
        BattleMap map = validMap();
        assertTrue(MapValidator.isValidMap(map));
    }

    @Test
    public void validatorMarksMapSsInvalidMapWithOnly1OneDeckerShip() throws Exception {
        List<Ship> ships = new ArrayList<>();
        ships.add(oneDecker1());

        BattleMap map = new BattleMap(ships);
        assertFalse(MapValidator.isValidMap(map));
    }

    @Test
    public void validatorMarksMapAsInvalidMapWithoutShips() throws Exception {
        BattleMap map = new BattleMap(new ArrayList<>());
        assertFalse(MapValidator.isValidMap(map));
    }

    @Test
    public void validatorMarksMapAsInvalidMapWith2FourDeckerShips() throws Exception {
        Decker decker1 = new Decker(new Coordinate(1,7));
        Decker decker2 = new Decker(new Coordinate(1,8));
        Decker decker3 = new Decker(new Coordinate(1,9));
        Decker decker4 = new Decker(new Coordinate(1,10));
        List<Decker> deckers = new ArrayList<>();
        deckers.add(decker1);
        deckers.add(decker2);
        deckers.add(decker3);
        deckers.add(decker4);
        Ship additionalFourDecker = new Ship(deckers);

        List<Ship> ships = new ArrayList<>();
        ships.add(fourDecker());
        ships.add(additionalFourDecker);
        ships.add(threeDecker1());
        ships.add(threeDecker2());
        ships.add(twoDecker1());
        ships.add(twoDecker2());
        ships.add(twoDecker3());
        ships.add(oneDecker1());
        ships.add(oneDecker2());
        ships.add(oneDecker3());
        ships.add(oneDecker4());

        BattleMap map = new BattleMap(ships);
        assertFalse(MapValidator.isValidMap(map));
    }

    @Test
    public void validatorSuccessfullyChecksDistanceBetweenAllShipsOnMapNotLessThenOneCell() throws Exception {
        BattleMap map = validMap();
        assertTrue(MapValidator.isValidMap(map));
    }

    @Test
    public void validatorMarksMapAsInvalidWhenDistanceNetweenTwoShipsOnMapLessThenOneCell() throws Exception {
        Decker decker1 = new Decker(new Coordinate(7,6));
        List<Decker> deckers = new ArrayList<>();
        deckers.add(decker1);
        Ship invalidOneDecker = new Ship(deckers);

        List<Ship> ships = new ArrayList<>();
        ships.add(fourDecker());
        ships.add(threeDecker1());
        ships.add(threeDecker2());
        ships.add(twoDecker1());
        ships.add(twoDecker2());
        ships.add(twoDecker3());
        ships.add(oneDecker1());
        ships.add(oneDecker2());
        ships.add(oneDecker3());
        ships.add(invalidOneDecker);

        BattleMap map = new BattleMap(ships);
        assertFalse(MapValidator.isValidMap(map));
    }

    @Test
    public void validatorSuccessfullyChecksThatAllShipsOnMapHaveStraightShape() throws Exception {
        BattleMap map = validMap();
        assertTrue(MapValidator.isValidMap(map));
    }

    @Test
    public void validatorMarksMapAsInvalidWhenAtLeastOneShipHasNotStraightShape() throws Exception {
        Decker decker1 = new Decker(new Coordinate(1,8));
        Decker decker2 = new Decker(new Coordinate(1,9));
        Decker decker3 = new Decker(new Coordinate(1,10));
        Decker decker4 = new Decker(new Coordinate(2,10));
        List<Decker> deckers = new ArrayList<>();
        deckers.add(decker1);
        deckers.add(decker2);
        deckers.add(decker3);
        deckers.add(decker4);
        Ship invalidFourDecker = new Ship(deckers);

        List<Ship> ships = new ArrayList<>();
        ships.add(invalidFourDecker);
        ships.add(threeDecker1());
        ships.add(threeDecker2());
        ships.add(twoDecker1());
        ships.add(twoDecker2());
        ships.add(twoDecker3());
        ships.add(oneDecker1());
        ships.add(oneDecker2());
        ships.add(oneDecker3());
        ships.add(oneDecker4());

        BattleMap map = new BattleMap(ships);
        assertFalse(MapValidator.isValidMap(map));
    }

    @Test
    public void validatorSuccessfullyChecksThatToOverlappingShipsOnTheMap() throws Exception {
        BattleMap map = validMap();
        assertTrue(MapValidator.isValidMap(map));
    }

    @Test
    public void validatorMarksMapAsInvalidWhenToShipsHaveOverlappingCoordinates() throws Exception {
        Decker decker1 = new Decker(new Coordinate(7,5));
        List<Decker> deckers = new ArrayList<>();
        deckers.add(decker1);
        Ship invalidOneDecker = new Ship(deckers);

        List<Ship> ships = new ArrayList<>();
        ships.add(fourDecker());
        ships.add(threeDecker1());
        ships.add(threeDecker2());
        ships.add(twoDecker1());
        ships.add(twoDecker2());
        ships.add(twoDecker3());
        ships.add(oneDecker1());
        ships.add(oneDecker2());
        ships.add(oneDecker3());
        ships.add(invalidOneDecker);

        BattleMap map = new BattleMap(ships);
        assertFalse(MapValidator.isValidMap(map));
    }

    @Test
    public void validatorMarksMapAsInvalidWhenAShipHasFreeSpaceBetweenDeckers() throws Exception {
        Decker decker1 = new Decker(new Coordinate(1,1));
        Decker decker2 = new Decker(new Coordinate(1,2));
        Decker decker3 = new Decker(new Coordinate(1,4));
        Decker decker4 = new Decker(new Coordinate(1,5));
        List<Decker> deckers = new ArrayList<>();
        deckers.add(decker1);
        deckers.add(decker2);
        deckers.add(decker3);
        deckers.add(decker4);
        Ship invalidFourDecker = new Ship(deckers);

        List<Ship> ships = new ArrayList<>();
        ships.add(invalidFourDecker);
        ships.add(threeDecker1());
        ships.add(threeDecker2());
        ships.add(twoDecker1());
        ships.add(twoDecker2());
        ships.add(twoDecker3());
        ships.add(oneDecker1());
        ships.add(oneDecker2());
        ships.add(oneDecker3());
        ships.add(oneDecker4());

        BattleMap map = new BattleMap(ships);
        assertFalse(MapValidator.isValidMap(map));
    }

    private static BattleMap validMap() {
        List<Ship> ships = new ArrayList<>();
        ships.add(fourDecker());
        ships.add(threeDecker1());
        ships.add(threeDecker2());
        ships.add(twoDecker1());
        ships.add(twoDecker2());
        ships.add(twoDecker3());
        ships.add(oneDecker1());
        ships.add(oneDecker2());
        ships.add(oneDecker3());
        ships.add(oneDecker4());

        return new BattleMap(ships);
    }

    private static Ship fourDecker() {
        Decker decker1 = new Decker(new Coordinate(1,1));
        Decker decker2 = new Decker(new Coordinate(1,2));
        Decker decker3 = new Decker(new Coordinate(1,3));
        Decker decker4 = new Decker(new Coordinate(1,4));
        List<Decker> deckers = new ArrayList<>();
        deckers.add(decker1);
        deckers.add(decker2);
        deckers.add(decker3);
        deckers.add(decker4);
        return new Ship(deckers);
    }

    private static Ship threeDecker1() {
        Decker decker1 = new Decker(new Coordinate(3,1));
        Decker decker2 = new Decker(new Coordinate(3,2));
        Decker decker3 = new Decker(new Coordinate(3,3));
        List<Decker> deckers = new ArrayList<>();
        deckers.add(decker1);
        deckers.add(decker2);
        deckers.add(decker3);
        return new Ship(deckers);
    }

    private static Ship threeDecker2() {
        Decker decker1 = new Decker(new Coordinate(3,5));
        Decker decker2 = new Decker(new Coordinate(3,6));
        Decker decker3 = new Decker(new Coordinate(3,7));
        List<Decker> deckers = new ArrayList<>();
        deckers.add(decker1);
        deckers.add(decker2);
        deckers.add(decker3);
        return new Ship(deckers);
    }

    private static Ship twoDecker1() {
        Decker decker1 = new Decker(new Coordinate(5,1));
        Decker decker2 = new Decker(new Coordinate(5,2));
        List<Decker> deckers = new ArrayList<>();
        deckers.add(decker1);
        deckers.add(decker2);
        return new Ship(deckers);
    }

    private static Ship twoDecker2() {
        Decker decker1 = new Decker(new Coordinate(5,4));
        Decker decker2 = new Decker(new Coordinate(5,5));
        List<Decker> deckers = new ArrayList<>();
        deckers.add(decker1);
        deckers.add(decker2);
        return new Ship(deckers);
    }

    private static Ship twoDecker3() {
        Decker decker1 = new Decker(new Coordinate(5,7));
        Decker decker2 = new Decker(new Coordinate(5,8));
        List<Decker> deckers = new ArrayList<>();
        deckers.add(decker1);
        deckers.add(decker2);
        return new Ship(deckers);
    }

    private static Ship oneDecker1() {
        Decker decker1 = new Decker(new Coordinate(7,1));
        List<Decker> deckers = new ArrayList<>();
        deckers.add(decker1);
        return new Ship(deckers);
    }

    private static Ship oneDecker2() {
        Decker decker1 = new Decker(new Coordinate(7,3));
        List<Decker> deckers = new ArrayList<>();
        deckers.add(decker1);
        return new Ship(deckers);
    }

    private static Ship oneDecker3() {
        Decker decker1 = new Decker(new Coordinate(7,5));
        List<Decker> deckers = new ArrayList<>();
        deckers.add(decker1);
        return new Ship(deckers);
    }

    private static Ship oneDecker4() {
        Decker decker1 = new Decker(new Coordinate(7,7));
        List<Decker> deckers = new ArrayList<>();
        deckers.add(decker1);
        return new Ship(deckers);
    }
}