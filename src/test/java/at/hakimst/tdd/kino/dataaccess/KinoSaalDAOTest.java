package at.hakimst.tdd.kino.dataaccess;

import at.hakimst.tdd.kino.domain.KinoSaal;
import org.junit.jupiter.api.BeforeEach;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class KinoSaalDAOTest {

    private KinoSaalDAO kinoSaalDAO;

    @BeforeEach
    void setUp() {
        kinoSaalDAO = new KinoSaalDAOListImpl();
    }

    public void testKinoSaalHinzufuegen() {
        Map<Character, Integer> map1 = new HashMap<>();
        map1.put('A', 5);
        map1.put('B', 7);
        map1.put('C', 9);
        KinoSaal saal1 = new KinoSaal("KS1", map1);

        assertEquals(0, kinoSaalDAO.getAll().size());

        saal1 = kinoSaalDAO.insert(saal1).get();

        assertEquals(1, saal1.getId());
        assertEquals(1, kinoSaalDAO.getAll().size());
    }
}
