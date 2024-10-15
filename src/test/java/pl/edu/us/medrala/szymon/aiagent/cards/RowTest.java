package pl.edu.us.medrala.szymon.aiagent.cards;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.edu.us.medrala.szymon.aiagent.board.YourBoard;

class RowTest {

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
        range.add(new Card(10l,"X", 4, 4,
                Ability.NONE, Type.NONE, 10, Range.CLOSE_COMBAT,
                Fraction.SCOIATAEL, CardsLoader.getImageBytes("Vernon Roche.png")));
        range.add(new Card(10l,"X", 10, 10,
                Ability.NONE, Type.NONE, 10, Range.CLOSE_COMBAT,
                Fraction.SCOIATAEL, CardsLoader.getImageBytes("Vernon Roche.png")));
        range.add(new Card(10l,"X", 4, 4,
                Ability.TIGHT_BOND, Type.NONE, 10, Range.CLOSE_COMBAT,
                Fraction.SCOIATAEL, CardsLoader.getImageBytes("Vernon Roche.png")));
        range.add(new Card(10l,"X", 10, 10,
                Ability.TIGHT_BOND, Type.NONE, 10, Range.CLOSE_COMBAT,
                Fraction.SCOIATAEL, CardsLoader.getImageBytes("Vernon Roche.png")));
        range.add(new Card(10l,"X", 10, 10,
                Ability.TIGHT_BOND, Type.NONE, 10, Range.CLOSE_COMBAT,
                Fraction.SCOIATAEL, CardsLoader.getImageBytes("Vernon Roche.png")));

        final Row siege = yourBoard.getSiege();
        siege.add(new Card(10l,"X", 10, 10,
                Ability.NONE, Type.HERO, 10, Range.CLOSE_COMBAT,
                Fraction.SCOIATAEL, CardsLoader.getImageBytes("Vernon Roche.png")));
        siege.add(new Card(10l,"X", 10, 10,
                Ability.NONE, Type.HERO, 10, Range.CLOSE_COMBAT,
                Fraction.SCOIATAEL, CardsLoader.getImageBytes("Vernon Roche.png")));
        siege.add(new Card(10l,"X", 10, 10,
                Ability.TIGHT_BOND, Type.NONE, 10, Range.CLOSE_COMBAT,
                Fraction.SCOIATAEL, CardsLoader.getImageBytes("Vernon Roche.png")));
        siege.add(new Card(10l,"X", 4, 4,
                Ability.TIGHT_BOND, Type.NONE, 10, Range.CLOSE_COMBAT,
                Fraction.SCOIATAEL, CardsLoader.getImageBytes("Vernon Roche.png")));
        siege.add(new Card(10l,"X", 4, 4,
                Ability.TIGHT_BOND, Type.NONE, 10, Range.CLOSE_COMBAT,
                Fraction.SCOIATAEL, CardsLoader.getImageBytes("Vernon Roche.png")));
    }


    @Test
    void shouldApplyWeatherEffectForHero(){
        yourBoard.getCloseCombat().setWeatherEffect(true);
        Assertions.assertEquals(50, yourBoard.getCloseCombat().addPoints().get());
    }
    @Test
    void shouldApplyWeatherEffectForNoneHero(){
        yourBoard.getCloseCombat().setWeatherEffect(true);
        Assertions.assertEquals(38, yourBoard.getSiege().addPoints().get());
    }
    @Test
    void shouldApplyWeatherEffectForEmpty(){
        /**
         * To perform this test, you need to delete the selected collection from @BeforeAll
         */
        yourBoard.getRanged().setWeatherEffect(true);
        Assertions.assertEquals(5, yourBoard.getRanged().addPoints().get());
    }
    @Test
    void shouldApplyWeatherEffectWhenDisabled(){
        yourBoard.getRanged().setWeatherEffect(false);
        Assertions.assertEquals(38, yourBoard.getRanged().addPoints().get());
    }
    @Test
    void shouldApplyWeatherEffectForVaried(){
        yourBoard.getSiege().setWeatherEffect(true);
        Assertions.assertEquals(23, yourBoard.getSiege().addPoints().get());
    }
    @Test
    void shouldApplyWeatherEffectForVariedWhenDisabled(){
        yourBoard.getSiege().setWeatherEffect(false);
        Assertions.assertEquals(38, yourBoard.getSiege().addPoints().get());
    }
}