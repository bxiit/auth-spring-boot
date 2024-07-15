package kz.spring.auth.auth.controller.advice;

import kz.spring.auth.auth.exception.InvalidJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalControllerAdvice {

    @ExceptionHandler({InvalidJwtException.class})
    public ProblemDetail handleInvalidJwtException(InvalidJwtException e) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, e.getMessage());
    }
}
