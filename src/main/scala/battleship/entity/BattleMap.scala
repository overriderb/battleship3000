package battleship.entity

import monocle.macros.Lenses

/**
  * TODO: Size?
  */
@Lenses("_")
case class BattleMap(ships: List[Ship])
