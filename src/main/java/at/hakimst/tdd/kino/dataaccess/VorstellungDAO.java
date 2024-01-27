package at.hakimst.tdd.kino.dataaccess;

import at.hakimst.tdd.kino.domain.Vorstellung;

import java.util.List;

public interface VorstellungDAO extends BaseRepository<Vorstellung, Long> {
    List<Vorstellung> getAlleZukuenftige();
}
