package battleship.entity

data class Ship(val deckers: List<Decker>)

data class Decker(val position: Coordinate)
