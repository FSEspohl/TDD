package at.hakimst.tdd.kino.dataaccess;

import at.hakimst.tdd.kino.domain.Vorstellung;

import java.util.*;

public class VorstellungDAOListImpl implements VorstellungDAO {

    private static long nextId = 1;

    private final List<Vorstellung> vorstellungen = new ArrayList<>();

    @Override
    public Optional<Vorstellung> insert(Vorstellung entity) {
        entity.setId(nextId++);

        vorstellungen.add(entity);
        return Optional.of(entity);
    }

    @Override
    public Optional<Vorstellung> getById(Long id) {
        return vorstellungen.stream().filter(v -> v.getId().equals(id)).findFirst();
    }

    @Override
    public List<Vorstellung> getAll() {
        return vorstellungen;
    }

    @Override
    public Optional<Vorstellung> update(Vorstellung entity) {
        // todo
        throw new RuntimeException("Method not implemented");
    }

    @Override
    public void deleteById(Long id) {
        // todo
        throw new RuntimeException("Method not implemented");
    }

    @Override
    public List<Vorstellung> getAlleZukuenftige() {
        // todo
        throw new RuntimeException("Method not implemented");
    }
}
