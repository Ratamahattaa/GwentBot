package pl.edu.us.medrala.szymon.aiagent.deck;

import lombok.Getter;
import lombok.Setter;
import pl.edu.us.medrala.szymon.aiagent.cards.ACard;
import pl.edu.us.medrala.szymon.aiagent.cards.Card;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
public class Deck {
    @Id
    private Integer id;

    @ManyToMany(mappedBy = "decks")
    private final List<Card> deck = new ArrayList<>();

    public void remove(Card card) {
        deck.remove(card);
    }

    public void add(Card card) {
        deck.add(card);
    }

    public boolean contains(Card card) {
        return deck.contains(card);
    }

    public void setDeck(List<Card> deckWithImages) {
    }
}
