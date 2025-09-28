package za.ac.cput.linkup.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.linkup.domain.Ticket;
import za.ac.cput.linkup.service.TicketService;
import za.ac.cput.linkup.util.TicketDTO;
import za.ac.cput.linkup.util.TicketRequest;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/tickets")
public class TicketController {

    private final TicketService ticketService;

    @Autowired
    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ticket> getTicket(@PathVariable Long id) {
        Ticket ticket = ticketService.getTicket(id);
        return ticket != null ? ResponseEntity.ok(ticket) : ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<Ticket>> getAllTickets() {
        return ResponseEntity.ok(ticketService.getAllTickets());
    }

    @PostMapping
    public ResponseEntity<TicketDTO> createTicket(@RequestBody TicketRequest ticketReq) {
        if(ticketReq==null) return ResponseEntity.badRequest().build();

        Ticket ticket = Ticket.builder()
                .user(ticketReq.getUser())
                .issueType(ticketReq.getIssueType())
                .description(ticketReq.getDescription())
                .status(null)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .resolvedAt(null)
                .resolvedBy(null)
                .build();

        Ticket created = ticketService.createTicket(ticket);

        if(created == null) return ResponseEntity.badRequest().build();
        TicketDTO dto = new TicketDTO(
                created.getTicketId(),
                created.getUser().getUserId(),
                created.getIssueType(),
                created.getDescription(),
                created.getStatus(),
                created.getCreatedAt(),
                created.getUpdatedAt(),
                created.getResolvedAt(),
                created.getResolvedBy() != null ? created.getResolvedBy().getUserId() : null
        );
        return ResponseEntity.ok(dto);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Ticket> patchTicket(@PathVariable Long id, @RequestBody Ticket ticket) {
        Ticket existingTicket = ticketService.getTicket(id);

        if (existingTicket == null) {
            return ResponseEntity.notFound().build();
        }

        Ticket.TicketBuilder builder = existingTicket.toBuilder();

        if (ticket.getUser() != null) {
            builder.user(ticket.getUser());
        }
        if (ticket.getIssueType() != null) {
            builder.issueType(ticket.getIssueType());
        }
        if (ticket.getDescription() != null) {
            builder.description(ticket.getDescription());
        }
        if (ticket.getStatus() != null) {
            builder.status(ticket.getStatus());
        }
        if (ticket.getCreatedAt() != null) {
            builder.createdAt(ticket.getCreatedAt());
        }
        if (ticket.getUpdatedAt() != null) {
            builder.updatedAt(ticket.getUpdatedAt());
        }
        if (ticket.getResolvedAt() != null) {
            builder.resolvedAt(ticket.getResolvedAt());
        }
        if (ticket.getResolvedBy() != null) {
            builder.resolvedBy(ticket.getResolvedBy());
        }

        Ticket patchedTicket = builder.build();

        Ticket updated = ticketService.updateTicket(patchedTicket);
        if(updated != null) {
            ResponseEntity.ok(updated);
        }

        return ResponseEntity.notFound().build();
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTicket(@PathVariable Long id) {
        ticketService.deleteTicket(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Ticket>> getTicketsByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(ticketService.getTicketsByUserId(userId));
    }
}
