package pl.edu.us.medrala.szymon.aiagent.algorithm;

import helper.generator.cards.CardGenerator;
import helper.repo.TestCardRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.edu.us.medrala.szymon.aiagent.board.YourBoard;
import pl.edu.us.medrala.szymon.aiagent.cards.*;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class NRAlgorithmTest {
    private NRAlgorithm algorithm;
    private YourBoard yourBoard;
    private TestCardRepo cardRepo;
    private Card ciriCard;
    private Card geraltCard;
    private Card spyCardStrong;
    private Card spyCardMedium;
    @BeforeEach
    void setup() {
        algorithm = new NRAlgorithm();
        yourBoard = new YourBoard();
        cardRepo = new TestCardRepo();
        final int rowSize = 10;

        for (int i = 0; i < rowSize; i++) {
            yourBoard.getCloseCombat().add(CardGenerator.generateCard("Close Combat " + i, 1, 1, Type.NONE));
            yourBoard.getSiege().add(CardGenerator.generateCard("Siege " + i, 1, 1, Type.NONE));
            yourBoard.getRanged().add(CardGenerator.generateCard("Ranged " + i, 1, 1, Type.NONE));
        }

        yourBoard.getHand().addAll(yourBoard.getCloseCombat().getRow());
        yourBoard.getHand().addAll(yourBoard.getSiege().getRow());
        yourBoard.getHand().addAll(yourBoard.getRanged().getRow());

        ciriCard = new Card(209L, "Ciri", 10, 10,
                Ability.NONE, Type.HERO, 10, Range.CLOSE_COMBAT,
                Fraction.SCOIATAEL, new byte[0]);
        geraltCard = new Card(198L, "Geralt", 20, 20,
                Ability.NONE, Type.HERO, 10, Range.CLOSE_COMBAT,
                Fraction.SCOIATAEL, new byte[0]);
        spyCardStrong = new Card(302L, "Spy Strong", 10, 10,
                Ability.SPY, Type.NONE, 1, Range.CLOSE_COMBAT,
                Fraction.SCOIATAEL, new byte[0]);
        spyCardMedium = new Card(303L, "Spy Medium", 7, 7,
                Ability.SPY, Type.NONE, 1, Range.CLOSE_COMBAT,
                Fraction.SCOIATAEL, new byte[0]);
        yourBoard.getHand().add(ciriCard);
        yourBoard.getHand().add(geraltCard);
        yourBoard.getHand().add(spyCardStrong);

        algorithm.yourBoard = yourBoard;
        algorithm.cardRepo = cardRepo;
        algorithm.applyCardToCollection();
    }

    @Test
    void shouldSetupCardsAtStart() {
        algorithm.setup(yourBoard, cardRepo);
        assertEquals(algorithm.yourBoard, yourBoard);
        assertEquals(algorithm.cardRepo, cardRepo);
    }

    @Test
    void shouldExecuteAndReturnCiriOrGeraltWhenPhaseFirstAndRoundFirst() {
        final Optional<Card> card = algorithm.execute(List.of(
                ciriCard,
                getCardWithAbility(Ability.TIGHT_BOND),
                getCardWithAbility(Ability.TIGHT_BOND),
                getCardWithAbility(Ability.HERO)
        ), yourBoard, cardRepo, 1);

        Assertions.assertTrue(card.isPresent());
        Assertions.assertEquals(ciriCard, card.get());
    }

    @Test
    void shouldNotReturnCardWhenNoPlayableCardAvailable() {
        final Optional<Card> card = algorithm.execute(List.of(
                spyCardStrong,
                getCardWithAbility(Ability.TIGHT_BOND),
                getCardWithAbility(Ability.TIGHT_BOND),
                getCardWithAbility(Ability.HERO)
        ), yourBoard, cardRepo, 1);

        assertTrue(card.isPresent());
    }

    @Test
    void shouldExecuteAndReturnGeraltWithoutCiriOnHandWhenPhaseFirstAndRoundFirst() {
        yourBoard.getHand().remove(ciriCard);
        final Optional<Card> card = algorithm.execute(List.of(
                spyCardStrong,
                getCardWithAbility(Ability.TIGHT_BOND),
                getCardWithAbility(Ability.TIGHT_BOND),
                getCardWithAbility(Ability.HERO)
        ), yourBoard, cardRepo, 1);

        Assertions.assertTrue(card.isPresent());
        Assertions.assertEquals(geraltCard, card.get());
    }
    @Test
    void shouldExecuteAndReturnCiriWithoutGeraltOnHandWhenPhaseFirstAndRoundFirst() {
        yourBoard.getHand().remove(geraltCard);
        final Optional<Card> card = algorithm.execute(List.of(
                spyCardStrong,
                getCardWithAbility(Ability.TIGHT_BOND),
                getCardWithAbility(Ability.TIGHT_BOND),
                getCardWithAbility(Ability.HERO)
        ), yourBoard, cardRepo, 1);

        Assertions.assertTrue(card.isPresent());
        Assertions.assertEquals(ciriCard, card.get());
    }

    @Test
    void shouldExecuteAndReturnSpyWhenPhaseFirstAndRoundFirst() {
        yourBoard.getHand().remove(ciriCard);
        yourBoard.getHand().remove(geraltCard);
        final Optional<Card> card = algorithm.execute(List.of(
                spyCardStrong,
                getCardWithAbility(Ability.TIGHT_BOND),
                getCardWithAbility(Ability.TIGHT_BOND),
                getCardWithAbility(Ability.HERO)
        ), yourBoard, cardRepo, 1);

        Assertions.assertTrue(card.isPresent());
        Assertions.assertEquals(spyCardStrong, card.get());
    }

    @Test
    void shouldExecuteAndReturnStrongestSpyCardWhenMultipleSpyCardsAvailableWhenPhaseFirstAndRoundFirst() {
        yourBoard.getHand().remove(ciriCard);
        yourBoard.getHand().remove(geraltCard);
        yourBoard.getHand().add(spyCardMedium);

        final Optional<Card> card = algorithm.execute(List.of(
                spyCardStrong,
                spyCardMedium,
                getCardWithAbility(Ability.TIGHT_BOND),
                getCardWithAbility(Ability.TIGHT_BOND),
                getCardWithAbility(Ability.HERO)
        ), yourBoard, cardRepo, 1);

        Assertions.assertTrue(card.isPresent());
        Assertions.assertEquals(spyCardStrong, card.get());
    }

    @Test
    void shouldExecuteAndReturnSpyCardWhenPhaseSecondAndRoundFirst() {
        yourBoard.getHand().remove(ciriCard);
        yourBoard.getHand().remove(geraltCard);
        final Optional<Card> card = algorithm.execute(List.of(
                spyCardStrong,
                getCardWithAbility(Ability.TIGHT_BOND),
                getCardWithAbility(Ability.TIGHT_BOND),
                getCardWithAbility(Ability.HERO)
        ), yourBoard, cardRepo, 2);

        Assertions.assertTrue(card.isPresent());
        Assertions.assertEquals(spyCardStrong, card.get());
    }
    @Test
    void shouldExecuteAndReturnWeaknessSpyCardWhenMultipleSpyCardsAvailableWhenPhaseSecondAndRoundFirst() {
        yourBoard.getHand().remove(ciriCard);
        yourBoard.getHand().remove(geraltCard);
        yourBoard.getHand().add(spyCardMedium);
        final Optional<Card> card = algorithm.execute(List.of(
                spyCardStrong,
                spyCardMedium,
                getCardWithAbility(Ability.TIGHT_BOND),
                getCardWithAbility(Ability.TIGHT_BOND),
                getCardWithAbility(Ability.HERO)
        ), yourBoard, cardRepo, 2);

        Assertions.assertTrue(card.isPresent());
        Assertions.assertEquals(spyCardStrong, card.get());
    }

    @Test
    void shouldExecuteAndReturnSpyCardWhenPhaseFirstAndRoundSecond() {
        yourBoard.isSecondRound();
        yourBoard.getHand().remove(ciriCard);
        yourBoard.getHand().remove(geraltCard);
        final Optional<Card> card = algorithm.execute(List.of(
                spyCardStrong,
                getCardWithAbility(Ability.TIGHT_BOND),
                getCardWithAbility(Ability.TIGHT_BOND),
                getCardWithAbility(Ability.HERO)
        ), yourBoard, cardRepo,1);

        Assertions.assertTrue(card.isPresent());
        Assertions.assertEquals(spyCardStrong, card.get());
    }
    @Test
    void shouldExecuteAndReturnStrongestSpyCardWhenMultipleSpyCardsAvailableWhenPhaseFirstAndRoundSecond() {
        yourBoard.isSecondRound();
        yourBoard.getHand().remove(ciriCard);
        yourBoard.getHand().remove(geraltCard);
        yourBoard.getHand().add(spyCardMedium);
        final Optional<Card> card = algorithm.execute(List.of(
                spyCardStrong,
                spyCardMedium,
                getCardWithAbility(Ability.TIGHT_BOND),
                getCardWithAbility(Ability.TIGHT_BOND),
                getCardWithAbility(Ability.HERO)
        ), yourBoard, cardRepo,1);

        Assertions.assertTrue(card.isPresent());
        Assertions.assertEquals(spyCardStrong, card.get());
    }
    @Test
    void shouldExecuteAndReturnHeroWhenPhaseFirstAndRoundSecond() {
        yourBoard.isSecondRound();
        final Optional<Card> card = algorithm.execute(List.of(
                ciriCard,
                getCardWithAbility(Ability.TIGHT_BOND),
                getCardWithAbility(Ability.TIGHT_BOND),
                getCardWithAbility(Ability.HERO)
        ), yourBoard, cardRepo, 1);

        Assertions.assertTrue(card.isPresent());
        Assertions.assertEquals(ciriCard, card.get());
    }
    @Test
    void shouldExecuteAndReturnWeaknessHeroWhenMultipleHeroCardsAvailablePhaseFirstAndRoundSecond() {
        yourBoard.isSecondRound();
        final Optional<Card> card = algorithm.execute(List.of(
                ciriCard,
                geraltCard,
                getCardWithAbility(Ability.TIGHT_BOND),
                getCardWithAbility(Ability.TIGHT_BOND),
                getCardWithAbility(Ability.HERO)
        ), yourBoard, cardRepo, 1);

        Assertions.assertTrue(card.isPresent());
        Assertions.assertEquals(ciriCard, card.get());
    }
    @Test
    void shouldExecuteAndReturnSpyWhenPhaseThirdAndRoundFirst() {
        final Optional<Card> card = algorithm.execute(List.of(
                spyCardStrong,
                getCardWithAbility(Ability.TIGHT_BOND),
                getCardWithAbility(Ability.TIGHT_BOND),
                getCardWithAbility(Ability.HERO)
        ), yourBoard, cardRepo, 3);

        Assertions.assertTrue(card.isPresent());
        Assertions.assertEquals(spyCardStrong, card.get());
    }
    @Test
    void shouldExecuteAndReturnStrongestSpyCardWhenMultipleSpyCardsAvailableWhenPhaseThirdAndRoundFirst() {
        yourBoard.isSecondRound();
        yourBoard.getHand().remove(ciriCard);
        yourBoard.getHand().remove(geraltCard);
        yourBoard.getHand().add(spyCardMedium);
        final Optional<Card> card = algorithm.execute(List.of(
                spyCardStrong,
                spyCardMedium,
                getCardWithAbility(Ability.TIGHT_BOND),
                getCardWithAbility(Ability.TIGHT_BOND),
                getCardWithAbility(Ability.HERO)
        ), yourBoard, cardRepo,3);

        Assertions.assertTrue(card.isPresent());
        Assertions.assertEquals(spyCardStrong, card.get());
    }

    @Test
    void shouldReturnPassWhenShouldPlayIsFalseInPhaseSecond() {
        yourBoard.setShouldPlay(false);
        final Optional<Card> card = algorithm.execute(List.of(
                ciriCard,
                getCardWithAbility(Ability.TIGHT_BOND),
                getCardWithAbility(Ability.TIGHT_BOND),
                getCardWithAbility(Ability.HERO)
        ), yourBoard, cardRepo, 2);
        Assertions.assertTrue(card.isPresent());
    }
    @Test
    void shouldReturnPassWhenShouldPlayIsFalseInPhaseFirst() {
        yourBoard.setShouldPlay(false);
        final Optional<Card> card = algorithm.execute(List.of(
                ciriCard,
                getCardWithAbility(Ability.TIGHT_BOND),
                getCardWithAbility(Ability.TIGHT_BOND),
                getCardWithAbility(Ability.HERO)
        ), yourBoard, cardRepo, 1);
        Assertions.assertTrue(card.isPresent());
    }
    @Test
    void shouldReturnPassWhenShouldPlayIsFalseInPhaseThird() {
        yourBoard.setShouldPlay(false);
        final Optional<Card> card = algorithm.execute(List.of(
                ciriCard,
                getCardWithAbility(Ability.TIGHT_BOND),
                getCardWithAbility(Ability.TIGHT_BOND),
                getCardWithAbility(Ability.HERO)
        ), yourBoard, cardRepo, 3);
        Assertions.assertTrue(card.isPresent());
    }
    private Card getCardWithAbility(final Ability ability) {
        return yourBoard.getHand().stream().filter(c -> c.getAbility() == ability).findFirst().get();
    }

}
