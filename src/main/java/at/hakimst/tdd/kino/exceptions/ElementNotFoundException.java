package at.hakimst.tdd.kino.exceptions;

import lombok.Getter;

@Getter
public class ElementNotFoundException extends Exception {
    private Long id;

    public ElementNotFoundException(String message, Long id) {
        super(message);
        this.id = id;
    }
}
