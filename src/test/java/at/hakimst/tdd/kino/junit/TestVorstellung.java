package at.hakimst.tdd.kino.junit;

import at.hakimst.tdd.kino.dataaccess.VorstellungDAOListImpl;
import at.hakimst.tdd.kino.domain.KinoSaal;
import at.hakimst.tdd.kino.domain.Vorstellung;
import at.hakimst.tdd.kino.domain.Zeitfenster;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class TestVorstellung {

    VorstellungDAOListImpl vorstellungDAO;

    @BeforeEach
    void setup(){
        Map<Character, Integer> map = new HashMap<>();
        map.put('A', 5);
        map.put('B', 7);
        map.put('C', 9);
        KinoSaal testSaal = new KinoSaal("TestKino", map);
        Vorstellung vorstellung = new Vorstellung(1L, testSaal, Zeitfenster.ABEND, LocalDate.now(), "DUNE 2", 17.99f);
        Vorstellung vorstellung2 = new Vorstellung(2L, testSaal, Zeitfenster.NACHMITTAG, LocalDate.now(), "Bee Movie", 11.99f);
        vorstellungDAO = new VorstellungDAOListImpl();
        vorstellungDAO.insert(vorstellung);
        vorstellungDAO.insert(vorstellung2);
    }

    @Test
    void testEquals(){
        assertEquals(vorstellungDAO.getById(1L), vorstellungDAO.getById(1L));
        assertNotEquals(vorstellungDAO.getById(1L), vorstellungDAO.getById(2L));
    }
}
