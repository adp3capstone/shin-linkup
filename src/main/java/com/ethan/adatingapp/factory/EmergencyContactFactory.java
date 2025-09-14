package com.ethan.adatingapp.factory;


import com.ethan.adatingapp.domain.EmergencyContact;
import com.ethan.adatingapp.domain.User;
import com.ethan.adatingapp.util.Helper;

public class EmergencyContactFactory {
    public static EmergencyContact createEmergencyContact(Long contactId, User user, String name, String phoneNumber, String email) {
        if (!Helper.isValidLong(contactId))
            return null;
        if (Helper.isObjectNull(user))
            return null;
        if (Helper.isStringNullOrEmpty(name))
            return null;
        if (Helper.isStringNullOrEmpty(phoneNumber))
            return null;
        if (!Helper.isStringNullOrEmpty(email) && !Helper.isValidEmail(email))
            return null;

        return new EmergencyContact.Builder()
                .setContactId(contactId)
                .setUser(user)
                .setName(name)
                .setPhoneNumber(phoneNumber)
                .setEmail(email)
                .build();
    }
}