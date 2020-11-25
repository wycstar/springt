package com.example.demo.util;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import java.util.Arrays;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.BadPaddingException;

public class Token {
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

    private static byte[] HexDigestTobyteArray(String input){
        
    }

    private static String generateContent(String username) {
        Date date = new Date();
        return String.join("|", Arrays.asList(randomString(), String.valueOf(date.getTime() / 1000), username));
    }

    public String generateToken(String username) {
        try {
            SecretKeySpec key = new SecretKeySpec("fuck_u_nvidia123".getBytes(), "AES");
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
            SecretKeySpec key = new SecretKeySpec("fuck_u_nvidia123".getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, key);
            cipher.doFinal(HexDigestTobyteArray(token));
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
}
