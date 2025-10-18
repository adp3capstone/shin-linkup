package za.ac.cput.linkup.domain;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import za.ac.cput.linkup.domain.enums.Role;

@ToString
@Getter
@Setter
@Entity
@Table(name="base_user")
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class BaseUser {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;

    private String firstName;

    private String lastName;

    private String email;

    private String username;

    private String password;

    private Role role;
}
