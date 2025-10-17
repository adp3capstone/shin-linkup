package za.ac.cput.linkup.util;

/**
 * TicketRequest.java
 * Author: Ethan Le Roux (222622172)
 */

import lombok.Getter;
import za.ac.cput.linkup.domain.User;
import za.ac.cput.linkup.domain.enums.IssueType;

@Getter
public class TicketRequest {
    private User user;
    IssueType issueType;
    private String description;
}
