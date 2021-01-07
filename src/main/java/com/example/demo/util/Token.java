package com.example.demo.util;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import java.util.Arrays;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.BadPaddingException;

public class Token {
    private String secret = "fuck_u_nvidia123";

    private static String alphanumericAlphabet() {
        return IntStream
                .concat(IntStream.rangeClosed('0', '9'),
                        IntStream.concat(IntStream.rangeClosed('A', 'Z'), IntStream.rangeClosed('a', 'z')))
                .mapToObj(c -> String.valueOf((char) c)).collect(Collectors.joining());
    }

    private static String randomString() {
        /*
         * 生成一个六位的随机字符串
         */
        String s = alphanumericAlphabet();
        return IntStream.range(0, 6)
                .mapToObj(c -> String.valueOf(s.charAt(ThreadLocalRandom.current().nextInt(s.length()))))
                .collect(Collectors.joining());
    }

    private static String byteArrayToHexDigest(byte[] input){
        return IntStream.range(0, input.length).mapToObj(i -> {return String.format("%02x", input[i]);}).collect(Collectors.joining());
    }

    private static byte[] hexDigestTobyteArray(String input){
        int strLen = input.length();
        if(strLen % 2 == 1) {
            return new byte[0];
        }
        byte[] output = new byte[strLen / 2];
        for (int i = 0; i < strLen / 2; i++) {
            output[i / 2] = (byte)(Character.digit(input.charAt(i), 16) << 4 | Character.digit(input.charAt(i + 1), 16));
        }
        return output;
    }

    private static String generateContent(String username) {
        Date date = new Date();
        return String.join("|", Arrays.asList(randomString(), String.valueOf(date.getTime() / 1000), username));
    }

    public String generateToken(String username) {
        try {
            SecretKeySpec key = new SecretKeySpec(this.secret.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            return byteArrayToHexDigest(cipher.doFinal(generateContent(username).getBytes()));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
            return "";
        } catch (InvalidKeyException e) {
            e.printStackTrace();
            return "";
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
            return "";
        } catch (BadPaddingException e) {
            e.printStackTrace();
            return "";
        }
    }

    public Boolean validateToken(String token) {
        try {
            SecretKeySpec key = new SecretKeySpec(this.secret.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, key);
            cipher.doFinal(hexDigestTobyteArray(token));
            return true;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return false;
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
            return false;
        } catch (InvalidKeyException e) {
            e.printStackTrace();
            return false;
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
            return false;
        } catch (BadPaddingException e) {
            e.printStackTrace();
            return false;
        }
    }

    public String getUsernameFromToken(String token) {
        try {
            SecretKeySpec key = new SecretKeySpec(this.secret.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, key);
            return new String(cipher.doFinal(hexDigestTobyteArray(token)), StandardCharsets.UTF_8);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
            return "";
        } catch (InvalidKeyException e) {
            e.printStackTrace();
            return "";
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
            return "";
        } catch (BadPaddingException e) {
            e.printStackTrace();
            return "";
        }
    }
}
