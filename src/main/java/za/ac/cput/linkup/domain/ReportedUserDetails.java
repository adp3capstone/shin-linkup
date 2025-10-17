package za.ac.cput.linkup.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReportedUserDetails {
    private Long userId;

    private String firstName;

    private String lastName;

    private String username;
}
