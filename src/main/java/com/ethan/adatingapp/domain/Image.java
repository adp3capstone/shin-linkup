package com.ethan.adatingapp.domain;
/* Image.java
Image model class
Author: Ethan Le Roux (222622172)
Date:11 May 2025
*/

import jakarta.persistence.*;

@Entity
@Table(name="image")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long imageId;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", unique = true, nullable = false)
    private User user;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] imageUrl;

    public Image(){}

    private Image(Builder builder) {
        this.imageId = builder.imageId;
        this.user = builder.user;
        this.imageUrl = builder.imageUrl;
    }

    public Long getImageId() {
        return imageId;
    }

    public User getUser() {
        return user;
    }

    public byte[] getImageUrl() {
        return imageUrl;
    }

    @Override
    public String toString() {
        return "Image{" +
                "imageId=" + imageId +
                ", user=" + user +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }

    public static class Builder{
        private Long imageId;
        private User user;
        private byte[] imageUrl;

        public Builder setImageId(Long imageId) {
            this.imageId = imageId;
            return this;
        }

        public Builder setUser(User user) {
            this.user = user;
            return this;
        }

        public Builder setImageUrl(byte[] imageUrl) {
            this.imageUrl = imageUrl;
            return this;
        }

        public Builder copy(Image image){
            this.imageId = image.imageId;
            this.user = image.user;
            this.imageUrl = image.imageUrl;
            return this;
        }

        public Image build(){
            return new Image(this);
        }
    }
}
