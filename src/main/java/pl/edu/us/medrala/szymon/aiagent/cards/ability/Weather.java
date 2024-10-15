package pl.edu.us.medrala.szymon.aiagent.cards.ability;

import pl.edu.us.medrala.szymon.aiagent.cards.Card;
import pl.edu.us.medrala.szymon.aiagent.cards.Row;
import pl.edu.us.medrala.szymon.aiagent.cards.Type;

public class Weather {
    public static void weatherEffect(final Row row, final WeatherStatus status) {
        switch (status) {
            case APPLY -> {
                for (final Card card : row.getRow()) {
                    if (card.getType() != Type.HERO) {
                        card.setUnitStrength(1);
                    }
                }
            }
            case REMOVE -> {
                for (final Card card : row.getRow()) {
                    if (card.getType() != Type.HERO) {
                        card.setUnitStrength(card.getBaseUnitStrength());
                    }
                }
            }
        }
    }
}
