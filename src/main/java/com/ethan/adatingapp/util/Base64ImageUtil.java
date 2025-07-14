package com.ethan.adatingapp.util;

import java.util.Base64;

public class Base64ImageUtil {
    public static byte[] convertBase64ToBytes(String base64DataUrl) {
        if (base64DataUrl == null || !base64DataUrl.startsWith("data:image/")) {
            throw new IllegalArgumentException("Invalid base64 image data URL");
        }

        // Remove the prefix: data:image/png;base64,
        String base64Image = base64DataUrl.substring(base64DataUrl.indexOf(",") + 1);

        // Decode to byte[]
        return Base64.getDecoder().decode(base64Image);
    }
}
