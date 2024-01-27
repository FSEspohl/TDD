package at.hakimst.tdd.kino.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;

@Getter
@AllArgsConstructor
public class KinoSaal {

    private final String name;

    private final Map<Character, Integer> reihen;

    public boolean pruefePlatz(char reihe, int platz) {
        Integer plaetze = reihen.get(reihe);

        return plaetze != null && platz <= plaetze && platz != 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof KinoSaal)) {
            return false;
        }

        return this.name.equals(((KinoSaal) obj).getName());
    }

}
