package org.ifunq.tanzx.jwt;

import io.jsonwebtoken.*;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class JjwtTest02 {
    public static void main(String[] args) throws InterruptedException {

        long now=System.currentTimeMillis();
        long exp=now+1000*10;//10秒过期

        JwtBuilder jwtBuilder = Jwts.builder().setId( "999" )
                .setIssuedAt( new Date() )//签发时间
                .setExpiration( new Date( exp ) )//过期时间
                .signWith( SignatureAlgorithm.HS256, "hahaha" );
        String token = jwtBuilder.compact();
        System.out.println(token);

        Claims claims = Jwts.parser().setSigningKey("hahaha").parseClaimsJws(token).getBody();
        System.out.println(claims);
        // 11秒后再次解析
        TimeUnit.SECONDS.sleep(11);
        claims = Jwts.parser().setSigningKey("hahaha").parseClaimsJws(token).getBody();
        System.out.println(claims);

    }
}
