package pl.edu.us.medrala.szymon.aiagent.cards.ability;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.edu.us.medrala.szymon.aiagent.board.YourBoard;
import pl.edu.us.medrala.szymon.aiagent.cards.*;

import java.util.Optional;

class CommandersHornTest {

    private YourBoard yourBoard;

    @BeforeEach
    void setUp() {
        yourBoard = new YourBoard();
        final Row closeCombat = yourBoard.getCloseCombat();
        closeCombat.add(new Card(10l,"X", 10, 10,
                Ability.NONE, Type.HERO, 10, Range.CLOSE_COMBAT,
                Fraction.SCOIATAEL, CardsLoader.getImageBytes("Vernon Roche.png")));
        closeCombat.add(new Card(10l,"Y", 10, 10,
                Ability.NONE, Type.HERO, 10, Range.CLOSE_COMBAT,
                Fraction.SCOIATAEL, CardsLoader.getImageBytes("Vernon Roche.png")));
        closeCombat.add(new Card(10l,"X", 10, 10,
                Ability.TIGHT_BOND, Type.HERO, 10, Range.CLOSE_COMBAT,
                Fraction.SCOIATAEL, CardsLoader.getImageBytes("Vernon Roche.png")));
        closeCombat.add(new Card(10l,"Y", 10, 10,
                Ability.TIGHT_BOND, Type.HERO, 10, Range.CLOSE_COMBAT,
                Fraction.SCOIATAEL, CardsLoader.getImageBytes("Vernon Roche.png")));
        closeCombat.add(new Card(10l,"Z", 10, 10,
                Ability.TIGHT_BOND, Type.HERO, 10, Range.CLOSE_COMBAT,
                Fraction.SCOIATAEL, CardsLoader.getImageBytes("Vernon Roche.png")));

        final Row range = yourBoard.getRanged();
        range.add(new Card(10l,"X", 10, 10,
                Ability.NONE, Type.HERO, 10, Range.CLOSE_COMBAT,
                Fraction.SCOIATAEL, CardsLoader.getImageBytes("Vernon Roche.png")));
        range.add(new Card(10l,"X", 10, 10,
                Ability.NONE, Type.HERO, 10, Range.CLOSE_COMBAT,
                Fraction.SCOIATAEL, CardsLoader.getImageBytes("Vernon Roche.png")));
        range.add(new Card(10l,"X", 4, 4,
                Ability.TIGHT_BOND, Type.NONE, 10, Range.CLOSE_COMBAT,
                Fraction.SCOIATAEL, CardsLoader.getImageBytes("Vernon Roche.png")));
        range.add(new Card(10l,"X", 4, 4,
                Ability.TIGHT_BOND, Type.NONE, 10, Range.CLOSE_COMBAT,
                Fraction.SCOIATAEL, CardsLoader.getImageBytes("Vernon Roche.png")));
        range.add(new Card(10l,"X", 4, 4,
                Ability.TIGHT_BOND, Type.NONE, 10, Range.CLOSE_COMBAT,
                Fraction.SCOIATAEL, CardsLoader.getImageBytes("Vernon Roche.png")));

        final Row siege = yourBoard.getSiege();
        siege.add(new Card(10l,"X", 10, 10,
                Ability.NONE, Type.NONE, 10, Range.CLOSE_COMBAT,
                Fraction.SCOIATAEL, CardsLoader.getImageBytes("Vernon Roche.png")));
        siege.add(new Card(10l,"X", 10, 10,
                Ability.NONE, Type.NONE, 10, Range.CLOSE_COMBAT,
                Fraction.SCOIATAEL, CardsLoader.getImageBytes("Vernon Roche.png")));
        siege.add(new Card(10l,"X", 10, 10,
                Ability.NONE, Type.NONE, 10, Range.CLOSE_COMBAT,
                Fraction.SCOIATAEL, CardsLoader.getImageBytes("Vernon Roche.png")));
        siege.add(new Card(10l,"X", 4, 4,
                Ability.NONE, Type.NONE, 10, Range.CLOSE_COMBAT,
                Fraction.SCOIATAEL, CardsLoader.getImageBytes("Vernon Roche.png")));
        siege.add(new Card(10l,"X", 4, 4,
                Ability.NONE, Type.NONE, 10, Range.CLOSE_COMBAT,
                Fraction.SCOIATAEL, CardsLoader.getImageBytes("Vernon Roche.png")));
    }

    @Test
    void shouldApplyCommandersHornForHeroWithWeather(){
        final Card card = new Card(1, "test", 1, 1, Ability.NONE, Type.NONE, 1, Range.CLOSE_COMBAT, Fraction.SCOIATAEL, CardsLoader.getImageBytes("Vernon Roche.png"));

        yourBoard.getCloseCombat().add(card);
        yourBoard.getCloseCombat().setWeatherEffect(true);
        AbilityApplier.commandersHorn(Optional.of(card), yourBoard);
        Assertions.assertEquals(52, yourBoard.getCloseCombat().addPoints().get());
    }

    @Test
    void shouldApplyCommandersHornForHeroWithoutWeather(){
        final Card card = new Card(1, "test", 1, 1, Ability.NONE, Type.NONE, 1, Range.CLOSE_COMBAT, Fraction.SCOIATAEL, CardsLoader.getImageBytes("Vernon Roche.png"));

        yourBoard.getCloseCombat().add(card);
        yourBoard.getCloseCombat().setWeatherEffect(true);
        AbilityApplier.commandersHorn(Optional.of(card), yourBoard);
        Assertions.assertEquals(52, yourBoard.getCloseCombat().addPoints().get());
    }

    @Test
    void shouldApplyCommandersHornForNoneHeroWithoutWeather(){
        final Card card = new Card(1, "test", 1, 1, Ability.NONE, Type.NONE, 1, Range.CLOSE_COMBAT, Fraction.SCOIATAEL, CardsLoader.getImageBytes("Vernon Roche.png"));

        yourBoard.getSiege().add(card);
        yourBoard.getSiege().setWeatherEffect(false);
        AbilityApplier.commandersHorn(Optional.of(card), yourBoard);
        Assertions.assertEquals(78, yourBoard.getSiege().addPoints().get());
    }

    @Test
    void shouldApplyCommandersHornForNoneHeroWithWeather(){
        final Card card = new Card(1, "test", 1, 1, Ability.NONE, Type.NONE, 1, Range.CLOSE_COMBAT, Fraction.SCOIATAEL, CardsLoader.getImageBytes("Vernon Roche.png"));

        yourBoard.getSiege().add(card);
        yourBoard.getSiege().setWeatherEffect(true);
        AbilityApplier.commandersHorn(Optional.of(card), yourBoard);
        Assertions.assertEquals(12, yourBoard.getSiege().addPoints().get());
    }

    @Test
    void shouldApplyCommandersHornForVariedWithWeather(){
        final Card card = new Card(1, "test", 1, 1, Ability.NONE, Type.NONE, 1, Range.CLOSE_COMBAT, Fraction.SCOIATAEL, CardsLoader.getImageBytes("Vernon Roche.png"));

        yourBoard.getRanged().add(card);
        yourBoard.getRanged().setWeatherEffect(true);
        AbilityApplier.apply(yourBoard.getRanged().getRow(), Ability.COMMANDERS_HORN);
        AbilityApplier.commandersHorn(Optional.of(card), yourBoard);
        Assertions.assertEquals(28, yourBoard.getRanged().addPoints().get());
    }

    @Test
    void shouldApplyCommandersHornForVariedWithoutWeatherAndStrengthZero(){
        final Card card = new Card(1, "test", 0, 0, Ability.NONE, Type.NONE, 1, Range.CLOSE_COMBAT, Fraction.SCOIATAEL, CardsLoader.getImageBytes("Vernon Roche.png"));

        yourBoard.getRanged().add(card);
        yourBoard.getRanged().setWeatherEffect(false);
        AbilityApplier.apply(yourBoard.getRanged().getRow(), Ability.COMMANDERS_HORN);
        AbilityApplier.commandersHorn(Optional.of(card), yourBoard);
        Assertions.assertEquals(44, yourBoard.getRanged().addPoints().get());
    }

    @Test
    void shouldApplyCommandersHornForVariedWithoutWeather(){
        final Card card = new Card(1, "test", 1, 1, Ability.NONE, Type.NONE, 1, Range.CLOSE_COMBAT, Fraction.SCOIATAEL, CardsLoader.getImageBytes("Vernon Roche.png"));

        yourBoard.getRanged().add(card);
        yourBoard.getRanged().setWeatherEffect(false);
        AbilityApplier.apply(yourBoard.getRanged().getRow(), Ability.COMMANDERS_HORN);
        AbilityApplier.commandersHorn(Optional.of(card), yourBoard);
        Assertions.assertEquals(46, yourBoard.getRanged().addPoints().get());
    }

    @Test
    void shouldApplyCommandersHornForEmpty(){
        final Card card = new Card(1, "test", 1, 1, Ability.NONE, Type.NONE, 1, Range.CLOSE_COMBAT, Fraction.SCOIATAEL, CardsLoader.getImageBytes("Vernon Roche.png"));

        yourBoard.getCloseCombat().add(card);
        yourBoard.getCloseCombat().setWeatherEffect(true);
        AbilityApplier.commandersHorn(Optional.of(card), yourBoard);
        Assertions.assertEquals(52, yourBoard.getCloseCombat().addPoints().get());
    }
}