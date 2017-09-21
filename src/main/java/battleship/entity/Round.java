package battleship.entity;

import java.util.Objects;

public class Round {

    private final Player player;
    private final Coordinate hitTarget;

    public Round(Player player, Coordinate hitTarget) {
        this.player = player;
        this.hitTarget = hitTarget;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Round round = (Round) o;
        return Objects.equals(player, round.player) &&
                Objects.equals(hitTarget, round.hitTarget);
    }

    @Override
    public int hashCode() {
        return Objects.hash(player, hitTarget);
    }

    @Override
    public String toString() {
        return "Round{" +
                "player=" + player +
                ", hitTarget=" + hitTarget +
                '}';
    }

    public Player getPlayer() {
        return player;
    }

    public Coordinate getHitTarget() {
        return hitTarget;
    }
}
