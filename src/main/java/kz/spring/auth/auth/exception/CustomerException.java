package kz.spring.auth.auth.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class CustomerException extends RuntimeException {
    private final HttpStatus httpStatus;

    public CustomerException(HttpStatus status, String message) {
        super(message);
        this.httpStatus = status;
    }
}
