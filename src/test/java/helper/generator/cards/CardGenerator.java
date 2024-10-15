package helper.generator.cards;

import pl.edu.us.medrala.szymon.aiagent.cards.*;

import java.security.SecureRandom;
import java.util.List;
import java.util.Random;

public final class CardGenerator {
    private static final Random random = new SecureRandom();
    private static final List<Ability> abilities = List.of(Ability.NONE, Ability.COMMANDERS_HORN, Ability.HERO, Ability.TIGHT_BOND);

    public static Card generateCard(final String name, final int baseUnitStrength, final int currentUnitStrength, final Type type) {
        return new Card(
                random.nextInt(),
                name,
                baseUnitStrength,
                currentUnitStrength,
                randomAbility(),
                type,
                1,
                randomRange(),
                randomFraction(),
                CardsLoader.getImageBytes("Vernon Roche.png")
        );
    }

    private static Ability randomAbility() {
        return abilities.get(random.nextInt(abilities.size()));
    }

    private static Range randomRange() {
        return Range.values()[random.nextInt(Range.values().length)];
    }

    private static Fraction randomFraction() {
        return Fraction.values()[random.nextInt(Fraction.values().length)];
    }
}
