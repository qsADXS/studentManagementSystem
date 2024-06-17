package com.example.studentmanagementsystem.util;

import io.jsonwebtoken.*;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Data
@Component
/*
* 只要知道这个是个工具类用来生成jwt的，和你们的编程没太大关系，不需要看
* */
public class JwtUtils {

    @Value("${jwt.expiration}")
    public void setExpiration(long expiration) {
        JwtUtils.expiration = expiration;
    }
    private static long expiration;
    @Value("${jwt.secret}")
    public void setSecret(String secret) {
        JwtUtils.secret = secret;
        System.out.println(secret);
    }
    private static String secret;
    public void setTokenHeader(String tokenHeader) {
        JwtUtils.tokenHeader = tokenHeader;
    }
    @Value("${jwt.tokenHeader}")
    private static String tokenHeader;

    // 生成JWT
    public static String generateToken(String id,int level) {
        Date nowDate = new Date();
        Date expireDate = new Date(nowDate.getTime() + expiration);
        Map<String, Object> claims = new HashMap<String, Object>();
        claims.put("id", id);
        claims.put("level", level);
        System.out.println(secret);
        return Jwts.builder()
                .setClaims(claims)
                .setHeaderParam("type", "JWT")
                .setSubject(id)
                .setIssuedAt(nowDate)
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    // 解析JWT
    public static Claims getClaimsByToken(String jwt) {
        try {
            return Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(jwt)
                    .getBody();
        } catch (Exception e) {
            return null;
        }
    }


    // 判断JWT是否过期
    public  boolean isTokenExpired(Claims claims) {
        return claims.getExpiration().before(new Date());
    }

}
