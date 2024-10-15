package pl.edu.us.medrala.szymon.aiagent.config;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

public class CustomException extends AIAException {
    public CustomException(final String message, final String errorMessage) {
        super(message, errorMessage);
    }

    public ResponseEntity<AIAError> response() {
        return new ResponseEntity<>(AIAError.of(error, errorMessage), HttpStatusCode.valueOf(400));
    }
}
