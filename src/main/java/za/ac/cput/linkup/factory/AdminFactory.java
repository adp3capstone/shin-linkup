package za.ac.cput.linkup.factory;

import za.ac.cput.linkup.domain.Admin;
import za.ac.cput.linkup.domain.User;
import za.ac.cput.linkup.util.Helper;

/**
 * AdminFactory.java
 * Author: Ethan Le Roux (222622172)
 */

public class AdminFactory {
    private AdminFactory() {
    }

    public static Admin createAdminUserForSignup(
            String firstName,
            String lastName,
            String email,
            String username,
            String password
    ){
        if(
                Helper.isStringNullOrEmpty(firstName)
                        || Helper.isStringNullOrEmpty(lastName)
                        || Helper.isStringNullOrEmpty(email)
                        || Helper.isStringNullOrEmpty(username)
                        || Helper.isStringNullOrEmpty(password)
        ){
            return null;
        }
        return Admin.builder()
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .username(username)
                .password(password)
                .build();
    }
}
