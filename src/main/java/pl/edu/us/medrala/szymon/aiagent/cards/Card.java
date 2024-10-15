package pl.edu.us.medrala.szymon.aiagent.cards;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import java.io.Serializable;

@Table(name = "CARDS", schema = "PUBLIC")
@Entity
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Card extends ACard implements Serializable {
    @Getter
    @Setter
    @Column(name = "UNIT_STRENGTH")
    private int unitStrength;

    @Getter
    @Column(name = "BASE_UNIT_STRENGTH")
    private int baseUnitStrength;

    @Getter
    @Setter
    @Column(name = "TYPE")
    @Enumerated(EnumType.STRING)
    private Type type;

    @Getter
    @Setter
    @Column(name = "RANGE")
    @Enumerated(EnumType.STRING)
    private Range range;

    @Getter
    @Setter
    @Column(name = "ABILITY")
    @Enumerated(EnumType.STRING)
    private Ability ability;

    public Card(long id, String name, int baseUnitStrength, int unitStrength, Ability ability, Type type, int price, Range range, Fraction fraction, byte[] image) {
        super(id, name, price, fraction, image);
        this.baseUnitStrength = baseUnitStrength;
        this.unitStrength = unitStrength;
        this.type = type;
        this.range = range;
        this.ability = ability;
    }

    public Card(int baseUnitStrength) {
        this.baseUnitStrength = baseUnitStrength;
    }

    @Override
    public String toString() {
        return "Card {id:%d,name:'%s',unitStrength:%d,baseUnitStrength:%d,range:%s}"
                .formatted(getId(), getName(), unitStrength, baseUnitStrength, range);
    }
}