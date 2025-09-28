package za.ac.cput.linkup.factory;

import za.ac.cput.linkup.domain.Ticket;
import za.ac.cput.linkup.domain.User;
import za.ac.cput.linkup.domain.enums.IssueType;
import za.ac.cput.linkup.domain.enums.TicketStatus;
import za.ac.cput.linkup.util.Helper;

import java.time.LocalDateTime;

public class TicketFactory {
    public static Ticket createTicket(User user, IssueType issueType, String description, TicketStatus status) {
        if (Helper.isObjectNull(user) ||
                issueType == null ||
                status ==null ||
                Helper.isStringNullOrEmpty(description)
) {
            return null;
        }
        return Ticket.builder()
                .user(user)
                .issueType(issueType)
                .description(description)
                .status(status)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }
}
