package by.kolesnik.course.bank.controller;

import by.kolesnik.course.bank.dto.ErrorResponse;
import by.kolesnik.course.bank.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundException(EntityNotFoundException exception) {
        final ErrorResponse response = new ErrorResponse(exception.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(ImpossiblyReadFileException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundException(ImpossiblyReadFileException exception) {
        final ErrorResponse response = new ErrorResponse(exception.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(ImpossiblyLoadFileException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundException(ImpossiblyLoadFileException exception) {
        final ErrorResponse response = new ErrorResponse(exception.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(ImpossiblyDeleteFileException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundException(ImpossiblyDeleteFileException exception) {
        final ErrorResponse response = new ErrorResponse(exception.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(IncorrectURLException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundException(IncorrectURLException exception) {
        final ErrorResponse response = new ErrorResponse(exception.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(ImpossiblySaveUserException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundException(ImpossiblySaveUserException exception) {
        final ErrorResponse response = new ErrorResponse(exception.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
}
