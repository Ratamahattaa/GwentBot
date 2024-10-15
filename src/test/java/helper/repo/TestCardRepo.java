package helper.repo;

import pl.edu.us.medrala.szymon.aiagent.cards.*;
import pl.edu.us.medrala.szymon.aiagent.cards.repository.CardRepo;

import java.util.Optional;

public class TestCardRepo extends InMemoryJpaRepository<Card, Long> implements CardRepo {

    public TestCardRepo() {
        super(Card::getId);

        database.put(777L, new Card(1, "test", 1, 1, Ability.NONE, Type.NONE, 1,
                Range.CLOSE_COMBAT, Fraction.SCOIATAEL, CardsLoader.getImageBytes("Vernon Roche.png")));
    }

    @Override
    public Optional<Card> findPassCard() {
        return Optional.ofNullable(database.get(777L));
    }
}
