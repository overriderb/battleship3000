package battleship.entity

import monocle.macros.Lenses

@Lenses("_")
case class Ship(deckers: List[Decker])

@Lenses("_")
case class Decker(position: Coordinate, alive: Boolean = true)