package battleship.entity

case class Battle(player1: Player, player2: Player, rounds: List[Round])

case class Round(player: Player, hitTarget: Coordinate)
