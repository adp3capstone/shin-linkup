package com.ethan.adatingapp.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "emergency_contact")
public class EmergencyContact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long emergencyContactId;

    @OneToOne
    @JoinColumn(name = "user_id", unique = true, nullable = true)
    private User user;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private String email; // enforced

    @Column(nullable = false)
    private String relationship;

    @Column(nullable = false)
    private String address; // enforced

    // Default constructor
    public EmergencyContact() {}

    // Builder constructor
    private EmergencyContact(Builder builder) {
        this.emergencyContactId = builder.emergencyContactId;
        this.user = builder.user;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.phoneNumber = builder.phoneNumber;
        this.email = builder.email;
        this.relationship = builder.relationship;
        this.address = builder.address;
    }

    // Getters & Setters
    public Long getEmergencyContactId() { return emergencyContactId; }
    public User getUser() { return user; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getPhoneNumber() { return phoneNumber; }
    public String getEmail() { return email; }
    public String getRelationship() { return relationship; }
    public String getAddress() { return address; }

    public void setEmergencyContactId(Long emergencyContactId) { this.emergencyContactId = emergencyContactId; }
    public void setUser(User user) { this.user = user; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
    public void setEmail(String email) { this.email = email; }
    public void setRelationship(String relationship) { this.relationship = relationship; }
    public void setAddress(String address) { this.address = address; }

    @Override
    public String toString() {
        return "EmergencyContact{" +
                "emergencyContactId=" + emergencyContactId +
                ", user=" + (user != null ? user.getUserId() : null) +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", relationship='" + relationship + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    // Builder Pattern
    public static class Builder {
        private Long emergencyContactId;
        private User user;
        private String firstName;
        private String lastName;
        private String phoneNumber;
        private String email;
        private String relationship;
        private String address;

        public Builder setEmergencyContactId(Long emergencyContactId) { this.emergencyContactId = emergencyContactId; return this; }
        public Builder setUser(User user) { this.user = user; return this; }
        public Builder setFirstName(String firstName) { this.firstName = firstName; return this; }
        public Builder setLastName(String lastName) { this.lastName = lastName; return this; }
        public Builder setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; return this; }
        public Builder setEmail(String email) { this.email = email; return this; } // required
        public Builder setRelationship(String relationship) { this.relationship = relationship; return this; }
        public Builder setAddress(String address) { this.address = address; return this; } // required

        public Builder copy(EmergencyContact emergencyContact) {
            this.emergencyContactId = emergencyContact.emergencyContactId;
            this.user = emergencyContact.user;
            this.firstName = emergencyContact.firstName;
            this.lastName = emergencyContact.lastName;
            this.phoneNumber = emergencyContact.phoneNumber;
            this.email = emergencyContact.email;
            this.relationship = emergencyContact.relationship;
            this.address = emergencyContact.address;
            return this;
        }

        public EmergencyContact build() {
            // Optional: enforce email and address presence in builder
            if (email == null || address == null) {
                throw new IllegalStateException("Email and address are required for EmergencyContact");
            }
            return new EmergencyContact(this);
        }
    }
}
