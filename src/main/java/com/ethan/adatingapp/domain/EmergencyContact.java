package com.ethan.adatingapp.domain;

import jakarta.persistence.*;

@Entity
public class EmergencyContact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long contactId;
    @ManyToOne
    private User user;
    private String name;
    private String phoneNumber;

    public EmergencyContact() {}

    private EmergencyContact(Builder builder) {
        this.contactId = builder.contactId;
        this.user = builder.user;
        this.name = builder.name;
        this.phoneNumber = builder.phoneNumber;
    }

    public Long getContactId() {
        return contactId;
    }

    public User getUser() {
        return user;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setContactId(Long contactId) {
        this.contactId = contactId;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public static class Builder {
        private Long contactId;
        private User user;
        private String name;
        private String phoneNumber;
        private String email;

        public Builder setContactId(Long contactId) {
            this.contactId = contactId;
            return this;
        }

        public Builder setUser(User user) {
            this.user = user;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder copy(EmergencyContact contact) {
            this.contactId = contact.contactId;
            this.user = contact.user;
            this.name = contact.name;
            this.phoneNumber = contact.phoneNumber;
            return this;
        }

        public EmergencyContact build() {
            return new EmergencyContact(this);
        }
    }
}
