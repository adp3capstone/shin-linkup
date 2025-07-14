package za.ac.cput.linkup.domain;
/* Image.java
Image model class
Author: Ethan Le Roux (222622172)
Date:11 May 2025
*/

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="image")
public class Image {
    @Id
    private long imageId;
    private long userId;
    private String imageUrl;

    public Image(){}

    private Image(Builder builder) {
        this.imageId = builder.imageId;
        this.userId = builder.userId;
        this.imageUrl = builder.imageUrl;
    }

    public long getImageId() {
        return imageId;
    }

    public long getUserId() {
        return userId;
    }

    public String getImageUrl() {
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
        private long imageId;
        private long userId;
        private String imageUrl;

        public Builder setImageId(long imageId) {
            this.imageId = imageId;
            return this;
        }

        public Builder setUserId(long userId) {
            this.userId = userId;
            return this;
        }

        public Builder setImageUrl(String imageUrl) {
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
