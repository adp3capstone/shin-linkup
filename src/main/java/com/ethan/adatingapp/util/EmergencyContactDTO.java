package com.ethan.adatingapp.util;

import com.ethan.adatingapp.domain.EmergencyContact;
import com.ethan.adatingapp.domain.User;

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
