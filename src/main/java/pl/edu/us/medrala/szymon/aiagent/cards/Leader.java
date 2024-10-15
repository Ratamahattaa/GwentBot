package pl.edu.us.medrala.szymon.aiagent.cards;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;

@Table(name = "LEADERS", schema = "PUBLIC")
@Entity
@NoArgsConstructor
public class Leader extends ACard {
    @Getter
    @Setter
    @Column(name = "ABILITY")
    @Enumerated(EnumType.STRING)
    private Ability.Leader ability;

    public Leader(long id, String name, int price, Ability.Leader ability, Fraction fraction, byte[] image) {
        super(id, name, price, fraction, image);
        this.ability = ability;
    }
}
