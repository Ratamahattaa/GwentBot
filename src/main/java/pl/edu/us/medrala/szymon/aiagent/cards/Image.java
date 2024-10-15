package pl.edu.us.medrala.szymon.aiagent.cards;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@AllArgsConstructor(staticName = "of")
public class Image implements Serializable {

    @Serial
    private static final long serialVersionUID = -747129929364443687L;

    @Getter
    @Setter
    private byte[] image;
}
