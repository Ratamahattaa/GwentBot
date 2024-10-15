package pl.edu.us.medrala.szymon.aiagent.cards.ability;

import pl.edu.us.medrala.szymon.aiagent.cards.Card;
import pl.edu.us.medrala.szymon.aiagent.cards.Type;

import java.util.List;

public class MoraleBoost {
    public static int moraleBoost(List<Card> row) {
        int sum = 0;
        for (Card card : row) {
            if (card.getType() != Type.HERO) {
                card.setUnitStrength(card.getUnitStrength() + 1);
            }
            sum += card.getUnitStrength();
        }
        return sum;
    }
}
