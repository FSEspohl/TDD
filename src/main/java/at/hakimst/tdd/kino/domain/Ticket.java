package at.hakimst.tdd.kino.domain;

import at.hakimst.tdd.kino.domain.Zeitfenster;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@ToString
public class Ticket extends BaseEntity {

    private Vorstellung vorstellung;

    private char reihe;

    private int platz;
}
