package pl.edu.us.medrala.szymon.aiagent.cards.ability;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.edu.us.medrala.szymon.aiagent.board.YourBoard;
import pl.edu.us.medrala.szymon.aiagent.cards.*;

class MoraleBoostTest {

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
    void shouldMoraleBoostForHeroWithWeather(){
        yourBoard.getCloseCombat().setWeatherEffect(true);
        MoraleBoost.moraleBoost(yourBoard.getCloseCombat().getRow());
        Assertions.assertEquals(50, yourBoard.getCloseCombat().addPoints().get());
    }

    @Test
    void shouldMoraleBoostForHeroWithoutWeather(){
        yourBoard.getCloseCombat().setWeatherEffect(false);
        MoraleBoost.moraleBoost(yourBoard.getCloseCombat().getRow());
        Assertions.assertEquals(50, yourBoard.getCloseCombat().addPoints().get());
    }

    @Test
    void shouldMoraleBoostForNoneHeroWithWeather(){
        yourBoard.getSiege().setWeatherEffect(true);
        MoraleBoost.moraleBoost(yourBoard.getSiege().getRow());
        Assertions.assertEquals(10, yourBoard.getSiege().addPoints().get());
    }

    @Test
    void shouldMoraleBoostForNoneHeroWithout(){
        yourBoard.getSiege().setWeatherEffect(false);
        MoraleBoost.moraleBoost(yourBoard.getSiege().getRow());
        Assertions.assertEquals(43, yourBoard.getSiege().addPoints().get());
    }

    @Test
    void shouldMoraleBoostForHeroWithWeatherInSiege(){
        yourBoard.getSiege().setWeatherEffect(true);
        MoraleBoost.moraleBoost(yourBoard.getSiege().getRow());
        Assertions.assertEquals(10, yourBoard.getSiege().addPoints().get());
    }

    @Test
    void shouldMoraleBoostForVariedWithoutWeather(){
        yourBoard.getRanged().setWeatherEffect(false);
        MoraleBoost.moraleBoost(yourBoard.getRanged().getRow());
        Assertions.assertEquals(35, yourBoard.getRanged().addPoints().get());
    }

    @Test
    void shouldMoraleBoostForVariedWithWeather(){
        yourBoard.getRanged().setWeatherEffect(true);
        MoraleBoost.moraleBoost(yourBoard.getRanged().getRow());
        Assertions.assertEquals(26, yourBoard.getRanged().addPoints().get());
    }

    @Test
    void shouldMoraleBoostForEmptyWithoutWeather(){
        /**
         * To perform this test, you need to delete the selected collection from @BeforeAll
         */
        yourBoard.getSiege().clear();
        yourBoard.getSiege().setWeatherEffect(false);
        MoraleBoost.moraleBoost(yourBoard.getSiege().getRow());
        Assertions.assertEquals(0, yourBoard.getSiege().addPoints().get());
    }

    @Test
    void shouldMoraleBoostForEmptyWithWeather(){
        /**
         * To perform this test, you need to delete the selected collection from @BeforeAll
         */
        yourBoard.getSiege().clear();
        yourBoard.getSiege().setWeatherEffect(true);
        MoraleBoost.moraleBoost(yourBoard.getSiege().getRow());
        Assertions.assertEquals(0, yourBoard.getSiege().addPoints().get());
    }

}