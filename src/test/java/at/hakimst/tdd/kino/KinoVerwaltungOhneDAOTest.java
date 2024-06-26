package at.hakimst.tdd.kino;

import at.hakimst.tdd.kino.dataaccess.KinoSaalDAO;
import at.hakimst.tdd.kino.dataaccess.TicketDAO;
import at.hakimst.tdd.kino.dataaccess.VorstellungDAO;
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

@ExtendWith(MockitoExtension.class)
public class KinoVerwaltungOhneDAOTest {

    @Mock
    private KinoSaalDAO kinoSaalDAO; // Mocking Stub zum Testen

    @Mock
    private TicketDAO ticketDAO; // Mocking Stub zum Testen

    @Mock
    private VorstellungDAO vorstellungDAO; // Mocking Stub zum Testen

    private KinoVerwaltung kinoVerwaltung;

    private KinoSaal kinoSaal;

    @BeforeEach
    void setup() {
        kinoVerwaltung = new KinoVerwaltung(kinoSaalDAO, vorstellungDAO, ticketDAO);

        Map<Character, Integer> map1 = new HashMap<>();
        map1.put('A', 5);
        map1.put('B', 7);
        map1.put('C', 9);
        kinoSaal = new KinoSaal("KS1", map1);
        kinoSaal.setId(1L);
    }


    @Test
    void testFreiePlaetzeMock() {

        Vorstellung vorstellung = new Vorstellung(kinoSaal, Zeitfenster.ABEND, LocalDate.of(2024, 1, 8), "Herr der Ringe",10);

        Mockito.when(kinoSaalDAO.getById(anyLong())).thenReturn(Optional.of(kinoSaal));

        List<Ticket> tickets = new ArrayList<>();
        tickets.add(new Ticket(vorstellung, 'A', 1));
        tickets.add(new Ticket(vorstellung, 'A', 2));
        tickets.add(new Ticket(vorstellung, 'A', 3));
        Mockito.when(ticketDAO.getAllByVorstellung(vorstellung)).thenReturn(tickets);

        int freiePlaetze = kinoVerwaltung.freiePlaetze(vorstellung);

        int plaetzeImSaal = kinoSaal.getPlatze();
        assertEquals(plaetzeImSaal - tickets.size(), freiePlaetze);
    }

    @Test
    void testKaufeTicketMock() {

        // todo: implement

    }
}
