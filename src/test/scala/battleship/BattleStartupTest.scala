package battleship

import battleship.entity.{BattleMap, Coordinate, Decker, Ship}
import org.scalatest.FlatSpec
import org.scalatest._

/**
  * TODO: Add comment
  */
class BattleStartupTest extends FlatSpec with Matchers {

  behavior of "BattleStartupTest"

  it should "successfully check that all ships in field of the map" in {
    val fourDecker = Ship(List(Decker(Coordinate(1,1)), Decker(Coordinate(1,2)), Decker(Coordinate(1,3)), Decker(Coordinate(1,4))))
    val threeDecker1 = Ship(List(Decker(Coordinate(3,1)), Decker(Coordinate(3,2)), Decker(Coordinate(3,3))))
    val threeDecker2 = Ship(List(Decker(Coordinate(3,5)), Decker(Coordinate(3,6)), Decker(Coordinate(3,7))))
    val twoDecker1 = Ship(List(Decker(Coordinate(5,1)), Decker(Coordinate(5,2))))
    val twoDecker2 = Ship(List(Decker(Coordinate(5,4)), Decker(Coordinate(5,5))))
    val twoDecker3 = Ship(List(Decker(Coordinate(5,7)), Decker(Coordinate(5,8))))
    val oneDecker1 = Ship(List(Decker(Coordinate(7,1))))
    val oneDecker2 = Ship(List(Decker(Coordinate(7,3))))
    val oneDecker3 = Ship(List(Decker(Coordinate(7,5))))
    val oneDecker4 = Ship(List(Decker(Coordinate(7,7))))
    val ships = List(fourDecker, threeDecker1, threeDecker2, twoDecker1, twoDecker2, twoDecker3, oneDecker1, oneDecker2, oneDecker3, oneDecker4)
    val map: BattleMap = BattleMap(ships)

    BattleStartup.validateMap(map) should be (true)
  }

  it should "mark map as invalid because of one ship out of field of the map" in {
    val fourDecker = Ship(List(Decker(Coordinate(11,1)), Decker(Coordinate(11,2)), Decker(Coordinate(11,3)), Decker(Coordinate(11,4))))
    val threeDecker1 = Ship(List(Decker(Coordinate(3,1)), Decker(Coordinate(3,2)), Decker(Coordinate(3,3))))
    val threeDecker2 = Ship(List(Decker(Coordinate(3,5)), Decker(Coordinate(3,6)), Decker(Coordinate(3,7))))
    val twoDecker1 = Ship(List(Decker(Coordinate(5,1)), Decker(Coordinate(5,2))))
    val twoDecker2 = Ship(List(Decker(Coordinate(5,4)), Decker(Coordinate(5,5))))
    val twoDecker3 = Ship(List(Decker(Coordinate(5,7)), Decker(Coordinate(5,8))))
    val oneDecker1 = Ship(List(Decker(Coordinate(7,1))))
    val oneDecker2 = Ship(List(Decker(Coordinate(7,3))))
    val oneDecker3 = Ship(List(Decker(Coordinate(7,5))))
    val oneDecker4 = Ship(List(Decker(Coordinate(7,7))))
    val ships = List(fourDecker, threeDecker1, threeDecker2, twoDecker1, twoDecker2, twoDecker3, oneDecker1, oneDecker2, oneDecker3, oneDecker4)
    val map: BattleMap = BattleMap(ships)

    BattleStartup.validateMap(map) should be (false)
  }

  it should "successfully check that map contains only 4 one-decker, 3 two-decker, 2 three-decker, 1 four-decker ships" in {
    val fourDecker = Ship(List(Decker(Coordinate(1,1)), Decker(Coordinate(1,2)), Decker(Coordinate(1,3)), Decker(Coordinate(1,4))))
    val threeDecker1 = Ship(List(Decker(Coordinate(3,1)), Decker(Coordinate(3,2)), Decker(Coordinate(3,3))))
    val threeDecker2 = Ship(List(Decker(Coordinate(3,5)), Decker(Coordinate(3,6)), Decker(Coordinate(3,7))))
    val twoDecker1 = Ship(List(Decker(Coordinate(5,1)), Decker(Coordinate(5,2))))
    val twoDecker2 = Ship(List(Decker(Coordinate(5,4)), Decker(Coordinate(5,5))))
    val twoDecker3 = Ship(List(Decker(Coordinate(5,7)), Decker(Coordinate(5,8))))
    val oneDecker1 = Ship(List(Decker(Coordinate(7,1))))
    val oneDecker2 = Ship(List(Decker(Coordinate(7,3))))
    val oneDecker3 = Ship(List(Decker(Coordinate(7,5))))
    val oneDecker4 = Ship(List(Decker(Coordinate(7,7))))
    val ships = List(fourDecker, threeDecker1, threeDecker2, twoDecker1, twoDecker2, twoDecker3, oneDecker1, oneDecker2, oneDecker3, oneDecker4)
    val map: BattleMap = BattleMap(ships)

    BattleStartup.validateMap(map) should be (true)
  }

  it should "try to validate an invalid map with only 1 one-decker ship" in {
    val oneDecker = Ship(List(Decker(Coordinate(1,1))))
    val map: BattleMap = BattleMap(List(oneDecker))

    BattleStartup.validateMap(map) should be (false)
  }

  it should "try to validate an invalid map without ships" in {
    val map: BattleMap = BattleMap(List())

    BattleStartup.validateMap(map) should be (false)
  }

}
