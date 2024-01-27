package at.hakimst.tdd.kino.dataaccess;

import at.hakimst.tdd.kino.domain.Ticket;
import at.hakimst.tdd.kino.domain.Vorstellung;

import java.util.List;

public interface TicketDAO extends BaseRepository<Ticket, Long>{
    List<Ticket> getAllByVorstellung(Vorstellung vorstellung);
}
