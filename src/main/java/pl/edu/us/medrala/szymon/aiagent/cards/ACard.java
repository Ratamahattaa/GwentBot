package pl.edu.us.medrala.szymon.aiagent.cards;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import pl.edu.us.medrala.szymon.aiagent.deck.Deck;
import pl.edu.us.medrala.szymon.aiagent.deck.DeckConfig;

import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.EnumType;
import jakarta.persistence.CascadeType;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@NoArgsConstructor
public abstract class ACard {
    @Id
    @Column(name = "ID")
    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    @Column(name = "NAME")
    private String name;

    @Getter
    @Setter
    @Column(name = "PRICE")
    private int price;

    @Getter
    @Setter
    @Column(name = "FRACTION")
    @Enumerated(EnumType.STRING)
    private Fraction fraction;

    @Getter
    @Setter
    @Lob
    @ToString.Exclude
    private byte[] image;

    @JsonIgnore
    @ToString.Exclude
    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "Card_Deck",
            joinColumns = { @JoinColumn(name = "acard_id") },
            inverseJoinColumns = { @JoinColumn(name = "deck_id") }
    )
    private final List<Deck> decks = new ArrayList<>();

    protected ACard(long id, String name, int price, Fraction fraction, byte[] image) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.fraction = fraction;
        this.image = image;
    }

    @Override
    public String toString() {
        return "Card {id:%d,name:'%s',fraction:%s,}".formatted(id, name, fraction);
    }
}
