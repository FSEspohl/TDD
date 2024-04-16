package at.hakimst.tdd.kino;

import at.hakimst.tdd.kino.domain.KinoSaal;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class KinoSaalTest {

    // todo: implement pruefe platz

    @Test
    public void testEquals() {
        Map<Character, Integer> map1 = new HashMap<>();
        map1.put('A', 5);
        map1.put('B', 7);
        map1.put('C', 9);
        KinoSaal saal1 = new KinoSaal("KS1", map1);

        Map<Character, Integer> map2 = new HashMap<>();
        map1.put('A', 5);
        map1.put('B', 7);
        map1.put('C', 9);
        KinoSaal saal2 = new KinoSaal("KS2", map2);

        assertEquals(saal1, saal1);
        assertNotEquals(saal1, saal2);

    }
}
