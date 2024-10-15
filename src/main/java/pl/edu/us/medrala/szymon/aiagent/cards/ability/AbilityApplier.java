package pl.edu.us.medrala.szymon.aiagent.cards.ability;

import pl.edu.us.medrala.szymon.aiagent.board.YourBoard;
import pl.edu.us.medrala.szymon.aiagent.cards.*;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class AbilityApplier {
    public static int apply(final List<Card> row, final Ability ability) {
        final AtomicInteger sum = new AtomicInteger(0);

        row.stream()
                .filter(card -> card.getAbility() == ability)
                .collect(Collectors.groupingBy(Card::getName))
                .forEach((name, cardsByName) -> cardsByName.forEach(card -> {
                    card.setUnitStrength(card.getUnitStrength() * cardsByName.size());
                    sum.addAndGet(card.getUnitStrength());
                }));

        return sum.get();
    }

    public static void apply(final Range range, final YourBoard board) {
        final Row row = board.getRow(range);

        row.getRow()
            .stream()
            .filter(card -> card.getAbility() == Ability.TIGHT_BOND)
            .collect(Collectors.groupingBy(Card::getName))
            .forEach((name, cardsByName) -> cardsByName.forEach(card -> card.setUnitStrength(card.getUnitStrength() * cardsByName.size())));

        row.recalculatePoints();
    }

    public static void commandersHorn(final Optional<Card> optionalCard, final YourBoard yourBoard) {
        final Row row = yourBoard.findCardInRow(optionalCard);
        for (final Card card : row.getRow()) {
            if (card.getType() != Type.HERO && card.getAbility() != Ability.COMMANDERS_HORN) {
                card.setUnitStrength(card.getUnitStrength() * 2);
            }
        }
    }

    public static void moraleBoost (final Optional<Card> optionalCard, final YourBoard yourBoard) {
        final Row row = yourBoard.findCardInRow(optionalCard);
            for (Card card : row.getRow()) {
                if (card.getType() != Type.HERO) {
                    card.setUnitStrength(card.getUnitStrength() + 1);
                }
            }
    }
}
