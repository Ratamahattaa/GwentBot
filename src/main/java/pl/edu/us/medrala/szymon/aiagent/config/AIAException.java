package pl.edu.us.medrala.szymon.aiagent.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;

@Getter
@AllArgsConstructor
@NoArgsConstructor(force = true)
public abstract class AIAException extends RuntimeException {
    protected final String error;
    protected final String errorMessage;

    public abstract ResponseEntity<AIAError> response();
}
