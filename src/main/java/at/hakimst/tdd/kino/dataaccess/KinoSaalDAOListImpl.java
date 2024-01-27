package at.hakimst.tdd.kino.dataaccess;

import at.hakimst.tdd.kino.domain.KinoSaal;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class KinoSaalDAOListImpl implements KinoSaalDAO {

    private static long nextId = 1;
    private final List<KinoSaal> kinosaele = new ArrayList<>();

    @Override
    public Optional<KinoSaal> insert(KinoSaal entity) {
        entity.setId(nextId++);

        kinosaele.add(entity);
        return Optional.of(entity);
    }

    @Override
    public Optional<KinoSaal> getById(Long id) {
        return kinosaele.stream().filter(v -> v.getId().equals(id)).findFirst();
    }

    @Override
    public List<KinoSaal> getAll() {
        return kinosaele;
    }

    @Override
    public Optional<KinoSaal> update(KinoSaal entity) {
        // todo
        throw new RuntimeException("Method not implemented");
    }

    @Override
    public void deleteById(Long id) {
        // todo
        throw new RuntimeException("Method not implemented");
    }
}
