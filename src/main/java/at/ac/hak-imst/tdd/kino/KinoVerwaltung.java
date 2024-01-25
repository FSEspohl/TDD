package at.itkolleg.ase.tdd.kino;

import java.util.LinkedList;
import java.util.List;

public class KinoVerwaltung {

    private final List<at.itkolleg.ase.tdd.kino.Vorstellung> vorstellungen = new LinkedList<>();

    public void einplanenVorstellung(at.itkolleg.ase.tdd.kino.Vorstellung vorstellung) {
        if (vorstellungen.contains(vorstellung)) {
            throw new IllegalArgumentException("Die Vorstellung ist bereits eingeplant");
        }
        vorstellungen.add(vorstellung);
    }

    public List<at.itkolleg.ase.tdd.kino.Vorstellung> getVorstellungen() {
        return vorstellungen;
    }

    public at.itkolleg.ase.tdd.kino.Ticket kaufeTicket(at.itkolleg.ase.tdd.kino.Vorstellung vorstellung, char reihe, int platz, float geld) {
        return vorstellung.kaufeTicket(reihe, platz, geld);
    }

}
