package org.fruit.blueberry.util;

import org.apache.commons.codec.binary.Base64;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by AFei on 2014/7/26.
 */
public class EncryptionUtil {
    public static String encode(String message) {
        String result = null;

        if (message != null) {
            MessageDigest md = null;
            try {
                md = MessageDigest.getInstance("md5");
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }

            byte[] md5 = md.digest(message.getBytes());
            result = Base64.encodeBase64String(md5);
        }

        return result;
    }
}
