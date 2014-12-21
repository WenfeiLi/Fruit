package org.fruit.blueberry.util;

import java.util.Random;

/**
 * Created by AFei on 2014/7/27.
 */
public class TokenUtil {
    public static String generateToken() {
        String token = System.currentTimeMillis() + new Random().nextInt() + "";
        return EncryptionUtil.encode(token);
    }


    public static boolean isTokenValid(String clientToken, String serverToken) {
        boolean isValid = false;

        if (clientToken != null && serverToken != null && serverToken.equals(clientToken)) {
            isValid = true;
        }

        return isValid;
    }
}
