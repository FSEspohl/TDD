package at.hakimst.tdd.kino.services;

import at.hakimst.tdd.kino.domain.Ticket;
import at.hakimst.tdd.kino.domain.Vorstellung;
import lombok.Getter;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

@Getter
public class KinoVerwaltung {

    // Use-Cases
    // 1. Vorstellung hinzufügen
    // 2. Vorstellungen anzeigen
    // 3. Auslastung Vorstellungen
    // 4. freie Plätze anzeigen
    // 5. Ticket kaufen

    private final List<Vorstellung> vorstellungen = new LinkedList<>();

    public void einplanenVorstellung(Vorstellung vorstellung) {
        if (vorstellungen.contains(vorstellung)) {
            throw new IllegalArgumentException("Die Vorstellung ist bereits eingeplant");
        }
        vorstellungen.add(vorstellung);
    }

    public void getVorstellungByFilmName(String name) {
        // todo
    }

    public void getVorstellungByDatum(LocalDate datum) {
        // todo
    }

    public Ticket kaufeTicket(Vorstellung vorstellung, char reihe, int platz, float geld) {
        return vorstellung.kaufeTicket(reihe, platz, geld);
    }

}
