package pl.edu.us.medrala.szymon.aiagent.board;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.edu.us.medrala.szymon.aiagent.cards.*;

class BoardTest {

    static class TestBoard extends Board {
    }


    @Test
    void testCheckCardRowForSiege() {
        TestBoard board = new TestBoard();
        Card card = new Card(10l,"X", 10, 10,
                Ability.NONE, Type.HERO, 10, Range.SIEGE,
                Fraction.SCOIATAEL, CardsLoader.getImageBytes("Vernon Roche.png"));
        Row row = board.checkCardRow(card);
        Assertions.assertEquals(board.getSiege(), row);
    }

    @Test
    void testCheckCardRowForRanged() {
        TestBoard board = new TestBoard();
        Card card = new Card(10l,"X", 10, 10,
                Ability.NONE, Type.HERO, 10, Range.RANGED,
                Fraction.SCOIATAEL, CardsLoader.getImageBytes("Vernon Roche.png"));
        Row row = board.checkCardRow(card);
        Assertions.assertEquals(board.getRanged(), row);
    }

    @Test
    void testCheckCardRowForCloseCombat() {
        TestBoard board = new TestBoard();
        Card card = new Card(10l,"X", 10, 10,
                Ability.NONE, Type.HERO, 10, Range.CLOSE_COMBAT,
                Fraction.SCOIATAEL, CardsLoader.getImageBytes("Vernon Roche.png"));
        Row row = board.checkCardRow(card);
        Assertions.assertEquals(board.getCloseCombat(), row);
    }

    @Test
    void testCheckCardRowForNullRange() {
        TestBoard board = new TestBoard();
        Card card = new Card(10l, "X", 10, 10,
                Ability.NONE, Type.HERO, 10, Range.NONE,
                Fraction.SCOIATAEL, CardsLoader.getImageBytes("Vernon Roche.png"));
        Row row = board.checkCardRow(card);
        Assertions.assertNull(row);
    }

    @Test
    void testSetNextRoundFirstToSecond() {
        TestBoard board = new TestBoard();
        board.getRound();
        board.setNextRound();
        Assertions.assertEquals(RoundNumber.SECOND, board.getRound());
    }

    @Test
    void testSetNextRoundSecondToThird() {
        TestBoard board = new TestBoard();
        board.getRound();
        board.setNextRound();
        board.setNextRound();
        Assertions.assertEquals(RoundNumber.THIRD, board.getRound());
    }

    @Test
    void testSetNextRoundThirdToEnd() {
        TestBoard board = new TestBoard();
        board.getRound();
        board.setNextRound();
        board.setNextRound();
        board.setNextRound();
        Assertions.assertEquals(RoundNumber.END, board.getRound());
    }

    @Test
    void testPrepareNextRoundSetsNextRound() {
        TestBoard board = new TestBoard();
        board.getRound();
        board.prepareNextRound();
        Assertions.assertEquals(RoundNumber.SECOND, board.getRound());
    }

    @Test
    void testPrepareNextRoundClearsRowsAndDiscardPile() {
        TestBoard board = new TestBoard();
        Row closeCombat = new Row();
        Row ranged = new Row();
        Row siege = new Row();
        board.getCloseCombat();
        board.getRanged();
        board.getSiege();
        board.getDiscardPile().add(new Card());
        board.prepareNextRound();
        Assertions.assertTrue(board.getCloseCombat().size() == 0);
        Assertions.assertTrue(board.getRanged().size() == 0);
        Assertions.assertTrue(board.getSiege().size() == 0);
        Assertions.assertTrue(board.getDiscardPile().isEmpty());
    }

    @Test
    void testPrepareNextRoundSetsNextRoundAndClearsDiscardPile() {
        TestBoard board = new TestBoard();
        board.getRound();
        board.getDiscardPile().add(new Card());
        board.prepareNextRound();
        Assertions.assertEquals(RoundNumber.SECOND, board.getRound());
        Assertions.assertTrue(board.getDiscardPile().isEmpty());
    }

    @Test
    void testCalculatePointsAllRowsEmpty() {
        TestBoard board = new TestBoard();
        int points = board.calculatePoints(0);
        Assertions.assertEquals(0, points);
    }

}