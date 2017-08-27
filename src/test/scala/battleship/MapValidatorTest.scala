package battleship

import battleship.entity.{BattleMap, Coordinate, Decker, Ship}
import org.scalatest.FlatSpec
import org.scalatest._

/**
  * TODO: Add comment
  */
class MapValidatorTest extends FlatSpec with Matchers {

  behavior of "BattleStartupTest"

  it should "successfully check that all ships in field of the map" in {
    val map: BattleMap = validMap

    MapValidator.isValidMap(map) should be (true)
  }

  it should "mark map as invalid because of one ship out of field of the map" in {
    val invalidFourDecker = Ship(List(Decker(Coordinate(11,1)), Decker(Coordinate(11,2)), Decker(Coordinate(11,3)), Decker(Coordinate(11,4))))
    val ships = List(invalidFourDecker, threeDecker1, threeDecker2, twoDecker1, twoDecker2, twoDecker3, oneDecker1, oneDecker2, oneDecker3, oneDecker4)
    val map: BattleMap = BattleMap(ships)

    MapValidator.isValidMap(map) should be (false)
  }

  it should "successfully check that map contains only 4 one-decker, 3 two-decker, 2 three-decker, 1 four-decker ships" in {
    val map: BattleMap = validMap

    MapValidator.isValidMap(map) should be (true)
  }

  it should "mark map as invalid map with only 1 one-decker ship" in {
    val oneDecker = Ship(List(Decker(Coordinate(1,1))))
    val map: BattleMap = BattleMap(List(oneDecker))

    MapValidator.isValidMap(map) should be (false)
  }

  it should "mark map as invalid map without ships" in {
    val map: BattleMap = BattleMap(List())

    MapValidator.isValidMap(map) should be (false)
  }

  it should "mark map as invalid map with 2 four-decker ships" in {
    val additionalFourDecker = Ship(List(Decker(Coordinate(1,7)), Decker(Coordinate(1,8)), Decker(Coordinate(1,9)), Decker(Coordinate(1,10))))
    val ships = List(fourDecker, additionalFourDecker, threeDecker1, threeDecker2, twoDecker1, twoDecker2, twoDecker3, oneDecker1, oneDecker2, oneDecker3, oneDecker4)
    val map: BattleMap = BattleMap(ships)

    MapValidator.isValidMap(map) should be (false)
  }

  it should "successfully check that distance between all ships on map not less then one cell" in {
    val map: BattleMap = validMap

    MapValidator.isValidMap(map) should be (true)
  }

  it should "mark map as invalid when distance between two ships on map less then one cell" in {
    val invalidOneDecker = Ship(List(Decker(Coordinate(7,6))))
    val ships = List(fourDecker, threeDecker1, threeDecker2, twoDecker1, twoDecker2, twoDecker3, oneDecker1, oneDecker2, oneDecker3, invalidOneDecker)
    val map: BattleMap = BattleMap(ships)

    MapValidator.isValidMap(map) should be (false)
  }

  it should "successfully check that all ships on map have straight shape" in {
    val map: BattleMap = validMap

    MapValidator.isValidMap(map) should be (true)
  }

  it should "mark map as invalid when at least one ship has not straight shape" in {
    val invalidFourDecker = Ship(List(Decker(Coordinate(1,8)), Decker(Coordinate(1,9)), Decker(Coordinate(1,10)), Decker(Coordinate(2,10))))
    val ships = List(invalidFourDecker, threeDecker1, threeDecker2, twoDecker1, twoDecker2, twoDecker3, oneDecker1, oneDecker2, oneDecker3, oneDecker4)
    val map: BattleMap = BattleMap(ships)

    MapValidator.isValidMap(map) should be (false)
  }

  it should "successfully check that no overlapping ships on the map" in {
    val map: BattleMap = validMap

    MapValidator.isValidMap(map) should be (true)
  }

  it should "mark map as invalid when to ships have overlapping coordinates" in {
    val invalidOneDecker = Ship(List(Decker(Coordinate(7,5))))
    val ships = List(fourDecker, threeDecker1, threeDecker2, twoDecker1, twoDecker2, twoDecker3, oneDecker1, oneDecker2, oneDecker3, invalidOneDecker)
    val map: BattleMap = BattleMap(ships)

    MapValidator.isValidMap(map) should be (false)
  }

  private val fourDecker = Ship(List(Decker(Coordinate(1,1)), Decker(Coordinate(1,2)), Decker(Coordinate(1,3)), Decker(Coordinate(1,4))))
  private val threeDecker1 = Ship(List(Decker(Coordinate(3,1)), Decker(Coordinate(3,2)), Decker(Coordinate(3,3))))
  private val threeDecker2 = Ship(List(Decker(Coordinate(3,5)), Decker(Coordinate(3,6)), Decker(Coordinate(3,7))))
  private val twoDecker1 = Ship(List(Decker(Coordinate(5,1)), Decker(Coordinate(5,2))))
  private val twoDecker2 = Ship(List(Decker(Coordinate(5,4)), Decker(Coordinate(5,5))))
  private val twoDecker3 = Ship(List(Decker(Coordinate(5,7)), Decker(Coordinate(5,8))))
  private val oneDecker1 = Ship(List(Decker(Coordinate(7,1))))
  private val oneDecker2 = Ship(List(Decker(Coordinate(7,3))))
  private val oneDecker3 = Ship(List(Decker(Coordinate(7,5))))
  private val oneDecker4 = Ship(List(Decker(Coordinate(7,7))))

  private val validMap: BattleMap = BattleMap(List(
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
