package at.hakimst.tdd.kino;

import at.hakimst.tdd.kino.dataaccess.*;
import at.hakimst.tdd.kino.domain.KinoSaal;
import at.hakimst.tdd.kino.domain.Ticket;
import at.hakimst.tdd.kino.domain.Vorstellung;
import at.hakimst.tdd.kino.domain.Zeitfenster;
import at.hakimst.tdd.kino.services.KinoVerwaltung;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;

public class KinoVerwaltungDAOTest {

    private KinoSaalDAO kinoSaalDAO;

    private TicketDAO ticketDAO;

    private VorstellungDAO vorstellungDAO;

    private KinoVerwaltung kinoVerwaltung;

    private KinoSaal kinoSaal;

    private Vorstellung vorstellung;

    @BeforeEach
    void setup() {

        kinoSaalDAO = new KinoSaalDAOListImpl();
        vorstellungDAO = new VorstellungDAOListImpl();
        ticketDAO = new TicketDAOListImpl();

        kinoVerwaltung = new KinoVerwaltung(kinoSaalDAO, vorstellungDAO, ticketDAO);

        Map<Character, Integer> map1 = new HashMap<>();
        map1.put('A', 5);
        map1.put('B', 7);
        map1.put('C', 9);
        kinoSaal = new KinoSaal("KS1", map1);
        kinoSaalDAO.insert(kinoSaal);

        vorstellung = new Vorstellung(kinoSaal, Zeitfenster.ABEND, LocalDate.of(2024, 1, 8), "Herr der Ringe",10);
        vorstellung = vorstellungDAO.insert(vorstellung).get();
    }

    @Test
    void testFreiePlaetze() {
        ticketDAO.insert(new Ticket(vorstellung, 'A', 1));
        ticketDAO.insert(new Ticket(vorstellung, 'A', 2));
        ticketDAO.insert(new Ticket(vorstellung, 'A', 3));

        int freiePlaetze = kinoVerwaltung.freiePlaetze(vorstellung);

        int plaetzeImSaal = kinoSaal.getPlatze();
        assertEquals(plaetzeImSaal - 3, freiePlaetze);
    }

    @Test
    void testKaufeTicket() {

        // todo: implement

    }
}
