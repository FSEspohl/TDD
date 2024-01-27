package at.hakimst.tdd.kino.domain;

import lombok.Getter;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

@Getter
public class Vorstellung {

    private final KinoSaal saal;

    private final Zeitfenster zeitfenster;

    private final LocalDate datum;

    private final String film;

    private final float preis;

    private final List<Ticket> tickets = new LinkedList<>();

    public Vorstellung(KinoSaal saal, Zeitfenster zeitfenster, LocalDate datum, String film, float preis) {
        this.saal = saal;
        this.zeitfenster = zeitfenster;
        this.datum = datum;
        this.film = film;
        this.preis = preis;
    }

    public Ticket kaufeTicket(char reihe, int platz, float geld) {
        if (geld < preis) {
            throw new IllegalArgumentException("Nicht ausreichend Geld, min. " + preis);
        }
        if (!saal.pruefePlatz(reihe, platz)) {
            throw new IllegalArgumentException("Der Platz " + reihe + platz + " existiert nicht.");
        }
        if (tickets.stream().anyMatch(t -> t.getReihe() == reihe && t.getPlatz() == platz)) {
            throw new IllegalStateException("Der Platz " + reihe + platz + " ist bereits belegt.");
        }

        // erstelle Ticket
        Ticket ticket = new Ticket(saal.getName(), zeitfenster, datum, reihe, platz);
        tickets.add(ticket);
        return ticket;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Vorstellung otherV)) {
            return false;
        }
        return saal.equals(otherV.getSaal()) && zeitfenster == otherV.getZeitfenster() && datum.isEqual(otherV.getDatum());
    }

}
