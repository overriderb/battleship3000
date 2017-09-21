package battleship.entity;

import java.util.List;
import java.util.Objects;

public class Battle {

    private final Player player1;
    private final Player player2;
    private final List<Round> rounds;

    public Battle(Player player1, Player player2, List<Round> rounds) {
        this.player1 = player1;
        this.player2 = player2;
        this.rounds = rounds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Battle battle = (Battle) o;
        return Objects.equals(player1, battle.player1) &&
                Objects.equals(player2, battle.player2) &&
                Objects.equals(rounds, battle.rounds);
    }

    @Override
    public int hashCode() {
        return Objects.hash(player1, player2, rounds);
    }

    @Override
    public String toString() {
        return "Battle{" +
                "player1=" + player1 +
                ", player2=" + player2 +
                ", rounds=" + rounds +
                '}';
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public List<Round> getRounds() {
        return rounds;
    }
}
