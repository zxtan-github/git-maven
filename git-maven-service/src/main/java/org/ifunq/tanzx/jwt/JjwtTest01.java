package org.ifunq.tanzx.jwt;

import io.jsonwebtoken.*;

import java.util.Date;

public class JjwtTest01 {
    public static void main(String[] args) {
        JwtBuilder builder = Jwts.builder()
                // 设置Header头部信息
                .setHeaderParam("company", "ifunq")
                .setHeaderParam("type", "JWT")

                // 设置Playload载体信息
                .setId("999")   //设置唯一编号
                .setSubject("tanzongxi")//设置主题  可以是JSON数据
                .setIssuedAt(new Date())//设置签发日期
                // 设置自定义私有的声明
                .claim( "role","admin" )

                // 设置Sign签名信息
                .signWith(SignatureAlgorithm.HS256, "hahaha");//设置签名 使用HS256算法，并设置SecretKey(字符串)
        String token = builder.compact();
        // 整个JWT的token字符串
        System.out.println(token);

        // 解密Playload信息
        Claims claims = Jwts.parser().setSigningKey("hahaha").parseClaimsJws(token).getBody();
        System.out.println(claims);
        // 解密Header信息
        Header header = Jwts.parser().setSigningKey("hahaha").parseClaimsJws(token).getHeader();
        System.out.println(header);

    }
}
