package pl.edu.us.medrala.szymon.aiagent.algorithm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.edu.us.medrala.szymon.aiagent.board.YourBoard;
import pl.edu.us.medrala.szymon.aiagent.cards.Ability;
import pl.edu.us.medrala.szymon.aiagent.cards.Card;
import pl.edu.us.medrala.szymon.aiagent.cards.Type;
import pl.edu.us.medrala.szymon.aiagent.cards.repository.CardRepo;

import java.util.*;

public abstract class Algorithm {
    private static final long CIRI_CARD_ID = 209L;
    private static final long GERALT_CARD_ID = 198L;
    private static final long ROACH_CARD_ID = 187L;
    private final Logger LOGGER = LoggerFactory.getLogger(Algorithm.class);
    protected final Comparator<Card> comparator;
    protected YourBoard yourBoard = null;
    protected CardRepo cardRepo = null;

    /**
     * Empty collections intended for cards with a given abilities from the user's hand
     */
    protected final List<Card> spyCards = new ArrayList<>();
    protected final List<Card> heroCards = new ArrayList<>();
    protected final List<Card> tightBondCards = new ArrayList<>();
    protected final List<Card> hornCards = new ArrayList<>();
    protected final List<Card> noneHero = new ArrayList<>();

    protected Algorithm(final Comparator<Card> cardComparator) {
        this.comparator = cardComparator;
    }

    public abstract Optional<Card> execute(final List<Card> cards, final YourBoard yourBoard, final CardRepo cardRepo, final int phase);

    protected void setYourBoard(final YourBoard board) {
        this.yourBoard = board;
    }

    protected void setCardRepo(final CardRepo repo) {
        this.cardRepo = repo;
    }

    /**
     * checking the cards in your hand for specific skills and adding them to dedicated sub-collections
     * <p>
     * at the beginning, provisional cleaning of sub-collections
     * checking the cards in your hand
     * adding a card if it has the desired ability to be added to the collection
     */

    public void applyCardToCollection() {
        spyCards.clear();
        heroCards.clear();
        tightBondCards.clear();
        hornCards.clear();
        noneHero.clear();
        //ability none + type hero do poprawy
        for (final Card card : yourBoard.getHand()) {
            if (card.getAbility() == Ability.SPY) {
                spyCards.add(card);
            }
            if (card.getType() == Type.HERO) {
                heroCards.add(card);
            }
            if (card.getAbility() == Ability.TIGHT_BOND) {
                tightBondCards.add(card);
            }
            if (card.getAbility() == Ability.COMMANDERS_HORN) {
                hornCards.add(card);
            }
            if (card.getAbility() == Ability.NONE && card.getType() != Type.HERO) {
                noneHero.add(card);
            }
        }
    }

    public Optional<Card> summonCiriOrGeralt() {
        Card card = null;
        for(final Card c : yourBoard.getHand()) {
            if (c.getId() == CIRI_CARD_ID || c.getId() == GERALT_CARD_ID) {
                card = c; break;
            }
        }

        if (card != null) {
            useRoach();
        }

        this.heroCards.remove(card);
        yourBoard.getHand().remove(card);
        removeFromHand(card);
        return Optional.ofNullable(card);
    }

    private void useRoach() {
        cardRepo.findById(ROACH_CARD_ID).ifPresent(card -> {
            if (isCardIn(ROACH_CARD_ID, yourBoard.getHand()) || isCardIn(ROACH_CARD_ID, yourBoard.getDeck())) {
                yourBoard.getHand().removeIf(cardToRemove -> cardToRemove.getId().equals(card.getId()));
                yourBoard.getDeck().removeIf(cardToRemove -> cardToRemove.getId().equals(card.getId()));
                noneHero.removeIf(cardToRemove -> cardToRemove.getId().equals(card.getId()));
                yourBoard.placeCardOnBoard(card);
            }
        });
    }

    public Optional<Card> returnSpy(final boolean takeStrongestFirst) {
        final List<Card> innerSpyCards = sortByBaseUnitStrength(this.spyCards, takeStrongestFirst);
        final Card card;
        if (innerSpyCards.isEmpty()) {
            card = null;
            LOGGER.info("SPY is empty, should return another type");
        }
        else {
            card = innerSpyCards.get(0);
            this.spyCards.remove(card);
            removeFromHand(card);
            LOGGER.info("Returning SPY card {}", card);
        }
        return Optional.ofNullable(card);
    }

    public Optional<Card> returnPass() {
        return cardRepo.findPassCard();
    }

    public Optional<Card> returnTightBond() {
        final List<Card> tightBonds = sortByComparator(this.tightBondCards);
        final Card card;
        if (tightBonds.isEmpty()) {
            card = null;
            LOGGER.info("TIGHT BOND is empty, should return another type");
        }
        else {
            card = tightBonds.get(0);
            this.tightBondCards.remove(card);
            removeFromHand(card);
            LOGGER.info("Returning TIGHT BOND card {}", card);
        }
        return Optional.ofNullable(card);
    }

    public Optional<Card> returnHero(final boolean takeStrongestFirst) {
        final List<Card> heroCards = sortByBaseUnitStrength(this.heroCards, takeStrongestFirst);
        final Card card;
        if (heroCards.isEmpty()) {
            card = null;
            LOGGER.info("HERO is empty, should return another type");
        }
        else {
            card = heroCards.get(0);
            this.heroCards.remove(card);
            removeFromHand(card);
            LOGGER.info("Returning HERO card {}", card);
        }
        return Optional.ofNullable(card);
    }

    public Optional<Card> returnHorn() {
        final List<Card> hornCards = sortByComparator(this.hornCards);
        final Card card;
        if (hornCards.isEmpty()) {
            card = null;
            LOGGER.info("HORN is empty, should return another type");
        }
        else {
            card = hornCards.get(0);
            this.hornCards.remove(card);
            removeFromHand(card);
            LOGGER.info("Returning HORN card {}", card);
        }

        return Optional.ofNullable(card);
    }

    public Optional<Card> returnNoneHero(final boolean takeStrongestFirst) {
        final List<Card> noneHero = sortByBaseUnitStrength(this.noneHero, takeStrongestFirst);
        final Card card;
        if (noneHero.isEmpty()) {
            card = null;
            LOGGER.info("NONE HERO is empty, should return another type");
        }
        else {
            card = noneHero.get(0);
            this.noneHero.remove(card);
            removeFromHand(card);
            LOGGER.info("Returning NONE HERO card {}", card);
        }
        return Optional.ofNullable(card);
    }

    private void removeFromHand(final Card card) {
        yourBoard.getHand().remove(card);
    }

    protected boolean isEnemyPassed() {
        return yourBoard.isEnemyPassed();
    }

    protected void setup(final YourBoard yourBoard, final CardRepo cardRepo) {
        setYourBoard(yourBoard);
        setCardRepo(cardRepo);
//        applyCardToCollection();
    }

    /**
     * sorting the list - cards in our hand depending on the situation on the board
     * <p>
     * if the list is empty or has only one element, the operation ends - there are no elements to sort
     */
    private List<Card> sortByComparator(final List<Card> list) {
        final List<Card> copyOf = new ArrayList<>(list);
        if (list.isEmpty() || list.size() == 1) {
            return copyOf;
        }

        copyOf.sort(this.comparator);
        return copyOf;
    }

    /**
     * sorting the list - cards in our hand depending on the situation on the board
     * <p>
     * if the list is empty or has only one element, the operation ends - there are no elements to sort
     */
    private List<Card> sortByBaseUnitStrength(final List<Card> list, final boolean takeStrongestFirst) {
        final List<Card> copyOf = new ArrayList<>(list);
        if (list.isEmpty() || list.size() == 1) {
            return copyOf;
        }

        copyOf.sort(
            takeStrongestFirst ?
                Comparator.comparingInt(Card::getBaseUnitStrength).reversed() :
                Comparator.comparingInt(Card::getBaseUnitStrength)
        );
        return copyOf;
    }

    private boolean isCardIn(final long cardId, final List<Card> cards) {
        return cards.stream().anyMatch(card -> card.getId() == cardId);
    }

    protected static boolean shouldSetupAlgorithm(final YourBoard yourBoard, final int phase) {
        return yourBoard.isFirstRound() && phase == 1;
    }

    public void clear() {
        yourBoard = null;
        cardRepo = null;
        spyCards.clear();
        heroCards.clear();
        tightBondCards.clear();
        hornCards.clear();
        noneHero.clear();
    }
}
