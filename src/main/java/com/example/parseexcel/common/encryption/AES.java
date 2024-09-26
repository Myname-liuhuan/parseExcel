package com.example.parseexcel.common.encryption;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

/**
 * AES 加密解密
 */
public class AES {

    // AES 密钥（16字节 = 128位，24字节 = 192位，32字节 = 256位）// 16 字节密钥（AES-128）
    private static final String KEY = "1234567890123456"; 

    /**
     * 加密
     *
     * @param data 要加密的明文
     * @return 加密后的 Base64 编码的密文
     */
    public static String encrypt(String data) throws Exception {
        SecretKeySpec secretKey = new SecretKeySpec(KEY.getBytes(), "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedBytes = cipher.doFinal(data.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    /**
     * 解密
     *
     * @param encryptedData 加密的密文（Base64 编码的字符串）
     * @return 解密后的明文
     */
    public static String decrypt(String encryptedData) throws Exception {
        SecretKeySpec secretKey = new SecretKeySpec(KEY.getBytes(), "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedData));
        return new String(decryptedBytes);
    }

    public static void main(String[] args) throws Exception {
        String originalText = "Hello, AES!";
        System.out.println("原始文本: " + originalText);

        // 加密
        String encryptedText = AES.encrypt(originalText);
        System.out.println("加密后的文本: " + encryptedText);

        // 解密
        String decryptedText = AES.decrypt(encryptedText);
        System.out.println("解密后的文本: " + decryptedText);
    }
}
