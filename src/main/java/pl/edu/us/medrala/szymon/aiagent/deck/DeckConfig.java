package pl.edu.us.medrala.szymon.aiagent.deck;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.edu.us.medrala.szymon.aiagent.api.DeckType;
import pl.edu.us.medrala.szymon.aiagent.cards.Card;
import pl.edu.us.medrala.szymon.aiagent.cards.repository.CardRepo;

import java.util.ArrayList;
import java.util.List;

@Configuration(proxyBeanMethods = false)
@RequiredArgsConstructor
public class DeckConfig {
    private final CardRepo cardRepository;

    @Bean
    Deck deck() {
        return new Deck();
    }

    public List<Card> determineDeck(final DeckType type) {
        switch (type) {
            case NORTHERN_REALMS_THE_UNIQUE -> {
                return northernRealmsTheUnique();
            }
            case NILFGAARD_ROLLERO -> {
                return nilfgaardRollero();
            }
            default -> throw new IllegalStateException("Unexpected value: " + type);
        }
    }

    private List<Card> northernRealmsTheUnique() {
        List<Card> northernRealmsTheUnique= new ArrayList<>();
        northernRealmsTheUnique.add(cardRepository.findById(557L).orElseThrow());
        northernRealmsTheUnique.add(cardRepository.findById(187L).orElseThrow());
        northernRealmsTheUnique.add(cardRepository.findById(568L).orElseThrow());
        northernRealmsTheUnique.add(cardRepository.findById(579L).orElseThrow());
        northernRealmsTheUnique.add(cardRepository.findById(590L).orElseThrow());
        northernRealmsTheUnique.add(cardRepository.findById(198L).orElseThrow());
        northernRealmsTheUnique.add(cardRepository.findById(209L).orElseThrow());
        northernRealmsTheUnique.add(cardRepository.findById(231L).orElseThrow());
        northernRealmsTheUnique.add(cardRepository.findById(242L).orElseThrow());
        northernRealmsTheUnique.add(cardRepository.findById(253L).orElseThrow());
        northernRealmsTheUnique.add(cardRepository.findById(299L).orElseThrow());
        northernRealmsTheUnique.add(cardRepository.findById(832L).orElseThrow());
        northernRealmsTheUnique.add(cardRepository.findById(843L).orElseThrow());
        northernRealmsTheUnique.add(cardRepository.findById(711L).orElseThrow());
        northernRealmsTheUnique.add(cardRepository.findById(645L).orElseThrow());
        northernRealmsTheUnique.add(cardRepository.findById(777L).orElseThrow());
        northernRealmsTheUnique.add(cardRepository.findById(788L).orElseThrow());
        northernRealmsTheUnique.add(cardRepository.findById(799L).orElseThrow());
        northernRealmsTheUnique.add(cardRepository.findById(909L).orElseThrow());
        northernRealmsTheUnique.add(cardRepository.findById(920L).orElseThrow());
        northernRealmsTheUnique.add(cardRepository.findById(931L).orElseThrow());
        northernRealmsTheUnique.add(cardRepository.findById(601L).orElseThrow());
        return northernRealmsTheUnique;
    }

    private List<Card> nilfgaardRollero() {
        List<Card> nilfgaardRollero= new ArrayList<>();
        nilfgaardRollero.add(cardRepository.findById(187L).orElseThrow());
        nilfgaardRollero.add(cardRepository.findById(990L).orElseThrow());
        nilfgaardRollero.add(cardRepository.findById(979L).orElseThrow());
        nilfgaardRollero.add(cardRepository.findById(1001L).orElseThrow());
        nilfgaardRollero.add(cardRepository.findById(1012L).orElseThrow());
        nilfgaardRollero.add(cardRepository.findById(198L).orElseThrow());
        nilfgaardRollero.add(cardRepository.findById(209L).orElseThrow());
        nilfgaardRollero.add(cardRepository.findById(253L).orElseThrow());
        nilfgaardRollero.add(cardRepository.findById(1365L).orElseThrow());
        nilfgaardRollero.add(cardRepository.findById(1376L).orElseThrow());
        nilfgaardRollero.add(cardRepository.findById(231L).orElseThrow());
        nilfgaardRollero.add(cardRepository.findById(242L).orElseThrow());
        nilfgaardRollero.add(cardRepository.findById(299L).orElseThrow());
        nilfgaardRollero.add(cardRepository.findById(1310L).orElseThrow());
        nilfgaardRollero.add(cardRepository.findById(1244L).orElseThrow());
        nilfgaardRollero.add(cardRepository.findById(1255L).orElseThrow());
        nilfgaardRollero.add(cardRepository.findById(1122L).orElseThrow());
        nilfgaardRollero.add(cardRepository.findById(1111L).orElseThrow());
        nilfgaardRollero.add(cardRepository.findById(1155L).orElseThrow());
        nilfgaardRollero.add(cardRepository.findById(1299L).orElseThrow());
        nilfgaardRollero.add(cardRepository.findById(1288L).orElseThrow());
        nilfgaardRollero.add(cardRepository.findById(1277L).orElseThrow());
        return nilfgaardRollero;
    }
}
