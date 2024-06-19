package com.example.studentmanagementsystem.util;

import io.jsonwebtoken.*;
import jakarta.servlet.http.HttpServletRequest;
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
    @Value("${jwt.tokenHeader}")
    public void setTokenHeader(String tokenHeader) {
        JwtUtils.tokenHeader = tokenHeader;
    }
    private static String tokenHeader;

    @Value("${jwt.tokenHead}")
    public void setTokenHead(String tokenHead) {
        JwtUtils.tokenHead = tokenHead;
    }
    private static String tokenHead;

    // 生成JWT
    public static String generateToken(String id,int level) {
        Date nowDate = new Date();
        Date expireDate = new Date(nowDate.getTime() + expiration);
        Map<String, Object> claims = new HashMap<String, Object>();
        claims.put("id", id);
        claims.put("level", level);
        return tokenHead+Jwts.builder()
                .setClaims(claims)
                .setHeaderParam("type", "JWT")
                .setSubject(id)
                .setIssuedAt(nowDate)
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    // 解析JWT
    public static Claims getClaims(String jwt) {
        try {
            jwt = jwt.substring(7);
            return Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(jwt)
                    .getBody();
        } catch (Exception e) {
            return null;
        }
    }

    public static Boolean isVerify(String token) {

        //签名秘钥，和生成的签名的秘钥一模一样
//        String key = user.getPassword();
        //Jwts.parser在执行parseClaimsJws(token)时如果token时间过期会抛出ExpiredJwtException异常
        try {
            //得到DefaultJwtParser
            Claims claims = getClaims(token);
            return true;
        }catch (ExpiredJwtException e){
            e.printStackTrace();
        }
        return false;
    }

    public static Claims getClaims(HttpServletRequest request){
        String token = request.getHeader(tokenHeader);
        return JwtUtils.getClaims(token);
    }

    // 判断JWT是否过期
    public static boolean isTokenExpired(Claims claims) {
        return claims.getExpiration().before(new Date());
    }

}
