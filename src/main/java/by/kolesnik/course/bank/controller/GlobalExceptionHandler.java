package by.kolesnik.course.bank.controller;

import by.kolesnik.course.bank.dto.ErrorResponse;
import by.kolesnik.course.bank.exception.CantLoadFileException;
import by.kolesnik.course.bank.exception.CantReadFileException;
import by.kolesnik.course.bank.exception.EntityNotFoundException;
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

    @ExceptionHandler(CantReadFileException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundException(CantReadFileException exception) {
        final ErrorResponse response = new ErrorResponse(exception.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(CantLoadFileException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundException(CantLoadFileException exception) {
        final ErrorResponse response = new ErrorResponse(exception.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
}
