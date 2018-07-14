package battleship

import battleship.entity.{Coordinate, _}

import scala.io.StdIn

object Game {
  def main(args: Array[String]): Unit = {
    val vasyaMap = MapReader.readMap("src/main/resources/vasya-battle-map.txt")
    val petyaMap = MapReader.readMap("src/main/resources/petya-battle-map.txt")
    val vasya = Player("Vasya", vasyaMap)
    val petya = Player("Petya", petyaMap)

    val newBattle = Battle(vasya, petya)
    val rounds: List[Round] = newBattle.rounds

    var attackingPlayer = vasya
    var defendingPlayer = petya

    while (!isGameFinished(attackingPlayer.map.ships, defendingPlayer.map.ships)) {
      println("Next turn for " + attackingPlayer.name)
      println("Input x: ")
      val x = StdIn.readLine()
      println("Input y: ")
      val y = StdIn.readLine()
      val hitTarget = Coordinate(x.toInt, y.toInt)
      val round = GameEngine.shoot(vasya, petya, hitTarget)
      val r = rounds :+ GameEngine.shoot(vasya, petya, hitTarget)
      if (!round.successful) {
        println("Miss target")
        val exAttackingPlayer = attackingPlayer.copy()
        attackingPlayer = defendingPlayer.copy()
        defendingPlayer = exAttackingPlayer
      } else {
        println("Hit target")
      }
    }
  }

  private def isGameFinished(ships1: List[Ship], ships2: List[Ship]) = GameEngine.areAllShipsDestroyed(ships1) || GameEngine.areAllShipsDestroyed(ships2)

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
