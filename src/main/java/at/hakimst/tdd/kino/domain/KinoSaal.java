package at.hakimst.tdd.kino.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.Map;

@Getter
@AllArgsConstructor
@ToString
public class KinoSaal extends BaseEntity {

    private final String name;

    private final Map<Character, Integer> reihen;

    public int getPlatze() {
        return reihen.values().stream().reduce(0, Integer::sum);
    }

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
