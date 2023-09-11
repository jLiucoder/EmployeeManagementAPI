package com.jiayuliu.apiserver;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

//import static io.jsonwebtoken.security.Keys.secretKeyFor;

//@SpringBootTest
class ApiServerApplicationTests {

    @Test
    public void testGenJWT(){
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", 1);
        claims.put("name", "jiayu");
        String jwt = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256,"Jiayuliu")
                .setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() + 3600 * 1000))
                .compact();

        System.out.println(jwt);
    }

    @Test
    public void testParseJWT(){
        Claims claims =  Jwts.parser()
                .setSigningKey("Jiayuliu")
                .parseClaimsJws("eyJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoiamlheXUiLCJpZCI6MSwiZXhwIjoxNjkzOTY5NDUxfQ.U102NEvThoN6goihJctLoluLpDEOt7fxdzB5TvYEc3Q")
                .getBody();

        System.out.println(claims);
    }

}
