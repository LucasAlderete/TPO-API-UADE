package tpo.uade.api.util;

import jakarta.validation.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
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

    private final HttpStatus dataIntegrityViolationExceptionCode = HttpStatus.BAD_REQUEST;
    private final String dataIntegrityViolationExceptionMessage = "Database Query Failed: API returned " + dataIntegrityViolationExceptionCode + " due to ---> \n";

    private final HttpStatus methodArgumentNotValidExceptionCode = HttpStatus.BAD_REQUEST;
    private final String methodArgumentNotValidExceptionMessage = "Validation Failed: API returned " + methodArgumentNotValidExceptionCode + " due to ---> \n";

    private final HttpStatus httpMessageNotReadableExceptionCode = HttpStatus.BAD_REQUEST;
    private final String httpMessageNotReadableExceptionMessage = "Deserialization Failed: API returned " + httpMessageNotReadableExceptionCode + " due to ---> \n";

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<String> handleConstraintViolationException (ConstraintViolationException e) {
        logger.info(constraintViolationExceptionMessage + e.getMessage());
        return new ResponseEntity<>(e.getMessage(), constraintViolationExceptionCode); //TODO -> update the return value to make it prettier
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> handleNoSuchElementException (NoSuchElementException e) {
        logger.info(noSuchElementExceptionMessage + e.getMessage());
        return new ResponseEntity<>(e.getMessage(), noSuchElementExceptionCode);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> handleDataIntegrityViolationException (DataIntegrityViolationException e) {
        logger.info(dataIntegrityViolationExceptionMessage + e.getMessage());
        return new ResponseEntity<>(e.getMessage(), dataIntegrityViolationExceptionCode);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleMethodArgumentNotValidException (MethodArgumentNotValidException e) {
        logger.info(methodArgumentNotValidExceptionMessage + e.getMessage());
        return new ResponseEntity<>(e.getMessage(), methodArgumentNotValidExceptionCode);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleHttpMessageNotReadableException (HttpMessageNotReadableException e) {
        logger.info(httpMessageNotReadableExceptionMessage + e.getMessage());
        return new ResponseEntity<>(e.getMessage(), httpMessageNotReadableExceptionCode);
    }
}
