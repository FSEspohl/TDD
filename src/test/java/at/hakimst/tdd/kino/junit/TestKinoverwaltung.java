package at.hakimst.tdd.kino.junit;

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

import static org.junit.jupiter.api.Assertions.*;

public class TestKinoverwaltung {

    private KinoSaalDAO kinoSaalDAO;
    private TicketDAO ticketDAO;
    private VorstellungDAO vorstellungDAO;
    private KinoVerwaltung kinoVerwaltung;

    @BeforeEach
    void setup() {
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
        kinoVerwaltung.einplanenVorstellung(new Vorstellung(kinoSaal, Zeitfenster.ABEND, LocalDate.now(), "DUNE 2", 17.99f));
    }

    @Test
    void testEinplanenUndZeigeAlleVorstellungen(){
        kinoVerwaltung.einplanenVorstellung(new Vorstellung(kinoSaalDAO.getAll().get(0), Zeitfenster.NACHMITTAG, LocalDate.now(), "Bee Movie", 11.99f));
        assertNotNull(kinoVerwaltung.zeigeVorstellungen());
    }

    @Test
    void testFreiePlaetze(){
        assertEquals(46, kinoVerwaltung.freiePlaetze(vorstellungDAO.getAll().get(0)));
    }

    @Test
    void testKaufeTicket(){
        kinoVerwaltung.kaufeTicket(vorstellungDAO.getAll().get(0), 'A', 2, 20.00f);
        assertEquals(1, ticketDAO.getAll().size());
    }



}
