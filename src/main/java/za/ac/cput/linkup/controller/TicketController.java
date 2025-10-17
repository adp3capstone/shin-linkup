package za.ac.cput.linkup.controller;

/**
 * TicketController.java
 * Author: Ethan Le Roux (222622172)
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.linkup.domain.Admin;
import za.ac.cput.linkup.domain.Ticket;
import za.ac.cput.linkup.domain.enums.TicketStatus;
import za.ac.cput.linkup.service.AdminService;
import za.ac.cput.linkup.service.TicketService;
import za.ac.cput.linkup.util.AdminAssignTicketRequest;
import za.ac.cput.linkup.util.TicketDTO;
import za.ac.cput.linkup.util.TicketRequest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tickets")
public class TicketController {

    private final TicketService ticketService;
    private final AdminService adminService;

    @Autowired
    public TicketController(TicketService ticketService, AdminService adminService) {
        this.ticketService = ticketService;
        this.adminService = adminService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<TicketDTO> getTicket(@PathVariable Long id) {
        Ticket ticket = ticketService.getTicket(id);
        TicketDTO dto = new TicketDTO(ticket);
        return ticket != null ? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<TicketDTO>> getAllTickets() {
        List<Ticket> tickets = ticketService.getAllTickets();
        if(tickets.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        List<TicketDTO> dtos = tickets.stream().map(ticket -> new TicketDTO(
                ticket.getTicketId(),
                ticket.getUser().getUserId(),
                ticket.getIssueType(),
                ticket.getDescription(),
                ticket.getStatus(),
                ticket.getCreatedAt(),
                ticket.getUpdatedAt(),
                ticket.getResolvedAt(),
                ticket.getResolvedBy() != null ? ticket.getResolvedBy().getUserId() : null
        )).toList();

        return ResponseEntity.ok(dtos);
    }

    @PostMapping
    public ResponseEntity<TicketDTO> createTicket(@RequestBody TicketRequest ticketReq) {
        if(ticketReq==null) return ResponseEntity.badRequest().build();

        Ticket ticket = Ticket.builder()
                .user(ticketReq.getUser())
                .issueType(ticketReq.getIssueType())
                .description(ticketReq.getDescription())
                .status(TicketStatus.UNASSIGNED)
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
    public ResponseEntity<TicketDTO> patchTicket(@PathVariable Long id, @RequestBody Ticket ticket) {
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


        Ticket patchedTicket = builder
                .updatedAt(LocalDateTime.now())
                .build();

        Ticket updated = ticketService.updateTicket(patchedTicket);
        if(updated != null) {
            TicketDTO dto = new TicketDTO(updated);
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
    public ResponseEntity<List<TicketDTO>> getTicketsByUserId(@PathVariable Long userId) {
        List<Ticket> tickets = ticketService.getTicketsByUserId(userId);

        if(tickets.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        List<TicketDTO> dtos = tickets.stream().map(ticket -> new TicketDTO(
                ticket.getTicketId(),
                ticket.getUser().getUserId(),
                ticket.getIssueType(),
                ticket.getDescription(),
                ticket.getStatus(),
                ticket.getCreatedAt(),
                ticket.getUpdatedAt(),
                ticket.getResolvedAt(),
                ticket.getResolvedBy() != null ? ticket.getResolvedBy().getUserId() : null
        )).toList();

        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/admin/{adminId}")
    public ResponseEntity<List<TicketDTO>> getTicketsByAdminId(@PathVariable Long adminId) {
        Optional<Admin> admin = adminService.findById(adminId);
        if (admin.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        List<Ticket> tickets = ticketService.getTicketsByAdminId(admin.get());
        if (tickets.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        List<TicketDTO> dtos = tickets.stream().map(ticket -> new TicketDTO(
                ticket.getTicketId(),
                ticket.getUser().getUserId(),
                ticket.getIssueType(),
                ticket.getDescription(),
                ticket.getStatus(),
                ticket.getCreatedAt(),
                ticket.getUpdatedAt(),
                ticket.getResolvedAt(),
                ticket.getResolvedBy() != null ? ticket.getResolvedBy().getUserId() : null
        )).toList();
        return ResponseEntity.ok(dtos);
    }

    @PatchMapping("/assign")
    public ResponseEntity<TicketDTO> assignTicketToAdmin(@RequestBody AdminAssignTicketRequest adminAssignTicketRequest) {
        Long ticketId = adminAssignTicketRequest.getTicketId();
        Long adminId = adminAssignTicketRequest.getAdminId();

        Ticket existingTicket = ticketService.getTicket(ticketId);
        Admin admin = adminService.findById(adminId).orElse(null);

        if (admin == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        if (existingTicket == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        // Create a new updated ticket using the builder
        Ticket updatedTicket = existingTicket.toBuilder()
                .assignedTo(admin)
                .status(TicketStatus.IN_PROGRESS)
                .updatedAt(LocalDateTime.now())
                .build();

        updatedTicket = ticketService.updateTicket(updatedTicket);

        if (updatedTicket == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        return ResponseEntity.ok(new TicketDTO(updatedTicket));
    }

    @PatchMapping("/resolve")
    public ResponseEntity<TicketDTO> resolveTicket(@RequestBody AdminAssignTicketRequest adminAssignTicketRequest) {
        Long ticketId = adminAssignTicketRequest.getTicketId();
        Long adminId = adminAssignTicketRequest.getAdminId();

        Ticket existingTicket = ticketService.getTicket(ticketId);
        Admin admin = adminService.findById(adminId).orElse(null);

        if (admin == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        if (existingTicket == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        Ticket updatedTicket = existingTicket.toBuilder()
                .status(TicketStatus.RESOLVED)
                .resolvedAt(LocalDateTime.now())
                .resolvedBy(admin)
                .updatedAt(LocalDateTime.now())
                .build();

        updatedTicket = ticketService.updateTicket(updatedTicket);

        if (updatedTicket == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        return ResponseEntity.ok(new TicketDTO(updatedTicket));
    }

}
