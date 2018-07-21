package battleship

import battleship.entity._
import monocle.function.Each._

/**
  * Game engine which supposed to support shooting and other game logic
  */
object GameEngine {

  def shoot(attackingPlayer: Player, defendingPlayer: Player, hitTarget: Coordinate): Round = {
    val maybeShip = defendingPlayer.battleMap.ships.find(ship => ship.deckers.exists(decker => decker.position == hitTarget))

    val modifyDefendingPlayer =
      (Player._battleMap composeLens BattleMap._ships composeTraversal each
        composeLens Ship._deckers composeTraversal each) modify (tryHitDecker(_, hitTarget))

    Round(attackingPlayer, modifyDefendingPlayer(defendingPlayer), hitTarget, maybeShip.isDefined)
  }

  def areAllShipsDestroyed(ships: List[Ship]): Boolean = {
    ships.flatMap(_.deckers).forall(!_.alive)
  }

  private def tryHitDecker(decker: Decker, hitTarget: Coordinate): Decker =
    if (decker.position == hitTarget) decker.copy(alive = false) else decker.copy()

}
