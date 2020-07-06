package com.cn.uk.common.utils;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Date;
import java.util.Random;

public class SecurityUtil {

    /**
     * MD5加密
     *
     * @param s
     * @return
     */
    public static String MD5(String s) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] bytes = md.digest(s.getBytes("utf-8"));
            return toHex(bytes);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 字节数组转十六进制字符串
     * @param bytes
     * @return
     */
    public static String toHex(byte[] bytes) {
        if (bytes == null)
            return null;

        final char[] HEX_DIGITS = "0123456789ABCDEF".toCharArray();
        StringBuilder ret = new StringBuilder(bytes.length * 2);
        for (int i = 0; i < bytes.length; i++) {
            ret.append(HEX_DIGITS[(bytes[i] >> 4) & 0x0f]);
            ret.append(HEX_DIGITS[bytes[i] & 0x0f]);
        }
        return ret.toString();
    }

    /**
     * 十六进制字符串转字节数组
     * @param hexStr
     * @return
     */
    public static byte[] hexToBytes(String hexStr){
        if (hexStr == null || hexStr.isEmpty())
            return null;
        byte[] result = new byte[hexStr.length()/2];
        for (int i = 0;i< hexStr.length()/2; i++) {
            int high = Integer.parseInt(hexStr.substring(i*2, i*2+1), 16);
            int low = Integer.parseInt(hexStr.substring(i*2+1, i*2+2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }

    /**
     * AES加密
     * @param str
     * @param key
     * @return
     */
    public static byte[] aesEncrypt(String str, String key){
        try {
            KeyGenerator aesGen = KeyGenerator.getInstance("AES");// 创建AES的Key生产者
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            secureRandom.setSeed(key.getBytes("UTF-8"));// 利用用户密码作为随机数初始化出

            aesGen.init(128, secureRandom);// 128位的key生产者

            SecretKey secretKey = aesGen.generateKey();// 根据用户密码，生成一个密钥
            byte[] keyEncode = secretKey.getEncoded(); // 返回基本编码格式的密钥，如果此密钥不支持编码，则返回 null
            SecretKeySpec secretKeySpec = new SecretKeySpec(keyEncode, "AES");// 转换为AES专用密钥

            Cipher cipher = Cipher.getInstance("AES"); // 创建密码器
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec); //设置加密模式

            byte[] bytes = str.getBytes("UTF-8");
            byte[] result = cipher.doFinal(bytes); //加密

            return result;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * AES解密
     * @param bytes
     * @param key
     * @return
     */
    public static byte[] aesDecrypt(byte[] bytes, String key){
        try {
            KeyGenerator aesGen = KeyGenerator.getInstance("AES");// 创建AES的Key生产者
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            secureRandom.setSeed(key.getBytes("UTF-8"));// 利用用户密码作为随机数初始化出

            aesGen.init(128, secureRandom);// 128位的key生产者

            SecretKey secretKey = aesGen.generateKey();// 根据用户密码，生成一个密钥
            byte[] keyEncode = secretKey.getEncoded(); // 返回基本编码格式的密钥，如果此密钥不支持编码，则返回 null
            SecretKeySpec secretKeySpec = new SecretKeySpec(keyEncode, "AES");// 转换为AES专用密钥

            Cipher cipher = Cipher.getInstance("AES"); // 创建密码器
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec); //设置解密模式

            byte[] result = cipher.doFinal(bytes); //加密

            return result;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * AES加密成十六进制字符串
     * @param str
     * @param key
     * @return
     */
    public static String aesEncryptToHex(String str, String key){
        byte[] result = aesEncrypt(str, key);

        return toHex(result);
    }

    /**
     * AES 解密十六进制字符串
     * @param str
     * @param key
     * @return
     */
    public static byte[] aesDecryptFromHex(String str, String key){
        byte[] bytes = hexToBytes(str);

        if (bytes == null)
            return null;

        return aesDecrypt(bytes, key);
    }

    public static String sha1Encrypt(String strToEncrypt) {
        String signature = "";

        try {
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(strToEncrypt.getBytes("UTF-8"));
            signature = toHex(crypt.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return signature;
    }


    /**
     * 生成随机字符串
     * @param length
     * @return
     */
    public static String getRandomString(int length){
        String str="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random=new Random();
        StringBuffer sb=new StringBuffer();
        for(int i=0;i<length;i++){
            int number=random.nextInt(62);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }

    /**
     * 获取当前时间秒级时间戳
     * @return
     */
    public static String getTimestamp(){
        long timestamp = new Date().getTime();
        int timeStampSecond = (int)(timestamp/1000);
        return String.valueOf(timeStampSecond);

    }

    public static void main(String[] args){

        System.out.println("秒级时间戳:"+getTimestamp());
        System.out.println("randomString:"+getRandomString(6));
        System.out.println("sign:"+MD5("random123123123"));
    }
}
