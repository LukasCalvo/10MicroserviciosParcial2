package duoc.cl.SupportService.controller;

import duoc.cl.SupportService.dto.TicketDto;
import duoc.cl.SupportService.model.Ticket;
import duoc.cl.SupportService.service.TicketService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/support")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @PostMapping
    public ResponseEntity<Ticket> abrirTicket(@Valid @RequestBody TicketDto dto) {
        return new ResponseEntity<>(ticketService.crearTicket(dto), HttpStatus.CREATED);
    }

    @GetMapping("/usuario/{email}")
    public ResponseEntity<List<Ticket>> verMisTickets(@PathVariable String email) {
        return ResponseEntity.ok(ticketService.verTicketsPorUsuario(email));
    }

    @PutMapping("/{id}/estado")
    public ResponseEntity<?> cambiarEstado(@PathVariable int id, @RequestParam String nuevoEstado) {
        try {
            Ticket actualizado = ticketService.actualizarEstado(id, nuevoEstado);
            return ResponseEntity.ok(actualizado);
        } catch (RuntimeException e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }
}