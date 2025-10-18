package za.ac.cput.linkup.repository;

/**
 * TicketRepository.java
 * Author: Ethan Le Roux (222622172)
 */

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.linkup.domain.Admin;
import za.ac.cput.linkup.domain.Ticket;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findByUser_UserId(Long userId);
    List<Ticket> findByAssignedTo(Admin assignedTo);

}
