package org.activiti.examples.util;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author wangkai
 * @since JDK8
 */
public class MD5Util {
    public static final int times = 5;

    public static final String SALT = "spring-security";

    public static String md5(String password) {
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("MD5 algorithm not available.  Fatal (should be in the JDK).");
        }
        try {
            for (int i = 0; i < times; i++) {
                byte[] bytes = digest.digest((password + SALT).getBytes("UTF-8"));
                password = String.format("%032x", new BigInteger(1, bytes));
            }
            return password;
        } catch (UnsupportedEncodingException e) {
            throw new IllegalStateException("UTF-8 encoding not available.  Fatal (should be in the JDK).");
        }
    }

    public static void main(String[] args) {
        System.out.println(md5("123456"));
    }
}
