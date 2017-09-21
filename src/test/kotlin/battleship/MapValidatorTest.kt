package battleship

import battleship.entity.BattleMap
import battleship.entity.Coordinate
import battleship.entity.Decker
import battleship.entity.Ship
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Some tests are redundant, but it is made in seek of presenting all tests cases
 */
class MapValidatorTest {

    @Test
    fun `Validator successfully checks that all ships in field of the map`() {
        val map: BattleMap = validMap
        assertEquals(true, MapValidator.isValidMap(map))
    }

    @Test
    fun `Validator marks map as invalid because of one ship out of field of the map`() {
        val invalidFourDecker = Ship(listOf(Decker(Coordinate(11,1)), Decker(Coordinate(11,2)), Decker(Coordinate(11,3)), Decker(Coordinate(11,4))))
        val ships = listOf(invalidFourDecker, threeDecker1, threeDecker2, twoDecker1, twoDecker2, twoDecker3, oneDecker1, oneDecker2, oneDecker3, oneDecker4)
        val map = BattleMap(ships)

        assertEquals(false, MapValidator.isValidMap(map))
    }

    @Test
    fun `Validator successfully checks that map contains only 4 one-decker, 3 two-decker, 2 three-decker, 1 four-decker ships`() {
        val map: BattleMap = validMap
        assertEquals(true, MapValidator.isValidMap(map))
    }

    @Test
    fun `Validator marks map as invalid map with only 1 one-decker ship`() {
        val oneDecker = Ship(listOf(Decker(Coordinate(1,1))))
        val map = BattleMap(listOf(oneDecker))

        assertEquals(false, MapValidator.isValidMap(map))
    }

    @Test
    fun `Validator marks map as invalid map without ships`() {
        val map = BattleMap(emptyList())
        assertEquals(false, MapValidator.isValidMap(map))
    }

    @Test
    fun `Validator marks map as invalid map with 2 four-decker ships`() {
        val additionalFourDecker = Ship(listOf(Decker(Coordinate(1,7)), Decker(Coordinate(1,8)), Decker(Coordinate(1,9)), Decker(Coordinate(1,10))))
        val ships = listOf(fourDecker, additionalFourDecker, threeDecker1, threeDecker2, twoDecker1, twoDecker2, twoDecker3, oneDecker1, oneDecker2, oneDecker3, oneDecker4)
        val map = BattleMap(ships)

        assertEquals(false, MapValidator.isValidMap(map))
    }

    @Test
    fun `Validator successfully checks that distance between all ships on map not less then one cell`() {
        val map: BattleMap = validMap
        assertEquals(true, MapValidator.isValidMap(map))
    }

    @Test
    fun `Validator marks map as invalid when distance between two ships on map less then one cell`() {
        val invalidOneDecker = Ship(listOf(Decker(Coordinate(7,6))))
        val ships = listOf(fourDecker, threeDecker1, threeDecker2, twoDecker1, twoDecker2, twoDecker3, oneDecker1, oneDecker2, oneDecker3, invalidOneDecker)
        val map = BattleMap(ships)

        assertEquals(false, MapValidator.isValidMap(map))
    }

    @Test
    fun `Validator successfully checks that all ships on map have straight shape`() {
        val map: BattleMap = validMap
        assertEquals(true, MapValidator.isValidMap(map))
    }

    @Test
    fun `Validator marks map as invalid when at least one ship has not straight shape`() {
        val invalidFourDecker = Ship(listOf(Decker(Coordinate(1,8)), Decker(Coordinate(1,9)), Decker(Coordinate(1,10)), Decker(Coordinate(2,10))))
        val ships = listOf(invalidFourDecker, threeDecker1, threeDecker2, twoDecker1, twoDecker2, twoDecker3, oneDecker1, oneDecker2, oneDecker3, oneDecker4)
        val map = BattleMap(ships)

        assertEquals(false, MapValidator.isValidMap(map))
    }

    @Test
    fun `Validator successfully checks that no overlapping ships on the map`() {
        val map: BattleMap = validMap
        assertEquals(true, MapValidator.isValidMap(map))
    }

    @Test
    fun `Validator marks map as invalid when to ships have overlapping coordinates`() {
        val invalidOneDecker = Ship(listOf(Decker(Coordinate(7,5))))
        val ships = listOf(fourDecker, threeDecker1, threeDecker2, twoDecker1, twoDecker2, twoDecker3, oneDecker1, oneDecker2, oneDecker3, invalidOneDecker)
        val map = BattleMap(ships)

        assertEquals(false, MapValidator.isValidMap(map))
    }

    @Test
    fun `Validator marks map as invalid when a ship has free space between deckers`() {
        val invalidFourDecker = Ship(listOf(Decker(Coordinate(1,1)), Decker(Coordinate(1,2)), Decker(Coordinate(1,4)), Decker(Coordinate(1,5))))
        val ships = listOf(invalidFourDecker, threeDecker1, threeDecker2, twoDecker1, twoDecker2, twoDecker3, oneDecker1, oneDecker2, oneDecker3, oneDecker4)
        val map = BattleMap(ships)

        assertEquals(false, MapValidator.isValidMap(map))
    }

    private val fourDecker = Ship(listOf(Decker(Coordinate(1,1)), Decker(Coordinate(1,2)), Decker(Coordinate(1,3)), Decker(Coordinate(1,4))))
    private val threeDecker1 = Ship(listOf(Decker(Coordinate(3,1)), Decker(Coordinate(3,2)), Decker(Coordinate(3,3))))
    private val threeDecker2 = Ship(listOf(Decker(Coordinate(3,5)), Decker(Coordinate(3,6)), Decker(Coordinate(3,7))))
    private val twoDecker1 = Ship(listOf(Decker(Coordinate(5,1)), Decker(Coordinate(5,2))))
    private val twoDecker2 = Ship(listOf(Decker(Coordinate(5,4)), Decker(Coordinate(5,5))))
    private val twoDecker3 = Ship(listOf(Decker(Coordinate(5,7)), Decker(Coordinate(5,8))))
    private val oneDecker1 = Ship(listOf(Decker(Coordinate(7,1))))
    private val oneDecker2 = Ship(listOf(Decker(Coordinate(7,3))))
    private val oneDecker3 = Ship(listOf(Decker(Coordinate(7,5))))
    private val oneDecker4 = Ship(listOf(Decker(Coordinate(7,7))))

    private val validMap: BattleMap = BattleMap(listOf(
            fourDecker,
            threeDecker1,
            threeDecker2,
            twoDecker1,
            twoDecker2,
            twoDecker3,
            oneDecker1,
            oneDecker2,
            oneDecker3,
            oneDecker4
    ))

}