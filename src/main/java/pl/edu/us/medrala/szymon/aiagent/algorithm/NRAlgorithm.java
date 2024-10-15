package pl.edu.us.medrala.szymon.aiagent.algorithm;

import pl.edu.us.medrala.szymon.aiagent.board.YourBoard;
import pl.edu.us.medrala.szymon.aiagent.cards.Card;
import pl.edu.us.medrala.szymon.aiagent.cards.Range;
import pl.edu.us.medrala.szymon.aiagent.cards.repository.CardRepo;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class NRAlgorithm extends Algorithm {

    public static final long LEADER_ID = 2348L;

    public NRAlgorithm() {
        super(Comparator.comparingInt(Card::getUnitStrength).reversed());
    }

    @Override
    public Optional<Card> execute(final List<Card> cards, final YourBoard yourBoard, final CardRepo cardRepo, int phase) {
        if (shouldSetupAlgorithm(yourBoard, phase)) {
            setup(yourBoard, cardRepo);
        }
        applyCardToCollection();

        if (yourBoard.isFirstRound()) {
            return switch (phase) {
                case 1 -> {
                    if (yourBoard.shouldPlay()) {
                        final Optional<Card> card = summonCiriOrGeralt().or(() -> returnSpy(true));
                        yourBoard.placeCardOnBoard(card.orElseThrow(() -> new IllegalStateException("No card to play.")));
                        yield card;
                    }

                    yield returnPass();
                }
                case 2 -> {
                    if (yourBoard.shouldPlay()) {
                        final Optional<Card> card = returnSpy(true);
                        yourBoard.placeCardOnBoard(card.orElseThrow());
                        yield card;
                    }

                    yield returnPass();
                }
                default -> {
                    if (yourBoard.shouldPlay()) {
                        final Optional<Card> card;
                        if (isEnemyPassed()) {
                            card = returnTightBond()
                                    .filter(c -> !c.getRange().equals(Range.SIEGE))
                                    .stream()
                                    .max(comparator)
                                    .or(() -> returnHero(false));
                        } else {
                            card = returnSpy(true)
                                    .or(() -> returnHero(true))
                                    .or(this::returnTightBond)
                                    .or(this::returnHorn)
                                    .or(this::returnPass);
                        }

                        yourBoard.placeCardOnBoard(card.orElseThrow());
                        yield card;
                    }

                    yield returnPass();
                }
            };
        }

        if (yourBoard.isSecondRound()) {
            if (phase == 1) {
                final Optional<Card> leader = returnLeader();
                yourBoard.placeCardOnBoard(leader.orElseThrow());
                return leader;
            } else {
                final Optional<Card> card = returnSpy(true)
                        .or(() -> returnHero(true))
                        .or(this::returnHorn)
                        .or(this::returnTightBond)
                        .or(this::returnPass);

                yourBoard.placeCardOnBoard(card.orElseThrow());
                return card;
            }
        }
        return Optional.empty();
    }

    private Optional<Card> returnLeader() {
        return cardRepo.findById(LEADER_ID);
    }
}