package duoc.cl.SupportService.service;

import duoc.cl.SupportService.dto.TicketDto;
import duoc.cl.SupportService.model.Ticket;
import duoc.cl.SupportService.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    public Ticket crearTicket(TicketDto dto) {
        Ticket ticket = new Ticket();
        ticket.setUsuarioEmail(dto.getUsuarioEmail());
        ticket.setAsunto(dto.getAsunto());
        ticket.setDescripcion(dto.getDescripcion());
        ticket.setEstado("ABIERTO");
        ticket.setFechaCreacion(LocalDateTime.now());
        ticket.setFechaActualizacion(LocalDateTime.now());

        return ticketRepository.save(ticket);
    }

    public List<Ticket> verTicketsPorUsuario(String email) {
        return ticketRepository.findByUsuarioEmail(email);
    }

    public Ticket actualizarEstado(int idTicket, String nuevoEstado) {
        Ticket ticket = ticketRepository.findById(idTicket)
                .orElseThrow(() -> new RuntimeException("Ticket no encontrado."));

        ticket.setEstado(nuevoEstado.toUpperCase());
        ticket.setFechaActualizacion(LocalDateTime.now());

        return ticketRepository.save(ticket);
    }
}