package duoc.cl.SupportService.repository;

import duoc.cl.SupportService.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer> {

    List<Ticket> findByUsuarioEmail(String usuarioEmail);

    List<Ticket> findByEstado(String estado);
}