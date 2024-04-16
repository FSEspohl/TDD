package at.hakimst.tdd.kino;

import at.hakimst.tdd.kino.dataaccess.KinoSaalDAOListImpl;
import at.hakimst.tdd.kino.dataaccess.TicketDAOListImpl;
import at.hakimst.tdd.kino.dataaccess.VorstellungDAOListImpl;
import at.hakimst.tdd.kino.domain.KinoSaal;
import at.hakimst.tdd.kino.domain.Vorstellung;
import at.hakimst.tdd.kino.domain.Zeitfenster;
import at.hakimst.tdd.kino.services.KinoVerwaltung;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Main
{
    public static void main( String[] args )
    {
        //Saal anlegen
        Map<Character,Integer> map1 = new HashMap<>();
        map1.put('A',10);
        map1.put('B',10);
        map1.put('C',15);
        KinoSaal ks1 = new KinoSaal("LadyX",map1);

        //Aufgabe3: Kinosaal anlegen
        Map<Character,Integer> map2 = new HashMap<>();
        map2.put('A',10);
        map2.put('B',10);
        map2.put('C',15);
        map2.put('D',15);
        map2.put('E',10);
        map2.put('F',6);
        KinoSaal ks2 = new KinoSaal("IMAX",map2);

        //Platz prüfen
        System.out.println(ks1.pruefePlatz('A',11));
        System.out.println(ks1.pruefePlatz('A',10));
        System.out.println(ks1.pruefePlatz('B',10));
        System.out.println(ks1.pruefePlatz('C',14));

        //Aufgabe3: Vorstellung anlegen
        Vorstellung dune2 = new Vorstellung(ks2, Zeitfenster.ABEND, LocalDate.of(2024, 1, 8), "Dune 2", 18.99f);

        //Aufgabe3: Vorstellung einplanen
        KinoVerwaltung kv = new KinoVerwaltung(new KinoSaalDAOListImpl(), new VorstellungDAOListImpl(), new TicketDAOListImpl());
        kv.einplanenVorstellung(dune2);

        //Aufgabe3: Tickets für Vorstellung ausgeben
        kv.kaufeTicket(dune2, 'A', 5, 18.99f);


    }
}
