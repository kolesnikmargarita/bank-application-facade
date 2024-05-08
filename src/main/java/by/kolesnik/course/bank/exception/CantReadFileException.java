package by.kolesnik.course.bank.exception;

public class CantReadFileException extends RuntimeException {

    public CantReadFileException(String message) {
        super(message);
    }
}
