package battleship.entity

case class Ship(deckers: List[Decker])

case class Decker(position: Coordinate, var alive: Boolean = true)