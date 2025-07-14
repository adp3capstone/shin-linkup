package za.ac.cput.linkup.factory;
/* Image Factory.java
Author: Ethan Le Roux (222622172)
Date:18 May 2025
*/
import za.ac.cput.linkup.domain.Image;
import za.ac.cput.linkup.util.Helper;

public class ImageFactory {
    public static Image createImage(
            long imageId,
            long userId,
            String imageUrl
    ){
        if(
                !Helper.isValidLong(imageId)
                || !Helper.isValidLong(userId)
                || Helper.isStringNullOrEmpty(imageUrl)
        ){
            return null;
        }
        return new Image.Builder()
                .setImageId(imageId)
                .setUserId(userId)
                .setImageUrl(imageUrl)
                .build();
    }
}
