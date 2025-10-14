package za.ac.cput.linkup.util;

/**
 * TicketDTO.java
 * Author: Ethan Le Roux (222622172)
 */

import lombok.Getter;
import lombok.Setter;
import za.ac.cput.linkup.domain.Ticket;
import za.ac.cput.linkup.domain.enums.IssueType;
import za.ac.cput.linkup.domain.enums.TicketStatus;
import java.time.LocalDateTime;

@Getter
@Setter
public class TicketDTO {
    private Long ticketId;
    private Long userId;
    private IssueType issueType;
    private String description;
    private TicketStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime resolvedAt;
    private Long resolvedById;

    public TicketDTO(Long ticketId, Long userId, IssueType issueType, String description,
                     TicketStatus status, LocalDateTime createdAt, LocalDateTime updatedAt,
                     LocalDateTime resolvedAt, Long resolvedById) {
        this.ticketId = ticketId;
        this.userId = userId;
        this.issueType = issueType;
        this.description = description;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.resolvedAt = resolvedAt;
        this.resolvedById = resolvedById;
    }

    public TicketDTO(Ticket ticket) {
        this.ticketId = ticket.getTicketId();
        this.userId = ticket.getUser().getUserId();
        this.issueType = ticket.getIssueType();
        this.description = ticket.getDescription();
        this.status = ticket.getStatus();
        this.createdAt = ticket.getCreatedAt();
        this.updatedAt = ticket.getUpdatedAt();
        this.resolvedAt = ticket.getResolvedAt();
        this.resolvedById = ticket.getResolvedBy() != null ? ticket.getResolvedBy().getUserId() : null;
    }
}
