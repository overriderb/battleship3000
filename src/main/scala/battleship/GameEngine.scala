package battleship

import battleship.entity.{Coordinate, Decker, Player, Ship}

/**
  * TODO: Add comment
  */
object GameEngine {

  def shoot(attackingPlayer: Player, defendingPlayer: Player, hitTarget: Coordinate): Boolean = {
    val maybeDecker: Option[Decker] = defendingPlayer.map.ships.flatMap(ship => ship.deckers).find(decker => decker.position == hitTarget)

    if (maybeDecker.isDefined) {
      maybeDecker.get.alive = false
      true
    } else {
      false
    }
  }

  def areAllShipsDestroyed(ships: List[Ship]): Boolean = {
    ships.flatMap(_.deckers).forall(!_.alive)
  }

}
