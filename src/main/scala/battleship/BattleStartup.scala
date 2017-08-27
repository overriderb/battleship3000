package battleship

import battleship.entity.{BattleMap, Coordinate, Rules, Ship}

/**
  * TODO: Add comment
  */
object BattleStartup {
  def isValidMap(map: BattleMap): Boolean = {
    val coordinateInsideMap = map.ships.flatMap(_.deckers).forall(d => isCoordinateInsideMap(d.position, Rules.mapSize))

    coordinateInsideMap && isAllShipsOnMap(map.ships)
  }

  private def isCoordinateInsideMap(coordinate: Coordinate, size: Int): Boolean = {
    val x = coordinate.horizontal
    val y = coordinate.vertical
    (x >= 1 && x <= size) && (y >= 1 && y <= size)
  }

  private def isAllShipsOnMap(ships: List[Ship]) = {
    val shipsBySize: Map[Int, Int] = ships.groupBy(s => s.deckers.size).map(s => (s._1, s._2.size))

    Rules.ships.forall(s => {
      val numberOfShipsOnMap = shipsBySize.getOrElse(s._1, -1)
      numberOfShipsOnMap == s._2
    })
  }
}
