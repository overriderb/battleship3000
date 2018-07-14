package battleship

import battleship.entity._

/**
  * TODO: Add comment
  */
object GameEngine {

  def shoot(attackingPlayer: Player, defendingPlayer: Player, hitTarget: Coordinate): Round = {
    val maybeDecker: Option[Decker] = defendingPlayer.map.ships.flatMap(ship => ship.deckers).find(decker => decker.position == hitTarget)

    if (maybeDecker.isDefined) {
//      maybeDecker.get.alive = false
      Round(attackingPlayer, defendingPlayer, hitTarget, successful = true)
    } else {
      Round(attackingPlayer, defendingPlayer, hitTarget, successful = false)
    }
  }

  def areAllShipsDestroyed(ships: List[Ship]): Boolean = {
    ships.flatMap(_.deckers).forall(!_.alive)
  }

}
