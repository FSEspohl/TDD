package at.hakimst.tdd.kino.services;

import at.hakimst.tdd.kino.dataaccess.KinoSaalDAO;
import at.hakimst.tdd.kino.dataaccess.TicketDAO;
import at.hakimst.tdd.kino.dataaccess.VorstellungDAO;
import at.hakimst.tdd.kino.domain.KinoSaal;
import at.hakimst.tdd.kino.domain.Ticket;
import at.hakimst.tdd.kino.domain.Vorstellung;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Main Use-Cases
 */
public class KinoVerwaltung {

    private KinoSaalDAO kinoSaalDAO;

    private VorstellungDAO vorstellungDAO;

    private TicketDAO ticketDAO;

    public KinoVerwaltung(KinoSaalDAO kinoSaalDAO, VorstellungDAO vorstellungDAO, TicketDAO ticketDAO) {
        this.kinoSaalDAO = kinoSaalDAO;
        this.vorstellungDAO = vorstellungDAO;
        this.ticketDAO = ticketDAO;
    }

    /**
     * Neue Vorstellung hinzufügen
     *
     * @param vorstellung
     */
    public void einplanenVorstellung(Vorstellung vorstellung) {
        if (vorstellungDAO.getAll().contains(vorstellung)) {
            throw new IllegalArgumentException("Die Vorstellung ist bereits eingeplant");
        }
        vorstellungDAO.insert(vorstellung);
    }

    /**
     * alle Vorstellungen anzeigen
     *
     * @return
     */
    public List<Vorstellung> zeigeVorstellungen() {
        return vorstellungDAO.getAll();
    }

    /**
     * freie Plätze anzeigen
     */
    public int freiePlaetze(Vorstellung vorstellung) {
        Long saalId = vorstellung.getSaal().getId();

        Optional<KinoSaal> saal = kinoSaalDAO.getById(saalId);

        if (saal.isPresent()) {
            int plaetze = saal.get().getReihen().values().stream().reduce(0, Integer::sum);

            List<Ticket> tickets = ticketDAO.getAllByVorstellung(vorstellung);
            return plaetze - tickets.size();
        }

        throw new RuntimeException("Saal nicht gefunden: " + saalId);
    }

    /**
     * Auslastung einer Vorstellung anzeigen
     *
     * @param vorstellung
     * @return
     */
    public int auslastung(Vorstellung vorstellung) {
        // todo
        throw new RuntimeException("Method not implemented yet");
    }

    /**
     * Ticket kaufen
     */
    public Ticket kaufeTicket(Vorstellung vorstellung, char reihe, int platz, float geld) {

        if (geld < vorstellung.getPreis()) {
            throw new IllegalArgumentException("Nicht ausreichend Geld, min. " + vorstellung.getPreis());
        }

        if (!vorstellung.getSaal().pruefePlatz(reihe, platz)) {
            throw new IllegalArgumentException("Der Platz " + reihe + platz + " existiert nicht.");
        }

        List<Ticket> tickets = ticketDAO.getAllByVorstellung(vorstellung);
        if (tickets.stream().anyMatch(t -> t.getReihe() == reihe && t.getPlatz() == platz)) {
            throw new IllegalStateException("Der Platz " + reihe + "/" +  platz + " ist bereits belegt.");
        }

        // erstelle neues Ticket
        Ticket ticket = new Ticket(vorstellung, reihe, platz);
        return ticketDAO.insert(ticket).get();
    }

    // 6. Ticket stornieren
    public void storniereTicket(Ticket ticket) {
        // todo
        throw new RuntimeException("Method not implemented yet");
    }

}
