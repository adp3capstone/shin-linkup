package com.ethan.adatingapp.factory;
/* Image Factory.java
Author: Ethan Le Roux (222622172)
Date:18 May 2025
*/
import com.ethan.adatingapp.domain.Image;
import com.ethan.adatingapp.domain.User;
import com.ethan.adatingapp.util.Helper;

public class ImageFactory {
    public static Image createImage(
            User user,
            byte[] imageUrl
    ){
        if( user == null
                || imageUrl == null || imageUrl.length == 0
        ){
            return null;
        }
        return new Image.Builder()
                .setUser(user)
                .setImageUrl(imageUrl)
                .build();
    }
}
