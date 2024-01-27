package at.hakimst.tdd.kino.dataaccess;

import at.hakimst.tdd.kino.domain.Ticket;
import at.hakimst.tdd.kino.domain.Vorstellung;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

public class TicketDAOListImpl implements TicketDAO {

    private static long nextId = 1;

    private final List<Ticket> tickets = new ArrayList<>();

    @Override
    public Optional<Ticket> insert(Ticket entity) {
        entity.setId(nextId++);

        tickets.add(entity);
        return Optional.of(entity);
    }

    @Override
    public Optional<Ticket> getById(Long id) {
        return tickets.stream().filter(v -> v.getId().equals(id)).findFirst();
    }

    @Override
    public List<Ticket> getAll() {
        return tickets;
    }

    @Override
    public Optional<Ticket> update(Ticket entity) {
        // todo
        throw new RuntimeException("Method not implemented");
    }

    @Override
    public void deleteById(Long id) {
        Iterator<Ticket> iterator = tickets.iterator();
        while(iterator.hasNext()) {
            Ticket t = iterator.next();
            if(t.getId().equals(id)) {
                iterator.remove();
                break;
            }
        }
    }

    @Override
    public List<Ticket> getAllByVorstellung(Vorstellung vorstellung) {
        return tickets.stream().filter(t -> t.getVorstellung().equals(vorstellung)).toList();
    }
}
