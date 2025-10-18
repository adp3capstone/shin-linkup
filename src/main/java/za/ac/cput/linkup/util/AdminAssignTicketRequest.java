package za.ac.cput.linkup.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdminAssignTicketRequest {
    private Long adminId;
    private Long ticketId;

    public AdminAssignTicketRequest(Long adminId, Long ticketId) {
        this.adminId = adminId;
        this.ticketId = ticketId;
    }

    public Long getAdminId() {
        return adminId;
    }

    public void setAdminId(Long adminId) {
        this.adminId = adminId;
    }

    public Long getTicketId() {
        return ticketId;
    }

    public void setTicketId(Long ticketId) {
        this.ticketId = ticketId;
    }
}
