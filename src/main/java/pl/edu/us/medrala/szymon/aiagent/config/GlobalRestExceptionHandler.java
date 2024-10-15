package pl.edu.us.medrala.szymon.aiagent.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j(topic = "GLOBAL_REST_EXCEPTION_HANDLER")
@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalRestExceptionHandler {
    @ExceptionHandler(value = {CustomException.class})
    public ProblemDetail handleCustomException(final CustomException ex) {
        final ProblemDetail detail = ProblemDetail.forStatusAndDetail(ex.response().getStatusCode(), ex.errorMessage == null ? "Not provided" : ex.errorMessage);
        detail.setProperty(ex.error, ex.errorMessage);
        return detail;
    }
}