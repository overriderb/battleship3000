package battleship

import battleship.entity.{BattleMap, Coordinate, Decker, Ship}

/**
  * Map reader, reads map from the file
  */
object MapReader {

  def readMap(path: String): BattleMap = {
    val source = scala.io.Source.fromFile(path)
    val lines = try source.mkString.split("\n") finally source.close()

    val matrix = lines.map(_.toCharArray.map(_.toString.toInt))
    readMap(matrix)
  }

  private def readMap(inMatrix: Array[Array[Int]]): BattleMap = {
    var matrix = inMatrix.clone()
    var ships = List[Ship]()

    do {
      val indexOfDecker = matrix.flatten.indexOf(1)
      val shipAndMatrix = findShip(indexOfDecker % matrix.length, indexOfDecker / matrix.length, matrix, List[Decker]())
      matrix = shipAndMatrix._2
      ships = ships :+ Ship(shipAndMatrix._1)
    } while (matrix.flatten.contains(1))

    BattleMap(ships)
  }

  private def findShip(startPointX: Int, startPointY: Int, inputMatrix: Array[Array[Int]], inputDeckers: List[Decker]): (List[Decker], Array[Array[Int]]) = {
    inputMatrix(startPointY)(startPointX) = 0
    var matrix = inputMatrix.clone()
    var deckers = inputDeckers :+ Decker(Coordinate(startPointX, startPointY))

    // check north (up)
    if (startPointY > 0 && matrix(startPointY - 1)(startPointX) == 1) {
      val result = findShip(startPointX, startPointY - 1, matrix, deckers)
      deckers = deckers ::: result._1
      matrix = result._2
    }

    // check east (right)
    if (startPointX < matrix.length && matrix(startPointY)(startPointX + 1) == 1) {
      val result = findShip(startPointX + 1, startPointY, matrix, deckers)
      deckers =  deckers ::: result._1
      matrix = result._2
    }

    // check south (down)
    if (startPointY < matrix.length && matrix(startPointY + 1)(startPointX) == 1) {
      val result = findShip(startPointX, startPointY + 1, matrix, deckers)
      deckers = deckers ::: result._1
      matrix = result._2
    }

    // check west (left)
    if (startPointX > 0 && matrix(startPointY)(startPointX - 1) == 1) {
      val result = findShip(startPointX - 1, startPointY, matrix, deckers)
      deckers = deckers ::: result._1
      matrix = result._2
    }

    (deckers.distinct, matrix)
  }
}
