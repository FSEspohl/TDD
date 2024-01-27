package at.hakimst.tdd.kino.exceptions;

public class InvalidValueException extends RuntimeException {
    public InvalidValueException(String message) {
        super(message);
    }
}
