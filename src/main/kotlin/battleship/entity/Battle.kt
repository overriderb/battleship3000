package battleship.entity

data class Battle(val player1: Player, val player2: Player, val rounds: List<Round>)

data class Round(val player: Player, val hitTarget: Coordinate)