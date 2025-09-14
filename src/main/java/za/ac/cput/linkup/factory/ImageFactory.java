package za.ac.cput.linkup.factory;
/* Image Factory.java
Author: Ethan Le Roux (222622172)
Date:18 May 2025
*/
import za.ac.cput.linkup.domain.Image;
import za.ac.cput.linkup.domain.User;

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
