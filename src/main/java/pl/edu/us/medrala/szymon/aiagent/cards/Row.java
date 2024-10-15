package pl.edu.us.medrala.szymon.aiagent.cards;

import lombok.Getter;
import lombok.Setter;
import pl.edu.us.medrala.szymon.aiagent.cards.ability.Weather;
import pl.edu.us.medrala.szymon.aiagent.cards.ability.WeatherStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Row {
    @Getter
    private final List<Card> row = new ArrayList<>();
    @Getter
    private final AtomicInteger points = new AtomicInteger(0);
    @Getter
    private boolean weatherEffect;
    @Getter
    @Setter
    private boolean hornEffect;

    public AtomicInteger addPoints() {
        if (weatherEffect) {
            Weather.weatherEffect(this, WeatherStatus.APPLY);
        }
        recalculatePoints();
        return points;
    }

    public void add(final Card card) {
        points.addAndGet(card.getAbility().equals(Ability.SPY) ? 0 : card.getUnitStrength());
        row.add(card);
    }

    public void recalculatePoints() {
        points.set(0);
        for (final Card card : row) {
            points.addAndGet(card.getAbility().equals(Ability.SPY) ? 0 : card.getUnitStrength());
        }
    }

    public void remove(Card card) {
        row.remove(card);
    }

    public int size() { return row.size(); }

    public int addHeroPoints() {
        int rowPoints = 0;
        for (Card card : row) {
            if (card.getType() == Type.HERO) {
                rowPoints += card.getUnitStrength();
            }
        }
        return rowPoints;
    }

    public int checkNotHeroCards(Row row) {
        int heroCards = 0;
        for (int i = 0; i < row.size(); i++) {
            if (row.getRow().get(i).getType() == Type.HERO) {
                heroCards += 1;
            }
        }
        return row.size() - heroCards;
    }

    public int getNoHeroPoints() {
        return points.get() - addHeroPoints();
    }

    public void clear() {
        row.clear();
        points.set(0);
        weatherEffect = false;
        hornEffect = false;
    }

    public void setWeatherEffect(boolean apply) {
        Weather.weatherEffect(this, apply ? WeatherStatus.APPLY : WeatherStatus.REMOVE);
        recalculatePoints();
    }
}
