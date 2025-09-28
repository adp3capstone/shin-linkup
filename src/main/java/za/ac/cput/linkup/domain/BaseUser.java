package za.ac.cput.linkup.domain;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import za.ac.cput.linkup.domain.enums.Role;

@ToString
@Getter
@Setter
@Entity
@Table(name="user")
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class BaseUser {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String firstName;

    private String lastName;

    private String email;

    private String username;

    private String password;

    private Role role;
}
