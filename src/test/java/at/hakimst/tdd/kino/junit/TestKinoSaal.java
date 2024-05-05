package at.hakimst.tdd.kino.junit;

import at.hakimst.tdd.kino.domain.KinoSaal;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class TestKinoSaal {

    @Test
    public void testPlaetze(){
        Map<Character, Integer> map = new HashMap<>();
        map.put('A', 5);
        map.put('B', 7);
        map.put('C', 9);
        KinoSaal testSaal = new KinoSaal("TestKino", map);

        assertEquals(21, testSaal.getPlatze());
    }

    @Test
    public void testPlatzFrei(){
        Map<Character, Integer> map = new HashMap<>();
        map.put('A', 5);
        map.put('B', 7);
        map.put('C', 9);
        KinoSaal testSaal = new KinoSaal("TestKino", map);

        assertTrue(testSaal.pruefePlatz('A', 3));
        assertFalse(testSaal.pruefePlatz('D', 1));
    }
}
