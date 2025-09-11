package com.ethan.adatingapp.factory;

import com.ethan.adatingapp.domain.EmergencyContact;
import com.ethan.adatingapp.domain.User;
import com.ethan.adatingapp.util.Helper;

public class EmergencyContactFactory {

    public static EmergencyContact createEmergencyContact(
            Long emergencyContactId,
            User user,
            String firstName,
            String lastName,
            String phoneNumber,
            String email,
            String relationship,
            String address
    ) {

        if (!Helper.isValidLong(emergencyContactId) || Helper.isObjectNull(user) ||
                Helper.isStringNullOrEmpty(firstName) || Helper.isStringNullOrEmpty(lastName) ||
                Helper.isStringNullOrEmpty(phoneNumber) || Helper.isStringNullOrEmpty(relationship)) {
            return null;
        }


        if (!Helper.isStringNullOrEmpty(email) && !Helper.isValidEmail(email)) {
            return null;
        }


        return new EmergencyContact.Builder()
                .setEmergencyContactId(emergencyContactId)
                .setUser(user)
                .setFirstName(firstName)
                .setLastName(lastName)
                .setPhoneNumber(phoneNumber)
                .setEmail(email)
                .setRelationship(relationship)
                .setAddress(address)
                .build();
    }
}
