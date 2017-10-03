package battleship.entity

case class Battle(player1: Player, player2: Player) {
  val rounds: List[Round] = List()
}

case class Round(attackingPlayer: Player, defendingPlayer: Player, hitTarget: Coordinate)
