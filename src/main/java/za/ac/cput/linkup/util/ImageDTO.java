package za.ac.cput.linkup.util;

public class ImageDTO {
    public Long userId;
    public Long imageId;
    public byte[] imageUrl;

    public ImageDTO(Long userId, Long imageId, byte[] imageUrl) {
        this.userId = userId;
        this.imageId = imageId;
        this.imageUrl = imageUrl;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getImageId() {
        return imageId;
    }

    public void setImageId(Long imageId) {
        this.imageId = imageId;
    }

    public byte[] getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(byte[] imageUrl) {
        this.imageUrl = imageUrl;
    }
}
