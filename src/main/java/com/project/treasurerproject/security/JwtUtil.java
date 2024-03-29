package com.project.treasurerproject.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.project.treasurerproject.entity.AppUser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {
    @Value("${app.smth.jwt.jwt-secret}")
    private String jwtSecret;
    @Value("${app.smth.jwt.app-name}")
    private String appName;
    @Value("${app.smth.jwt.jwt-expire}")
    private long jwtExpiration;

    public String generateToken(AppUser appUser) {
        try {

            Algorithm algorithm = Algorithm.HMAC256(jwtSecret.getBytes(StandardCharsets.UTF_8));
            return JWT.create()
                    .withIssuer(appName)
                    .withSubject(appUser.getId())
                    .withExpiresAt(Instant.now().plusSeconds(jwtExpiration))//expire token
                    .withIssuedAt(Instant.now())
                    .withClaim("role",appUser.getRole().toString())
                    .sign(algorithm);
        }catch (JWTCreationException e) {
            throw new RuntimeException();
        }
    }
    public boolean verifyWithToken (String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(jwtSecret.getBytes(StandardCharsets.UTF_8));
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT decodedJWT = verifier.verify(token);
            return decodedJWT.getIssuer().equals(appName);
        }catch (JWTVerificationException e){
            throw new RuntimeException();
        }
    }
    public Map<String,String> getUserInfoByToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(jwtSecret.getBytes(StandardCharsets.UTF_8));
            JWTVerifier jwtVerifier = JWT.require(algorithm).build();
            DecodedJWT decodedJWT = jwtVerifier.verify(token);

            Map<String,String> userInfo = new HashMap<>();
            userInfo.put("userId", decodedJWT.getSubject());
            userInfo.put("role", decodedJWT.getClaim("role").asString());
            return userInfo;
        }catch (JWTVerificationException e) {
            throw new RuntimeException();
        }

    }
}
