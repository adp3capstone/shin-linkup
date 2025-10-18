package za.ac.cput.linkup.util;

import za.ac.cput.linkup.domain.EmergencyContact;
import za.ac.cput.linkup.domain.User;

public class EmergencyContactDTO {
    private Long contactId;
    private Long userId;
    private String name;
    private String phoneNumber;

    public EmergencyContactDTO(EmergencyContact contact) {
        if (contact != null) {
            this.contactId = contact.getContactId();
            User user = contact.getUser();
            this.userId = (user != null) ? user.getUserId() : null;
            this.name = contact.getName();
            this.phoneNumber = contact.getPhoneNumber();
        }
    }

    public Long getContactId() {
        return contactId;
    }

    public Long getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
