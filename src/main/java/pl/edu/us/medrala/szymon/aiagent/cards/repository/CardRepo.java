package pl.edu.us.medrala.szymon.aiagent.cards.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.edu.us.medrala.szymon.aiagent.cards.ACard;
import pl.edu.us.medrala.szymon.aiagent.cards.Card;

import java.util.Optional;

@Repository
public interface CardRepo extends JpaRepository<Card, Long> {
    @Query(value = "SELECT c FROM Card c WHERE c.name = 'Pass'")
    Optional<Card> findPassCard();
}
