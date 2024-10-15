package pl.edu.us.medrala.szymon.aiagent.api;

import lombok.Getter;
import pl.edu.us.medrala.szymon.aiagent.algorithm.Algorithm;
import pl.edu.us.medrala.szymon.aiagent.algorithm.NGAlgorithm;
import pl.edu.us.medrala.szymon.aiagent.algorithm.NRAlgorithm;
import pl.edu.us.medrala.szymon.aiagent.board.YourBoard;
import pl.edu.us.medrala.szymon.aiagent.cards.Card;
import pl.edu.us.medrala.szymon.aiagent.cards.repository.CardRepo;

import java.util.List;
import java.util.Optional;

public enum DeckType {
    NORTHERN_REALMS_THE_UNIQUE("NR", "NorthernRealmsTheUnique", new NRAlgorithm()),
    NILFGAARD_ROLLERO("NG", "NilfgardRollero", new NGAlgorithm());

    private final String name;
    private final String abbreviation;
    @Getter
    private final Algorithm algorithmStrategy;

    DeckType(final String abbreviation, final String name, final Algorithm algorithmStrategy) {
        this.name = name;
        this.abbreviation = abbreviation;
        this.algorithmStrategy = algorithmStrategy;
    }

    public static DeckType fromAbbreviation(final String abbreviation) {
        for (final DeckType deckType : DeckType.values()) {
            if (deckType.abbreviation.equals(abbreviation)) {
                return deckType;
            }
        }
        throw new IllegalArgumentException("No deck type with abbreviation " + abbreviation);
    }
}
