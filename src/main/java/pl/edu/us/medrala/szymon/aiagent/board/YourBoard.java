package pl.edu.us.medrala.szymon.aiagent.board;

import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicBoolean;

@Component
public class YourBoard extends Board {
    private final AtomicBoolean shouldPlay = new AtomicBoolean(true);

    public YourBoard() {
        super();
    }

    public boolean shouldPlay() {
        return shouldPlay.get();
    }

    public void setShouldPlay(boolean shouldPlay) {
        this.shouldPlay.set(shouldPlay);
    }
}
