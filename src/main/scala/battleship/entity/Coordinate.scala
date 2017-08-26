package battleship.entity

case class Coordinate(vertical: Int, horizontal: Char)

object VerticalPosition extends Enumeration {
  val one = Value("1")
  val two = Value("2")
  val three = Value("3")
  val four = Value("4")
  val five = Value("5")
  val six = Value("6")
  val seven = Value("7")
  val eight = Value("8")
  val nine = Value("9")
  val ten = Value("10")
}

object HorizontalPosition extends Enumeration {
  val a = Value("A")
  val b = Value("B")
  val c = Value("C")
  val d = Value("D")
  val e = Value("E")
  val f = Value("F")
  val g = Value("G")
  val h = Value("H")
  val i = Value("I")
  val j = Value("J")
}
