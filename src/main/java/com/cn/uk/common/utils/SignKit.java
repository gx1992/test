package com.cn.uk.common.utils;


import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Map;

/**
 * 生成请求签名
 *
 * @author <a href="mailto:maxid@qq.com">ZengHan</a>
 * @since $$Id$$
 */
public class SignKit {

    public static String signCaopRequest(Map<String, String> params, String secret, String signMethod) throws IOException {
        // 第一步：检查参数是否已经排序
        String[] keys = params.keySet().toArray(new String[0]);
        Arrays.sort(keys);

        // 第二步：把所有参数名和参数值串在一起
        StringBuilder query = new StringBuilder();
        if (Constants.SIGN_METHOD_MD5.equals(signMethod)) {
            query.append(secret);
        }
        for (String key : keys) {
            String value = params.get(key);
            if (StringKit.areNotEmpty(key, value)) {
                query.append(key).append(value);
            }
        }

        // 第三步：使用MD5/HMAC加密
        byte[] bytes;
        if (Constants.SIGN_METHOD_HMAC.equals(signMethod)) {
            bytes = encryptHMAC(query.toString(), secret);
        } else {
            query.append(secret);
            bytes = encryptMD5(query.toString());
        }

        // 第四步：把二进制转化为大写的十六进制
        return byte2hex(bytes);
    }

    /**
     * 用HMAC进行摘要
     * @param data
     * @param secret
     * @return
     * @throws IOException
     */
    public static byte[] encryptHMAC(String data, String secret) throws IOException {
        byte[] bytes = null;
        try {
            SecretKey secretKey = new SecretKeySpec(secret.getBytes(Constants.CHARSET_UTF8), "HmacMD5");
            Mac mac = Mac.getInstance(secretKey.getAlgorithm());
            mac.init(secretKey);
            bytes = mac.doFinal(data.getBytes(Constants.CHARSET_UTF8));
        } catch (GeneralSecurityException gse) {
            throw new IOException(gse.toString());
        }
        return bytes;
    }

    /**
     * 对字符串采用UTF-8编码后，用MD5进行摘要。
     * @param data
     * @return
     * @throws IOException
     */
    public static byte[] encryptMD5(String data) throws IOException {
        return encryptMD5(data.getBytes(Constants.CHARSET_UTF8));
    }

    /**
     * 对字节流进行MD5摘要。
     * @param data
     * @return
     * @throws IOException
     */
    public static byte[] encryptMD5(byte[] data) throws IOException {
        byte[] bytes = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            bytes = md.digest(data);
        } catch (GeneralSecurityException gse) {
            throw new IOException(gse.toString());
        }
        return bytes;
    }

    public static String byte2hex(byte[] bytes) {
        StringBuilder sign = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(bytes[i] & 0xFF);
            if (hex.length() == 1) {
                sign.append("0");
            }
            sign.append(hex.toUpperCase());
        }
        return sign.toString();
    }
}
