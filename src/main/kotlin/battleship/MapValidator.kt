package battleship

import battleship.entity.BattleMap
import battleship.entity.Coordinate
import battleship.entity.Decker
import battleship.entity.Rules
import battleship.entity.Ship

object MapValidator {

    fun isValidMap(map: BattleMap): Boolean {
        val ships = map.ships

        return isAllShipsInsideMap(ships) and isAllShipsOnMap(ships) and isDistanceSufficientBetweenShips(ships) and isAllShipsCorrectShape(ships)
    }

    private fun isAllShipsInsideMap(ships: List<Ship>): Boolean {
        fun isCoordinateInsideMap(coordinate: Coordinate, size: Int): Boolean {
            val x = coordinate.horizontal
            val y = coordinate.vertical
            return (x in 1..size) && (y in 1..size)
        }

        return ships.flatMap { it.deckers }.all { isCoordinateInsideMap(it.position, Rules.mapSize) }
    }

    private fun isAllShipsOnMap(ships: List<Ship>): Boolean {
        val shipsBySize: Map<Int, Int> = ships.groupBy { it.deckers.size }.mapValues { it.value.size }

        return Rules.ships.all {
            val numberOfShipsOnMap = shipsBySize.getOrDefault(it.key, -1)
            numberOfShipsOnMap == it.value
        }
    }

    private fun isDistanceSufficientBetweenShips(ships: List<Ship>): Boolean {
        fun isDistanceSufficientBetweenCoordinates(coordinate1: Coordinate, coordinate2: Coordinate): Boolean {
            val horizontalDistanceSufficient = Math.abs(coordinate1.horizontal - coordinate2.horizontal) >= 2
            val verticalDistanceSufficient = Math.abs(coordinate1.vertical - coordinate2.vertical) >= 2
            return horizontalDistanceSufficient or verticalDistanceSufficient
        }

        return ships.all { ship ->
            val others = ships.minus(ship)
            others.all { otherShip ->
                otherShip.deckers.all { otherDecker ->
                    ship.deckers.all { decker ->
                        isDistanceSufficientBetweenCoordinates(decker.position, otherDecker.position)
                    }
                }
            }
        }
    }

    private fun isAllShipsCorrectShape(ships: List<Ship>): Boolean {
        fun isPartOfOneShip(position1: Coordinate, position2: Coordinate): Boolean {
            val verticalShip = position1.horizontal == position2.horizontal && Math.abs(position1.vertical - position2.vertical) == 1
            val horizontalShip = position1.vertical == position2.vertical && Math.abs(position1.horizontal - position2.horizontal) == 1
            return verticalShip || horizontalShip
        }

        fun isShipsIndissoluble(prevDecker: Decker, deckers: List<Decker>): Boolean {
            return when {
                deckers.isEmpty() -> true
                else -> isPartOfOneShip(prevDecker.position, deckers.first().position) && isShipsIndissoluble(deckers.first(), deckers.drop(1))
            }
        }

        fun isShipHasStraightShape(ship: Ship): Boolean {
            val firstDecker = ship.deckers.first()
            val horizontalPositionEqual = ship.deckers.all { it.position.horizontal == firstDecker.position.horizontal }
            val verticalPositionEqual = ship.deckers.all { it.position.vertical == firstDecker.position.vertical }
            return horizontalPositionEqual || verticalPositionEqual
        }

        val shipsIndissoluble = ships.all { isShipsIndissoluble(it.deckers.first(), it.deckers.drop(1))}
        val shipsHaveStraightShape = ships.all { isShipHasStraightShape(it) }
        return shipsIndissoluble && shipsHaveStraightShape
    }

}