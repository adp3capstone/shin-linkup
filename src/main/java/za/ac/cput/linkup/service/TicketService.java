package za.ac.cput.linkup.service;

/**
 * TicketService.java
 * Author: Ethan Le Roux (222622172)
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.linkup.domain.Admin;
import za.ac.cput.linkup.domain.Ticket;
import za.ac.cput.linkup.repository.TicketRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;

    @Autowired
    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public Ticket getTicket(Long id) {
        return ticketRepository.findById(id).orElse(null);
    }

    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    public Ticket createTicket(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    public Ticket updateTicket(Ticket ticket) {
        if (ticket.getTicketId() == null || !ticketRepository.existsById(ticket.getTicketId())) {
            return null;
        }
        return ticketRepository.save(ticket);
    }

    public void deleteTicket(Long id) {
        ticketRepository.deleteById(id);
    }

    public List<Ticket> getTicketsByUserId(Long userId) {
        return ticketRepository.findByUser_UserId(userId);
    }

    public List<Ticket> getTicketsByAdminId(Admin adminId) {
        return ticketRepository.findByAssignedTo(adminId);
    }
}
