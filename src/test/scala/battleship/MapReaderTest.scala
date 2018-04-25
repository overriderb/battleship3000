package battleship

import battleship.entity.{BattleMap, Coordinate, Decker, Ship}
import org.scalatest.{FlatSpec, Matchers}

class MapReaderTest extends FlatSpec with Matchers {

  behavior of "MapReaderTest"

  it should "read valid map from the file" in {
    val actualMap = MapReader.readMap("src/test/resources/test-valid-battle-map-1.txt")
    actualMap.ships should contain theSameElementsAs expectedMap.ships
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

  private val expectedMap: BattleMap = BattleMap(List(
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
