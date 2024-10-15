package pl.edu.us.medrala.szymon.aiagent.api;

import lombok.RequiredArgsConstructor;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import pl.edu.us.medrala.szymon.aiagent.algorithm.StatusChecker;
import pl.edu.us.medrala.szymon.aiagent.board.YourBoard;
import pl.edu.us.medrala.szymon.aiagent.cards.ACard;
import pl.edu.us.medrala.szymon.aiagent.cards.Card;
import pl.edu.us.medrala.szymon.aiagent.cards.CardsToResponse;
import pl.edu.us.medrala.szymon.aiagent.cards.DraftedWithDeck;
import pl.edu.us.medrala.szymon.aiagent.cards.ability.AbilityApplier;
import pl.edu.us.medrala.szymon.aiagent.cards.repository.CardRepo;
import pl.edu.us.medrala.szymon.aiagent.deck.DeckConfig;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RestControllerAdvice
@RequestMapping("/gwent")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class GameController implements WebMvcConfigurer {
    private static final int INITIAL_PHASE_VALUE = 1;
    private static final long MANEKIN_CARD_ID = 2371L;
    private final Logger logger = LoggerFactory.getLogger(GameController.class);
    private final YourBoard yourBoard;
    private final DeckConfig deckConfig;
    private final CardRepo cardRepo;
    private final AtomicInteger phaseCounter = new AtomicInteger(INITIAL_PHASE_VALUE);

    @PostMapping("/newGame")
    public ResponseEntity<String> createNewGame() {
        try {
            createNewGame0();

            logger.info("Settings reset! New game created!");
            return ResponseEntity.ok("New game created!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error occurred while creating new game!");
        }
    }

    @GetMapping("/init-deck")
    public ResponseEntity<List<ImageCardContextResponse>> getDeck(@RequestParam("abbreviation") final String abbreviation) {
        final DeckType type = DeckType.fromAbbreviation(abbreviation);
        yourBoard.setDeckType(type);
        yourBoard.replaceDeck(deckConfig.determineDeck(type));

        List<Card> handToResponse = yourBoard.getDeck();
        final List<ImageCardContextResponse> response = handToResponse.stream()
                .map(card -> new ImageCardContextResponse(card.getId(), card.getImage()))
                .toList();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/addCards")
    public ResponseEntity<DraftedWithDeck> addNewCards(@RequestBody final List<Long> ids) {
        if (CollectionUtils.isEmpty(ids) || ids.size() > 2) {
            return ResponseEntity.badRequest().build();
        }


        final int size = ids.size();
        if (size == 1) {
            final Long id = ids.get(0);
            final Optional<Card> optionalCard = yourBoard.getDeck().stream().filter(card -> card.getId().equals(id)).findFirst();

            if (optionalCard.isEmpty()) {
                return ResponseEntity.badRequest().build();
            }
            final Card newCard =  optionalCard.get();
            yourBoard.getHand().add(newCard);
            yourBoard.getDeck().removeIf(c -> Objects.equals(c.getId(), id));
        }

        if (size == 2) {
            final Long id1 = ids.get(0);
            final Long id2 = ids.get(1);
            final List<Card> cards = yourBoard.getDeck().stream().filter(card -> card.getId().equals(id1) || card.getId().equals(id2)).toList();

            yourBoard.getHand().addAll(cards);
            yourBoard.getDeck().removeIf(c -> Objects.equals(c.getId(), id1));
            yourBoard.getDeck().removeIf(c -> Objects.equals(c.getId(), id2));
        }


        logger.info("New hand" + Arrays.toString(yourBoard.getHand().toArray()));
        return ResponseEntity.ok(new DraftedWithDeck(yourBoard.getHand(), yourBoard.getDeck()));
    }

    @PostMapping("/hand")
    public ResponseEntity<List<Card>> createHand(@RequestBody final GetStartingCards startingCards) {
        if (startingCards.cardsId().size() != 10) {
            throw new RuntimeException();
        }

        logger.info("Creating hand");
        startingCards.cardsId().forEach(card -> logger.info("CardID: {}", card));

        logger.info("DECK BEFORE DELETION: '{}'", yourBoard.getDeck().stream().map(ACard::getName).toList());
        final List <Card> handToGet = yourBoard.getDeck();
        final Iterator<Card> iterator = handToGet.iterator();
        while (iterator.hasNext()) {
            final Card toModify = iterator.next();
            if (startingCards.cardsId().contains(toModify.getId())) {
                yourBoard.getHand().add(toModify);
                iterator.remove();
            }
        }
        logger.info("DECK STATE: '{}'", yourBoard.getDeck().stream().map(ACard::getName).toList());
        logger.info("HAND STATE: '{}'", yourBoard.getHand().stream().map(ACard::getName).toList());
        return ResponseEntity.ok(yourBoard.getDeck());
    }

    @PostMapping
    public ResponseEntity<List<Card>> extraCard(@RequestBody final List<Card> cards) {
        if (cards == null || cards.isEmpty() || cards.size() > 2) {
            throw new RuntimeException();
        }

        if (cards.size() == 1) {
            yourBoard.getHand().addAll(cards);
            yourBoard.getDeck().removeAll(cards);
        }

        if (cards.size() == 2) {
            yourBoard.getHand().addAll(cards);
            yourBoard.getDeck().removeAll(cards);
        }

        return ResponseEntity.ok(yourBoard.getDeck());
    }

    @PostMapping("/board")
    public ResponseEntity<CardsToResponse> updateBoard(@RequestBody final UpdateBoardQuery query) {
        final int calculatedPoints = yourBoard.calculatePoints(query.enemySpyPoints());
        final boolean shouldContinue = StatusChecker.shouldContinue(query.enemyAllPoints(), calculatedPoints, query.enemyPassed(), yourBoard.getRound());
        yourBoard.setShouldPlay(shouldContinue);
        logger.info("Should continue game: {}", shouldContinue);
        if (!shouldContinue) {
            prepareNextRound();
            return ResponseEntity.ok(new CardsToResponse(cardRepo.findPassCard().orElseThrow(), calculatedPoints));
        }

        if (query.enemySpyPoints() > 0) {
            final Card enemySpy = cardRepo.findById(MANEKIN_CARD_ID).orElseThrow();
            enemySpy.setUnitStrength(query.enemySpyPoints());
            yourBoard.getCloseCombat().add(enemySpy);
        }
        yourBoard.getCloseCombat().setWeatherEffect(query.isFrost());
        yourBoard.getRanged().setWeatherEffect(query.isFog());
        yourBoard.getSiege().setWeatherEffect(query.isRain());
        yourBoard.setEnemyPassed(query.enemyPassed());
        yourBoard.setAllEnemiesPoints(query.enemyAllPoints());
        logger.info(query.toString());

        final int phase = phaseCounter.getAndIncrement();
        final Optional<Card> optionalACard = yourBoard.getDeckType().getAlgorithmStrategy().execute(yourBoard.getHand(), yourBoard, cardRepo, phase);
        AbilityApplier.commandersHorn(optionalACard, yourBoard);

        final ACard cardToPlaceOnBoard = optionalACard.orElseThrow();

        logger.info("CARD NAME: {}", cardToPlaceOnBoard);
        final int points = yourBoard.calculatePoints(query.enemySpyPoints());
        logger.info("NASZE PUNKTY {}",  points);
        return ResponseEntity.ok(new CardsToResponse(cardToPlaceOnBoard, points));
    }

    @GetMapping
    public ResponseEntity<List<ImageCardContextResponse>> determineDeck(@RequestParam DeckType type) {
        final List<Card> cards = deckConfig.determineDeck(type);

        yourBoard.setDeckType(type);
        yourBoard.replaceDeck(cards);
        final List<ImageCardContextResponse> response = cards.stream()
                .map(card -> new ImageCardContextResponse(card.getId(), card.getImage()))
                .toList();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/deck")
    public ResponseEntity<List<Card>> getDeck() {
        return ResponseEntity.ok(yourBoard.getDeck());
    }

    private void createNewGame0() {
        yourBoard.reset();
        phaseCounter.set(INITIAL_PHASE_VALUE);
    }

    private void prepareNextRound() {
        yourBoard.prepareNextRound();
        phaseCounter.set(INITIAL_PHASE_VALUE);
    }
}
