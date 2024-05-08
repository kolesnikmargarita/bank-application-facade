package by.kolesnik.course.bank.exception;

public class CantDeleteFileException extends RuntimeException {

    public CantDeleteFileException(String message) {
        super(message);
    }
}
