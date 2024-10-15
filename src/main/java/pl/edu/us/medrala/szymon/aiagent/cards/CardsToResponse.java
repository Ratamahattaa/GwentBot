package pl.edu.us.medrala.szymon.aiagent.cards;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CardsToResponse {
    private final Long id;
    private final byte[] image;
    private final int pointsOnBoard;
    private final boolean isSpy;

    public CardsToResponse(final ACard card, final int pointsOnBoard) {
        if (card instanceof Card c) {
            this.id = c.getId();
            this.image = c.getImage();
            this.pointsOnBoard = pointsOnBoard;
            this.isSpy = c.getAbility().equals(Ability.SPY);
        } else {
            this.id = card.getId();
            this.image = card.getImage();
            this.pointsOnBoard = pointsOnBoard;
            this.isSpy = false;
        }
    }
}
