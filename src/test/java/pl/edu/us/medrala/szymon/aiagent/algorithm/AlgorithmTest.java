package pl.edu.us.medrala.szymon.aiagent.algorithm;

import helper.repo.TestCardRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.edu.us.medrala.szymon.aiagent.board.YourBoard;
import pl.edu.us.medrala.szymon.aiagent.cards.*;
import pl.edu.us.medrala.szymon.aiagent.cards.repository.CardRepo;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class AlgorithmTest {
    private YourBoard yourBoard;
    private CardRepo cardRepo;
    private Algorithm algorithm;

    private Card ciriCard;
    private Card geraltCard;
    private Card cardToRemove;
    private Card roachCard;
    private Card hornCardStrong;
    private Card hornCardMedium;
    private Card hornCardWeak;

    private Card spyCardWeak;
    private Card spyCardStrong;
    private Card spyCardMedium;

    private static final long CIRI_CARD_ID = 209L;
    private static final long GERALT_CARD_ID = 198L;
    private static final long ROACH_CARD_ID = 187L;

    @BeforeEach
    void setUp() {
        yourBoard = new YourBoard();
        cardRepo = new TestCardRepo();
        algorithm = new Algorithm(Comparator.comparingInt(Card::getBaseUnitStrength)) {
            @Override
            public Optional<Card> execute(List<Card> cards, YourBoard yourBoard, CardRepo cardRepo, int phase) {
                return Optional.empty();
            }
        };

        ciriCard = new Card(CIRI_CARD_ID, "Ciri", 10, 10,
                Ability.NONE, Type.HERO, 10, Range.CLOSE_COMBAT,
                Fraction.SCOIATAEL, CardsLoader.getImageBytes("Vernon Roche.png"));
        geraltCard = new Card(GERALT_CARD_ID, "Geralt", 20, 20,
                Ability.NONE, Type.HERO, 10, Range.CLOSE_COMBAT,
                Fraction.SCOIATAEL, CardsLoader.getImageBytes("Vernon Roche.png"));
        roachCard = new Card(ROACH_CARD_ID, "Roach", 5, 5,
                Ability.NONE, Type.HERO, 10, Range.CLOSE_COMBAT,
                Fraction.SCOIATAEL, CardsLoader.getImageBytes("Vernon Roche.png"));


        yourBoard.getHand().add(ciriCard);
        yourBoard.getHand().add(geraltCard);
        yourBoard.getDeck().add(roachCard);

        algorithm.setup(yourBoard, cardRepo);

        spyCardWeak = new Card(101L, "Weak Spy", 10, 10,
                Ability.NONE, Type.HERO, 10, Range.CLOSE_COMBAT,
                Fraction.SCOIATAEL, CardsLoader.getImageBytes("Vernon Roche.png"));
        spyCardStrong = new Card(102L, "Strong Spy", 20, 20,
                Ability.NONE, Type.HERO, 10, Range.CLOSE_COMBAT,
                Fraction.SCOIATAEL, CardsLoader.getImageBytes("Vernon Roche.png"));
        spyCardMedium = new Card(103L, "Strong Spy", 15, 15,
                Ability.NONE, Type.HERO, 10, Range.CLOSE_COMBAT,
                Fraction.SCOIATAEL, CardsLoader.getImageBytes("Vernon Roche.png"));
        cardToRemove = new Card(110L, "Test Card", 20, 20,
                Ability.NONE, Type.HERO, 10, Range.CLOSE_COMBAT,
                Fraction.SCOIATAEL, CardsLoader.getImageBytes("Vernon Roche.png"));
        hornCardMedium = new Card(111L, "Test Card", 10, 10,
                Ability.COMMANDERS_HORN, Type.HERO, 10, Range.CLOSE_COMBAT,
                Fraction.SCOIATAEL, CardsLoader.getImageBytes("Vernon Roche.png"));
        hornCardWeak = new Card(112L, "Test Card", 5, 5,
                Ability.COMMANDERS_HORN, Type.HERO, 10, Range.CLOSE_COMBAT,
                Fraction.SCOIATAEL, CardsLoader.getImageBytes("Vernon Roche.png"));
        hornCardWeak = new Card(113L, "Test Card", 15, 15,
                Ability.COMMANDERS_HORN, Type.HERO, 10, Range.CLOSE_COMBAT,
                Fraction.SCOIATAEL, CardsLoader.getImageBytes("Vernon Roche.png"));

        yourBoard.getHand().add(spyCardWeak);
        yourBoard.getHand().add(spyCardStrong);
        yourBoard.getHand().add(spyCardMedium);

        algorithm.setup(yourBoard, cardRepo);
        algorithm.applyCardToCollection();
    }
    @Test
    void testSummonCiriOrGeralt_BothInHand() {
        yourBoard.getHand().add(ciriCard);
        yourBoard.getHand().add(geraltCard);
        algorithm.applyCardToCollection();
        Optional<Card> result = algorithm.summonCiriOrGeralt();

        assertTrue(result.isPresent());
        assertTrue(result.get() == ciriCard || result.get() == geraltCard);

        assertFalse(yourBoard.getHand().contains(result.get()));
    }
    @Test
    void testSummonCiriOrGeralt_NeitherInHand() {
        yourBoard.getHand().remove(ciriCard);
        yourBoard.getHand().remove(geraltCard);
        algorithm.applyCardToCollection();

        Optional<Card> result = algorithm.summonCiriOrGeralt();

        assertFalse(result.isPresent());

        assertFalse(yourBoard.getHand().contains(ciriCard));
        assertFalse(yourBoard.getHand().contains(geraltCard));
    }
    @Test
    void testSummonCiriOrGeralt_CiriInHand() {
        yourBoard.getHand().add(ciriCard);
        yourBoard.getHand().remove(geraltCard);
        algorithm.applyCardToCollection();

        Optional<Card> result = algorithm.summonCiriOrGeralt();

        assertTrue(result.isPresent());
        assertEquals(ciriCard, result.get());

        assertFalse(yourBoard.getHand().contains(ciriCard));
    }

    @Test
    void testSummonCiriOrGeralt_GeraltInHand() {
        yourBoard.getHand().remove(ciriCard);
        yourBoard.getHand().add(geraltCard);
        algorithm.applyCardToCollection();

        Optional<Card> result = algorithm.summonCiriOrGeralt();

        assertTrue(result.isPresent());
        assertEquals(geraltCard, result.get());

        assertFalse(yourBoard.getHand().contains(geraltCard));
    }


    @Test
    void testSummonCiriOrGeralt_NoCiriOrGeralt() {
        yourBoard.getHand().remove(ciriCard);
        yourBoard.getHand().remove(geraltCard);
        algorithm.applyCardToCollection();

        Optional<Card> result = algorithm.summonCiriOrGeralt();

        assertFalse(result.isPresent());
    }
    @Test
    void testReturnSpy_EmptySpyCards_StrongestFirst() {
        algorithm.spyCards.clear();
        Optional<Card> result = algorithm.returnSpy(true);
        assertFalse(result.isPresent());
    }
    @Test
    void testReturnSpy_EmptySpyCards_WeakestFirst() {
        algorithm.spyCards.clear();
        Optional<Card> result = algorithm.returnSpy(false);
        assertFalse(result.isPresent());
    }
    @Test
    void testReturnSpy_OneCardInSpyCards_StrongestFirst() {
        algorithm.spyCards.clear();
        algorithm.spyCards.add(spyCardStrong);
        Optional<Card> result = algorithm.returnSpy(true);
        assertTrue(result.isPresent());
        assertEquals(spyCardStrong, result.get());
        assertFalse(algorithm.spyCards.contains(spyCardStrong));
    }
    @Test
    void testReturnSpy_OneCardInSpyCards_WeakestFirst() {
        algorithm.spyCards.clear();
        algorithm.spyCards.add(spyCardWeak);
        Optional<Card> result = algorithm.returnSpy(false);
        assertTrue(result.isPresent());
        assertEquals(spyCardWeak, result.get());
        assertFalse(algorithm.spyCards.contains(spyCardWeak));
    }
    @Test
    void testReturnSpy_MultipleCardsInSpyCards_StrongestFirst() {
        algorithm.spyCards.clear();
        algorithm.spyCards.addAll(Arrays.asList(spyCardWeak, spyCardStrong, spyCardMedium));
        Optional<Card> result = algorithm.returnSpy(true);
        assertTrue(result.isPresent());
        assertEquals(spyCardStrong, result.get());
        assertFalse(algorithm.spyCards.contains(spyCardStrong));
    }
    @Test
    void testReturnSpy_MultipleCardsInSpyCards_WeakestFirst() {
        algorithm.spyCards.clear();
        algorithm.spyCards.addAll(Arrays.asList(spyCardWeak, spyCardStrong, spyCardMedium));
        Optional<Card> result = algorithm.returnSpy(false);
        assertTrue(result.isPresent());
        assertEquals(spyCardWeak, result.get());
        assertFalse(algorithm.spyCards.contains(spyCardWeak));
    }

    @Test
    void testReturnSpy_EmptySpyCards() {
        algorithm.spyCards.clear();
        Optional<Card> result = algorithm.returnSpy(true);

        assertFalse(result.isPresent());
    }

    @Test
    void testIsEnemyPassed_NotPassedInitially() {
        assertFalse(algorithm.isEnemyPassed());
    }

    @Test
    void testIsEnemyPassed_EnemyPassesOnce() {
        assertFalse(algorithm.isEnemyPassed());

        yourBoard.setEnemyPassed(true);

        assertTrue(algorithm.isEnemyPassed());

        yourBoard.setEnemyPassed(false);

        assertFalse(algorithm.isEnemyPassed());
    }
    @Test
    void testIsEnemyPassed_EnemyPassesMultipleTimes() {
        assertFalse(algorithm.isEnemyPassed());

        yourBoard.setEnemyPassed(true);
        assertTrue(algorithm.isEnemyPassed());

        yourBoard.setEnemyPassed(true);
        assertTrue(algorithm.isEnemyPassed());

        yourBoard.setEnemyPassed(false);
        assertFalse(algorithm.isEnemyPassed());
    }
    @Test
    void testIsEnemyPassed_BoardStates() {
        assertFalse(algorithm.isEnemyPassed());

        yourBoard.setEnemyPassed(true);
        assertTrue(algorithm.isEnemyPassed());

        for (int i = 0; i < 5; i++) {
            boolean enemyPassed = i % 2 == 0;
            yourBoard.setEnemyPassed(enemyPassed);
            assertEquals(enemyPassed, algorithm.isEnemyPassed());
        }
    }
    @Test
    void testReturnHero_EmptyHeroCards_StrongestFirst() {
        algorithm.heroCards.clear();
        Optional<Card> result = algorithm.returnHero(true);
        assertFalse(result.isPresent());
    }
    @Test
    void testReturnHero_EmptyHeroCards_WeakestFirst() {
        algorithm.heroCards.clear();
        Optional<Card> result = algorithm.returnHero(false);
        assertFalse(result.isPresent());
    }
    @Test
    void testReturnHero_OneCardInHeroCards_WeakestFirst() {
        algorithm.heroCards.clear();
        algorithm.heroCards.add(ciriCard);
        Optional<Card> result = algorithm.returnHero(false);
        assertTrue(result.isPresent());
        assertEquals(ciriCard, result.get());
        assertFalse(algorithm.heroCards.contains(ciriCard));
    }
    @Test
    void testReturnHero_MultipleCardsInHeroCards_WeakestFirst() {
        algorithm.heroCards.clear();
        algorithm.heroCards.addAll(Arrays.asList(roachCard, geraltCard, ciriCard));
        Optional<Card> result = algorithm.returnHero(false);
        assertTrue(result.isPresent());
        assertEquals(roachCard, result.get());
        assertFalse(algorithm.heroCards.contains(roachCard));
    }
    @Test
    void testReturnHorn_EmptyHornCards() {
        algorithm.hornCards.clear();
        Optional<Card> result = algorithm.returnHorn();
        assertFalse(result.isPresent());
    }
    @Test
    void testReturnHorn_OneCardInHornCards() {
        algorithm.hornCards.clear();
        algorithm.hornCards.add(hornCardMedium);
        Optional<Card> result = algorithm.returnHorn();
        assertTrue(result.isPresent());
        assertEquals(hornCardMedium, result.get());
        assertFalse(algorithm.hornCards.contains(hornCardMedium));
    }
    @Test
    void testReturnHorn_MixedTypesInHornCards() {
        algorithm.hornCards.clear();
        algorithm.hornCards.addAll(Arrays.asList(hornCardMedium, spyCardStrong));
        Optional<Card> result = algorithm.returnHorn();
        assertTrue(result.isPresent());
        assertEquals(hornCardMedium, result.get());
        assertFalse(algorithm.hornCards.contains(hornCardMedium));
    }
    @Test
    void testClear_FullInstance() {
        algorithm.setYourBoard(yourBoard);
        algorithm.setCardRepo(cardRepo);
        algorithm.applyCardToCollection();
        algorithm.spyCards.add(spyCardStrong);
        algorithm.heroCards.add(ciriCard);
        algorithm.hornCards.add(hornCardWeak);
        algorithm.clear();
        assertNull(algorithm.yourBoard);
        assertNull(algorithm.cardRepo);
        assertTrue(algorithm.spyCards.isEmpty());
        assertTrue(algorithm.heroCards.isEmpty());
        assertTrue(algorithm.tightBondCards.isEmpty());
        assertTrue(algorithm.hornCards.isEmpty());
        assertTrue(algorithm.noneHero.isEmpty());
    }
    @Test
    void testClear_EmptyInstance() {
        algorithm.clear();
        assertNull(algorithm.yourBoard);
        assertNull(algorithm.cardRepo);
        assertTrue(algorithm.spyCards.isEmpty());
        assertTrue(algorithm.heroCards.isEmpty());
        assertTrue(algorithm.tightBondCards.isEmpty());
        assertTrue(algorithm.hornCards.isEmpty());
        assertTrue(algorithm.noneHero.isEmpty());
    }
    @Test
    void testClear_WithYourBoardOnly() {
        algorithm.setYourBoard(yourBoard);
        algorithm.clear();
        assertNull(algorithm.yourBoard);
        assertNull(algorithm.cardRepo);
        assertTrue(algorithm.spyCards.isEmpty());
        assertTrue(algorithm.heroCards.isEmpty());
        assertTrue(algorithm.tightBondCards.isEmpty());
        assertTrue(algorithm.hornCards.isEmpty());
        assertTrue(algorithm.noneHero.isEmpty());
    }
    @Test
    void testClear_WithCardRepoOnly() {
        algorithm.setCardRepo(cardRepo);
        algorithm.clear();
        assertNull(algorithm.yourBoard);
        assertNull(algorithm.cardRepo);
        assertTrue(algorithm.spyCards.isEmpty());
        assertTrue(algorithm.heroCards.isEmpty());
        assertTrue(algorithm.tightBondCards.isEmpty());
        assertTrue(algorithm.hornCards.isEmpty());
        assertTrue(algorithm.noneHero.isEmpty());
    }
    @Test
    void testClear_WithNonEmptyCollections() {
        algorithm.setYourBoard(yourBoard);
        algorithm.setCardRepo(cardRepo);
        algorithm.applyCardToCollection();
        algorithm.spyCards.add(spyCardWeak);
        algorithm.heroCards.add(ciriCard);
        algorithm.hornCards.add(hornCardStrong);
        algorithm.clear();
        assertNull(algorithm.yourBoard);
        assertNull(algorithm.cardRepo);
        assertTrue(algorithm.spyCards.isEmpty());
        assertTrue(algorithm.heroCards.isEmpty());
        assertTrue(algorithm.tightBondCards.isEmpty());
        assertTrue(algorithm.hornCards.isEmpty());
        assertTrue(algorithm.noneHero.isEmpty());
    }
    @Test
    void testClear_WithPartiallyFilledCollections() {
        algorithm.setYourBoard(yourBoard);
        algorithm.setCardRepo(cardRepo);
        algorithm.applyCardToCollection();
        algorithm.spyCards.add(spyCardWeak);
        algorithm.clear();
        assertNull(algorithm.yourBoard);
        assertNull(algorithm.cardRepo);
        assertTrue(algorithm.spyCards.isEmpty());
        assertTrue(algorithm.heroCards.isEmpty());
        assertTrue(algorithm.tightBondCards.isEmpty());
        assertTrue(algorithm.hornCards.isEmpty());
        assertTrue(algorithm.noneHero.isEmpty());
    }
    @Test
    void testShouldSetupAlgorithm_FirstRound_Phase1() {
        YourBoard yourBoard = new YourBoard();
        yourBoard.isFirstRound();
        boolean result = Algorithm.shouldSetupAlgorithm(yourBoard, 1);
        assertTrue(result);
    }
    @Test
    void testShouldSetupAlgorithm_FirstRound_NotPhase1() {
        YourBoard yourBoard = new YourBoard();
        yourBoard.isFirstRound();
        boolean result = Algorithm.shouldSetupAlgorithm(yourBoard, 2);
        assertFalse(result);
    }
    @Test
    void testShouldSetupAlgorithm_NotFirstRound_Phase2() {
        YourBoard yourBoard = new YourBoard();
        yourBoard.isSecondRound();
        boolean result = Algorithm.shouldSetupAlgorithm(yourBoard, 1);
        assertTrue(result);
    }
    @Test
    void testShouldSetupAlgorithm_NotFirstRound_NotPhase1() {
        YourBoard yourBoard = new YourBoard();
        yourBoard.isSecondRound();
        boolean result = Algorithm.shouldSetupAlgorithm(yourBoard, 2);
        assertFalse(result);
    }
}