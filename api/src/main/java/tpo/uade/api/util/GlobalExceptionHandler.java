package tpo.uade.api.util;

import jakarta.validation.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.NoSuchElementException;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    private final HttpStatus constraintViolationExceptionCode = HttpStatus.BAD_REQUEST;
    private final String constraintViolationExceptionMessage = "Validation Failed: API returned " + constraintViolationExceptionCode.toString() + " due to ---> \n";

    private final HttpStatus noSuchElementExceptionCode = HttpStatus.BAD_REQUEST;
    private final String noSuchElementExceptionMessage = "Database Query Failed: API returned " + noSuchElementExceptionCode + " due to ---> \n";

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<String> handleConstraintViolationException (ConstraintViolationException e) {
        logger.info(constraintViolationExceptionMessage + e.getMessage());
        return new ResponseEntity<String>(e.getMessage(), constraintViolationExceptionCode);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> handleNoSuchElementException (NoSuchElementException e) {
        logger.info(noSuchElementExceptionMessage + e.getMessage());
        return new ResponseEntity<String>(e.getMessage(), noSuchElementExceptionCode);
    }
}
