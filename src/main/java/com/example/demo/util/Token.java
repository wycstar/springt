package com.example.demo.util;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import java.security.SecureRandom;
import java.util.concurrent.ThreadLocalRandom;

import java.security.InvalidKeyException;
import java.security.MessageDigest;
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

    private String randomString() {
        /*
         * 生成一个六位的随机字符串
         */
        String s = alphanumericAlphabet();
        System.out.println(IntStream.range(0, 6)
                .mapToObj(c -> String.valueOf(s.charAt(ThreadLocalRandom.current().nextInt(s.length()))))
                .collect(Collectors.joining()));
    }

    public static String byteArrayHexDigest(byte[] input){
        // String result="";
        // for(byte b: input){
        //     result+="0x"+ String.format("%02x", arr[i]).toUpperCase()+" ";
        // }
        // return result.substring(0,result.length()-1);
        return IntStream.range(0, input.length).mapToObj(i -> String.format("%02x", input[i])).collect(Collectors.joining()));
    }

    private String generateToken() {
        try {
            KeyGenerator kg = KeyGenerator.getInstance("AES");
            kg.init(128, new SecureRandom("fuck_u_nvidia".getBytes()));
            SecretKey sk = kg.generateKey();
            SecretKeySpec key = new SecretKeySpec(sk.getEncoded(), "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] rawSecret = cipher.doFinal("fuck".getBytes());
            byte[] digest = md.digest(rawSecret);
            System.out.println(byteArray2Str(digest));
            return "a";
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "a";
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
            return "a";
        } catch (InvalidKeyException e) {
            e.printStackTrace();
            return "a";
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
            return "a";
        } catch (BadPaddingException e) {
            e.printStackTrace();
            return "a";
        }
    }
}
