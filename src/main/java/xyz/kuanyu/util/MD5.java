package xyz.kuanyu.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {
    public static String getMD5String(String str){

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            return new BigInteger(1,md.digest()).toString(16);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return null;
    }
}
