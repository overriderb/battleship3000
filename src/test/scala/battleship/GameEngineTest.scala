package battleship

import battleship.entity._
import org.scalatest.{FlatSpec, Matchers}

/**
  * TODO: Add comment
  */
class GameEngineTest extends FlatSpec with Matchers {

  behavior of "Game Engine"

  it should "checks that attacking player hits target ship" in {
    val oneDecker = Ship(List(Decker(Coordinate(1,1))))
    val attackingPlayer = Player("Attacking", BattleMap(List(oneDecker)))
    val defendingPlayer = Player("Defending", BattleMap(List(oneDecker)))

    val target = Coordinate(1, 1)
    val expected = Round(attackingPlayer, defendingPlayer, target, successful = true)

    GameEngine.shoot(attackingPlayer, defendingPlayer, target) should be (expected)
  }

  it should "checks that attacking player misses target ship" in {
    val oneDecker = Ship(List(Decker(Coordinate(1,1))))
    val attackingPlayer = Player("Attacking", BattleMap(List(oneDecker)))
    val defendingPlayer = Player("Defending", BattleMap(List(oneDecker)))

    val target = Coordinate(5, 5)
    val expected = Round(attackingPlayer, defendingPlayer, target, successful = false)

    GameEngine.shoot(attackingPlayer, defendingPlayer, target) should be (expected)
  }

}
