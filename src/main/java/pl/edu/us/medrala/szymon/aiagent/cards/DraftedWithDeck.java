package pl.edu.us.medrala.szymon.aiagent.cards;

import java.util.List;

public record DraftedWithDeck(List<Card> drafted, List<Card> deck) {}