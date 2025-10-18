package za.ac.cput.linkup.domain;

import lombok.*;
import lombok.experimental.SuperBuilder;
import za.ac.cput.linkup.domain.enums.*;
import jakarta.persistence.*;
import za.ac.cput.linkup.domain.enums.*;

import java.util.List;
import java.util.Set;

@Getter
@ToString
@Setter
@SuperBuilder
@Entity
@Table(name="user")
@AllArgsConstructor
public class User extends BaseUser{
    private int age;

    private String bio;

    private Institution institution;
    private Gender gender;
    private Course course;

    @ElementCollection(targetClass = Interest.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(
            name = "user_interests",
            joinColumns = @JoinColumn(name = "user_id")
    )
    @Column(name = "interest")
    private List<Interest> interests;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Image image;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Preference preferences;

    @OneToMany(mappedBy = "liker", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Like> likesGiven;

    @OneToMany(mappedBy = "liked", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Like> likesReceived;

    @OneToMany(mappedBy = "user1")
    private List<Match> matchesAsUser1;

    @OneToMany(mappedBy = "user2")
    private List<Match> matchesAsUser2;

    private boolean isSmoker;

    private boolean isDrinker;

    private double height;

    private Orientation orientation;

    @OneToMany
    private List<EmergencyContact> emergencyContacts;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Ticket> tickets;

    public User() {

    }
}
