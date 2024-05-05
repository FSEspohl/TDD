package at.hakimst.tdd.kino;

import at.hakimst.tdd.kino.dataaccess.*;
import at.hakimst.tdd.kino.domain.KinoSaal;
import at.hakimst.tdd.kino.domain.Vorstellung;
import at.hakimst.tdd.kino.domain.Zeitfenster;
import at.hakimst.tdd.kino.services.KinoVerwaltung;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class KinoEndToEndTest {
    private KinoSaalDAO kinoSaalDAO;
    private TicketDAO ticketDAO;
    private VorstellungDAO vorstellungDAO;
    private KinoVerwaltung kinoVerwaltung;

    @BeforeEach
    void setup() {
        // Echte DAO Implementation ohne Mocks, damit ein echter End-to-End-Test möglich ist.
        kinoSaalDAO = new KinoSaalDAOListImpl();
        vorstellungDAO = new VorstellungDAOListImpl();
        ticketDAO = new TicketDAOListImpl();
        kinoVerwaltung = new KinoVerwaltung(kinoSaalDAO, vorstellungDAO, ticketDAO);

        // Daten für Tests vorbereiten
        Map<Character, Integer> map = new HashMap<>();
        map.put('A', 15);
        map.put('B', 18);
        map.put('C', 13);
        KinoSaal kinoSaal = new KinoSaal("KS1", map);
        kinoSaalDAO.insert(kinoSaal);

        Vorstellung vorstellung = new Vorstellung(kinoSaal, Zeitfenster.ABEND, LocalDate.now(), "DUNE 2", 17.99f);
        vorstellungDAO.insert(vorstellung);
    }

    @Test
    void testEndToEnd() {
        // Schritt 1: Eine Vorstellung auswählen
        Vorstellung vorstellung = vorstellungDAO.getAll().get(0);

        // Schritt 2: Ein Ticket für einen verfügbaren Sitz kaufen
        char reihe = 'A';
        int sitznummer = 1;
        float preis = 20.00f; // Nutzer:in zahlt mehr als notwendig
        kinoVerwaltung.kaufeTicket(vorstellung, reihe, sitznummer, preis);

        // Schritt 3: Überprüfen, ob das Ticket richtig gekauft wurde
        assertEquals(1, ticketDAO.getAllByVorstellung(vorstellung).size());

        // Schritt 4: Überprüfung der freien Plätze nach dem Ticketkauf
        int erwarteteFreiePlaetze = 45;
        assertEquals(erwarteteFreiePlaetze, kinoVerwaltung.freiePlaetze(vorstellung));


    }
}
