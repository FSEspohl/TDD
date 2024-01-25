package at.itkolleg.ase.tdd.kino;

import java.time.LocalDate;

public class Ticket {

    private final String saal;

    private final at.itkolleg.ase.tdd.kino.Zeitfenster zeitfenster;

    private final LocalDate datum;

    private final char reihe;

    private final int platz;

    public Ticket(String saal, at.itkolleg.ase.tdd.kino.Zeitfenster zeitfenster, LocalDate datum, char reihe, int platz) {
        this.saal = saal;
        this.zeitfenster = zeitfenster;
        this.datum = datum;
        this.reihe = reihe;
        this.platz = platz;
    }

    public String getSaal() {
        return saal;
    }

    public at.itkolleg.ase.tdd.kino.Zeitfenster getZeitfenster() {
        return zeitfenster;
    }

    public LocalDate getDatum() {
        return datum;
    }

    public char getReihe() {
        return reihe;
    }

    public int getPlatz() {
        return platz;
    }

}
