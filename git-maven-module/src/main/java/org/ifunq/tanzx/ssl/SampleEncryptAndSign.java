package org.ifunq.tanzx.ssl;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import sun.security.x509.X509CertImpl;

import javax.crypto.Cipher;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.security.*;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * 公钥和私钥的签名和加密
 *
 * @author tanzx [tanzongxi@ifunq.com]
 * @date 2018-02-06 15:54
 **/
public class SampleEncryptAndSign {

    // 证书密码
    private static final String PASSWORD = "123456";
    // 证书别名
    private static final String ALIAS = "test1";
    /** 证书Base64字符串 */
    public  static final String PUBLIC_CER ="MIIDSzCCAjOgAwIBAgIETf5kajANBgkqhkiG9w0BAQsFADBVMQswCQYDVQQGEwJjbjELMAkGA1UE" +
                                            "CBMCY3ExCzAJBgNVBAcTAmNuMQ4wDAYDVQQKEwVpZnVucTEOMAwGA1UECxMFaWZ1bnExDDAKBgNV" +
                                            "BAMTA3RhbjAgFw0xODAyMDYwMjA0NDVaGA8yMTE2MDgzMTAyMDQ0NVowVTELMAkGA1UEBhMCY24x" +
                                            "CzAJBgNVBAgTAmNxMQswCQYDVQQHEwJjbjEOMAwGA1UEChMFaWZ1bnExDjAMBgNVBAsTBWlmdW5x" +
                                            "MQwwCgYDVQQDEwN0YW4wggEiMA0GCSqGSIb3DQEBAQUAA4IBDwAwggEKAoIBAQC23W/CTQ6TQIoU" +
                                            "UcHsiV5MNGOIXX6j3jJbncHCk4dfjytiz8kaN1FSJqkJFqq/HMdNjH3cZnpOJlZrrSFR3VyJ+fcz" +
                                            "fIlAfclLAc7K3RoTuymK/5Z+q67Rfn9EiFwWXA23XT1zLh9lVQCZ78DaR9Q63z1TH9RCYQ22ixpe" +
                                            "FPbjW5k0zHQcD+S1CZzO1qpdX35a7NN+E9/gOH2Acjux/NeWXZz2P2WZq/eS8vS9STy95aA8fOjA" +
                                            "ds7u7sDf1bTSPxWgF7PnUrJ20rUZknErDzJ7SqyyUg0GCJ38m5f4gKsWagvAkdkCRanIz27b+xKX" +
                                            "Vd3hEp0uCkYpzWC43ZCnrBg1AgMBAAGjITAfMB0GA1UdDgQWBBRukrwNDhDOjGz9TS+9GjA0iriO" +
                                            "7DANBgkqhkiG9w0BAQsFAAOCAQEAMLwvhAudORGBrDHwIc9Hmgvjpbl+octx224v/yUXPI1QtA/u" +
                                            "e56GG48D1nViiqgQMUe2aWQfgeI3L/wKFDOk1lc92TvxZUHReIYRM91lioC7AVY8mAhW/VRxMPoJ" +
                                            "uu96vE+dHW/ApcmX2Fdl3WfkiEIh049xSjpC7LGYyqww5QQc+FgYrBciuNxZt4JAhzom42WDber0" +
                                            "kmnOdQJlPHq5TS/2EgMRF6t90THsFzzifuK310gRA9eal61UbLlxZKuc8hyjj1YPXryIWs/JWZIY" +
                                            "zbfkExqUJLYn0wmQ6No36dETZfpnW7AEX/kYm5lT4o3c0bLxYjThn1SlmoqoD+S3LA==";
    // 公钥Base64字符串
    private static final String PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAtt1vwk0Ok0CKFFHB7IleTDRjiF1+o94" +
            "yW53BwpOHX48rYs/JGjdRUiapCRaqvxzHTYx93GZ6TiZWa60hUd1cifn3M3yJQH3JSwHOyt0aE7spiv+Wfquu0X5/RIhcFlwNt109cy4fZVU" +
            "Ame/A2kfUOt89Ux/UQmENtosaXhT241uZNMx0HA/ktQmcztaqXV9+WuzTfhPf4Dh9gHI7sfzXll2c9j9lmav3kvL0vUk8veWgPHzowHbO7u7" +
            "A39W00j8VoBez51KydtK1GZJxKw8ye0qsslINBgid/JuX+ICrFmoLwJHZAkWpyM9u2/sSl1Xd4RKdLgpGKc1guN2Qp6wYNQIDAQAB";
    // 私钥Base64字符串
    private static final String PRIVATE_KEY = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQC23W/CTQ6TQIoUUcHsiV5MN" +
            "GOIXX6j3jJbncHCk4dfjytiz8kaN1FSJqkJFqq/HMdNjH3cZnpOJlZrrSFR3VyJ+fczfIlAfclLAc7K3RoTuymK/5Z+q67Rfn9EiFwWXA23" +
            "XT1zLh9lVQCZ78DaR9Q63z1TH9RCYQ22ixpeFPbjW5k0zHQcD+S1CZzO1qpdX35a7NN+E9/gOH2Acjux/NeWXZz2P2WZq/eS8vS9STy95aA" +
            "8fOjAds7u7sDf1bTSPxWgF7PnUrJ20rUZknErDzJ7SqyyUg0GCJ38m5f4gKsWagvAkdkCRanIz27b+xKXVd3hEp0uCkYpzWC43ZCnrBg1Ag" +
            "MBAAECggEAN81lEFbxbrAQMtksI2Fb/Ec0ZGWbgqgIl/qRKS5Z7ihDZhjgVnaxAy+2/4YCPuWqUBjiQ4KmKfj7AErJ0zCIz7YO4JBNVffMm" +
            "G4Jy9Ai6AiCSd9q7ma3p0hB0AkIiO44ZU83tFFL1WFWGKOYJ0QqGHkNPrOE02jIhX73YeRl3sUMOeyEx16ELJ+rHJJs+/8DMexeeL0v+Iln" +
            "y9obNujtvBlMlDrP8h5F3LGVfivV6B02+goKXT1sDy/ZSdTTd2O+agI+hdI8TYO9DG3WsPN7hZIluFG0AEdYOrz+7V7w2Pad2Fr0S4kO62R" +
            "uyVePVvhmNq8kuYwOaV0h5vFo0jM+YQKBgQD+RTeCB3C5K2rauWZOP2cmIIldyMuJgpmO5bcyxCIj3wGGiqCwhfQpONO6UYgNu5w9bongey" +
            "+OMmFAbm6KPhJo37awm6VNF5V5wOreAJbipa5nw1w26u58IgviDU08NjfbjcxqtwMn8O2OKMHP/YKMUPGKCWvcYV/7QeuLX89P/QKBgQC4G" +
            "+ATjj/p3lY4ZaDHDFf5zG/E2mVfeHI3FA5q2pMgGVKUsSKdnUVXzkTvvr/w41iQ/GcT5x6bZUq9hNh/11LwPVfahlU8g/1CgzSSh0+PtXws" +
            "ocEUsDznswr8LUD/vej1UNRmNeQYvohVwITj8jeb+Ni+9Qqn7LXOYLGi7POSmQKBgQDt4NONUmii03ukJM8RZT+a69WXZ8MDYqtGhKJfXB6" +
            "UcI180TSD8M37wq5SIPSn5FzHHCLhYF2dKoTRQn8n1epjMug3UWXUObwdq94O035Vi6seaCZdOmzitPdpBAi9y/t167LSI2VQeJXF6Vtouk" +
            "LoAtH10dqW0oJ5DbLPMVNCAQKBgGzdxt2pcu+20mMc7TpmeSs0ES9lOSaAEYGyMWhMAU2sy6EzMVXkJpnqVecyc0tdFAT/y3AAbZ/UAKMah" +
            "+7og5mdnHKSlppnLf4DmbW/n3NKDLhTmdnC8PDNdYRyugm/4Z4WAtLBsTRUjkeuxKYJl4p6VqhsO7XaihMUKFZAKTOZAoGBAMaMAl/vnvvI" +
            "8dPFkSFeFCLU9aJJv8G7aQA37NCVwl/gSV+khWqnCp95eDwp1p27bUK5G++BKdgc1G1dxIEWJZ4tLmTENi5xfOsNmuU1BDIl3oPIQRDzajU" +
            "hAcoHCjys7Agmkp10C35kPHLeh9v83HkxinZ7uy5W0INYJ9AqsR8/";



    public static void main(String[] args) throws Exception {

        KeyStore keyStore = KeyStore.getInstance("JKS");
        keyStore.load(new FileInputStream("D:/ifunq/支付申报/宝付/ssl/test.keystore"), PASSWORD.toCharArray());

        // 方法一：从keystore中获取证书
        // Certificate x509Certificate = keyStore.getCertificate(ALIAS);

        // 方法二：直接读取证书文件
        CertificateFactory certificate_factory = CertificateFactory.getInstance("X.509");
        FileInputStream file_inputstream = new FileInputStream("D:/ifunq/支付申报/宝付/ssl/test1.cer");
        X509Certificate x509Certificate = (X509Certificate) certificate_factory.generateCertificate(file_inputstream);

        System.out.println("=====================================公钥私钥加解密=========================================");
        // 从证书中获取公钥和从keystore中获取私钥
        // 公钥加密，私钥解密
        encrypt(x509Certificate.getPublicKey(), (PrivateKey) keyStore.getKey(ALIAS, PASSWORD.toCharArray()));

        System.out.println("=====================================签名验证=========================================");
        // 签名验证
        sign(keyStore);

        System.out.println("=====================================字符中提取公钥私钥=========================================");
        // 证书字符串获取公钥
        // PublicKey publicKey = getPublicKeyByCer(PUBLIC_CER);
        // 公钥字符串获取公钥
        PublicKey publicKey = getPublicKey(PUBLIC_KEY);
        // 私钥字符串获取私钥
        PrivateKey privateKey = getPrivateKey(PRIVATE_KEY);
        // 私钥加密，公钥解密
        encryptReverse(publicKey, privateKey);
    }

    /**
     * 签名和验证签名
     *
     * @throws Exception
     */
    public static void sign(KeyStore keyStore) throws Exception {

        X509Certificate x509Certificate = (X509Certificate) keyStore.getCertificate(ALIAS);
        //需要签名的信息的内容
        String message = "中国移动通信研究院";
        //获取CA证书私钥
        PrivateKey priKey = (PrivateKey) keyStore.getKey(ALIAS, PASSWORD.toCharArray());
        System.out.println("私钥Base64:" + Base64.encodeBase64String(priKey.getEncoded()));
        System.out.println("私钥:" + Hex.encodeHexString(priKey.getEncoded()));

        //用私钥签名
        Signature signature = Signature.getInstance("NONEwithRSA");
        signature.initSign(priKey);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        dataOutputStream.writeUTF(message);
        signature.update(byteArrayOutputStream.toByteArray());
        String result = Hex.encodeHexString(signature.sign());
        System.out.println("签名之后的内容:" + result);


        //用公钥来验证签名
        Signature signature1 = Signature.getInstance("NONEwithRSA");
        //从证书中获取公钥
        signature1.initVerify(x509Certificate.getPublicKey());
        System.out.println("证书Base64:" + new String(Base64.encodeBase64(x509Certificate.getEncoded())));
        System.out.println("公钥Base64:" + Base64.encodeBase64String(x509Certificate.getPublicKey().getEncoded()));
        System.out.println("公钥:" + Hex.encodeHexString(x509Certificate.getPublicKey().getEncoded()));
        ByteArrayOutputStream byteArrayOutputStream1 = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream1 = new DataOutputStream(byteArrayOutputStream1);
        dataOutputStream1.writeUTF(message);
        signature1.update(byteArrayOutputStream1.toByteArray());

        System.out.println("验证结果:   " + signature1.verify(Hex.decodeHex(result.toCharArray())));
    }

    /**
     * 公钥加密，私钥解密
     *
     * @param publicKey
     * @param privateKey
     * @throws Exception
     */
    public static void encrypt(PublicKey publicKey, PrivateKey privateKey) throws Exception {

        String input = "慧与(中国)有限公司";
        Cipher cipher = Cipher.getInstance("RSA");
        RSAPublicKey pubKey = (RSAPublicKey) publicKey;
        cipher.init(Cipher.ENCRYPT_MODE, pubKey);
        byte[] cipherText = cipher.doFinal(input.getBytes());
        //加密后的内容
        System.out.println("加密之后的内容:" + Hex.encodeHexString(cipherText));


        //解密
        RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) privateKey;
        cipher.init(Cipher.DECRYPT_MODE, rsaPrivateKey);
        byte[] plainText = cipher.doFinal(cipherText);
        System.out.println("解密之后的内容 : " + new String(plainText));

    }


    /**
     * 私钥加密，公钥解密
     *
     * @param publicKey
     * @param privateKey
     * @throws Exception
     */
    public static void encryptReverse(PublicKey publicKey, PrivateKey privateKey) throws Exception {

        String input = "慧与(中国)有限公司";
        Cipher cipher = Cipher.getInstance("RSA");
        RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) privateKey;
        cipher.init(Cipher.ENCRYPT_MODE, rsaPrivateKey);
        byte[] cipherText = cipher.doFinal(input.getBytes());
        //加密后的内容
        System.out.println("加密之后的内容:" + Hex.encodeHexString(cipherText));


        //解密
        RSAPublicKey pubKey = (RSAPublicKey) publicKey;
        cipher.init(Cipher.DECRYPT_MODE, pubKey);
        byte[] plainText = cipher.doFinal(cipherText);
        System.out.println("解密之后的内容 : " + new String(plainText));

    }


    /**
     * 将base64编码后的公钥字符串转成PublicKey实例
     *
     * @param publicKey 公钥base64字符
     * @return
     * @throws Exception
     */
    public static PublicKey getPublicKey(String publicKey) throws Exception{
        byte[] keyBytes= Base64.decodeBase64(publicKey.getBytes());
        X509EncodedKeySpec keySpec=new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory=KeyFactory.getInstance("RSA");
        return keyFactory.generatePublic(keySpec);
    }

    /**
     * 将base64编码后的私钥字符串转成PrivateKey实例
     *
     * @param privateKey 私钥base64字符
     * @return
     * @throws Exception
     */
    public static PrivateKey getPrivateKey(String privateKey) throws Exception{
        byte[] keyBytes=Base64.decodeBase64(privateKey.getBytes());
        PKCS8EncodedKeySpec keySpec=new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory=KeyFactory.getInstance("RSA");
        return keyFactory.generatePrivate(keySpec);
    }


    /**
     * 将base64编码后的证书字符串转成PublicKey实例
     *
     * @param cer 证书base64字符
     * @return
     * @throws Exception
     */
    public static PublicKey getPublicKeyByCer(String cer) throws Exception{
        byte[] keyBytes= Base64.decodeBase64(cer.getBytes());
        X509CertImpl x509Cert = new X509CertImpl(keyBytes);
        return x509Cert.getPublicKey();
    }
}