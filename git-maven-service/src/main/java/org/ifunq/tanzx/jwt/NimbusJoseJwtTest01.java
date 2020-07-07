package org.ifunq.tanzx.jwt;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jose.crypto.RSASSAVerifier;
import com.nimbusds.jose.jwk.RSAKey;
import org.apache.commons.codec.binary.Base64;

import java.security.KeyFactory;
import java.security.interfaces.RSAPrivateCrtKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.text.ParseException;

public class NimbusJoseJwtTest01 {
    public static void main(String[] args) throws Exception {
        // secret必须是256bit，就去64的16进制数，比如MD5校验码
        String token = generateTokenByHMAC("{'name':'tanzongxi','city':'shanghai'}", "e49364e4d990970b3de785ed92d51d02");
        System.out.println("token->" + token);
        // 对称加密不合法校验
        System.out.println(verifyTokenByHMAC(token + "123", "e49364e4d990970b3de785ed92d51d02"));
        // 对称加密合法校验
        System.out.println(verifyTokenByHMAC(token, "e49364e4d990970b3de785ed92d51d02"));

        //获取RSA私钥
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        RSAPrivateCrtKey privateKey = (RSAPrivateCrtKey) keyFactory.generatePrivate(new PKCS8EncodedKeySpec(
                Base64.decodeBase64(PRIKEY)));
        //获取RSA公钥
        RSAPublicKey publicKey = (RSAPublicKey) keyFactory.generatePublic(new X509EncodedKeySpec(
                Base64.decodeBase64(PUBKEY)));
        RSAKey rsaKey = new RSAKey.Builder(publicKey).privateKey(privateKey).build();
        token = generateTokenByRSA("{'name':'tangyan','city':'chongqing'}", rsaKey);
        System.out.println("token->" + token);
        // 非对称加密不合法校验
        System.out.println(verifyTokenByRSA(token + "123", rsaKey));
        // 非对称加密合法校验
        System.out.println(verifyTokenByRSA(token, rsaKey));

    }

    public static final String PRIKEY = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAMYOAjLhi2y/jHjyCg88ASBYx/b5MsDeowfg0G+PH2zquDG4wUsMQ2s8PsEnW3YR8Ay+b0SVafM4Z0/dmhrVPfRskZ/evAemWICupb0Cl+c3JAoFEJHI13E7bU4wvEW6FWosJYYi3Fewu1q6GseXNo5R6pY1RDcxWnc3APTVfeJ3AgMBAAECgYEAjJQo72C0jE4yMS7sAQBAdGtQFHexlMDyy4bBrE3UqoS4lWJkoRZlTFqI2KVNADOfRgxuAipj9+XTPxc2aVFvFSxppcVxD1jxuK7+czIlhLDKRxfnB4xyP6Uq8/LEMAsIICyQqgcdULzh7EhLaUZGbF1PzGRmLR4+czEb+0Hxb9ECQQD3FltbrLc6Zic4KvuGyhyjDFMsMZg1msxxwKjA3KuuraYyGpqXATU03RrNS/4Bz7U8a6w+x6S5IK2Dii0cb3TdAkEAzTLgfW5RXkJ9YlIFDJcg48NnuHH4OWcsGtU+ODwg/Qp6l7eQjo5jIokskw0vhDYV8LjgMB/d+5zT0YQpe6LlYwJAdzTACrB/DZuvHDgQdW0Y98jAb7fVmTb/n3m1cKaF5ZY9cjrHjka05rYtchJRj4ooA+hmhztGS1Jqo+WL8gYLuQJAAkRRnpEdbQEnCXMGg0EYJ+v8tVjs+RVes10vtsdTRfhbqlloy2pfRf5l86ntoHRIgcRJFb8EYMc2v9XJ74e5KQJBAOynS5qDAGk4jfyd9wsh2BKCuXAlKUH94U/LM6ulaHBbJo1oM3EPvqGEWMEreKyMWgEamob/PsTujMYTJaEuCx0=";
    public static final String PUBKEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDGDgIy4Ytsv4x48goPPAEgWMf2+TLA3qMH4NBvjx9s6rgxuMFLDENrPD7BJ1t2EfAMvm9ElWnzOGdP3Zoa1T30bJGf3rwHpliArqW9ApfnNyQKBRCRyNdxO21OMLxFuhVqLCWGItxXsLtauhrHlzaOUeqWNUQ3MVp3NwD01X3idwIDAQAB";


    public static String generateTokenByHMAC(String payloadStr, String secret) throws JOSEException {
        //创建JWS头，设置签名算法和类型
        JWSHeader jwsHeader = new JWSHeader.Builder(JWSAlgorithm.HS256).
                type(JOSEObjectType.JWT)
                .build();
        //将负载信息封装到Payload中
        Payload payload = new Payload(payloadStr);
        //创建JWS对象
        JWSObject jwsObject = new JWSObject(jwsHeader, payload);
        //创建HMAC签名器
        JWSSigner jwsSigner = new MACSigner(secret);
        //签名
        jwsObject.sign(jwsSigner);
        return jwsObject.serialize();
    }

    public static String verifyTokenByHMAC(String token, String secret) throws ParseException, JOSEException {
        //从token中解析JWS对象
        JWSObject jwsObject = JWSObject.parse(token);
        //创建HMAC验证器
        JWSVerifier jwsVerifier = new MACVerifier(secret);
        if (!jwsObject.verify(jwsVerifier)) {
            return "token签名不合法！";
        }
        String payload = jwsObject.getPayload().toString();
        return payload;
    }


    public static String generateTokenByRSA(String payloadStr, RSAKey rsaKey) throws JOSEException {
        //创建JWS头，设置签名算法和类型
        JWSHeader jwsHeader = new JWSHeader.Builder(JWSAlgorithm.RS256)
                .type(JOSEObjectType.JWT)
                .build();
        //将负载信息封装到Payload中
        Payload payload = new Payload(payloadStr);
        //创建JWS对象
        JWSObject jwsObject = new JWSObject(jwsHeader, payload);
        //创建RSA签名器
        JWSSigner jwsSigner = new RSASSASigner(rsaKey, true);
        //签名
        jwsObject.sign(jwsSigner);
        return jwsObject.serialize();
    }

    public static String verifyTokenByRSA(String token, RSAKey rsaKey) throws ParseException, JOSEException {
        //从token中解析JWS对象
        JWSObject jwsObject = JWSObject.parse(token);
        RSAKey publicRsaKey = rsaKey.toPublicJWK();
        //使用RSA公钥创建RSA验证器
        JWSVerifier jwsVerifier = new RSASSAVerifier(publicRsaKey);
        if (!jwsObject.verify(jwsVerifier)) {
            return "token签名不合法！";
        }
        String payload = jwsObject.getPayload().toString();
        return payload;
    }
}
