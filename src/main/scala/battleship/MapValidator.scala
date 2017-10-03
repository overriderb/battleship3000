package battleship

import battleship.entity._

/**
  * Validates initial ships setup on a map before battle
  */
object MapValidator {

  def isValidMap(map: BattleMap): Boolean = {
    val ships = map.ships

    isAllShipsInsideMap(ships) && isAllShipsOnMap(ships) && isDistanceSufficientBetweenShips(ships) && isAllShipsCorrectShape(ships)
  }

  private def isAllShipsInsideMap(ships: List[Ship]): Boolean = {
    def isCoordinateInsideMap(coordinate: Coordinate, size: Int): Boolean = {
      val x = coordinate.horizontal
      val y = coordinate.vertical
      (x >= 1 && x <= size) && (y >= 1 && y <= size)
    }

    ships.flatMap(_.deckers).forall(d => isCoordinateInsideMap(d.position, Rules.mapSize))
  }

  private def isAllShipsOnMap(ships: List[Ship]): Boolean = {
    val shipsBySize: Map[Int, Int] = ships.groupBy(s => s.deckers.size).map(s => (s._1, s._2.size))

    Rules.ships.forall(s => {
      val numberOfShipsOnMap = shipsBySize.getOrElse(s._1, -1)
      numberOfShipsOnMap == s._2
    })
  }

  private def isDistanceSufficientBetweenShips(ships: List[Ship]): Boolean = {
    def isDistanceSufficientBetweenCoordinates(coordinate1: Coordinate, coordinate2: Coordinate): Boolean = {
      val horizontalDistanceSufficient = Math.abs(coordinate1.horizontal - coordinate2.horizontal) >= 2
      val verticalDistanceSufficient = Math.abs(coordinate1.vertical - coordinate2.vertical) >= 2
      horizontalDistanceSufficient || verticalDistanceSufficient
    }

    ships.forall(ship => {
      val others: List[Ship] = ships.diff(List(ship))
      others.forall(otherShip => {
        otherShip.deckers.forall(otherDecker => {
          ship.deckers.forall(decker => {
            isDistanceSufficientBetweenCoordinates(decker.position, otherDecker.position)
          })
        })
      })
    })
  }

  private def isAllShipsCorrectShape(ships: List[Ship]): Boolean = {
    def isPartOfOneShip(position1: Coordinate, position2: Coordinate) = {
      val verticalShip = position1.horizontal == position2.horizontal && Math.abs(position1.vertical - position2.vertical) == 1
      val horizontalShip = position1.vertical == position2.vertical && Math.abs(position1.horizontal - position2.horizontal) == 1
      verticalShip || horizontalShip
    }

    def isShipsIndissoluble(prevDecker: Decker, deckers: List[Decker]): Boolean = deckers match {
      case List() => true
      case x::xs => isPartOfOneShip(prevDecker.position, x.position) && isShipsIndissoluble(x, xs)
    }

    def isShipHasStraightShape(ship: Ship): Boolean = {
      val firstDecker = ship.deckers.head
      val horizontalPositionEqual = ship.deckers.forall(_.position.horizontal == firstDecker.position.horizontal)
      val verticalPositionEqual = ship.deckers.forall(_.position.vertical == firstDecker.position.vertical)
      horizontalPositionEqual || verticalPositionEqual
    }

    val shipsIndissoluble = ships.forall(ship => isShipsIndissoluble(ship.deckers.head, ship.deckers.tail))
    val shipsHaveStraightShape = ships.forall(ship => isShipHasStraightShape(ship))
    shipsIndissoluble && shipsHaveStraightShape
  }
}
