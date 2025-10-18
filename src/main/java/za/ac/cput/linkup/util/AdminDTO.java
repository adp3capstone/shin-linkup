package za.ac.cput.linkup.util;

import lombok.Getter;
import lombok.Setter;
import za.ac.cput.linkup.domain.Admin;

import java.util.List;

@Getter
@Setter
public class AdminDTO {
    private Long userId;
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    List<TicketDTO> assignedTickets;
    List<TicketDTO> resolvedTickets;

    public AdminDTO(Long userId, String firstName, String lastName, String email, String username, List<TicketDTO> assignedTickets, List<TicketDTO> resolvedTickets) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.assignedTickets = assignedTickets;
        this.resolvedTickets = resolvedTickets;
    }
    public AdminDTO(Admin admin, List<TicketDTO> assignedTickets,List<TicketDTO> resolvedTickets) {
        this.userId = admin.getUserId();
        this.firstName = admin.getFirstName();
        this.lastName = admin.getLastName();
        this.email = admin.getEmail();
        this.username = admin.getUsername();
        this.assignedTickets = assignedTickets;
        this.resolvedTickets = resolvedTickets;
    }
}
