package za.ac.cput.linkup.domain;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@ToString
@Entity
@Table(name = "admin")
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Admin extends BaseUser{

    @OneToMany(
            mappedBy = "resolvedBy",
            orphanRemoval = true,
            cascade = CascadeType.ALL
    )
    List<Ticket> resolvedTickets;

    @OneToMany(
            mappedBy = "assignedTo",
            orphanRemoval = true,
            cascade = CascadeType.ALL
    )
    List<Ticket> assignedTickets;
}
