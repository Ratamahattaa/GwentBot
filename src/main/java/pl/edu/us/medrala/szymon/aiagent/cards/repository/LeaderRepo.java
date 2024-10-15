package pl.edu.us.medrala.szymon.aiagent.cards.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.us.medrala.szymon.aiagent.cards.Card;
import pl.edu.us.medrala.szymon.aiagent.cards.Leader;

@Repository
public interface LeaderRepo extends JpaRepository<Leader, Long> {
}
