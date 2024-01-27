package at.hakimst.tdd.kino.domain;

import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@ToString
public class Vorstellung extends BaseEntity {
    private final KinoSaal saal;

    private final Zeitfenster zeitfenster;

    private final LocalDate datum;

    private final String film;

    private final float preis;

    public Vorstellung(Long id, KinoSaal saal, Zeitfenster zeitfenster, LocalDate datum, String film, float preis) {
        super(id);
        this.saal = saal;
        this.zeitfenster = zeitfenster;
        this.datum = datum;
        this.film = film;
        this.preis = preis;
    }

    public Vorstellung(KinoSaal saal, Zeitfenster zeitfenster, LocalDate datum, String film, float preis) {
        this.saal = saal;
        this.zeitfenster = zeitfenster;
        this.datum = datum;
        this.film = film;
        this.preis = preis;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Vorstellung otherV)) {
            return false;
        }
        return saal.equals(otherV.getSaal()) && zeitfenster == otherV.getZeitfenster() && datum.isEqual(otherV.getDatum());
    }

}
