package at.hakimst.tdd.kino;

import at.hakimst.tdd.kino.dataaccess.*;
import at.hakimst.tdd.kino.domain.KinoSaal;
import at.hakimst.tdd.kino.domain.Vorstellung;
import at.hakimst.tdd.kino.domain.Zeitfenster;
import at.hakimst.tdd.kino.services.KinoVerwaltung;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class KinoIntegrationTest {
    private KinoSaalDAO kinoSaalDAO;
    private TicketDAO ticketDAO;
    private VorstellungDAO vorstellungDAO;
    private KinoVerwaltung kinoVerwaltung;

    @BeforeEach
    void setup() {
        // Echte DAO Implementation ohne Mocks, damit echte Integrationstests möglich sind.
        kinoSaalDAO = new KinoSaalDAOListImpl();
        vorstellungDAO = new VorstellungDAOListImpl();
        ticketDAO = new TicketDAOListImpl();
        kinoVerwaltung = new KinoVerwaltung(kinoSaalDAO, vorstellungDAO, ticketDAO);

        //Daten für Tests im setup() erstellen:
        Map<Character, Integer> map = new HashMap<>();
        map.put('A', 15);
        map.put('B', 18);
        map.put('C', 13);
        KinoSaal kinoSaal = new KinoSaal("IMAX", map);
        kinoSaalDAO.insert(kinoSaal);

        Vorstellung vorstellung = new Vorstellung(kinoSaal, Zeitfenster.ABEND, LocalDate.now(), "DUNE 2", 17.99f);
        vorstellungDAO.insert(vorstellung);

        Map<Character, Integer> map2 = new HashMap<>();
        map2.put('A', 10);
        map2.put('B', 10);
        map2.put('C', 10);
        KinoSaal kinoSaal2 = new KinoSaal("KS2", map2);
        kinoSaalDAO.insert(kinoSaal2);

        Vorstellung vorstellung2 = new Vorstellung(kinoSaal2, Zeitfenster.NACHMITTAG, LocalDate.now(), "Bee Movie", 11.99f);
        kinoVerwaltung.einplanenVorstellung(vorstellung2);


    }

    // Testet das Anzeigen von Vorstellungen in einem Integrationstest
    @Test
    void testAnzeigenVorstellung()
    {
        assertEquals(2, kinoVerwaltung.zeigeVorstellungen().size());
    }

    // Testet das Kaufen von Tickets und das nachfolgende Prüfen der freien Plätze in einem Integrationstest
    @Test
    void testTicketkaufUndPlatzpruefung()
    {
        Vorstellung vorstellung = vorstellungDAO.getAll().get(0);
        Vorstellung vorstellung2 = vorstellungDAO.getAll().get(1);

        assertNotNull(kinoVerwaltung.kaufeTicket(vorstellung, 'C', 5, 17.99f));
        assertNotNull(kinoVerwaltung.kaufeTicket(vorstellung2, 'B', 2, 11.99f));
        assertEquals(45, kinoVerwaltung.freiePlaetze(vorstellung));
        assertEquals(29, kinoVerwaltung.freiePlaetze(vorstellung2));
        assertNotEquals(vorstellung, vorstellung2);
    }

}
