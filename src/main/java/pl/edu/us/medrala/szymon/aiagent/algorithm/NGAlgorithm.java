package pl.edu.us.medrala.szymon.aiagent.algorithm;

import pl.edu.us.medrala.szymon.aiagent.board.YourBoard;
import pl.edu.us.medrala.szymon.aiagent.cards.Card;
import pl.edu.us.medrala.szymon.aiagent.cards.repository.CardRepo;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class NGAlgorithm extends Algorithm {

    public NGAlgorithm() {
        super(Comparator.comparingInt(Card::getUnitStrength));
    }

    @Override
    public Optional<Card> execute(final List<Card> cards, final YourBoard yourBoard, final CardRepo cardRepo, final int phase) {
        if (shouldSetupAlgorithm(yourBoard, phase)) {
            setup(yourBoard, cardRepo);
        }
        applyCardToCollection();

        if (yourBoard.isFirstRound()) {
            return switch (phase) {
                case 1 -> {
                    if (yourBoard.shouldPlay()) {
                        final Card card = summonCiriOrGeralt().orElse(spyCards.stream().min(comparator).orElseThrow());
                        yourBoard.placeCardOnBoard(card);
                        yield Optional.of(card);
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
                        if (super.isEnemyPassed()) {
                            card = returnNoneHero(true).or(this::returnTightBond);
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
            final Optional<Card> card = returnSpy(true)
                    .or(() -> returnHero(false))
                    .or(() -> returnNoneHero(true))
                    .or(this::returnHorn)
                    .or(this::returnTightBond)
                    .or(this::returnPass);
            yourBoard.placeCardOnBoard(card.orElseThrow());
            return card;
        }

        return Optional.empty();
    }
}
