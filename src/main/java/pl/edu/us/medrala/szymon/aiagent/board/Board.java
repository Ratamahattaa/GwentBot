package pl.edu.us.medrala.szymon.aiagent.board;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.edu.us.medrala.szymon.aiagent.algorithm.Algorithm;
import pl.edu.us.medrala.szymon.aiagent.algorithm.NRAlgorithm;
import pl.edu.us.medrala.szymon.aiagent.api.DeckType;
import pl.edu.us.medrala.szymon.aiagent.cards.Card;
import pl.edu.us.medrala.szymon.aiagent.cards.Leader;
import pl.edu.us.medrala.szymon.aiagent.cards.Range;
import pl.edu.us.medrala.szymon.aiagent.cards.Row;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static pl.edu.us.medrala.szymon.aiagent.cards.Range.SIEGE;

@Getter
@NoArgsConstructor
public abstract class Board {
    private static final Logger LOGGER = LoggerFactory.getLogger(Board.class);
    protected Algorithm algorithmStrategy;

    @Getter
    protected RoundNumber round = RoundNumber.FIRST;

    @Setter
    @Getter
    protected Leader leader = null;

    protected final Row closeCombat = new Row();
    protected final Row ranged = new Row();
    protected final Row siege = new Row();

    @Getter
    protected final List<Card> hand = new ArrayList<>();
    protected final List<Card> deck = new ArrayList<>();
    protected final List<Card> discardPile = new ArrayList<>();

    @Getter
    protected DeckType deckType;

    @Setter
    boolean leaderCharge = true;

    @Getter
    @Setter
    boolean enemyPassed;

    @Setter
    @Getter
    int allEnemiesPoints;

    public void setDeckType(DeckType type) {
        this.deckType = type;
        this.algorithmStrategy = type.getAlgorithmStrategy();
    }

    public void replaceDeck(final List<Card> cards) {
        this.deck.clear();
        this.deck.addAll(cards);
    }

    public final void placeCardOnBoard(final Card card) {
//        AbilityApplier.apply(getCloseCombat().getRow(), Ability.TIGHT_BOND);
//        AbilityApplier.apply(getRanged().getRow(), Ability.TIGHT_BOND);
//        AbilityApplier.apply(getSiege().getRow(), Ability.TIGHT_BOND);
        checkCardRow(card).add(card);
    }

    public Row checkCardRow(Card card) {
        Row unitRow = null;
        if (card.getRange() == SIEGE || isSiegeLeader(card)) {
            unitRow = this.getSiege();
        }
        if (card.getRange() == Range.RANGED) {
            unitRow = this.getRanged();
        }
        if (card.getRange() == Range.CLOSE_COMBAT) {
            unitRow = this.getCloseCombat();
        }
        return unitRow;
    }

    private static boolean isSiegeLeader(Card card) {
        return card.getId() == NRAlgorithm.LEADER_ID && card.getRange() == null;
    }

    public boolean isFirstRound() {
        return this.round.equals(RoundNumber.FIRST);
    }

    public boolean isSecondRound() {
        return this.round.equals(RoundNumber.SECOND);
    }

    public int calculatePoints(final int spyPoints) {
        return closeCombat.getPoints().addAndGet(ranged.getPoints().addAndGet(siege.getPoints().addAndGet(spyPoints)));
    }

    public void reset() {
        round = RoundNumber.FIRST;
        leader = null;
        closeCombat.clear();
        ranged.clear();
        siege.clear();
        hand.clear();
        deck.clear();
        discardPile.clear();
        algorithmStrategy.clear();
    }

    public void prepareNextRound() {
        setNextRound();
        closeCombat.clear();
        ranged.clear();
        siege.clear();
        discardPile.clear();

        LOGGER.info("DECK CONTENT: ");
        deck.forEach(c -> LOGGER.info(c.toString()));

        LOGGER.info("HAND CONTENT: ");
        hand.forEach(c -> LOGGER.info(c.toString()));
    }

    public final void setNextRound() {
        switch (round) {
            case FIRST -> {
                LOGGER.info("SETTING NEXT ROUND AS '{}'", RoundNumber.SECOND);
                this.round = RoundNumber.SECOND;
            }
            case SECOND -> {
                LOGGER.info("SETTING NEXT ROUND AS '{}'", RoundNumber.THIRD);
                this.round = RoundNumber.THIRD;
            }
            case THIRD -> {
                LOGGER.info("SETTING NEXT ROUND AS '{}'", RoundNumber.END);
                this.round = RoundNumber.END;
            }
        }
    }

    public final Row getRow(final Range range) {
        return switch (range){
            case CLOSE_COMBAT -> getCloseCombat();
            case RANGED -> getRanged();
            case SIEGE -> getSiege();
            default -> throw new IllegalArgumentException();
        };
    };

    public Row findCardInRow(final Optional<Card> optionalCard) {
        if (optionalCard.isEmpty()) {
            return null;
        }
        final Card card = optionalCard.get();

        final boolean isInCloseCombat = closeCombat.getRow().stream().anyMatch(c -> card.getId().longValue() == c.getId());
        if (isInCloseCombat) {
            return closeCombat;
        }

        final boolean isInRanged = ranged.getRow().stream().anyMatch(c -> card.getId().longValue() == c.getId());
        if (isInRanged) {
            return ranged;
        }

        final boolean isInSiege = siege.getRow().stream().anyMatch(c -> card.getId().longValue() == c.getId());
        if (isInSiege) {
            return siege;
        }

        return null;
    }
}
