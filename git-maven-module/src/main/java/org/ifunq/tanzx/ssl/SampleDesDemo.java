package org.ifunq.tanzx.ssl;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class SampleDesDemo {

    // 初始向量
    private static byte[] iv1 = { (byte) 0xEF, (byte) 0xAB, (byte) 0x56, (byte) 0x78, (byte) 0x90, (byte) 0x34, (byte) 0xCD, (byte) 0x12 };

    public static String decrypt(String decryptString, String decryptKey) throws Exception {
        IvParameterSpec iv = new IvParameterSpec(iv1);
        SecretKeySpec key = new SecretKeySpec(decryptKey.getBytes(), "DES");
        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, key, iv);

        return new String(cipher.doFinal(Base64.decodeBase64(decryptString)));
    }

    public static String encrypt(String encryptString, String encryptKey) throws Exception {
        IvParameterSpec iv = new IvParameterSpec(iv1);
        DESKeySpec dks = new DESKeySpec(encryptKey.getBytes());
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey key = keyFactory.generateSecret(dks);

        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, key, iv);

        return Base64.encodeBase64String(cipher.doFinal(encryptString.getBytes()));
    }

    public static void main(String[] args) throws Exception {
        String key = "GYPSYSH0";
        String str = "{\"state\":\"1\",\"msg\":\"成功\"}";
        String encryptData = encrypt(str, key);
        System.out.println("加密后: " + encryptData);
        String decryptData = decrypt(encryptData, key);
        System.out.println("解密后: " + decryptData);
    }
}
