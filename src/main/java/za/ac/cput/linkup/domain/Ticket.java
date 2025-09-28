package za.ac.cput.linkup.domain;

import jakarta.persistence.*;
import lombok.*;
import za.ac.cput.linkup.domain.enums.IssueType;
import za.ac.cput.linkup.domain.enums.TicketStatus;

import java.time.LocalDateTime;
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Entity
@Table(name = "tickets")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ticketId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    IssueType issueType;
    private String description;
    private TicketStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime resolvedAt;

    @OneToOne(cascade = CascadeType.ALL)
    private User resolvedBy;
}
