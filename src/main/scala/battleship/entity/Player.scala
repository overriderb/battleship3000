package battleship.entity

import monocle.macros.Lenses

@Lenses("_")
case class Player(name: String, battleMap: BattleMap)
