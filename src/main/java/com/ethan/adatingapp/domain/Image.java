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

    private Long userId;
    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] imageUrl;

    public Image(){}

    private Image(Builder builder) {
        this.imageId = builder.imageId;
        this.userId = builder.userId;
        this.imageUrl = builder.imageUrl;
    }

    public Long getImageId() {
        return imageId;
    }

    public Long getUserId() {
        return userId;
    }

    public byte[] getImageUrl() {
        return imageUrl;
    }

    @Override
    public String toString() {
        return "Image{" +
                "imageId=" + imageId +
                ", userId=" + userId +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }

    public static class Builder{
        private Long imageId;
        private Long userId;
        private byte[] imageUrl;

        public Builder setImageId(Long imageId) {
            this.imageId = imageId;
            return this;
        }

        public Builder setUserId(Long userId) {
            this.userId = userId;
            return this;
        }

        public Builder setImageUrl(byte[] imageUrl) {
            this.imageUrl = imageUrl;
            return this;
        }

        public Builder copy(Image image){
            this.imageId = image.imageId;
            this.userId = image.userId;
            this.imageUrl = image.imageUrl;
            return this;
        }

        public Image build(){
            return new Image(this);
        }
    }
}
