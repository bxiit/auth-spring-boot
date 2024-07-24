package kz.spring.auth.auth.controller.advice;

import kz.spring.auth.auth.exception.CustomerException;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalControllerAdvice {

    @ExceptionHandler(CustomerException.class)
    public ProblemDetail handleRuntimeException(CustomerException e) {
        return ProblemDetail.forStatusAndDetail(e.getHttpStatus(), e.getMessage());
    }
}
