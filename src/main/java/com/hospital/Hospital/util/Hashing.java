package com.hospital.Hospital.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


/**
 *
 * Hashing utility class
 *
 */
public class Hashing {
    public static String hashMD5(String input) {
        String result = "";
        try {
            MessageDigest m = MessageDigest.getInstance("MD5");
            m.update(input.getBytes(), 0, input.length());
            result = new BigInteger(1, m.digest()).toString(16);
            return result.toUpperCase();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return result;
    }
}
