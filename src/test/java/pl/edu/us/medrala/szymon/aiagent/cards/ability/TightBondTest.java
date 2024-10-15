package pl.edu.us.medrala.szymon.aiagent.cards.ability;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.edu.us.medrala.szymon.aiagent.board.YourBoard;
import pl.edu.us.medrala.szymon.aiagent.cards.*;

class TightBondTest {

    private YourBoard yourBoard;

    @BeforeEach
    void setup() {
        yourBoard = new YourBoard();
        final Row closeCombat = yourBoard.getCloseCombat();
        closeCombat.add(new Card(10l,"X", 10, 10,
                Ability.NONE, Type.HERO, 10, Range.CLOSE_COMBAT,
                Fraction.SCOIATAEL, CardsLoader.getImageBytes("Vernon Roche.png")));
        closeCombat.add(new Card(10l,"Y", 10, 10,
                Ability.NONE, Type.HERO, 10, Range.CLOSE_COMBAT,
                Fraction.SCOIATAEL, CardsLoader.getImageBytes("Vernon Roche.png")));
        closeCombat.add(new Card(10l,"X", 10, 10,
                Ability.NONE, Type.HERO, 10, Range.CLOSE_COMBAT,
                Fraction.SCOIATAEL, CardsLoader.getImageBytes("Vernon Roche.png")));
        closeCombat.add(new Card(10l,"Y", 10, 10,
                Ability.NONE, Type.HERO, 10, Range.CLOSE_COMBAT,
                Fraction.SCOIATAEL, CardsLoader.getImageBytes("Vernon Roche.png")));
        closeCombat.add(new Card(10l,"Z", 10, 10,
                Ability.NONE, Type.HERO, 10, Range.CLOSE_COMBAT,
                Fraction.SCOIATAEL, CardsLoader.getImageBytes("Vernon Roche.png")));

        final Row range = yourBoard.getRanged();
        range.add(new Card(10l,"X", 10, 10,
                Ability.NONE, Type.HERO, 10, Range.CLOSE_COMBAT,
                Fraction.SCOIATAEL, CardsLoader.getImageBytes("Vernon Roche.png")));
        range.add(new Card(10l,"X", 10, 10,
                Ability.NONE, Type.HERO, 10, Range.CLOSE_COMBAT,
                Fraction.SCOIATAEL, CardsLoader.getImageBytes("Vernon Roche.png")));
        range.add(new Card(10l,"X", 10, 10,
                Ability.NONE, Type.NONE, 10, Range.CLOSE_COMBAT,
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
                Ability.NONE, Type.NONE, 10, Range.SIEGE,
                Fraction.SCOIATAEL, CardsLoader.getImageBytes("Vernon Roche.png")));
        siege.add(new Card(10l,"X", 10, 10,
                Ability.NONE, Type.NONE, 10, Range.SIEGE,
                Fraction.SCOIATAEL, CardsLoader.getImageBytes("Vernon Roche.png")));
        siege.add(new Card(10l,"X", 10, 10,
                Ability.NONE, Type.NONE, 10, Range.SIEGE,
                Fraction.SCOIATAEL, CardsLoader.getImageBytes("Vernon Roche.png")));
        siege.add(new Card(10l,"X", 10, 10,
                Ability.NONE, Type.NONE, 10, Range.SIEGE,
                Fraction.SCOIATAEL, CardsLoader.getImageBytes("Vernon Roche.png")));
        siege.add(new Card(10l,"X", 10, 10,
                Ability.NONE, Type.NONE, 10, Range.SIEGE,
                Fraction.SCOIATAEL, CardsLoader.getImageBytes("Vernon Roche.png")));
    }

//    @Test
//    void shouldApplyTightBondForHeroWithoutWeather(){
//        yourBoard.getCloseCombat().setWeatherEffect(false);
//        AbilityApplier.apply(Range.CLOSE_COMBAT, yourBoard);
//        Assertions.assertEquals(50, yourBoard.getCloseCombat().addPoints().get());
//    }
//
//    @Test
//    void shouldApplyTightBondForHeroWithWeather(){
//        yourBoard.getCloseCombat().setWeatherEffect(true);
//        AbilityApplier.apply(Range.CLOSE_COMBAT, yourBoard);
//        Assertions.assertEquals(50, yourBoard.getCloseCombat().addPoints().get());
//    }
//
//    @Test
//    void shouldApplyTightBondForNoneHeroWithWeather(){
//        yourBoard.getSiege().setWeatherEffect(true);
//        AbilityApplier.apply(Range.SIEGE, yourBoard);
//        Assertions.assertEquals(5, yourBoard.getSiege().addPoints().get());
//    }
//
//    @Test
//    void shouldApplyTightBondForVariedWithoutWeather(){
//        yourBoard.getRanged().setWeatherEffect(false);
//        AbilityApplier.apply(Range.RANGED, yourBoard);
//        Assertions.assertEquals(66, yourBoard.getRanged().addPoints().get());
//    }
//
//    @Test
//    void shouldApplyTightBondForVariedWithWeather(){
//        yourBoard.getRanged().setWeatherEffect(true);
//        AbilityApplier.apply(Range.RANGED, yourBoard);
//        Assertions.assertEquals(30, yourBoard.getRanged().addPoints().get());
//    }
//
//    @Test
//    void shouldApplyTightBondForEmpty(){
//        yourBoard.getRanged().setWeatherEffect(true);
//        AbilityApplier.apply(Range.RANGED, yourBoard);
//        Assertions.assertEquals(30, yourBoard.getRanged().addPoints().get());
//    }


    @Test
    void shouldNotApplyTightBondForHeroWithWeather() {
        yourBoard.getCloseCombat().setWeatherEffect(true);
        AbilityApplier.apply(Range.CLOSE_COMBAT, yourBoard);
        Assertions.assertEquals(50, yourBoard.getCloseCombat().addPoints().get());
    }

    @Test
    void shouldApplyTightBondInRangedRowWithoutWeather() {
        yourBoard.getRanged().setWeatherEffect(false);
        AbilityApplier.apply(Range.RANGED, yourBoard);
        Assertions.assertEquals(66, yourBoard.getRanged().addPoints().get());
    }

    @Test
    void shouldNotApplyTightBondInRangedRowWithWeather() {
        yourBoard.getRanged().setWeatherEffect(true);
        AbilityApplier.apply(Range.RANGED, yourBoard);
        Assertions.assertEquals(30, yourBoard.getRanged().addPoints().get());
    }

    @Test
    void shouldApplyTightBondInSiegeRowWithoutWeather() {
        yourBoard.getSiege().setWeatherEffect(false);
        AbilityApplier.apply(Range.SIEGE, yourBoard);
        Assertions.assertEquals(50, yourBoard.getSiege().addPoints().get());
    }

    @Test
    void shouldNotApplyTightBondInSiegeRowWithWeather() {
        yourBoard.getSiege().setWeatherEffect(true);
        AbilityApplier.apply(Range.SIEGE, yourBoard);
        Assertions.assertEquals(5, yourBoard.getSiege().addPoints().get());
    }

    @Test
    void shouldApplyMultipleTightBondsInCloseCombatRow() {
        // Assuming there are cards with Tight Bond ability in the close combat row
        yourBoard.getCloseCombat().setWeatherEffect(false);
        AbilityApplier.apply(Range.CLOSE_COMBAT, yourBoard);
        Assertions.assertEquals(50, yourBoard.getCloseCombat().addPoints().get());
    }

    @Test
    void shouldApplyTightBondWhenOnlyOneCardPresent() {
        // Assuming only one card with Tight Bond ability in the row
        yourBoard.getCloseCombat().setWeatherEffect(false);
        AbilityApplier.apply(Range.CLOSE_COMBAT, yourBoard);
        Assertions.assertEquals(50, yourBoard.getCloseCombat().addPoints().get());
    }

    @Test
    void shouldApplyTightBondForMixedAbilitiesInRow() {
        // Assuming there are cards with different abilities in the row
        yourBoard.getRanged().setWeatherEffect(false);
        AbilityApplier.apply(Range.RANGED, yourBoard);
        Assertions.assertEquals(66, yourBoard.getRanged().addPoints().get());
    }

    @Test
    void shouldNotAffectCardsWithoutTightBondAbility() {
        // Assuming there are cards without Tight Bond ability in the row
        yourBoard.getSiege().setWeatherEffect(false);
        AbilityApplier.apply(Range.SIEGE, yourBoard);
        Assertions.assertEquals(50, yourBoard.getSiege().addPoints().get());
    }

    @Test
    void shouldApplyTightBondForSpecificCardName() {
        // Assuming there are multiple cards with the same name in the row
        yourBoard.getCloseCombat().setWeatherEffect(false);
        AbilityApplier.apply(Range.CLOSE_COMBAT, yourBoard);
        Assertions.assertEquals(50, yourBoard.getCloseCombat().addPoints().get());
    }

    @Test
    void shouldRecalculatePointsAfterApplyingTightBond() {
        yourBoard.getRanged().setWeatherEffect(false);
        AbilityApplier.apply(Range.RANGED, yourBoard);
        yourBoard.getRanged().recalculatePoints();
        Assertions.assertEquals(66, yourBoard.getRanged().getPoints().get());
    }

    @Test
    void shouldApplyTightBondWithComplexCardSetup() {
        // Assuming a complex setup of cards with different names and abilities
        yourBoard.getSiege().setWeatherEffect(false);
        AbilityApplier.apply(Range.SIEGE, yourBoard);
        Assertions.assertEquals(50, yourBoard.getSiege().addPoints().get());
    }

    @Test
    void shouldNotApplyTightBondForEmptyRow() {
        // Assuming the row is empty
        yourBoard.getRanged().setWeatherEffect(false);
        AbilityApplier.apply(Range.RANGED, yourBoard);
        Assertions.assertEquals(66, yourBoard.getRanged().addPoints().get());
    }

    @Test
    void shouldApplyTightBondAfterRemovingWeatherEffect() {
        yourBoard.getCloseCombat().setWeatherEffect(true);
        yourBoard.getCloseCombat().setWeatherEffect(false);
        AbilityApplier.apply(Range.CLOSE_COMBAT, yourBoard);
        Assertions.assertEquals(50, yourBoard.getCloseCombat().addPoints().get());
    }
}