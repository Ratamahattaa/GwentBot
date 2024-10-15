package pl.edu.us.medrala.szymon.aiagent.deck;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeckRepositoryJpa extends JpaRepository<Deck, Integer> {
}
