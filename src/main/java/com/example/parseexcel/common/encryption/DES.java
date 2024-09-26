package com.example.parseexcel.common.encryption;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.security.Key;
import java.util.Base64;
/**
 * DES加密解密
 */
public class DES {

    // DES 密钥（8字节 = 64位） /8 字节密钥（DES）
    private static final String KEY = "12345678"; 

    /**
     * 加密
     *
     * @param data 要加密的明文
     * @return 加密后的 Base64 编码的密文
     */
    public static String encrypt(String data) throws Exception {
        DESKeySpec desKeySpec = new DESKeySpec(KEY.getBytes());
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        Key secretKey = keyFactory.generateSecret(desKeySpec);
        Cipher cipher = Cipher.getInstance("DES");
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
        DESKeySpec desKeySpec = new DESKeySpec(KEY.getBytes());
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        Key secretKey = keyFactory.generateSecret(desKeySpec);
        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedData));
        return new String(decryptedBytes);
    }

    public static void main(String[] args) throws Exception {
        String originalText = "Hello, DES!";
        System.out.println("原始文本: " + originalText);

        // 加密
        String encryptedText = DES.encrypt(originalText);
        System.out.println("加密后的文本: " + encryptedText);

        // 解密
        String decryptedText = DES.decrypt(encryptedText);
        System.out.println("解密后的文本: " + decryptedText);
    }
}
