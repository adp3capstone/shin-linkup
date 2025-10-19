package za.ac.cput.linkup.util;

/**
 * AdminSignUpRequest.java
 * Author: Ethan Le Roux (222622172)
 */

import lombok.Getter;
import lombok.ToString;
import za.ac.cput.linkup.domain.enums.*;

import java.util.List;

@ToString
@Getter
public class AdminSignUpRequest {
            String firstName;
            String lastName;
            String email;
            String username;
            String password;

            public AdminSignUpRequest(String firstName,
                                      String lastName,
                                      String email,
                                      String username,
                                      String password) {
                this.firstName = firstName;
                this.lastName = lastName;
                this.email = email;
                this.username = username;
                this.password = password;
            }
}
