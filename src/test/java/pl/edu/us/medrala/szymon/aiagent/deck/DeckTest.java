package pl.edu.us.medrala.szymon.aiagent.deck;

import org.junit.jupiter.api.Test;
import pl.edu.us.medrala.szymon.aiagent.cards.Card;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DeckTest {

    @Test
    public void testDeckInitialization() {
        Deck deck = new Deck();
        assertNotNull(deck);
        assertNotNull(deck.getDeck());
        assertEquals(0, deck.getDeck().size());
    }

    @Test
    public void testAddCardToDeck() {
        Deck deck = new Deck();
        Card card = new Card();
        deck.add(card);
        assertTrue(deck.getDeck().contains(card));
    }

    @Test
    public void testRemoveCardFromDeck() {
        Deck deck = new Deck();
        Card card = new Card();
        deck.add(card);
        assertTrue(deck.getDeck().contains(card));
        deck.remove(card);
        assertFalse(deck.getDeck().contains(card));
    }

    @Test
    public void testDeckContainsCard() {
        Deck deck = new Deck();
        Card card = new Card();
        deck.add(card);
        assertTrue(deck.contains(card));
    }

    @Test
    public void testDeckDoesNotContainCard() {
        Deck deck = new Deck();
        Card card = new Card();
        assertFalse(deck.contains(card));
    }

    @Test
    public void testSetDeck() {
        Deck deck = new Deck();
        List<Card> cards = new ArrayList<>();
        Card card1 = new Card();
        Card card2 = new Card();
        cards.add(card1);
        cards.add(card2);
        deck.setDeck(cards);
        assertEquals(0, deck.getDeck().size());
        assertFalse(deck.getDeck().contains(card1));
        assertFalse(deck.getDeck().contains(card2));
    }

    @Test
    public void testDeckRemoveNonExistentCard() {
        Deck deck = new Deck();
        Card card = new Card();
        assertFalse(deck.contains(card));
        deck.remove(card);
        assertFalse(deck.contains(card));
    }

    @Test
    public void testDeckAddNullCard() {
        Deck deck = new Deck();
        deck.add(null);
        assertEquals(1, deck.getDeck().size());
    }

    @Test
    public void testDeckRemoveNullCard() {
        Deck deck = new Deck();
        deck.remove(null);
        assertEquals(0, deck.getDeck().size());
    }

    @Test
    public void testDeckContainsNullCard() {
        Deck deck = new Deck();
        assertFalse(deck.contains(null));
    }

    @Test
    public void testDeckSetNullDeck() {
        Deck deck = new Deck();
        deck.setDeck(null);
        assertNotNull(deck.getDeck());
        assertEquals(0, deck.getDeck().size());
    }

    @Test
    public void testDeckSetEmptyDeck() {
        Deck deck = new Deck();
        deck.setDeck(new ArrayList<>());
        assertNotNull(deck.getDeck());
        assertEquals(0, deck.getDeck().size());
    }

    @Test
    public void testDeckSetWithCards() {
        Deck deck = new Deck();
        List<Card> cards = new ArrayList<>();
        cards.add(new Card());
        cards.add(new Card());
        deck.setDeck(cards);
        assertNotNull(deck.getDeck());
        assertEquals(0, deck.getDeck().size());
    }

    @Test
    public void testDeckId() {
        Deck deck = new Deck();
        deck.setId(1);
        assertEquals(1, deck.getId());
    }

    @Test
    public void testAddDuplicateCardToDeck() {
        Deck deck = new Deck();
        Card card = new Card();
        deck.add(card);
        deck.add(card);
        assertEquals(2, deck.getDeck().size());
    }

    @Test
    public void testRemoveNonExistingCardFromDeck() {
        Deck deck = new Deck();
        Card card = new Card();
        deck.remove(card);
        assertEquals(0, deck.getDeck().size());
    }

    @Test
    public void testDeckClear() {
        Deck deck = new Deck();
        deck.add(new Card());
        deck.add(new Card());
        deck.getDeck().clear();
        assertEquals(0, deck.getDeck().size());
    }

    @Test
    public void testDeckSize() {
        Deck deck = new Deck();
        deck.add(new Card());
        deck.add(new Card());
        assertEquals(2, deck.getDeck().size());
    }

    @Test
    public void testDeckIsEmpty() {
        Deck deck = new Deck();
        assertTrue(deck.getDeck().isEmpty());
    }

    @Test
    public void testDeckIsNotEmpty() {
        Deck deck = new Deck();
        deck.add(new Card());
        assertFalse(deck.getDeck().isEmpty());
    }
}
