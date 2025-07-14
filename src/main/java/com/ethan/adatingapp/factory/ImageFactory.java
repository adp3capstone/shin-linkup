package com.ethan.adatingapp.factory;
/* Image Factory.java
Author: Ethan Le Roux (222622172)
Date:18 May 2025
*/
import com.ethan.adatingapp.domain.Image;
import com.ethan.adatingapp.util.Helper;

public class ImageFactory {
    public static Image createImage(
            long imageId,
            long userId,
            byte[] imageUrl
    ){
        if(
                !Helper.isValidLong(imageId)
                || !Helper.isValidLong(userId)
                || imageUrl == null || imageUrl.length == 0
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
