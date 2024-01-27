package at.hakimst.tdd.kino.domain;

import at.hakimst.tdd.kino.domain.Zeitfenster;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class Ticket {
    private String saal;

    private Zeitfenster zeitfenster;

    private LocalDate datum;

    private char reihe;

    private int platz;

}
