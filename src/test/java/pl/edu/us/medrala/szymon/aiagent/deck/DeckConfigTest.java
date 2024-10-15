package pl.edu.us.medrala.szymon.aiagent.deck;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.us.medrala.szymon.aiagent.api.DeckType;
import pl.edu.us.medrala.szymon.aiagent.cards.Card;
import pl.edu.us.medrala.szymon.aiagent.cards.repository.CardRepo;
import pl.edu.us.medrala.szymon.aiagent.cards.repository.LeaderRepo;
import pl.edu.us.medrala.szymon.aiagent.deck.Deck;
import pl.edu.us.medrala.szymon.aiagent.deck.DeckRepositoryJpa;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class DeckConfigTest {

    @Autowired
    private CardRepo cardRepo;

    @Autowired
    private LeaderRepo leaderRepo;

    @Autowired
    private DeckRepositoryJpa deckRepository;

    @Autowired
    private DeckConfig deckConfig;

    @Test
    public void testDeckBeanCreation() {
        assertNotNull(deckConfig.deck());
    }

    @Test
    public void testDetermineDeckNorthernRealmsTheUnique() {
        List<Card> cards = deckConfig.determineDeck(DeckType.NORTHERN_REALMS_THE_UNIQUE);
        assertNotNull(cards);
        assertEquals(22, cards.size()); // Assuming all cards are added correctly
    }

    @Test
    public void testDetermineDeckNilfgaardRollero() {
        List<Card> cards = deckConfig.determineDeck(DeckType.NILFGAARD_ROLLERO);
        assertNotNull(cards);
        assertEquals(22, cards.size()); // Assuming all cards are added correctly
    }

    @Test
    public void testDetermineDeckInvalidType() {
        assertThrows(NullPointerException.class, () -> deckConfig.determineDeck(null));
    }

    @Test
    public void testDetermineDeckNotNull() {
        assertNotNull(deckConfig.determineDeck(DeckType.NORTHERN_REALMS_THE_UNIQUE));
    }

    @Test
    public void testDetermineDeckContainsCards() {
        List<Card> cards = deckConfig.determineDeck(DeckType.NORTHERN_REALMS_THE_UNIQUE);
        assertTrue(cards.stream().allMatch(card -> card.getId() != null && card.getId() > 0));
    }

    @Test
    public void testDetermineDeckDistinctCards() {
        List<Card> cards = deckConfig.determineDeck(DeckType.NORTHERN_REALMS_THE_UNIQUE);
        long distinctCount = cards.stream().map(Card::getId).distinct().count();
        assertEquals(cards.size(), distinctCount);
    }

    @Test
    public void testDetermineDeckSizeNilfgaardRollero() {
        List<Card> cards = deckConfig.determineDeck(DeckType.NILFGAARD_ROLLERO);
        assertEquals(22, cards.size());
    }

    @Test
    public void testDetermineDeckSizeNorthernRealmsTheUnique() {
        List<Card> cards = deckConfig.determineDeck(DeckType.NORTHERN_REALMS_THE_UNIQUE);
        assertEquals(22, cards.size());
    }

    @Test
    public void testDetermineDeckWithInvalidType() {
        assertThrows(NullPointerException.class, () -> deckConfig.determineDeck(null));
    }
}
